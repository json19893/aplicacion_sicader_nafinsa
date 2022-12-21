import React, { useState } from 'react';
import { Form, Input, Button, Row, Col,Tooltip,message,notification } from 'antd';
import { getLogin,getSucees } from '../../services/loginService'
import { stringify } from 'rc-field-form/es/useWatch';






const Login = ({
  visible,
  setModalLoginVisible,
}) => {


  const [form, setValues] = useState({
    email: "",
    password: "",
  });

  const [loading, setLoading] = useState({
    state: false,
    message: 'Acceder'
  });


  const tailLayout = {
    wrapperCol: { offset: 7, span: 10 },
  };

  const handleInput = event => {
    setValues({
      ...form,
      [event.target.id]: event.target.value
    })
  };
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
 
  const handleSubmit=  async (values) => {
    setLoading({
      state: true,
      message: '...'
    });
    const request={
      "usuario":values.username,
      "password":values.password
    }
    try {
      const response = await getLogin(request);
      console.log(response);
     let data =response.data==undefined?response.response.data:response.data;
     console.log("theirs data:"+JSON.stringify(data));
      if (response.status==200){
       //if (response	.error=="OK"){
        /*const sucess=   await getSucees(request);
        let dat=sucess.data==undefined?sucess.response.data:sucess.data;*/
        //if (dat.status==200){
        //  if (dat.error=="OK"){
	 	console.log("Entro");			
        let da=response.data;
        console.log("data jcarias:"+JSON.stringify(da));
        /*da=da.replaceAll('"','')
        da=da.replaceAll('{','')
        da=da.replaceAll('}','')
        let valores= da.split(',')
        let usuario=valores[0].replace('usuario:','')
        let token=valores[1].replace('token:','')*/
       let usuario = da.usuario;
       let token = da.token;
        console.log(usuario);
        console.log(token);
        sessionStorage.setItem('usuario',usuario );
        sessionStorage.setItem('toke',token);
        sessionStorage.setItem('access',true);
        
        window.location.href = "/sicader/home"
     /* }else{
   
        openNotification(dat.message, 2)
        setLoading({
          state: false,
          message: 'Acceder'
        });
       }*/
      /*}else if (dat.status==401){
        openNotification(dat.message, 2)
        setLoading({
          state: false,
          message: 'Acceder'
        });
      }*/
       /*}else{
   
        openNotification(data.message, 2)
        setLoading({
          state: false,
          message: 'Acceder'
        });
       }*/
            }else if (data.status==401){
              openNotification(data.message, 2)
              setLoading({
                state: false,
                message: 'Acceder'
              });
            }
    
        
    } catch (error) {
      console.log("Error:: "+error);
      openNotification(error, 2)
      setLoading({
        state: false,
        message: 'Acceder'
      });
      setLoading({
        state: false,
        message: 'Acceder'
      });
    }
   
  
 ;

  };
  return (
    <div id="fondo" >
      <Row>
        <Col span={10}>
          <Row>
            <Col span={2}></Col>
            <Col span={4} >
              <br></br>
              <img width="150px" height="150px" alt="logo" src="https://www.nafin.com/portalnf/images/nafin-logo.png"></img>
            </Col>
          </Row>

          <br></br> <br></br> <br></br> <br></br><br></br>
          <Row >
            <Col span={2}>  </Col>
            <Col span={22} >
              <p className='subtitulo'>SISTEMA DE CONCILIACIÓN AUTOMÁTICA DE DERIVADOS</p>
            </Col>
          </Row>


          <br></br> <br></br> <br></br> <br></br><br></br><br></br><br></br><br></br><br></br><br></br>
          <Row >

            <Col span={12} >
              <p className='copyRigh'>Nafin.com. Copyright. ©2022.</p>
            </Col>
          </Row>
        </Col>
        <Col className='box-shadow' span={14}>
          <br></br>
          <br></br>


          <Row >
            <Col span={6} ></Col>
            <Col span={16} >
              <p className='bienvenida'>HOLA DE NUEVO</p>
            </Col>
          </Row>

          <Row >
            <Col span={6} ></Col>
            <Col span={16} >
              <p className='listo'>INGRESA LOS DATOS PARA INICIAR SESIÓN</p>
            </Col>
          </Row>
          <br></br> <br></br> <br></br> <br></br>
          <Row type="flex" style={{ padding: "10px" }}>
            <Col span={6}></Col>
            <Col span={11}>
              <Form
                name="basic"
                onFinish={handleSubmit}
                layout="vertical"
              >

                <Form.Item
                  label="Usuario"
                  name="username"

                  rules={[
                    {
                      required: true,
                      message: 'El campo es requerido',
                    },
                  ]}

                >

                  <Input
                    id="email"
                    type="text"
                    placeholder="Correo"
                    defaultValue={(form || {}).email || ""}
                    onChange={handleInput}

                  />

                </Form.Item>

                <Form.Item
                  label="Contraseña"
                  name="password"
                  rules={[
                    {
                      required: true,
                      message: 'El campo es requerido',
                    },
                  ]}

                >
                  <Input.Password
                    id="password"
                    type="password"
                    defaultValue={(form || {}).password || ""}
                    placeholder="Contraseña"
                    onChange={handleInput}

                  />
                </Form.Item>

                <Form.Item {...tailLayout}>
                  <Button
                    type="primary"
                    loading={loading.state}
                    htmlType="submit"
                    className='bt1'
                  >
                    {loading.message}
                  </Button>
                </Form.Item>
              </Form>
            </Col>
          </Row>
          <br></br> <br></br> <br></br> <br></br><br />
          <Row >
            <Col span={7} ></Col>

          </Row>
        </Col>

      </Row>


    </div>
  )
}

export default Login
