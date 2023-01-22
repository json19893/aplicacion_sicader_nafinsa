import { useState, useEffect } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, DatePicker, message, Popover, Modal } from 'antd';
import { FileExcelOutlined, PlusOutlined, CloseOutlined, QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import { cargaGanancia, getGananciaPerdidaUDI } from '../../services/gananciaPerdidaService'
import * as moment from "moment";
const { Meta } = Card;
const stateInitialLoading = {
    state: false,
}
const usu = {
    usu: "",
  }
function GananciaPerdida() {
    const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
    const [msjMod, setMsjMod] = useState([]);
    const [formModal, setformModal] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [dataGananciaPerdida, setDataGananciaPerdida] = useState([]);
    const [usuario, setUsuario] = useState(usu);

    useEffect(() => {
      setUsuario({
        usu: sessionStorage.getItem('usuario'),
        letra:sessionStorage.getItem('usuario').charAt(0)
      });
    }, []);
    
    const columns = [
        {
            title: "Fecha de Vencimiento",
            dataIndex: "fechaVencimiento",
            key: "fechaVencimiento",
            align: "center"
        },
        {
            title: "Valor de UDI",
            dataIndex: "valorUdi",
            key: "valorUdi",
            align: "center"
        },
        {
            title: "Pérdida Inflacionaria",
            dataIndex: "perdida",
            key: "perdida",
            align: "center"
        },
    ];

    async function loadGananciaPerdidaUDI(fecha) {
        try {


            const response = await getGananciaPerdidaUDI(fecha)

            if (response.status === 200) {
                setDataGananciaPerdida(response.data)
            } else {
                message.error(response.data.mensaje);
                setLoadingBoton({
                    state: false,
                });
            }
        } catch (error) {
            message.error('Error en al mostrar la información');
            setLoadingBoton({
                state: false,
            });
        }
    }

    useEffect(() => {
        loadGananciaPerdidaUDI(null)
    }, []);

    const data = dataGananciaPerdida;

    const onReset = () => {
        form.resetFields();
    };

    const [form] = Form.useForm();

    const onChange = (date) => {
        loadGananciaPerdidaUDI(date)
    };

    const submitForm = (values) => {
        try {


            setLoadingBoton({
                state: true,
            });
            const request = {
                fechaVencimiento: values.fechaVencimiento,
                forzar: false,
                perdida: values.perdidaInflacionaria,
                usuario: usuario.usu,
                valorUid: values.valorUdi
            }
            submitPost(request)
        } catch (error) {
            message.error('Error en la creación del registro.');
            setLoadingBoton({
                state: false,
            });
        }
    };

    async function submitPost(request) {
        try {

            const response = await cargaGanancia(request)
            console.log("response:::", response.data.respuesta);
            if (response.status === 200) {
                if (response.data.respuesta === 'OK') {
                    loadGananciaPerdidaUDI(request.fechaOperacion)
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
        } catch (error) {
            message.error('Error en la creación del registro.');
            setLoadingBoton({
                state: false,
            });
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
                description="En esta pantalla se captura el detalle del comportamiento que tienen los nocionales pasivos de los SWAPS y que se tienen pactados en UDIS y que es determinado por el valor de la UDI la cual también se captura en esta pantalla. Esta información se obtiene de un correo enviado por el área de Riesgos de Mercado. Los datos obtenidos de esta captura son necesarios para realizar la conciliación de Swaps."
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
            <Card size="small" align="left" title="Ganancia/Pérdida  Inflacionaria en UDI-TIIE"
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
                        label="Fecha de Vencimiento"
                        name="fechaVencimiento"
                        rules={[{
                            required: true,
                        }]}
                    >
                        <DatePicker onChange={onChange} />
                    </Form.Item>
                    <Form.Item
                        label="Valor de UDI"
                        name="valorUdi"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa el Valor UDI"
                        }]}
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input />
                            </Col>
                        </Row>
                    </Form.Item>
                    <Form.Item
                        label="Pérdida Inflacionaria:"
                        name="perdidaInflacionaria"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa la Pérdida Inflacionaria"
                        }]}
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input />
                            </Col>
                            <Col span={12} align="right">
                                <Button htmlType="submit" className="buttonAdd" type="primary" shape="circle" loading={loadingBoton.state} icon={<PlusOutlined />} size="large" />

                                &nbsp;&nbsp;&nbsp;

                                <Button type="danger" shape="circle" icon={<CloseOutlined />} size="large" onClick={onReset} />
                            </Col>
                        </Row>

                    </Form.Item>

                </Form>
            </Card>

            <Card size="small" align="left" title="Detalle UDI-TIIE"
                headStyle={{ backgroundColor: '#39c0c4' }}
                extra={
                    <CSVLink data={data} filename={"detalle_UDI_TIIE.csv"}>
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

export default GananciaPerdida;