import React from "react";
import { useState, useEffect } from 'react';
import { Table, Button, Form, Input, Card, Row, Col, Modal, DatePicker, Popover, message, Upload, notification, Typography } from 'antd';
import { FileExcelOutlined, PlusOutlined, DownloadOutlined, UploadOutlined, QuestionOutlined } from '@ant-design/icons';
import { CSVLink } from 'react-csv';
import { cargarArchivo, getArchivoFecha, getArchivoDetalle } from '../../services/cargaArchivoService'
import { getColumnasDetalleArchivo } from './columnasArchivoDetalle'

import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';

const { Meta } = Card;
const { Title } = Typography;
const stateInitialLoading = {
  state: false,
}
function CargaArchivo() {
  const [loadingBoton, setLoadingBoton] = useState(stateInitialLoading);
  const [fileUpload, setFileUpload] = useState([]);
  const [open, setOpen] = useState(false);
  const [msjMod, setMsjMod] = useState([]);
  const [formModal, setformModal] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isModalDetailOpen, setIsModalDetailOpen] = useState(false);
  const [dataArchivos, setDataArchivos] = useState([]);
  const [dataDetalleArchivo, setDataDetalleArchivo] = useState([]);
  const [columnsDetalle, setColumnsDetalle] = useState([]);
  const [uploading, setUploading] = useState(false);
  const [nombreReporte, setNombreReporte] = useState([]);
  const [nombreArchivo,setNombreArchivo] = useState([]);

  const handleInputChange = (event) => {
    setFileUpload(event.target.files[0])

  }

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

      setLoadingBoton({
        state: false
      });
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
          setDataDetalleArchivo(response.data)
        }
      }
    } catch (error) {

      setLoadingBoton({
        state: false
      });
    }
  }

  useEffect(() => {
    loadArchivoDetalle(null)
  }, []);

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
      //if (!uploading) {
        let cadena = file.name.split('.');
        console.log(cadena[1])
        if (cadena[1] != 'TXT' && cadena[1] != 'xlsx') {
          return openNotification('Favor de validar el nombre del archivo ingresado', 2);
        }
        let val = cadena[0] + '.' + cadena[1] == '01_REP_FUTUROS_POSICION.TXT' ? 0 :
          cadena[0] + '.' + cadena[1] == '03_FX_FORWARD_POSITIONS.TXT' ? 0 :
            cadena[0] + '.' + cadena[1] == '06_SWAP_RESUMEN_POSICION.TXT' ? 0 :
              cadena[0] + '.' + cadena[1] == '40_JOURNAL_ENTRIES_DETAIL.TXT' ? 0 :
                cadena[0] + '.' + cadena[1] == '42_GARANTIAS_CONTRAPARTE.TXT' ? 0 :
                  cadena[0] + '.' + cadena[1] == 'IRDT.xlsx' ? 0 : 1
        if (val == 1) {
          return openNotification('Favor de validar el nombre del archivo ingresado', 2);
        }
        setFileUpload([...fileUpload, file]);
        setUploading(true);
        openNotification('Archivo seleccionado correctamente', 1)
      //} else {
      //  openNotification('Debe borrar el archivo para cargar otro', 2)
      //}
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

    fileUpload.forEach(fileProcess => {
      try {
        const request = {
          fechaOperacion: values.fechaOperacion,
          file: fileProcess,
          forzar: false,
          usuario: 'Jose'
        }
        submitPost(request)
      } catch (error) {
        message.error('Error en la creación del registro.');
        setLoadingBoton({
          state: false,
        });
      }
    }
    )
  };

  async function submitPost(request) {
    try {
      setNombreArchivo(request.file.name)
      const response = await cargarArchivo(request)
      if (response.status === 200) {
        if (response.data.respuesta === 'OK') {
          await loadArchivoFecha(request.fechaOperacion)
          onReset();
          setFileUpload([]);
          setUploading(false);
          message.success('Archivo cargado correctamente.');
          setLoadingBoton({
            state: false
          });
        }
        else {
          setMsjMod(response.data.respuesta);
          setformModal(request);
          setIsModalOpen(true);
        }
      } else {
        message.error(response.data.mensaje);
        await loadArchivoFecha(request.fechaOperacion)
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