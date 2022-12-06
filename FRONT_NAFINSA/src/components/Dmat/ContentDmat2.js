import { useEffect, useState } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Select,Popover,message, DatePicker, Upload,notification,Modal } from 'antd';
import { FileExcelOutlined, PlusOutlined, DownloadOutlined, UploadOutlined,QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
const { Dragger } = Upload;
import * as moment from "moment";
//import * as fs from 'fs';
const { Meta } = Card;
const stateInitialLoading = {
  state: false,
}
const fec = {
  fecha: "",
}
import { getArchivoMensual, cargaArchivoMensual } from '../../../src/services/dtmaServices'

function ContentDmat2() {
  const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
  const [fileList, setFileList] = useState([]);
  const [uploading, setUploading] = useState(false);
  const [dataArchivos, setDataArchivos] = useState([]);
  const [msjMod, setMsjMod] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [formModal, setformModal] = useState([]);
  const [fechaReporte, setFechaReporte] = useState(fec);
    const [form] = Form.useForm();
    useEffect(() => {
      loadArchivoFecha(null)
    }, []);
  const data = dataArchivos;
  const columns = [
    {
      title: "Nombre del Socio Liquidador",
      dataIndex: "nombre",
      key: "nombre",
      align: "center"
    },
    {
      title: "Fecha de Operación",
      dataIndex: "fechaOperacion",
      key: "fechaOperacion",
      align: "center"
    },
    {
      title: "Recibir SL",
      dataIndex: "recibirSl",
      key: "recibirSl",
      align: "center"
    },
    {
      title: "Entregar SL",
      dataIndex: "entregarSl",
      key: "entregarSl",
      align: "center"
    },
    {
      title: "Recibir SIDECA",
      dataIndex: "recibirSideca",
      key: "recibirSideca",
      align: "center"
    },
    {
      title: "Entregar SIDECA",
      dataIndex: "entregarSideca",
      key: "entregarSideca",
      align: "center"
    },
  ];

 
  const openNotification = (mensaje,tipo) => {
   const placement='bottom';
   if(tipo==1){
   return notification.success({
      description:
      `${mensaje}`,
      placement,
    });
   }
   if(tipo==2){
    return notification.error({
       description:
       `${mensaje}`,
       placement,
     });
    }

    
  };
  const onReset = () => {
    form.resetFields();
  };



  const onChange = (date, dateString) => {
   console.log(dateString);
    loadArchivoFecha(dateString);
    setFechaReporte({
      fecha: dateString,
    });
  };




  const props = {
    onRemove: (file) => {
      const index = fileList.indexOf(file);
      const newFileList = fileList.slice();
      newFileList.splice(index, 1);
      setFileList(newFileList);
      setUploading(false);
      openNotification('Archivo borrado correctamente',1)
    },
    
    beforeUpload: (file) => {
      console.log(fechaReporte.fecha)
      let f=fechaReporte.fecha.split("-")
      let a=f[0];
      let m=f[1];
      let mes=m=='01'?"ENERO ":m=='02'?"FEBRERO ":m=='03'?"MARZO ":m=='04'?"ABRIL ":m=='05'?"MAYO ":m=='06'?"JUNIO ":m=='07'?"JULIO ":m=='08'?"AGOSTO ":m=='09'?"SEPTIEMBRE ":m=='10'?"OCTUBRE ":m=='11'?"NOVIEMBRE ":"DICIEMBRE "
      let fecha=mes+a;
      console.log (fecha)
      if(!uploading){
        let cadena=file.name.split('.');
        if(cadena[1]!='xlsx'){
          return openNotification('Favor de validar el nombre del archivo ingresado', 2);
        }
        

        let val=   cadena[0]+'.'+cadena[1] == 'VALUACIONES SL VS SIDECA '+fecha+'.xlsx'?0:1
        if (val==1){
          return openNotification('Favor de validar el nombre del archivo ingresado', 2);
        }
        setFileList([...fileList, file]);
      setUploading(true);
      openNotification('Archivo cargado correctamente',1)
      }else{
        openNotification('Debe borrar el archivo para cargar otro' ,2)
      }
      return false;
    },
    fileList,
  };


 
  const handleInputConfirm = async (values) => {
    try {
      
 
    setLoadingBoton({
      state: true,
    });

  if (fileList[0]==undefined){
    setLoadingBoton({
      state: false,
    });
   return openNotification('Por favor seleccione un Archivo', 2)
  }
    const request = {
      fechaOperacion: values.fechaOperacion,
      file: fileList[0],
      forzar: false,
      usuario: 'jsalgado'
    }
    submitPost(request)
  } catch (error) {
    message.error('Error en la creación del registro.'+ error);
    setLoadingBoton({
      state: false,
    });
  }
  }

  async function submitPost(request) {
    try {
      
  
    const response = await cargaArchivoMensual(request)
    if (response.status === 200) {
      if (response.data.respuesta === 'OK') {
        loadArchivoFecha(request.fechaOperacion)
        onReset();
        setFileList([]);
        setUploading(false);
        message.success('Archivo cargado correctamente.');
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
  } catch (error) {
    message.error('Error en la creación del registro.');
    setLoadingBoton({
      state: false,
    });
  }
  }
  async function loadArchivoFecha(fechaOperacion) {
    try {
      
    
    
    const response = await getArchivoMensual(fechaOperacion)

    if (response.status === 200) {
      setDataArchivos(response.data)
    }
  } catch (error) {
    message.error('Error al mostrar la información');
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
    const desc=(
      <Card
      style={{
        width: 300,
        marginTop: 16,
      }}
     
    >
      <Meta
        description="Pantalla donde se carga de forma mensual del archivo de cifras comparativas generadas por el SIDECA contra la valuación confirmada de los Socios Liquidadores. El nombre del archivo que se tiene que cargar es VALUACIONES SL VS SIDECA[mes] y el formato es en xls. Esta información se obtiene de un correo de “Información del Cierre” enviado por la Dirección de Administración de Mercados y Tesorería (DMAT). La información cargada se utiliza para la conciliación de Swaps."
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
             title="Archivos mensuales"
            extra={content}
            headStyle={{ backgroundColor: '#39c0c4' }}>
        <Form form={form} name="formCargaArchivo"  onFinish={handleInputConfirm} size="small"
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
            label="Fecha de Operacion"
            name="fechaOperacion"
            rules={[{
              required: false,
              message: 'Por favor ingresar Fecha de Operacion',

          }]}
          >
            
                <DatePicker onChange={onChange} />
            
          </Form.Item>
          <Form.Item
            label="Archivo:"
            name="archivoMensual"
            rules={[{
              required: true,
              message: "Por favor de cargar un archivo "
            }]}
          >
            <Row gutter={8}>
              <Col span={14}>
                
              <Dragger {...props}>
                  <p className="ant-upload-drag-icon">
                    <UploadOutlined />
                  </p>
                  <p className="ant-upload-hint">Haga clic o arrastre el archivo a esta área para cargarlo</p>
                </Dragger>
			
			
		
              </Col>
              <Col span={8} align="center">
                <Button className="buttonAdd" type="primary"  htmlType="submit" loading={loadingBoton.state} shape="circle" icon={<PlusOutlined />} size="large" />
              </Col>
            </Row>

          </Form.Item>

        </Form>
      </Card>

      <Card size="small" align="left" title="Detalle del archivo"
        headStyle={{ backgroundColor: '#39c0c4' }}
        extra={
          <CSVLink data={data} filename={"archivoMensual.csv"}>
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

export default ContentDmat2;