import { useState, useEffect } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Select, DatePicker,Popover } from 'antd';
import { FileExcelOutlined, PlusOutlined, CloseOutlined,QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
const { Meta } = Card;
import { getTipoDerivado } from '../../services/catalogosService';
function ContentConcilia1() {
    const [deribado, setDeribado] = useState([]);
    useEffect(() => {

        async function getDerivado() {
            const response = await getTipoDerivado()

            if (response.status === 200) {
                setDeribado(response.data)
            }
        }
        getDerivado()
    }, []);

    const columns = [
        {
            title: "Fecha de Vencimiento",
            dataIndex: "fechaVencimiento",
            key: "fechaVencimiento",
            align: "center"
        },
        {
            title: "Tipo de Derivado",
            dataIndex: "tipoDerivado",
            key: "valorUdi",
            align: "center"
        },
        {
            title: "Cuenta Contable",
            dataIndex: "cuentaContable",
            key: "cuentaContable",
            align: "center"
        },
        {
            title: "Moneda Contable",
            dataIndex: "monedaContable",
            key: "monedaContable",
            align: "center"
        },
        {
            title: "Ente",
            dataIndex: "ente",
            key: "ente",
            align: "center"
        },
        {
            title: "Importe SIF",
            dataIndex: "importeSif",
            key: "importeSif",
            align: "center"
        },
        {
            title: "Importe Operativo",
            dataIndex: "importeOperativo",
            key: "importeOperativo",
            align: "center"
        },
        {
            title: "Diferencia",
            dataIndex: "diferencia",
            key: "diferencia",
            align: "center"
        },
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
        description="En esta pantalla se realiza el proceso de conciliación, se elige la fecha a conciliar y previó a realizar la conciliación se validan que los insumos se encuentren cargados y/o capturados para realizar la conciliación solicitada. La conciliación puede ser diaria o mensual y es posible elegir un derivado a conciliar o su totalidad. "
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
          <Card size="small" align="left" title="Conciliación Contable"
                extra={content}
                headStyle={{ backgroundColor: '#39c0c4' }}
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
                    label="Fecha de Conciliacion"
                    name="fechaConciliacion"
                    rules={[{
                        required: true,
                    }]}
                >
                    <Row gutter={8}>
                        <Col span={12}>
                            <DatePicker onChange={onChange} />
                        </Col>
                    </Row>
                </Form.Item>
                <Form.Item
                    label="Tipo de Conciliación"
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
                        <Col span={12} align="right">
                        <Button type="primary">Validar Conciliación</Button>
                        </Col>
                    </Row>
                </Form.Item>
                <Form.Item
                    label="Tipo de Derivado:"
                    name="tipoDerivado"
                    rules={[{
                        required: true,
                        message: "Por favor ingresa el Tipo de Derivado"
                    }]}
                >
                    <Row gutter={8}>
                        <Col span={12}>
                            <Select >
                            {deribado.map(elemento => (
                                        <Select.Option key={elemento.id} value={elemento.id}>{elemento.nombre}</Select.Option>
                                    ))}
                            </Select>
                        </Col>
                        <Col span={12} align="right">
                            <Button type="primary" disabled>Ejecutar Conciliación</Button>
                        </Col>
                    </Row>

                </Form.Item>

            </Form>
            </Card>
            <Card size="small" align="left" title="Detalle de la última Conciliación"
                headStyle={{ backgroundColor: '#39c0c4' }}
                extra={
                    <CSVLink data={data} filename={"catalogoCoberturas.csv"}>
                        <FileExcelOutlined style={{ color: 'green', fontSize: 25 }} />
                    </CSVLink>}
            >
                <Table size="small" columns={columns} dataSource={data} className="table-striped-rows"></Table>
            </Card>

        </div>
    );
}

export default ContentConcilia1;