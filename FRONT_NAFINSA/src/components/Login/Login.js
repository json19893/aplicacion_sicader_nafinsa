import React, { useState } from 'react';
import { Form, Input, Button, Row, Col, } from 'antd';







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

  const entra = async () => {

  };
  const handleSubmit = event => {

    setLoading({
      state: true,
      message: 'cargando...'
    });
    entra();

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
