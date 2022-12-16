import { useEffect, useState } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Select, DatePicker,Popover, message, Modal } from 'antd';
import { FileExcelOutlined, PlusOutlined, CloseOutlined ,QuestionOutlined} from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import * as moment from "moment";
import { getAllSicaderInteresMargen, cargaCuentaMargen } from '../../../src/services/dtmaServices'
import { getContraparte } from '../../services/catalogosService'
const { Meta } = Card;
const stateInitialLoading = {
    state: false,
  }
function ContentDmat3() {
    const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
    const [dataCuentaMargen, setDataCuentaMargen] = useState([]);
    const [msjMod, setMsjMod] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [formModal, setformModal] = useState([]);
    const [contraparte, setContraparte] = useState([]);
    const data = dataCuentaMargen;
    const [form] = Form.useForm();
    const [usuario, setUsuario] = useState(usu);

    useEffect(() => {
      setUsuario({
        usu: sessionStorage.getItem('usuario'),
        letra:sessionStorage.getItem('usuario').charAt(0)
      });
    }, []);

    useEffect(() => {

        async function loadContraparte() {
            const response = await getContraparte()

            if (response.status === 200) {
                setContraparte(response.data)
            }
        }
        loadContraparte()
    }, []);

    const columns = [
        {
            title: "Fecha de Operacion",
            dataIndex: "fechaOperacion",
            key: "fechaOperacion",
            align: "center"
        },
        {
            title: "Contraparte",
            dataIndex: "contraparte",
            key: "contraparte",
            align: "center"
        },
        {
            title: "Ingresos",
            dataIndex: "ingresos",
            key: "ingresos",
            align: "center"
        },
        {
            title: "Egresos",
            dataIndex: "egresos",
            key: "egresos",
            align: "center"
        },
    ];


    const handleInputConfirm = async (values) => {
        setLoadingBoton({
            state: true,
          });
        const request=
        {
            "contraparteId":parseInt(values.contraparte),
            "egresos":values.egresos==undefined?0: parseFloat(values.egresos),
            "fechaOperacion":  moment(values.fechaOperacion).format("YYYY-MM-DD"),
            "forzar": false,
            "ingresos":values.ingresos==undefined?0: parseFloat(values.ingresos),
            "usuario": usuario.usu
          }

    
              
              submitPost(request)
    }
    async function submitPost(request) {
        const response = await cargaCuentaMargen(request)
        if (response.status === 200) {
            if (response.data.respuesta === 'OK') {
                loadCuentaMargen(request.fechaOperacion)
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
    async function loadCuentaMargen(fecha) {
        const response = await getAllSicaderInteresMargen(fecha)

        if (response.status === 200) {
            setDataCuentaMargen(response.data)
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
        loadCuentaMargen(date);
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
        description="Esta pantalla nos sirve para capturar la información de los intereses generados al margen que se obtienen de un correo de “Información de Cierre” enviado por la Dirección de Administración de Mercados y Tesorería (DMAT) de forma mensual. Se espera que se capture la información por cada una de las contrapartes que vienen en la tabla del correo mencionado. Estos datos son necesarios para realizar la conciliación de Swaps."
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
             title="Registro de Interes Generados al Margen"
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
                        label="Fecha de Operación:"
                        name="fechaOperacion"
                        rules={[{
                            required: true,
                        }]}
                    >
                      
                                <DatePicker onChange={onChange} />
                         
                    </Form.Item>
                    <Form.Item
                        label="Contraparte:"
                        name="contraparte"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa la Contraparte"
                        }]}
                    >
                       
                                <Select>
                                    {contraparte.map(elemento => (
                                        <Select.Option key={elemento.id} value={elemento.id}>{elemento.nombre}</Select.Option>
                                    ))}
                                </Select>
                        
                    </Form.Item>
                    <Form.Item
                        label="Ingresos:"
                        name="ingresos"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa los Ingresos"
                        }]}
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input type='number' />
                            </Col>
                        </Row>
                    </Form.Item>
                    <Form.Item
                        label="Egresos:"
                        name="egresos"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa los Egresos"
                        }]}
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input  type='number'/>
                            </Col>
                            <Col span={12} align="right">
                                <Button className="buttonAdd" type="primary" loading={loadingBoton.state}  shape="circle" icon={<PlusOutlined />} size="large" htmlType="submit" />

                                &nbsp;&nbsp;&nbsp;

                                <Button type="danger" shape="circle" icon={<CloseOutlined />} size="large" onClick={onReset} />
                            </Col>
                        </Row>
                    </Form.Item>
                </Form>
            </Card>

            <Card size="small" align="left" title="Resumen de Intereses generados al Margen"
                headStyle={{ backgroundColor: '#39c0c4' }}
                extra={
                    <CSVLink data={data} filename={"cuentaMargen.csv"}>
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

export default ContentDmat3;