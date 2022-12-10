import { useState, useEffect } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Select, Icon, DatePicker, Checkbox } from 'antd';
import { FileExcelOutlined, PlusOutlined, CloseOutlined, SearchOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import moment from 'moment';
import { getTipoDerivado } from '../../services/catalogosService'
import { getEstatusConciliacion } from '../../services/conciliacionService'

function ContentConcilia2() {

    const stateInitialLoading = {
        state: false,
    }

    const [tipoDerivado, setTipoDerivado] = useState([]);
    const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
    const [dataResumenEstatusConcilia, setDataResumenEstatusConcilia] = useState([]);

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

    const onChange = (date, dateString) => {
        console.log(date, dateString);
    };

    const { RangePicker } = DatePicker;

    const dateFormat = 'YYYY/MM/DD';

    const submitForm = (values) => {
        setLoadingBoton({
            state: true,
        });
        const request = {
            fechaOperacionIni: moment(values.fechaOperativa[0]).format("YYYY-MM-DD"),
            fechaOperacionFin: moment(values.fechaOperativa[1]).format("YYYY-MM-DD"),
            fechaVencimientoIni: moment(values.fechaEjecucion[0]).format("YYYY-MM-DD"),
            fechaVencimientoFin: moment(values.fechaEjecucion[1]).format("YYYY-MM-DD"),
            usuario: values.usuario,
            tipoConciliacion: values.tipoConciliacion,
            estatus: values.estatus,
            derivado: values.tipoDerivado,
        }
        console.log(request);
        loadEstatusConciliacion(request)
    };

    return (
        <div>
            <Card size="small" align="left"
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
                        label="Fecha Operativa:"
                        name="fechaOperativa"
                        rules={[{
                            required: true,
                        }]}
                    >
                        <RangePicker />
                    </Form.Item>
                    <Form.Item
                        label="Fecha Ejecucion:"
                        name="fechaEjecucion"
                        rules={[{
                            required: true,
                        }]}
                    >
                        <RangePicker />
                    </Form.Item>

                    <Form.Item
                        label="Usuario"
                        name="usuario"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa el Usuario"
                        }]}
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input />
                            </Col>
                        </Row>
                    </Form.Item>

                    <Form.Item
                        label="Tipo de Conciliación: "
                        name="tipoConciliacion"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa el Tipo de Conciliación"
                        }]}
                    >

                        <Select
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
                    <Col span={12} align="left">
                        Ultima Conciliacion:&nbsp;
                        <Checkbox></Checkbox>
                    </Col>
                    <Form.Item
                        label="Estatus"
                        name="estatus"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa el Estatus"
                        }]}
                    >
                        <Select
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

                    <Form.Item
                        label="Tipo Derivado: "
                        name="tipoDerivado"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa el Tipo Derivado"
                        }]}
                    >
                        <Select>
                            {tipoDerivado.map(elemento => (
                                <Select.Option key={elemento.id} value={elemento.id}>{elemento.nombre}</Select.Option>
                            ))}
                        </Select>
                    </Form.Item>
                    <Col span={12} align="right">
                        <Button htmlType="submit" className="buttonSearch" type="primary" shape="circle" icon={<SearchOutlined />} size="large" />
                        &nbsp;&nbsp;&nbsp;
                        <Button type="danger" shape="circle" icon={<CloseOutlined />} size="large" onClick={onReset} />
                    </Col>
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