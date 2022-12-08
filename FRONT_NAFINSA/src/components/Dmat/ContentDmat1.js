import { useEffect, useState } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Select, DatePicker,Popover, message, Modal } from 'antd';
import { FileExcelOutlined, PlusOutlined, CloseOutlined,QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import * as moment from "moment";
import { getAllSicaderBanxico, cargaValuacionBanxico } from '../../../src/services/dtmaServices'
const { Meta } = Card;
const stateInitialLoading = {
    state: false,
  }
function ContentDmat1() {
    const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
    const [dataComplemento, setDataComplemento] = useState([]);
    const [msjMod, setMsjMod] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [formModal, setformModal] = useState([]);
   
    const columns = [
        {
            title: "Fecha de Operacion",
            dataIndex: "fechaOperacion",
            key: "fechaOperacion",
            align: "center"
        },
        {
            title: "Valuación BANXICO",
            dataIndex: "valBanxico",
            key: "valBanxico",
            align: "center"
        },
    ];

    const data = dataComplemento;

    const onReset = () => {
        form.resetFields();
    };

    const [form] = Form.useForm();

    const handleInputConfirm = async (values) => {
        
        setLoadingBoton({
            state: true,
          });
        const request=
            {
                "fechaOperacion": moment(values.fechaOperacion).format("YYYY-MM-DD"),
                "forzar": false,
                "usuario": "jsalgado",
                "valuacionBanxico": parseFloat(values.valuacion)
              }

              console.log('request::: '+request)
              submitPost(request)
    }

    async function submitPost(request) {
        const response = await cargaValuacionBanxico(request)
        if (response.status === 200) {
            if (response.data.respuesta === 'OK') {
              loadComplemetoBanxico(request.fechaOperacion)
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
    async function loadComplemetoBanxico(fecha) {
        const response = await getAllSicaderBanxico(fecha)

        if (response.status === 200) {
            setDataComplemento(response.data)
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

    const onChange = (date, dateString) => {
        console.log(date, dateString);
        loadComplemetoBanxico(date);
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
        description="En esta pantalla se captura la Valuación complemento de Banxico que se obtiene de la diferencia de la valuación de las subastas de cobertura cambiaria de Banxico y la Plus minusvalía de las subastas de Banxico. Esta información se obtiene de un correo enviado diariamente por la Dirección de Administración de Mercados y Tesorería (DMAT). La información que se captura se utiliza para realizar la conciliación de Forwards."
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
            <Card size="small" align="left" title="Complemento BANXICO"
                extra={content}
                headStyle={{ backgroundColor: '#39c0c4' }}
            >
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
                            message: 'Por favor ingresar Fecha de Operacion',

                        }]}
                    >
                    
                                <DatePicker onChange={onChange}/>
                       
                    </Form.Item>
                    <Form.Item
                        label="Valuación BANXICO:"
                        name="valuacion"
                        rules={[{
                            required: true,
                            message: "Por favor ingresa el la Valuación BANXICO"
                        }]}
                    >
                        <Row gutter={8}>
                            <Col span={12}>
                                <Input type='number'  />
                            </Col>
                            <Col span={12} align="right">
                                <Button className="buttonAdd" type="primary"   loading={loadingBoton.state} shape="circle" icon={<PlusOutlined />}  htmlType="submit" size="large" />

                                &nbsp;&nbsp;&nbsp;

                                <Button type="danger" shape="circle" icon={<CloseOutlined />} size="large" onClick={onReset} />
                            </Col>
                        </Row>
                    </Form.Item>
                </Form>
            </Card>

            <Card size="small" align="left" title="Resumen de Valuación Complemento BANXICO"
                headStyle={{ backgroundColor: '#39c0c4' }}
                extra={
                    <CSVLink data={data} filename={"valuacionBANXICO.csv"}>
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

export default ContentDmat1;