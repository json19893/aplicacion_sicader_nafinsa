//import React from "react";
import { useState, useEffect } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Select, DatePicker,Popover, message, Modal } from 'antd';
import { FileExcelOutlined, PlusOutlined, CloseOutlined,QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import { getSocioLiquidador } from '../../../services/catalogosService'
import { getAllCartaConfirmacion, cargaCartaConfirmacion } from '../../../services/infSidecaService'
import * as moment from "moment";
const { Meta } = Card;
const stateInitialLoading = {
    state: false,
  }
  const usu = {
    usu: "",
  }
function InfSideca1() {
    const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
    const [socios, setSocios] = useState([]);
    const [dataCartaConfirma, setDataCartaConfirma] = useState([]);

    const [msjMod, setMsjMod] = useState([]);
    const [formModal, setformModal] = useState([]);
    const [usuario, setUsuario] = useState(usu);

    useEffect(() => {
      setUsuario({
        usu: sessionStorage.getItem('usuario'),
        letra:sessionStorage.getItem('usuario').charAt(0)
      });
    }, []);

    useEffect(() => {

        async function loadSocios() {
            const response = await getSocioLiquidador()

            if (response.status === 200) {
                setSocios(response.data)
            }
        }
        loadSocios()
    }, []);

    async function loadCartaConfirmacion(fecha) {
        const response = await getAllCartaConfirmacion(fecha)

        if (response.status === 200) {
            setDataCartaConfirma(response.data)
        }
    }

    useEffect(() => {
        loadCartaConfirmacion(null)
    }, []);

    const columns = [
        {
            title: "Fecha de Operacion",
            dataIndex: "fechaOperacion",
            key: "fechaOperacion",
            align: "center"
        },
        {
            title: "Nombre del Socio Liquidador",
            dataIndex: "nombre",
            key: "nombre",
            align: "center"
        },
        {
            title: "Monto Ending Balance",
            dataIndex: "endingBalance",
            key: "endingBalance",
            align: "center"
        },
        {
            title: "Monto de IVA",
            dataIndex: "ivaTax",
            key: "ivaTax",
            align: "center"
        },
    ];

    const data = dataCartaConfirma;

    const onReset = () => {
        form.resetFields();
    };

    const [form] = Form.useForm();

    const submitForm = (values) => {
        setLoadingBoton({
            state: true,
          });
        const request = {
            fechaOperacion: moment(values.fechaOperacion).format("YYYY-MM-DD"),
            idSocioLiquidador: values.socioLiquidador,
            montoBalance: values.montoEndingBalance,
            montoIva: values.montoIVA,
            forzar: false,
            usuario: usuario.usu
        }
        submitPost(request)
    };

    async function submitPost(request) {
        const response = await cargaCartaConfirmacion(request)
        if (response.status === 200) {
            if (response.data.respuesta === 'OK') {
                loadCartaConfirmacion(request.fechaOperacion)
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

    const [isModalOpen, setIsModalOpen] = useState(false);

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

    const onChange = (date, dateString) => {
        console.log(date, dateString);
        loadCartaConfirmacion(date)
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
        description="Pantalla cuyo objetivo es permitir el registro de la información de las “Cartas de confirmación (Estados de cuenta)”. Se capturan datos para cada uno de los socios liquidadores que se tenga, la fuente de los datos es un Estado de Cuenta en pdf (300016_90_FUTURE_FF_300016_080027) que se obtiene diariamente por un correo enviado por el SIDECA. Esta información es necesaria para la conciliación de Futuros y Swaps."
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
          <Button type="dashed" shape="circle" icon={<QuestionOutlined />} size="small" ></Button>
        </Popover>
      );
    
    return (
        <div>
            <Card align="left" size="small"
             title="Registro Cartas de Confirmación"
            extra={content}
            headStyle={{ backgroundColor: '#39c0c4' }}>
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
                        label="Fecha de Operación:"
                        name="fechaOperacion"
                        rules={[{
                            required: true,
                        }]}
                    >
                        <DatePicker onChange={onChange} />
                    </Form.Item>
                    <Form.Item
                        label="Nombre del Socio Liquidador:"
                        name="socioLiquidador"
                        rules={[{
                            required: true,
                        }]}
                    >
                        <Select >
                            {socios.map(elemento => (
                                <Select.Option value={elemento.id}>{elemento.nombre}</Select.Option>
                            ))}
                        </Select>
                    </Form.Item>
                    <Form.Item
                        label="Monto de Ending Balance:"
                        name="montoEndingBalance"
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input type='number' />
                            </Col>
                        </Row>
                    </Form.Item>
                    <Form.Item
                        label="Monto de IVA:"
                        name="montoIVA"
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input type='number' />
                            </Col>
                            <Col span={12} align="right">
                                <Button htmlType="submit" className="buttonAdd" type="primary" loading={loadingBoton.state} shape="circle" icon={<PlusOutlined />} size="large" />

                                &nbsp;&nbsp;&nbsp;

                                <Button type="danger" shape="circle" icon={<CloseOutlined />} size="large" onClick={onReset} />
                            </Col>
                        </Row>
                    </Form.Item>
                    <Input type='hidden' name="forzar" />

                </Form>
            </Card>

            <Card size="small" align="left" title="Resumen de Información de Cartas de Confirmación"
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

export default InfSideca1;