import { useState, useEffect } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Select, Icon, DatePicker, Checkbox } from 'antd';
import { FileExcelOutlined, PlusOutlined, CloseOutlined, SearchOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import moment from 'moment';
import { getTipoDerivado } from '../../services/catalogosService'

function ContentConcilia2() {

    const [tipoDerivado, setTipoDerivado] = useState([]);

    useEffect(() => {

        async function loadTipoDerivado() {
            const response = await getTipoDerivado()

            if (response.status === 200) {
                let de= {
                    "id": 4,
                    "nombre": "TODOS"
                  }
               
                response.data.push(de);
                setTipoDerivado(response.data)
            }
        }
        loadTipoDerivado()
    }, []);

    const columns = [
        {
            title: "Fecha Operativa",
            dataIndex: "fechaOperativa",
            key: "fechaOperativa",
            align: "center"
        },
        {
            title: "Fecha Ejecución",
            dataIndex: "fechaEjecucion",
            key: "fechaEjecucion",
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

    return (
        <div>
            <Card size="small" align="left"
            >

                <Form form={form} size="small"
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
                        <RangePicker
                            defaultValue={[moment('2015/01/01', dateFormat), moment('2015/01/01', dateFormat)]}
                            format={dateFormat}
                        />
                    </Form.Item>
                    <Form.Item
                        label="Fecha Ejecucion:"
                        name="fechaEjecucion"
                        rules={[{
                            required: true,
                        }]}
                    >
                        <RangePicker
                            defaultValue={[moment('2015/01/01', dateFormat), moment('2015/01/01', dateFormat)]}
                            format={dateFormat}
                        />
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
                                <Select />
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
                        <Row gutter={8}>
                            <Col span={12}>
                                <Select />
                            </Col>
                            <Col span={12} align="left">
                                Ultima Conciliacion:&nbsp;
                                <Checkbox></Checkbox>
                            </Col>
                        </Row>
                    </Form.Item>

                    <Form.Item
                        label="Estatus"
                        name="estatus"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa el Estatus"
                        }]}
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Select />
                            </Col>
                        </Row>
                    </Form.Item>
                    <Form.Item
                        label="Tipo Derivado: "
                        name="estatus"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa el Tipo Derivado"
                        }]}
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Select>
                                    {tipoDerivado.map(elemento => (
                                        <Select.Option key={elemento.id} value={elemento.id}>{elemento.nombre}</Select.Option>
                                    ))}
                                </Select>
                            </Col>
                            <Col span={12} align="right">
                                <Button className="buttonSearch" type="primary" shape="circle" icon={<SearchOutlined />} size="large" />
                            </Col>
                        </Row>
                    </Form.Item>
                </Form>
            </Card>


            <Card size="small" align="left" title="Resumen de Conciliación"
                headStyle={{ backgroundColor: '#39c0c4' }}
                extra={
                    <CSVLink data={data} filename={"resumenConciliacion.csv"}>
                        <FileExcelOutlined style={{ color: 'green', fontSize: 25 }} />
                    </CSVLink>}
            >
                <Table size="small" columns={columns} dataSource={data} className="table-striped-rows"></Table>
            </Card>

        </div>
    );
}

export default ContentConcilia2;