import { useState, useEffect } from 'react';
import { Table, Button, Form, Input, Card, Row, Col,message, Select, DatePicker,Popover,Modal } from 'antd';
import { FileExcelOutlined, PlusOutlined, CloseOutlined,QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import * as moment from "moment";
const { Meta } = Card;
import { getTipoDerivado } from '../../services/catalogosService';
import { validaConciliacion,ejecutaConciliacion,ejecutaValidacion,getListaConciliacion } from '../../services/conciliacionService';
const stateInitialLoading = {
    state: false,
  }
  const stateInitialLoading2 = {
    state: false,
  }
  const usu = {
    usu: "",
  }

  const stateConciliacion = {};
function ContentConcilia1() {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [deribado, setDeribado] = useState([]);
    const [tipoConciliacion, settipoConciliacion] = useState([]);
    const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
    const [loadingBoton2, setLoadingBoton2] = useState(stateInitialLoading2);
    const [conciliacion, setConciliacion] = useState(stateConciliacion);
    const [disabledC, setDisabledC] = useState(true);
    const [disabledV, setDisabledV] = useState(false);
    const [disableDerivado, setDisableDeribado] = useState(true);
    const [tipoDeribado, setTipoDeribado] = useState();
    const [tipoConciliacionS, setTipoCociliacionsS] = useState();
    const [dataValidacion, SetdataValidacion] = useState([]);
    const [dataConciliacion, setDataCociliacion] = useState([]);
    const [usuario, setUsuario] = useState(usu);

    useEffect(() => {
      setUsuario({
        usu: sessionStorage.getItem('usuario'),
        letra:sessionStorage.getItem('usuario').charAt(0)
      });
    }, []);

    useEffect(() => {
      let cons=[
        {
        "id":"D",
        "nombre":"Diaria"
        },
        {
            "id":"M",
            "nombre":"Mensual"
        },]
settipoConciliacion(cons);        
    }, []);
    async function loadConciliacion(datosConciliacion) {
      const response = await getListaConciliacion(datosConciliacion)

      if (response.status === 200) {
        if (response.data.length > 0){
          const newData = response.data;
          newData.forEach(function(item){
            item.fechaOperacion = item.fechaOperacion.substring(0,10);
          })
          setDataCociliacion(newData)
        }
      }
  }
    const columns = [
        {
            title: "Fecha Operativa",
            dataIndex: "fechaOperacion",
            key: "fechaOperacion",
            align: "center"
        },
        {
            title: "Tipo de Derivado",
            dataIndex: "nombre",
            key: "nombre",
            align: "center"
        },
        {
            title: "Cuenta Contable",
            dataIndex: "cuenta",
            key: "cuenta",
            align: "center"
        },
        {
            title: "Moneda Contable",
            dataIndex: "moneda",
            key: "moneda",
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
            dataIndex: "importeOp",
            key: "importeOp",
            align: "center"
        },
        {
            title: "Diferencia",
            dataIndex: "diferencia",
            key: "diferencia",
            align: "center"
        },
        {
          title: "Tipo Conciliación",
          dataIndex: "tipoConciliacion",
          key: "tipoConciliacion",
          align: "center"
      },

    ];

    const colRep = [
      {
        title: "Archivo",
        dataIndex: "insumo",
        key: "insumo",
        align: "center",
      },
  
    ]

    const data = dataConciliacion;
const filesRep=dataValidacion;
    const onReset = () => {
        form.resetFields();
    };

    const [form] = Form.useForm();
    const submitForm = async (values) => {
        setLoadingBoton({
          state: true,
        }); 
        const request = {
            
                "derivado": values.tipoDerivado,
                "fechaOperacion":  moment(values.fechaConciliacion).format("YYYY-MM-DD"),
                "tipoConciliacion": values.tipoConciliacion,
                "tipoValidacion": 0
              }
           
              const conciliacion = {
            
                "inDerivado": values.tipoDerivado,
                "inFecha":  moment(values.fechaConciliacion).format("YYYY-MM-DD"),
                "inTipoConcilia": values.tipoConciliacion,
                "inUsuario": usuario.usu
              }
              
              setConciliacion (conciliacion)
        
        submitPost(request);
      }


      async function submitPost(request) {
        try {
          const response = await validaConciliacion(request)
          if (response.status === 200) {
            if (response.data.respuesta === true) {
                setDisabledC(false)
              message.success('Se ejecuto correctamente la validaciòn');
              setLoadingBoton({
                state: false
              });
            }else{
              const validacion = await ejecutaValidacion()
              if (validacion.status === 200) {
                  const arch=[];
                  console.log("validacion::: "+validacion);
                  validacion.data.forEach((item)=>{
                    console.log(item);
                    if (tipoDeribado!=4){
                       if(item.tipoDerivado==tipoDeribado && item.tipoConcilia==tipoConciliacionS ){
                        arch.push(item);
                     }
                    }else{
                      if(item.tipoConcilia==tipoConciliacionS ){
                        arch.push(item);
                      }
                    }
                   })
                  SetdataValidacion(arch)
                  if(arch.length>0){
                    setDisabledC(true)
                    setIsModalOpen(true);
                  }
                  setDisabledC(true)
                
                 
              } else {
                message.error(validacion.data.mensaje);
                setDisabledC(true)
                setLoadingBoton({
                  state: false,
                });
              }
            }
            setDisabledC(false)
            message.success('Se realizo la validacion');
            setLoadingBoton({
              state: false
            });
          } else {
            message.error(response.data.mensaje);
            setDisabledC(true)
            setLoadingBoton({
              state: false,
            });
          }
       
    
        } catch (error) {
            setDisabledC(true)
          message.error('Error al ejecutar al validar');
          setLoadingBoton({
            state: false,
          });
        }
      }

      async function ejecutaConcliacion(){
        setLoadingBoton2({
            state: true,
          });
          setDisabledV(true)
        console.log(conciliacion);
        try {
            const response = await ejecutaConciliacion(conciliacion)
            console.log("estatus:: "+response.status)
            if (response.status === 200) {
              if (response.data.respuesta === 'OK') {

                message.success('Se ejecuto correctamente la conciliación ');
                setLoadingBoton2({
                    state: false,
                  });
                  setDisabledV(false)
                  loadConciliacion(conciliacion)  
              }else{
                setLoadingBoton2({
                    state: false,
                  });
                  setDisabledV(false)
                  loadConciliacion(conciliacion)
                  message.error(response.data.mensaje);
              }
              
            } else {
              message.error(response.data.mensaje);
              setLoadingBoton2({
                state: false,
              });
              setDisabledV(false)
            }
          } catch (error) {
    
            message.error('Error al ejecutar la conciliación');
            setLoadingBoton2({
                state: false,
              });
          }

      }
      const handleChangeSelectDeribado = async value => {
        console.log("value",value)
        setTipoCociliacionsS(value)
        setDisableDeribado(true)
        const response = await getTipoDerivado()
        if (response.status === 200) {
          if (value=="D"){
            let de= {
                "id": 4,
                "nombre": "TODOS"
              }
           
            response.data.push(de);
            setDeribado(response.data)
            setDisableDeribado(false)
          }else{
            const result = [];
            response.data.forEach((item)=>{
             console.log(item);
                if(item.nombre=="SWAPS"){
                  result.push(item);
              }
            })
            setDeribado(result)
            setDisableDeribado(false)
          }
      }
     
      };
      const handleChangetipoDerivado =  value => {
        setTipoDeribado(value);
     
      };
    
      const handleRepCancel = () => {
        setIsModalOpen(false);
        setDisabledC(true)
        setLoadingBoton({
          state: false
        });
    
      };

      const handleRepOk = () => {
        setIsModalOpen(false);
        setDisabledC(false)
        setLoadingBoton({
          state: false
        });
    
      };
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
                    onFinish={submitForm} 
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
                    }]}>
                   
                            <DatePicker onChange={onChange} />
                     
                </Form.Item>
                <Row gutter={8}>
                <Col span={4}></Col>
                    <Col span={11}>
                <Form.Item
                    label="Tipo de Conciliación"
                    name="tipoConciliacion"
                    rules={[{
                        required: true,
                        message: "Por favor ingresa el Tipo de Conciliación"
                    }]}
                >
                    
                            <Select  onChange={handleChangeSelectDeribado} autoClearSearchValue >
                            {tipoConciliacion.map(elemento => (
                                        <Select.Option value={elemento.id}>{elemento.nombre}</Select.Option>
                                    ))}
                            </Select>
                      
                </Form.Item>
                </Col>
                <Form.Item>
                <Col span={12} align="right">
                        <Button htmlType="submit" disabled={disabledV} loading={loadingBoton.state} type="primary">Validar Conciliación</Button>
                        </Col>
                        </Form.Item>
                </Row>
                <Row gutter={8}>
                <Col span={4}></Col>
                    <Col span={11}>
                <Form.Item
                    label="Tipo de Derivado:"
                    name="tipoDerivado"
                    rules={[{
                        required: true,
                        message: "Por favor ingresa el Tipo de Derivado"
                    }]}>
                            <Select disabled={disableDerivado} onChange={handleChangetipoDerivado} >
                            {deribado.map(elemento => (
                                        <Select.Option  value={elemento.id}>{elemento.nombre}</Select.Option>
                                    ))}
                                     
                            </Select>

                </Form.Item>
                </Col>
                <Form.Item>
                <Col span={12} align="right">
                <Button type="primary" onClick={ejecutaConcliacion} loading={loadingBoton2.state} disabled={disabledC}>Ejecutar Conciliación</Button>
                        </Col>
                        </Form.Item>
                </Row>
                
                        
            </Form>
            </Card>
            <Card size="small" align="left" title="Detalle de la última Conciliación"
                headStyle={{ backgroundColor: '#39c0c4' }}
                extra={
                    <CSVLink data={data} filename={"detalleConciliacion.csv"}>
                        <FileExcelOutlined style={{ color: 'green', fontSize: 25 }} />
                    </CSVLink>}
            >
                <Table size="small" columns={columns} dataSource={data} className="table-striped-rows"></Table>
            </Card>
            <Modal open={isModalOpen} onOk={handleRepOk}  onCancel={handleRepCancel}
            okButtonProps={{
              disabled: false,
            }}
            cancelButtonProps={{
              disabled: false,
            }} >
        <p>Falta cargar lo siguiente pra continuar</p>
        <Table 
          size="small" 
          columns={colRep} 
          dataSource={filesRep} 
          className="table-striped-rows"          
          pagination={false}
        >
        </Table>
      </Modal>
        </div>
    );
}

export default ContentConcilia1;