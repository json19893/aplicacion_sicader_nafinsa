import { Table, Button, Form, Input, Card, Row, Col, Select, Popover, message, Modal,Popconfirm,Space } from 'antd';
import 'antd/dist/antd.min.css';
import './cobertura.css';
import { FileExcelOutlined, PlusOutlined, CloseOutlined, QuestionOutlined,DeleteOutlined, EditOutlined,FormOutlined} from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import { useState, useEffect } from 'react';
import { getCobertura, cargaCobertura, getCuentasConciliar,deletCobertura,getCoberturaId } from '../../services/catalogosService'
const { Meta } = Card;

const { Item } = Form;
const { Password } = Input;
const stateInitialLoading = {
    state: false,
}
const usu = {
    usu: "",
  }
  const edit={
    id:null,
    nombre: null,
    activo: null,
    pasivo: null,
    capital: null
  }
function CatCoberturas() {
    const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
    const [dataCobertura, setDataCobertura] = useState([]);
    const [msjMod, setMsjMod] = useState([]);
    const [formModal, setformModal] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isModalOpen2, setIsModalOpen2] = useState(false);
    const [dataEdit, setdataEdit] = useState(edit);
    const [cuentas, setCuentas] = useState([]);

    useEffect(() => {

     console.log("dataEdit :: "+dataEdit)
        loadCuentasConciliar()
    }, []);
    async function loadCuentasConciliar() {
        const response = await getCuentasConciliar()

        if (response.status === 200) {
            setCuentas(response.data)
        }
    }
    var dataTemp = [];

    async function loadCobertura() {
        const response = await getCobertura()

        if (Number(response.status) === 200) {
            /*response.data.forEach(function (data, index) {
                const dataResp = {
                    cobertura: data.nombre,
                    cuentaActiva: data.cuentaActiva?.nombre,
                    cuentaPasiva: data.cuentaPasiva?.nombre,
                    cuentaCapital: data.cuentaCapital?.nombre
                }
                dataTemp.push(dataResp)
            })
            setDataCobertura(dataTemp)
            */
            setDataCobertura(response.data)
        }
    }

    useEffect(() => {
        loadCobertura()
    }, []);

    const columns = [
        {
            title: "Cobertura",
            dataIndex: "nombre",
            key: "nombre"
        },
        {
            title: "Activo",
            dataIndex: "activo",
            key: "activo",
            align: "center"
        },
        {
            title: "Pasivo",
            dataIndex: "pasivo",
            key: "pasivo",
            align: "center"
        },
        {
            title: "Capital",
            dataIndex: "capital",
            key: "capital",
            align: "center"
        }, {
            title: 'Acciones',
            dataIndex: 'Acciones',
            align: "left",
            render: (_, record) =>
                <Space>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <Popconfirm title="¿Desea eliminar el registro?" onConfirm={() => handleDelete(record.id)}>
                    <DeleteOutlined style={{ fontSize: '18px', color: '#E51E0A' }} />
                </Popconfirm>
                &nbsp;&nbsp;
                <Popconfirm title="¿Desea editar el registro?"  onConfirm={() => handleEdit(record.id)}>
                        <EditOutlined  style={{ fontSize: '18px', color: '#0A42E5' }} />
                    </Popconfirm></Space>
               
          },
    ];

    const data = dataCobertura;
    async function deltCobertura(id) {
        try {
        
            const response = await deletCobertura(id)
           
            if (response.status === 200) {
                if (response.data.respuesta === 'OK') {
                    message.success('Registro borrado');
                    loadCuentasConciliar()
                }
                else {
                    message.error("No se puede borrar el registro");
                    loadCuentasConciliar()
                }
            } else {
                message.error(response.data.mensaje);
                loadCuentasConciliar()
            }
        } catch (error) {
            message.error(error);
        }
       
    }
    const handleDelete = (key) => {
        deltCobertura(key);
      };
      const handleEdit = (key) => {
        Edit(key);
        setIsModalOpen2(true);
      };

       async function Edit(id) {
        try {
            const response = await getCoberturaId(id)
           console.log("response:  "+response)
            if (response.status === 200) {
                let edit={
                    id:response.data[0].id,
                    nombre: response.data[0].nombre,
                    activo:response.data[0].activo!=null?   parseInt (response.data[0].activo):null,
                    pasivo:response.data[0].pasivo!=null? parseInt (response.data[0].pasivo):null,
                    capital:response.data[0].capital!=null? parseInt ( response.data[0].capital):null
                  }
                 
               setdataEdit(edit);
               form2.resetFields();
             
            } else {
                message.error(response.data.mensaje);
               
            }
        } catch (error) {
            message.error(error);
        }
       
    }
    const onReset = () => {
        form.resetFields();
    };
    const [form] = Form.useForm();
    const [form2] = Form.useForm();

    const submitForm = (values) => {
        setLoadingBoton({
            state: true,
        });
        const request = {
            nombre: values.cobertura,
            cuentaActiva: parseInt (values.cuentaActiva),
            cuentaPasiva: parseInt (values.cuentaPasiva),
            cuentaCapital: parseInt (values.cuentaCapital)
        }
        submitPost(request)
    };

    async function submitPost(request) {
        console.log('request: ' + request)
        try {
            const response = await cargaCobertura(request)
            console.log('response: ' + response.status)
            if (response.status === 200) {
                if (response.data.respuesta === 'OK') {
                    loadCobertura()
                    form.resetFields();
                    message.success('Accion realizada con exito');
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
                message.error(response.data.mensaje);
                setLoadingBoton({
                    state: false,
                });
            }
        }
        catch (error) {
            message.error('Error en realizar la acción.');
            setLoadingBoton({
                state: false,
            });
        }
    }

    const handleOk = () => {
        //formModal.forzar = true;
        submitPost(formModal)
        setIsModalOpen(false);
    };

    const handleOk2 = (values) => {
        const request = {
            nombre: values.cobertura==undefined?dataEdit.nombre:values.cobertura,
            cuentaActiva: values.cuentaActiva==undefined?Number (dataEdit.activo):Number (values.cuentaActiva),
            cuentaPasiva: values.cuentaPasiva==undefined?Number (dataEdit.pasivo):Number (values.cuentaPasiva),
            cuentaCapital: values.cuentaCapital==undefined?Number (dataEdit.capital):Number (values.cuentaCapital),
            id:Number (dataEdit.id)
        }
        submitPost(request)
       
        form2.resetFields();
        setIsModalOpen2(false);
        loadCobertura()
        
    };

    const handleCancel = () => {
        setIsModalOpen(false);
        setLoadingBoton({
            state: false,
        });
    };

    const handleCancel2 = () => {
        setIsModalOpen2(false);
      
    };
  
    const [open, setOpen] = useState(false);
    const handleOpenChange = (newOpen) => {
        setOpen(newOpen);
    };
    const desc = (
        <Card
            style={{
                width: 300,
                marginTop: 16,
            }}

        >
            <Meta
                description="Esta pantalla muestra el catálogo de Coberturas que hacen referencia al reporte C-10. Las coberturas tienen relación con las cuentas de conciliación y pueden ser de naturaleza activa-pasiva o de Capital."
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
            <Card size="small" align="left" title="Catálogo de Coberturas"
                extra={content}
                headStyle={{ backgroundColor: '#39c0c4' }}
            >
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
                        label="Cobertura"
                        name="cobertura"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa la Cobertura"
                        }]}
                    >
                       
                          <Input  />
                        
                    </Form.Item>
                    <Form.Item
                        label="Cuenta Activa"
                        name="cuentaActiva"
                    >
                        <Select >
                            {cuentas.map(elemento => (
                                <Select.Option value={elemento.id}>{elemento.descripcion}</Select.Option>
                            ))}
                        </Select>
                    </Form.Item>
                    <Form.Item
                        label="Cuenta Pasiva"
                        name="cuentaPasiva"
                    >
                        <Select >
                            {cuentas.map(elemento => (
                                <Select.Option value={elemento.id}>{elemento.descripcion}</Select.Option>
                            ))}
                        </Select>
                    </Form.Item>
                    <Form.Item
                        label="Cuenta de Capital"
                        name="cuentaCapital"
                    >
                        <Select >
                            {cuentas.map(elemento => (
                                <Select.Option value={elemento.id}>{elemento.descripcion}</Select.Option>
                            ))}
                        </Select>
                    </Form.Item>
                    <div>
                        <Row gutter={8}>
                            <Col span={12} align="right">
                                <Button htmlType="submit" loading={loadingBoton.state} className="buttonAdd" type="primary" shape="circle" icon={<PlusOutlined />} size="large" />

                                &nbsp;&nbsp;&nbsp;

                                <Button type="danger" shape="circle" icon={<CloseOutlined />} size="large" onClick={onReset} />
                            </Col>
                        </Row>
                    </div>
                </Form>
            </Card>

            <Card size="small" align="left" title="Resumen Catálogo de Coberturas"
                headStyle={{ backgroundColor: '#39c0c4' }}
                extra={
                    <CSVLink data={data} filename={"catalogoCoberturas.csv"}>
                        <FileExcelOutlined style={{ color: 'green', fontSize: 25 }} />
                    </CSVLink>}
            >
                <Table size="small" columns={columns} dataSource={data} className="table-striped-rows"></Table>
            </Card>

            <Modal
                open={isModalOpen}
                onOk={handleCancel}
                closable={false}
                cancelButtonProps={{
                    style: {
                        display: "none",
                    },
                }}
            >
                <p>{msjMod}</p>
            </Modal>


            <Modal
                open={isModalOpen2}
                onOk={handleOk2}
                onCancel={handleCancel2}
                //closable={false}
               /* cancelButtonProps={{
                    style: {
                        display: "true",
                    },
                    
                }}*/
                footer={[]}
            >
                 <Form form={form2} size="small"
                    onFinish={handleOk2}
                    name="formulario2"
                    labelCol={{
                        span: 8,
                    }}
                    wrapperCol={{
                        span: 12,
                    }}
                    initialValues={{
                        remember: false,
                    }}>
                    <Form.Item
                        label="Cobertura"
                        name="cobertura"
                        rules={[{
                            required: false,
                            message: "Por favor ingresa la Cobertura"
                        }]}
                    >
                       
                          <Input   defaultValue={dataEdit.nombre} value={dataEdit.nombre}/>
                        
                    </Form.Item>
                    <Form.Item
                        label="Cuenta Activa"
                        name="cuentaActiva"
                    >
                        <Select  defaultValue={dataEdit.activo} value={dataEdit.activo} >
                        <Select.Option value=""></Select.Option>
                            {cuentas.map(elemento => (
                                
                                <Select.Option  value={elemento.id} >{elemento.descripcion}</Select.Option>
                            ))}
                            
                        </Select>
                    </Form.Item>
                    <Form.Item
                        label="Cuenta Pasiva"
                        name="cuentaPasiva"
                    >                        
                        <Select defaultValue={dataEdit.pasivo} value={dataEdit.pasivo}  >
                        <Select.Option value=""></Select.Option>
                            {cuentas.map(elemento => (
                                <Select.Option value={elemento.id}>{elemento.descripcion}</Select.Option>
                            ))}
                        </Select>
                    </Form.Item>
                    <Form.Item
                        label="Cuenta de Capital"
                        name="cuentaCapital"
                    >                       
                         <Select defaultValue={dataEdit.capital} value={dataEdit.capital}  >
                         <Select.Option value=""></Select.Option>
                            {cuentas.map(elemento => (
                                <Select.Option value={elemento.id}>{elemento.descripcion}</Select.Option>
                            ))}
                        </Select>
                    </Form.Item>
                    <div>
                        <Row gutter={8}>
                            <Col span={12} align="right">
                                <Button htmlType="submit" loading={loadingBoton.state} className="buttonAdd" type="primary" shape="circle" icon={<FormOutlined />} size="large" />

                               
                            </Col>
                        </Row>
                    </div>
                </Form>
            </Modal>

      

           
        </div>
    );
}

export default CatCoberturas;