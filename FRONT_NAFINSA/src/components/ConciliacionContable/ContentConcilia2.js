import { useState, useEffect } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Select, Icon, DatePicker,Popover, Checkbox } from 'antd';
import { FileExcelOutlined, PlusOutlined, CloseOutlined, SearchOutlined,QuestionOutlined, DownloadOutlined } from '@ant-design/icons';
import { CSVLink  } from 'react-csv';
import moment from 'moment';
const { Meta } = Card;
import { getTipoDerivado } from '../../services/catalogosService'
import { getEstatusConciliacion, getConciliacionDetalle } from '../../services/conciliacionService'

const usu = {
    usu: "",
  }
function ContentConcilia2() {

    const stateInitialLoading = {
        state: false,
    }

    const [tipoDerivado, setTipoDerivado] = useState([]);
    const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
    const [dataResumenEstatusConcilia, setDataResumenEstatusConcilia] = useState([]);
    const [checkUltimaConcilia, setCheckUltimaConcilia] = useState(false);
    const [required, setRequired] = useState(true);

    const [detalleConcilia, setDetalleConcilia] = useState([]);
    const download = (data) => {
        const header = ",id,fecha,tipoconciliacion,tipoderivadoid,oficina,moneda,cuenta,subcuenta1,subcuenta2,subcuenta3,subcuenta4,subcuenta5,subcuenta6,subcuenta7,tipoente,ente,importesif,importeop,formulacalculoid,ejecucionid";
        const contenido= header+data.map( archivo => "\n"+archivo.id+","+archivo.fecha+","+archivo.tipoconciliacion+","+archivo.tipoderivadoid+","+archivo.oficina+","+archivo.moneda+","+archivo.cuenta+","+archivo.subcuenta1+","+archivo.subcuenta2+","+archivo.subcuenta3+","+archivo.subcuenta4+","+archivo.subcuenta5+","+archivo.subcuenta6+","+archivo.subcuenta7+","+archivo.tipoente+","+archivo.ente+","+archivo.importesif+","+archivo.importeop+","+archivo.formulacalculoid+","+archivo.ejecucionid);
        const csvContent = `data:text/csv;charset=utf-8${contenido}`;
        const encodedUri = encodeURI(csvContent);
        var link = document.createElement("a");
        link.setAttribute("href", encodedUri);
        link.setAttribute("download", "detalleConciliacion.csv");
        document.body.appendChild(link);
        return link.click();
    };

    useEffect(() => {

        async function loadTipoDerivado() {
            const response = await getTipoDerivado()

            if (response.status === 200) {
                /*let de= {
                    "id": 4,
                    "nombre": "TODOS"
                  }                
                response.data.push(de);*/
                setTipoDerivado(response.data)
            }
        }
        loadTipoDerivado()
    }, []);

    async function loadEstatusConciliacion(request) {
        const response = await getEstatusConciliacion(request)
        console.log(response)
        if (response.status === 200) {
            setDataResumenEstatusConcilia(response.data)
            setLoadingBoton({
                state: false,
            });
        }
        else {
            message.error(response.data.mensaje);
            setLoadingBoton({
                state: false,
            });
        }
    }

    //useEffect(() => {
    //    loadEstatusConciliacion(null)
    //}, []);

    async function loadDetalleConciliacion(id) {
        try {
            //setDetalleConcilia([])
            const response = await getConciliacionDetalle(id)
    
          if (response.status === 200) {
            console.log(response.data)
              download(response.data);
                //setDetalleConcilia(response.data);
                //console.log(detalleConcilia)
          }
        } catch (error) {    
          console.log(error)
        }
      }
    

    const columns = [
        {
            title: "Fecha Operativa",
            dataIndex: "fechaOperacion",
            key: "fechaOperacion",
            align: "center"
        },
        {
            title: "Fecha Ejecución",
            dataIndex: "fechaVencimiento",
            key: "fechaVencimiento",
            align: "center"
        },
        {
            title: "Usuario",
            dataIndex: "usuario",
            key: "usuario",
            align: "center"
        },
        {
            title: "Tipo Conciliación",
            dataIndex: "tipoConciliacion",
            key: "tipoConciliacion",
            align: "center"
        },
        {
            title: "Tipo Derivado",
            dataIndex: "tipoDerivado",
            key: "tipoDerivado",
            align: "center"
        },
        {
            title: "Estatus",
            dataIndex: "estatus",
            key: "estatus",
            align: "center"
        },
        {
            title: 'Descargar archivo',
            dataIndex: '',
            key: 'x',
            align: "center",
            render: (reg) => 
            <Button type="primary" shape='circle'  size="small" onClick={e=>loadDetalleConciliacion(reg.id)}>
                    <FileExcelOutlined />
            </Button>
        }        
    ];

    const data = [
    ];

    const onReset = () => {
        form.resetFields();
    };
    const [form] = Form.useForm();

    const onChange = (e) => {
        let valueCkeck = e.target.checked;
        console.log(`checked = ${valueCkeck}`);
        setCheckUltimaConcilia(valueCkeck)
        setRequired(valueCkeck==true?false:true)
    };

    const { RangePicker } = DatePicker;

    const dateFormat = 'YYYY/MM/DD';

    const submitForm = (values) => {
        console.log("form")
        setLoadingBoton({
            state: true,
        });
        let request;
        if(checkUltimaConcilia){
            request = {
                ultimaConciliacion: checkUltimaConcilia
            }
        }else{
            request = {
                fechaOperacionIni:values.fechaOperativa[0]==null?null: moment(values.fechaOperativa[0]).format("YYYY-MM-DD"),
                fechaOperacionFin:values.fechaOperativa[1]==null?null: moment(values.fechaOperativa[1]).format("YYYY-MM-DD"),
                fechaVencimientoIni:values.fechaEjecucion!=undefined?moment(values.fechaEjecucion[0]).format("YYYY-MM-DD"):null,
                fechaVencimientoFin:values.fechaEjecucion!=undefined? moment(values.fechaEjecucion[1]).format("YYYY-MM-DD"):null,
                usuario: values.usuario!=undefined?values.usuario:null,
                tipoConciliacion: values.tipoConciliacion!=undefined?values.tipoConciliacion:null,
                estatus: values.estatus!=undefined?values.estatus:null,
                derivado: values.tipoDerivado!=undefined?values.tipoDerivado:null,
                ultimaConciliacion: checkUltimaConcilia
            }
        }
        loadEstatusConciliacion(request)
    };
    const [open, setOpen] = useState(false);
    const handleOpenChange = (newOpen) => {
        setOpen(newOpen);
      };
    const desc=(
        <Card
        style={{
          width: 300,
          marginTop: 16,
        }}
       
      >
        <Meta
          description="Pantalla cuyo objetivo es mostrar el estatus de las conciliaciones ejecutadas en un rango de fechas ya sea para la conciliación Diaria o Mensual. Y también se puede obtener el detalle de las mismas."
        />
      </Card>
      )
    const content = (
        <Popover
           content={desc}
           trigger="click"
           placement="leftTop"
           open={open}
           onOpenChange={handleOpenChange}
         >
           <Button type="ghost" shape="circle" icon={<QuestionOutlined />} size="small" ></Button>
         </Popover>
       );

    return (
        <div>
             <Card size="small" align="left" title="Estatus de la Conciliación Contable"
                extra={content}
                headStyle={{ backgroundColor: '#39c0c4' }}
            >
            <br></br>
                <Form form={form} size="small"
                    onFinish={submitForm}
                    name="formulario"
                    labelCol={{
                        span: 8,
                    }}
                    wrapperCol={{
                        span: 12,
                    }}
                    initialValues={{
                        remember: true,
                    }}>
                    <Form.Item
                        label="Fecha Operativa:"
                        name="fechaOperativa"
                        rules={[{
                            required: required,
                        }]}
                    >
                        <RangePicker disabled={checkUltimaConcilia} />
                    </Form.Item>
                    <Form.Item
                        label="Fecha Ejecucion:"
                        name="fechaEjecucion"
                        rules={[{
                            required: false,
                        }]}
                    >
                        <RangePicker disabled={checkUltimaConcilia} />
                    </Form.Item>
                    <Row>
                        <Col span={12} align="center">
                            <Form.Item
                                label="Usuario"
                                name="usuario"
                                rules={[{
                                    required: false,
                                    message: "Por favor ingresa el Usuario"
                                }]}
                            >
                                <Input disabled={checkUltimaConcilia} />
                            </Form.Item>
                        </Col>
                        <Col span={12} align="left">
                            <Form.Item
                                label="Tipo de Conciliación: "
                                name="tipoConciliacion"
                                rules={[{
                                    required: false,
                                    message: "Por favor ingresa el Tipo de Conciliación"
                                }]}
                                wrapperCol={{
                                    span: 10,
                                }}
                            >
                                <Select disabled={checkUltimaConcilia}
                                    options={[
                                        {
                                            value: 'D',
                                            label: 'Diaria'
                                        },
                                        {
                                            value: 'M',
                                            label: 'Mensual'
                                        },
                                    ]}
                                />
                            </Form.Item>
                        </Col>
                    </Row>

                    <Row>
                        <Col span={12} align="left">
                            <Form.Item
                                label="Estatus"
                                name="estatus"
                                rules={[{
                                    required: false,
                                    message: "Por favor ingresa el Estatus"
                                }]}
                                wrapperCol={{
                                    span: 10,
                                }}
                            >
                                <Select disabled={checkUltimaConcilia}
                                    options={[
                                        {
                                            value: 'E',
                                            label: 'Exitosa'
                                        },
                                        {
                                            value: 'D',
                                            label: 'Con Diferencias'
                                        },
                                        {
                                            value: 'X',
                                            label: 'Con Errores'
                                        },
                                    ]}
                                />
                            </Form.Item>
                        </Col>
                        <Col span={12} align="left">
                            <Form.Item
                                label="Tipo Derivado: "
                                name="tipoDerivado"
                                rules={[{
                                    required: false,
                                    message: "Por favor ingresa el Tipo Derivado"
                                }]}
                                wrapperCol={{
                                    span: 10,
                                }}
                            >
                                <Select disabled={checkUltimaConcilia}>
                                    {tipoDerivado.map(elemento => (
                                        <Select.Option key={elemento.id} value={elemento.id}>{elemento.nombre}</Select.Option>
                                    ))}
                                </Select>
                            </Form.Item>
                        </Col>
                    </Row>

                    <Row>
                        <Col span={12} align="rigth">
                            <Form.Item
                                label="Ultima Conciliacion: "
                                name="ultimaConciliacion"
                                wrapperCol={{
                                    span: 10,
                                }}
                            >
                                <Checkbox onChange={onChange}></Checkbox>
                            </Form.Item>
                        </Col>

                        <Col span={4} align="center">
                            <Button htmlType="submit" className="buttonSearch" type="primary" shape="circle" icon={<SearchOutlined />} size="large" />
                            &nbsp;&nbsp;&nbsp;
                            <Button type="danger" shape="circle" icon={<CloseOutlined />} size="large" onClick={onReset} />
                        </Col>
                    </Row>

                </Form>
            </Card>
            <Card size="small" align="left" title="Resumen de Conciliación"
                headStyle={{ backgroundColor: '#39c0c4' }}
                /*extra={
                    <CSVLink data={dataResumenEstatusConcilia} filename={"resumenConciliacion.csv"}>
                        <FileExcelOutlined style={{ color: 'green', fontSize: 25 }} />
                    </CSVLink>}*/
            >
                <Table size="small" columns={columns} dataSource={dataResumenEstatusConcilia} className="table-striped-rows"></Table>
            </Card>
        </div>
    );
}

export default ContentConcilia2;