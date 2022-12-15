import { useEffect, useState } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Select, DatePicker,Popover, message, Modal } from 'antd';
import { FileExcelOutlined, PlusOutlined, CloseOutlined,QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import { getCobertura } from '../../services/catalogosService';
import { getAllC10, cargaC10 } from '../../../src/services/dtmaServices'
import * as moment from "moment";
const { Meta } = Card;
const stateInitialLoading = {
    state: false,
  }
function ContentDmat4() {
    const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
    const [cobertura, setCobertura] = useState([]);
    const [tipoCuenta, setTipoCuenta] = useState([]);
    const [disabledA, setDisabledA] = useState(true);
    const [disabledP, setDisabledP] = useState(true);
    const [disabledC, setDisabledC] = useState(true);
    const [dataC10, setDataC10] = useState([]);
    const [msjMod, setMsjMod] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [formModal, setformModal] = useState([]);
    const data = dataC10;
    const [form] = Form.useForm();
    useEffect(() => {

        async function loadCobertura() {
            const response = await getCobertura()

            if (response.status === 200) {
                setCobertura(response.data.sort(function(a,b){
                    return b.id-a.id
                }))
            }
        }
        loadCobertura()
    }, []);


    const columns = [
        {
            title: "Cobertura",
            dataIndex: "nombre",
            key: "nombre",
            align: "center"
        },
        {
            title: "Fecha",
            dataIndex: "fechaOperacion",
            key: "fechaOperacion",
            align: "center"
        },
        {
            title: "Importe cuenta Pasiva",
            dataIndex: "pasivo",
            key: "pasivo",
            align: "center"
        },
        {
            title: "Importe cuenta Activa",
            dataIndex: "activo",
            key: "activo",
            align: "center"
        },
        {
            title: "Importe cuenta Capital",
            dataIndex: "capital",
            key: "capital",
            align: "center"
        },
    ];

    const handleInputConfirm = async (values) => {
        setLoadingBoton({
            state: true,
          });
        const arr = values.cobertura.split(",");
        let fecha=  moment(values.fecha).format("YYYY-MM-DD")
        console.log("fecha:: ",fecha)
        const request=
        
        {
            "coberturaId": parseInt(arr[0]),
            "forzar": false,
            "mesAnio": fecha,
            "sicaderC10SubList": [
                {
                  "importe": values.activa==undefined?null: parseFloat(values.activa),
                  "tipoCuentaId": 1
                },
                {
                    "importe": values.pasiva==undefined?null: parseFloat(values.pasiva),
                    "tipoCuentaId": 2
                },
                {
                    "importe": values.capital==undefined?null: parseFloat(values.capital),
                    "tipoCuentaId": 3
                },

              ],
        
          
            "usuario": "jsalgado"
          }
        
       
              
              submitPost(request)
    }
    async function submitPost(request) {
        const response = await cargaC10(request)
        if (response.status === 200) {
            if (response.data.respuesta === 'OK') {
                loadC10(request.mesAnio)
                form.resetFields();
                message.success('Registro creado correctamente.');
                setLoadingBoton({
                    state: false,
                  });
            }
            else {
                setMsjMod(response.data.respuesta);
                setformModal(request);
                setIsModalOpen(true);
            }
        } else {
            message.error('Error en la creación del registro.');
            setLoadingBoton({
                state: false,
              });
        }
    }
    async function loadC10(fecha) {
        const response = await getAllC10(fecha)

        if (response.status === 200) {
            setDataC10(response.data)
        }
    }
    const handleOk = () => {
        formModal.forzar = true;
        submitPost(formModal)
        setIsModalOpen(false);
    };
    const handleCancel = () => {
        setIsModalOpen(false);
        setLoadingBoton({
            state: false,
        });
    };
    const onReset = () => {
        form.resetFields();
    };

    const onChange = (date, dateString) => {
        console.log(date, dateString);
        loadC10(date);
    };

    const handleChangeSelectCobertura = value => {
        console.log("value",value)
        const arr = value.split(",");
        arr[1]!='null'?setDisabledA(false):setDisabledA(true);
        arr[2]!='null'?setDisabledP(false):setDisabledP(true);
        arr[3]!='null'?setDisabledC(false):setDisabledC(true); 
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
          description="En esta pantalla se captura la información correspondiente a las cifras de ajuste a la valuación de la posición cubierta por cobertura. Esta información viene en el reporte C-10 que se enviado de manera mensual por la Dirección de Administración de Mercados y Tesorería (DMAT).  En la pantalla se muestran los importes a capturar dependiendo de la naturaleza de la cobertura. Toda la información capturada se utiliza para la conciliación de Swaps."
        />
      </Card>
      );
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
            <Card align="left" size="small"
             title="Resumen C-10"
            extra={content}
            headStyle={{ backgroundColor: '#39c0c4' }}>
                <Form form={form} size="small"
                    name="formulario"
                    onFinish={handleInputConfirm} 
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
                        label="Fecha"
                        name="fecha"
                        rules={[{
                            required: true,
                        }]}
                    >
                                <DatePicker onChange={onChange} />  
                    </Form.Item>
                    <Form.Item
                        label="Cobertura:"
                        name="cobertura"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa la Contraparte"
                        }]}
                    >
                       
                                <Select
                                onChange={handleChangeSelectCobertura}>
                                    {cobertura.map(elemento => (
                                        <Select.Option key={elemento.nombre} value={elemento.id+","+elemento.activo+","+elemento.pasivo+","+elemento.capital}>{elemento.nombre}</Select.Option>
                                    ))}
                                </Select>
                        
                    </Form.Item>
                    <Form.Item
                        label="Importe Cuenta Activa:"
                        name="activa"
                        rules={[{
                            required: false,
                            message: "Por favor ingresa el Importe"
                        }]}
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input type='number'  disabled={disabledA}/>
                            </Col>
                        </Row>
                    </Form.Item>
                    <Form.Item
                        label="Importe Cuenta Pasiva"
                        name="pasiva"
                        rules={[{
                            required: false,
                            message: "Por favor ingresa el Importe"
                        }]}
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input type='number' disabled={disabledP} />
                            </Col>
                        </Row>
                    </Form.Item>
                    <Form.Item
                        label="Importe Cuenta Capital:"
                        name="capital"
                        rules={[{
                            required: false,
                            message: "Por favor ingresa el Importe"
                        }]}
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input type='number' disabled={disabledC}/>
                            </Col>
                            <Col span={12} align="right">
                                <Button className="buttonAdd" loading={loadingBoton.state}  type="primary" shape="circle" icon={<PlusOutlined />} size="large" htmlType="submit" />

                                &nbsp;&nbsp;&nbsp;

                                <Button type="danger" shape="circle" icon={<CloseOutlined />} size="large" onClick={onReset} />
                            </Col>
                        </Row>

                    </Form.Item>

                </Form>
            </Card>

            <Card size="small" align="left" title="Detalle C-10"
                headStyle={{ backgroundColor: '#39c0c4' }}
                extra={
                    <CSVLink data={data} filename={"catalogoCoberturas.csv"}>
                        <FileExcelOutlined style={{ color: 'green', fontSize: 25 }} />
                    </CSVLink>}
            >
                <Table size="small" columns={columns} dataSource={data} className="table-striped-rows"></Table>
            </Card>
            <Modal open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
                <p>{msjMod}</p>
            </Modal>
        </div>
    );
}

export default ContentDmat4;