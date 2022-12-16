import { useState, useEffect } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Select, DatePicker,Popover, message, Modal } from 'antd';
import { FileExcelOutlined, PlusOutlined, CloseOutlined,QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import { getSocioLiquidador } from '../../../services/catalogosService'
import { getAllCuentaAsigna, cargaCuentaAsigna } from '../../../services/infSidecaService'
import * as moment from "moment";
const { Meta } = Card;
const stateInitialLoading = {
    state: false,
  }
  const usu = {
    usu: "",
  }
function InfSideca2() {
    const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
    const [socios, setSocios] = useState([]);
    const [dataCuentaAsigna, setDataCuentaAsigna] = useState([]);

    const [msjMod, setMsjMod] = useState([]);
    const [formModal, setformModal] = useState([]);

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [usuario, setUsuario] = useState(usu);

    useEffect(() => {
      setUsuario({
        usu: sessionStorage.getItem('usuario'),
        letra:sessionStorage.getItem('usuario').charAt(0)
      });
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
            title: "IVA comisión por manejo de cuenta",
            dataIndex: "ivaManejoCuenta",
            key: "ivaManejoCuenta",
            align: "center"
        },
        {
            title: "IVA comisión por operación",
            dataIndex: "ivaComisionOperacion",
            key: "ivaComisionOperacion",
            align: "center"
        },
        {
            title: "Balance Final",
            dataIndex: "balanceFinal",
            key: "balanceFinal",
            align: "center"
        },
    ];

    useEffect(() => {

        async function loadSocios() {
            const response = await getSocioLiquidador()

            if (response.status === 200) {
                setSocios(response.data)
            }
        }
        loadSocios()
    }, []);

    async function loadCuentaAsigna(fecha) {
        const response = await getAllCuentaAsigna(fecha)

        if (response.status === 200) {
            setDataCuentaAsigna(response.data)
        }
    }

    useEffect(() => {
        loadCuentaAsigna(null)
    }, []);

    const data = dataCuentaAsigna;

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
            balanceFinal: values.balanceFinal,
            ivaCuenta: values.ivaCuenta,
            ivaOperacion: values.ivaOperacion,
            forzar: false,
            usuario: usuario.usu
        }
        submitPost(request)
    };

    async function submitPost(request) {
        const response = await cargaCuentaAsigna(request)
        if (response.status === 200) {
            if (response.data.respuesta === 'OK') {
                loadCuentaAsigna(request.fechaOperacion)
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

    const handleOk = () => {
        formModal.forzar = true;
        /*const request = {
            fechaOperacion: formModal.fechaOperacion,
            idSocioLiquidador: formModal.idSocioLiquidador,
            montoBalance: formModal.montoBalance,
            montoIva: formModal.montoIva,
            forzar: true,
            usuario: 'Jose'
        }*/
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
        loadCuentaAsigna(date)
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
        description="En esta pantalla se capturan los datos de los Estados de Cuenta Asigna por socio liquidador que son necesarios para el proceso de conciliación contable, este estado de cuenta se obtiene diariamente directamente del SIDECA. Los datos que se capturan en esta pantalla son necesarios para la conciliación de Futuros y Swaps."
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
             title="Registro Cuenta ASIGNA"
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
                        label="IVA comisiones por manejo de cuenta:"
                        name="ivaCuenta"
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input type='number'/>
                            </Col>
                        </Row>
                    </Form.Item>
                    <Form.Item
                        label="IVA comisiones por operación:"
                        name="ivaOperacion"
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input type='number'/>
                            </Col>
                        </Row>
                    </Form.Item>
                    <Form.Item
                        label="Balance Final:"
                        name="balanceFinal"
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input type='number'/>
                            </Col>
                            <Col span={12} align="right">
                                <Button htmlType="submit" className="buttonAdd" type="primary" loading={loadingBoton.state} shape="circle" icon={<PlusOutlined />} size="large" />

                                &nbsp;&nbsp;&nbsp;

                                <Button type="danger" shape="circle" icon={<CloseOutlined />} size="large" onClick={onReset} />
                            </Col>
                        </Row>
                    </Form.Item>
                </Form>
            </Card>

            <Card size="small" align="left" title="Resumen de la carga de Estados de Cuenta ASIGNA"
                headStyle={{ backgroundColor: '#39c0c4' }}
                extra={
                    <CSVLink data={data} filename={"catalogoCoberturas.csv"}>
                        <FileExcelOutlined style={{ color: 'green', fontSize: 25 }} />
                    </CSVLink>}
            >
                <Table size="small" columns={columns} dataSource={dataCuentaAsigna} className="table-striped-rows"></Table>
            </Card>

            <Modal open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
                <p>{msjMod}</p>
            </Modal>

        </div>
    );
}

export default InfSideca2;