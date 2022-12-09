import { Table, Button, Form, Input, Card, Row, Col, Select, Popover, message, Modal } from 'antd';
import 'antd/dist/antd.min.css';
import './cobertura.css';
import { FileExcelOutlined, PlusOutlined, CloseOutlined, QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import { useState, useEffect } from 'react';
import { getCobertura, cargaCobertura, getCuentasConciliar } from '../../services/catalogosService'
const { Meta } = Card;

const { Item } = Form;
const { Password } = Input;
const stateInitialLoading = {
    state: false,
}
function CatCoberturas() {
    const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
    const [dataCobertura, setDataCobertura] = useState([]);
    const [msjMod, setMsjMod] = useState([]);
    const [formModal, setformModal] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [cuentas, setCuentas] = useState([]);

    useEffect(() => {

        async function loadCuentasConciliar() {
            const response = await getCuentasConciliar()

            if (response.status === 200) {
                setCuentas(response.data)
            }
        }
        loadCuentasConciliar()
    }, []);

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
        }
    ];

    const data = dataCobertura;

    const onReset = () => {
        form.resetFields();
    };
    const [form] = Form.useForm();

    const submitForm = (values) => {
        setLoadingBoton({
            state: true,
        });
        const request = {
            nombre: values.cobertura,
            cuentaActiva: values.cuentaActiva,
            cuentaPasiva: values.cuentaPasiva,
            cuentaCapital: values.cuentaCapital
        }
        submitPost(request)
    };

    async function submitPost(request) {
        try {
            const response = await cargaCobertura(request)
            console.log('response: ' + response.status)
            if (response.status === 200) {
                if (response.data.respuesta === 'OK') {
                    loadCobertura()
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
                message.error(response.data.mensaje);
                setLoadingBoton({
                    state: false,
                });
            }
        }
        catch (error) {
            message.error('Error en la creación del registro.');
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

    const handleCancel = () => {
        setIsModalOpen(false);
        setLoadingBoton({
            state: false,
        });
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
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input />
                            </Col>
                        </Row>
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
        </div>
    );
}

export default CatCoberturas;