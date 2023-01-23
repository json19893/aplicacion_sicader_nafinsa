import React from "react";
import { useState, useEffect } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Modal, DatePicker, Popover, message, Upload, notification, Typography, Spin } from 'antd';
import { FileExcelOutlined, PlusOutlined, DownloadOutlined, UploadOutlined, QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import { cargarArchivo, cargarArchivo06IRDT, getArchivoFecha, getArchivoDetalle } from '../../services/cargaArchivoService'
import { getColumnasDetalleArchivo } from './columnasArchivoDetalle'
import * as moment from "moment";
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';

const { Meta } = Card;
const { Title } = Typography;
const stateInitialLoading = {
  state: false,
}
const usu = {
  usu: "",
}
function CargaArchivo() {
  const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
  const [fileUpload, setFileUpload] = useState([]);
  const [open, setOpen] = useState(false);
  const [msjMod, setMsjMod] = useState([]);
  const [formModal, setformModal] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isModalDetailOpen, setIsModalDetailOpen] = useState(false);
  const [isModalRepOpen, setIsModalRepOpen] = useState(false);
  const [dataArchivos, setDataArchivos] = useState([]);
  const [dataDetalleArchivo, setDataDetalleArchivo] = useState([]);
  const [columnsDetalle, setColumnsDetalle] = useState([]);
  const [uploading, setUploading] = useState(false);
  const [nombreReporte, setNombreReporte] = useState([]);
  const [nombreArchivo,setNombreArchivo] = useState([]);
  const [filesRep, setFilesRep] = useState([]);
  const [filesSelectedRep, setFilesSelectedRep] = useState([]);
  const [sheetNames, SetSheetNames] = useState([]);
  const [sheetData, setSheetData] = useState({});

  const [usuario, setUsuario] = useState(usu);
  const [spinLoading, setSpinLoading] = useState(false);
  const [botonModalProc, setBotonModalProc] = useState(false);

  useEffect(() => {
    setUsuario({
      usu: sessionStorage.getItem('usuario'),
      letra: sessionStorage.getItem('usuario').charAt(0)
    });
  }, []);

  const filesReprocess = [];

  const handleInputChange = (event) => {
    setFileUpload(event.target.files[0])

  }

  const colRep = [
    {
      title: "Archivo",
      dataIndex: "fileName",
      key: "fileName",
      align: "center",
    },

  ]

  const columns = [
    {
      title: "Reporte",
      dataIndex: "tipoReporte",
      key: "tipoReporte",
      align: "center"
    },
    {
      title: "Nombre del Reporte",
      dataIndex: "nombreReporte",
      key: "nombreReporte",
      align: "center"
    },
    {
      title: "Fecha de Operación",
      dataIndex: "fechaOperacion",
      key: "fechaOperacion",
      align: "center"
    },
    {
      title: "Estatus de Carga",
      dataIndex: "estatusCarga",
      key: "estatusCarga",
      align: "center"
    },
    {
      title: 'Descargar archivo',
      dataIndex: '',
      key: 'x',
      align: "center",
      render: (reg) =>
        <div hidden={reg.estatusCarga != 'Carga exitosa'}>
          <Button type="primary" shape="circle" icon={<DownloadOutlined />} size="small" onClick={() => showModal(reg)} />
        </div>,
    }
  ];

  async function loadArchivoFecha(fechaOperacion) {
    try {
      const response = await getArchivoFecha(fechaOperacion)

      if (response.status === 200) {
        setDataArchivos(response.data)
      }
    } catch (error) {

      //setLoadingBoton({
      //  state: false
      //});
      console.log(error)
    }
  }

  useEffect(() => {
    loadArchivoFecha(null)
  }, []);

  const data = dataArchivos;

  async function loadArchivoDetalle(request) {
    try {
      const response = await getArchivoDetalle(request)

      if (response.status === 200) {
        if (response.data != null) {
          response.data.forEach(element => {
            if(request.tipoReporte == 'REPORTE40'){
              element.valueDate = element.valueDate.substring(0,10);
            }
            delete element.id;
            delete element.reporteId;            
            //console.log('elemento: '+JSON.stringify(element))
          })
          setDataDetalleArchivo(response.data)
        }
      }
    } catch (error) {

      setLoadingBoton({
        state: false
      });
    }
  }

  //useEffect(() => {
  //  loadArchivoDetalle(null)
  //}, []);

  const dataDetalle = dataDetalleArchivo;

  function loadColumnasArchivoDetalle(request) {
    try {
      const response = getColumnasDetalleArchivo(request)
      setColumnsDetalle(response)
      console.log(response)
    } catch (error) {
      message.error('Error al obtener las columnas del archivo');
      setLoadingBoton({
        state: false,
      });
    }
  }

  const onReset = () => {
    form.resetFields();
  };

  const [form] = Form.useForm();

  const { Dragger } = Upload;

  const props = {
    multiple: true,
    onRemove: (file) => {
      const index = fileUpload.indexOf(file);
      const newFileList = fileUpload.slice();
      newFileList.splice(index, 1);
      setFileUpload(newFileList);
      setUploading(false);
      openNotification('Archivo borrado correctamente', 1)
    },

    beforeUpload: (file) => {
      let cadena = file.name.split('.');
      console.log(cadena[1])
      if (cadena[1] != 'TXT' && cadena[1] != 'xlsx') {
        return openNotification('Favor de validar el nombre del archivo ingresado', 2);
      }
      /*let val = cadena[0] + '.' + cadena[1] == '01_REP_FUTUROS_POSICION.TXT' ? 0 :
        cadena[0] + '.' + cadena[1] == '03_FX_FORWARD_POSITIONS.TXT' ? 0 :
          cadena[0] + '.' + cadena[1] == '06_SWAP_RESUMEN_POSICION.TXT' ? 0 :
            cadena[0] + '.' + cadena[1] == '40_JOURNAL_ENTRIES_DETAIL.TXT' ? 0 :
              cadena[0] + '.' + cadena[1] == '42_GARANTIAS_CONTRAPARTE.TXT' ? 0 :
                cadena[0] + '.' + cadena[1] == 'IRDT.xlsx' ? 0 : 1
      if (val == 1) {
        return openNotification('Favor de validar el nombre del archivo ingresado', 2);
      }*/
      setFileUpload(fileUpload => [...fileUpload, file]);
      setUploading(true);
      //openNotification('Archivo seleccionado correctamente', 1)
      return false;
    },
    fileUpload,
  };

  const openNotification = (mensaje, tipo) => {
    const placement = 'bottom';
    if (tipo == 1) {
      return notification.success({
        description:
          `${mensaje}`,
        placement,
      });
    }
    if (tipo == 2) {
      return notification.error({
        description:
          `${mensaje}`,
        placement,
      });
    }
  };

  const submitForm = async (values) => {
    setLoadingBoton({
      state: true,
    });
    console.log('Archivos a procesar: ' + fileUpload)
    if (fileUpload == undefined || fileUpload.length === 0) {
      setLoadingBoton({
        state: false,
      });
      return openNotification('Por favor seleccione un Archivo', 2)
    }
    console.log('total archivos a procesar: ' + fileUpload.length)

    for (const fileProcess of fileUpload){
      try {
     
        const request = {
          fechaOperacion: values.fechaOperacion,
          file: fileProcess,
          forzar: false,
          usuario: usuario.usu
        }
        await submitPost(request)
    

      } catch (error) {
        message.error('Error en la creación del registro.');
        setLoadingBoton({
          state: false,
        });
      }
    }

    console.log(filesReprocess.length)

    if (filesReprocess.length > 0) {
      var dataTemp = [];
      var idTemp = 0;
      
      filesReprocess.forEach(function (file) {
        let idDataResp = ++idTemp;
        const dataResp = {
          key: idDataResp,
          id: idDataResp,
          fileName: file.name,
          file: file,
          fechaOperacion: values.fechaOperacion
        }
        dataTemp.push(dataResp)
      })

      setFilesRep(dataTemp)
      setIsModalRepOpen(true);
    }
    setLoadingBoton({
      state: false
    });
  };

  const handleRepCancel = () => {
    setIsModalRepOpen(false);
    setLoadingBoton({
      state: false
    });

  };

  const handleRepOk = async () => {
    setSpinLoading(true);
    setBotonModalProc(true);
    console.log(filesSelectedRep);

    for (const fileProcess of filesSelectedRep){
      const request = {
        fechaOperacion: fileProcess.fechaOperacion,
        file: fileProcess.file,
        forzar: true,
        usuario: usuario.usu
      }
      await submitPost(request)
    };
    
    setIsModalRepOpen(false);
    setLoadingBoton({
      state: false
    });
    setSpinLoading(false);
    setBotonModalProc(false);
  }

  async function submitPost(request) {
    
      setNombreArchivo(request.file.name)
      try {
        const extFile = request.file.name.split('.').pop();
        let response=null;
        if(extFile == 'xlsx'){
          console.log('entra xlsx')
          const data = await request.file.arrayBuffer();
          const mySheetData = readDataFromExcel(data);
          console.log(mySheetData)
          const requestIrdt = {
            fechaOperacion:moment(request.fechaOperacion).format("YYYY-MM-DD"), 
            archivoMensualJsDtoList: mySheetData,
            forzar: request.forzar==null?false: request.forzar,
            usuario: usuario.usu,
            nombreArchivo: request.file.name,
          }
          console.log("requestIrdt::: "+requestIrdt)
          response = await cargarArchivo06IRDT(requestIrdt)
          console.log("response cargarArchivo06IRDT::: "+response)
        }else{
          response = await cargarArchivo(request)
        }
      if (response.status === 200) {
        console.log("response.data.respuesta");
        if (response.data.respuesta === 'OK') {
          await loadArchivoFecha(request.fechaOperacion)
          onReset();
          setFileUpload([]);
          setUploading(false);
          message.success('Archivo cargado correctamente.');
          //setLoadingBoton({
          //  state: false
          //});
        }
        else {
          console.log("request.file :::"+request.file);
          filesReprocess.push(request.file);
        }
      } else {
        message.error(response.response.data.mensaje+': '+request.file.name);
        await loadArchivoFecha(request.fechaOperacion)
        setLoadingBoton({
          state: false,
        });
      }
    } catch (error) {
      console.log('error: '+error);
      message.error('Error en la creación del registrosss.');
      setLoadingBoton({
        state: false,
      });
    }
  }

  

    const readDataFromExcel = (data) => {
      const wb = XLSX.read(data,{cellDates:true});
  
      SetSheetNames(wb.SheetNames);
  
      var mySheetData = [];
  
      for(var i=0; i<wb.SheetNames.length; i++){
        let sheetName = wb.SheetNames[i];
  
        const workSheet = wb.Sheets[sheetName];
        //const jsonData = XLSX.utils.sheet_to_json(workSheet)
        let sheet = XLSX.utils.sheet_to_json(workSheet, { header: 1,raw:true,dateNF:'yyyy-mm-dd'} );
        let dat={
          socio:sheetName,
          data:sheet
        }
        mySheetData.push(dat);
  
        console.log("sww:: "+sheetName);
        
      }
     return mySheetData ;
    };

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

  const onChange = (date) => {
    loadArchivoFecha(date)
  };

  const showModal = (param) => {
    loadColumnasArchivoDetalle(param.tipoReporte)
    const request = {
      id: param.id,
      tipoReporte: param.tipoReporte
    }
    setNombreReporte(param.nombreReporte)
    loadArchivoDetalle(request)

    let extFile = param.nombreReporte.split('.').pop();
    console.log('Ext. Archvo: ' + extFile);
    setShow(extFile === 'TXT' ? true : false)

    setIsModalDetailOpen(true);
  };

  const handleDetailOk = () => {
    setIsModalDetailOpen(false);
  };

  const handleDetailCancel = () => {
    setIsModalDetailOpen(false);
  };
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
        description="En esta pantalla puedes realizar la carga de los siguientes archivos diarios de la aplicación SIDECA: 01_REP_FUTUROS_POSICION, 03_FX_FORWARD_POSITIONS, 06_SWAP_RESUMEN_POSICION, 40_JOURNAL_ENTRIES_DETAIL, 42_GARANTIAS_CONTRAPARTE, IRDT. 
    Se espera la carga diaria de estos archivos, con los nombres anteriormente descritos y en el mismo formato con que los genera el sistema SIDECA.
    La información de los archivos se carga en el sistema en su totalidad y son insumos para el proceso de conciliación."
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

  const fileType = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
  //const fileExtension = '.xlsx';

  const exportToCSV = (csvData, fileName) => {
    const ws = XLSX.utils.json_to_sheet(csvData);
    const wb = { Sheets: { 'data': ws }, SheetNames: ['data'] };
    const excelBuffer = XLSX.write(wb, { bookType: 'xlsx', type: 'array' });
    const data = new Blob([excelBuffer], { type: fileType });
    FileSaver.saveAs(data, fileName);
  }

  const [show, setShow] = useState();

  return (
    <div>
      <Card
        size="small" align="left"
        title="Carga de reportes operativos SIDECA"
        extra={content}
        headStyle={{ backgroundColor: '#39c0c4' }}>

        <Form form={form} size="small" enctype="multipart/form-data"
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
            label="Fecha de Operacion"
            name="fechaOperacion"
            rules={[{
              required: true,
            }]}
          >
            <DatePicker onChange={onChange} />
          </Form.Item>
          <Form.Item
            label="Archivo:"
            name="file"
            rules={[{
              required: false,
              message: "Por favor seleccione un Archivo"
            }]}
          >
            <Row gutter={8}>
              <Col span={16}>
                <Card>
                <Dragger {...props}>
                  <p className="ant-upload-drag-icon">
                    <UploadOutlined />
                  </p>
                  <p className="ant-upload-hint">Haga clic o arrastre el archivo a esta área para cargarlo</p>
                </Dragger>
                </Card>
              </Col>
              <Col span={8} align="center">
                <Button htmlType="submit" className="buttonAdd" type="primary" loading={loadingBoton.state} shape="circle" icon={<PlusOutlined />} size="large" />
              </Col>
            </Row>

          </Form.Item>

        </Form>
      </Card>

      <Card size="small" align="left" title="Resumen de la carga de archivos"
        headStyle={{ backgroundColor: '#39c0c4' }}
      >
        <Table size="small" columns={columns} dataSource={data} className="table-striped-rows"></Table>
      </Card>

      <Modal open={isModalOpen} onOk={handleOk} onCancel={handleCancel} title={nombreArchivo}>
        <p>{msjMod}</p>
      </Modal>

      <Modal open={isModalRepOpen} 
        onOk={handleRepOk} 
        onCancel={handleRepCancel} 
        okButtonProps={{disabled:botonModalProc}}
        cancelButtonProps={{disabled:botonModalProc}}
        >
        <p>Los siguientes archivos ya se encuentran procesados.<br></br>Seleccionar los que desea reprocesar.</p>
        <Spin spinning={spinLoading}>
        <Table 
          size="small" 
          columns={colRep} 
          dataSource={filesRep} 
          className="table-striped-rows"          
          pagination={false}
          rowSelection={{
            onChange: (selectedRowKeys, selectedRows) => {
              console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
              setFilesSelectedRep(selectedRows)
            },
          }}
        >
        </Table>
        </Spin>
      </Modal>

      <Modal
        open={isModalDetailOpen}
        onOk={handleDetailOk}
        onCancel={handleDetailCancel}
        closable={false}
        width={1000}
        okButtonProps={{ hidden: true }}
        cancelButtonProps={{ hidden: true }}
      >
        <Card size="small" align="left" title={nombreReporte}
          headStyle={{ backgroundColor: '#39c0c4' }}
          extra={
            show ?
              <div>
                <Button type="ghost" shape='circle'>
                  <CSVLink data={dataDetalle} filename={nombreReporte}>
                    <FileExcelOutlined style={{ color: 'green', fontSize: 25 }} />
                  </CSVLink>
                </Button>
              </div>
              :
              <div>
                <Button type="ghost" shape='circle'
                  icon={<FileExcelOutlined style={{ color: 'green', fontSize: 25 }} />}
                  onClick={(e) => exportToCSV(dataDetalle, nombreReporte)} >
                </Button>
              </div>
          }
        >
          <Table
            size="small"
            columns={columnsDetalle}
            dataSource={dataDetalle}
            className="table-striped-rows"
            scroll={{ x: "100" }}
          >
          </Table>
        </Card>
      </Modal>
    </div>
  );
}

export default CargaArchivo;