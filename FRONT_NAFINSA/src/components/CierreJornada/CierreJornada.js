import { useState, useEffect } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Select, DatePicker, Popover, message } from 'antd';
import { FileExcelOutlined, PlusOutlined, CloseOutlined, SearchOutlined, QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import { getMoneda } from '../../services/catalogosService'
import { getResumenDivisa } from '../../services/cierreJornadaService'
import * as moment from "moment";
const { Meta } = Card;
const stateInitialLoading = {
    state: false,
}
function CierreJornada() {
    const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
    const [divisas, setDivisas] = useState([]);
    const [dataResumenDivisa, setDataResumenDivisa] = useState([]);

    const columns = [
        {
            title: "Fecha",
            dataIndex: "parFecha",
            key: "parFecha",
            align: "center"
        },
        {
            title: "Clave de Moneda",
            dataIndex: "monClave",
            key: "monClave",
            align: "center"
        },
        {
            title: "Nombre de Moneda",
            dataIndex: "monNombre",
            key: "monNombre",
            align: "center"
        },
        {
            title: "Tipo de Cambio",
            dataIndex: "nemonClave",
            key: "nemonClave",
            align: "center"
        }
    ];

    useEffect(() => {

        async function loadMoneda() {
            const response = await getMoneda()

            if (response.status === 200) {
                setDivisas(response.data)
            }
        }
        loadMoneda()
    }, []);

    async function loadResumenDivisa(request) {
        const response = await getResumenDivisa(request)

        if (response.status === 200) {
            setDataResumenDivisa(response.data)
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
        loadResumenDivisa(null)
    }, []);

    const data = dataResumenDivisa;

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
            fechaIni: moment(values.fecha[0]).format("YYYY-MM-DD"),
            fechaFin: moment(values.fecha[1]).format("YYYY-MM-DD"),
            monClave: values.divisa,
        }
        loadResumenDivisa(request)
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
                description="Pantalla donde se puede consultar los tipos de cambio de Cierre de Jornada determinados por el Banco de México, esta información se obtiene directamente del Sistema de Información Financiera (SIF) y es importante para los procesos de Conciliación contable."
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
            <Button type="ghost" shape="circle" loading={loadingBoton.state} icon={<QuestionOutlined />} size="small" ></Button>
        </Popover>
    );
    return (
        <div>
            <Card size="small" align="left" title="Cierre de Jornada antes FIX"
                extra={content} headStyle={{ backgroundColor: '#39c0c4' }}
            >
                <Form form={form} size="small"
                    onFinish={submitForm}
                    name="formulario"
                    labelCol={{
                        span: 6,
                    }}
                    wrapperCol={{
                        span: 10,
                    }}
                    initialValues={{
                        remember: true,
                    }}>
                    <Form.Item
                        label="Fecha: "
                        name="fecha"
                        rules={[{
                            required: true,
                        }]}
                    >
                        <RangePicker
                        //defaultValue={[moment('2015/01/01', dateFormat), moment('2015/01/01', dateFormat)]}
                        //format={dateFormat}
                        />
                    </Form.Item>
                    <Form.Item
                        label="Divisa: "
                        name="divisa"
                    >
                        <Select >
                            {divisas.map(elemento => (
                                <Select.Option value={elemento.monClave}>{elemento.monNombre}</Select.Option>
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

            <Card size="small" align="left" title="Resumen de Divisas / Cierre de Jornada"
                headStyle={{ backgroundColor: '#39c0c4' }}
                extra={
                    <CSVLink data={data} filename={"cierreJornada.csv"}>
                        <FileExcelOutlined style={{ color: 'green', fontSize: 25 }} />
                    </CSVLink>}
            >
                <Table size="small" columns={columns} dataSource={data} className="table-striped-rows"></Table>
            </Card>

        </div>
    );
}

export default CierreJornada;