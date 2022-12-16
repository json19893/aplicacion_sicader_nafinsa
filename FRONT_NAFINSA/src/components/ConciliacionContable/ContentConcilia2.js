import { useState, useEffect } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Select, Icon, DatePicker,Popover, Checkbox } from 'antd';
import { FileExcelOutlined, PlusOutlined, CloseOutlined, SearchOutlined,QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import moment from 'moment';
const { Meta } = Card;
import { getTipoDerivado } from '../../services/catalogosService'
import { getEstatusConciliacion } from '../../services/conciliacionService'
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

        if (response.status === 200) {
            setDataResumenEstatusConcilia(response.data)
            setLoadingBoton({
                state: false,
            });
        } else {
            message.error(response.data.mensaje);
            setLoadingBoton({
                state: false,
            });
        }
    }

    useEffect(() => {
        loadEstatusConciliacion(null)
    }, []);

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
                fechaOperacionIni: moment(values.fechaOperativa[0]).format("YYYY-MM-DD"),
                fechaOperacionFin: moment(values.fechaOperativa[1]).format("YYYY-MM-DD"),
                fechaVencimientoIni: moment(values.fechaEjecucion[0]).format("YYYY-MM-DD"),
                fechaVencimientoFin: moment(values.fechaEjecucion[1]).format("YYYY-MM-DD"),
                usuario: values.usuario,
                tipoConciliacion: values.tipoConciliacion,
                estatus: values.estatus,
                derivado: values.tipoDerivado,
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
                            required: required,
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
                                    required: required,
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
                                    required: required,
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
                                    required: required,
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
                                    required: required,
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
                extra={
                    <CSVLink data={dataResumenEstatusConcilia} filename={"resumenConciliacion.csv"}>
                        <FileExcelOutlined style={{ color: 'green', fontSize: 25 }} />
                    </CSVLink>}
            >
                <Table size="small" columns={columns} dataSource={dataResumenEstatusConcilia} className="table-striped-rows"></Table>
            </Card>
        </div>
    );
}

export default ContentConcilia2;