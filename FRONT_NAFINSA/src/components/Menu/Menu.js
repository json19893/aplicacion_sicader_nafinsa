import {
  HomeOutlined,
  FolderOpenOutlined,
  PieChartOutlined,
  BarChartOutlined,
  ContainerOutlined,
  BlockOutlined,
  FundOutlined,
  InteractionOutlined,
  UserOutlined,CommentOutlined ,
  CustomerServiceOutlined

} from '@ant-design/icons';
import { Layout, Menu, Icon,Avatar, Image, Row, Col,Tooltip,Button, Dropdown} from 'antd';

import { Link } from 'react-router-dom';
import logo from '../../assest/logo.png'
import { useState, useEffect } from 'react';
const { Header, Content, Footer, Sider } = Layout;
function getItem(label, key, route, icon) {
  return {
    key,
    route,
    icon,
    label,
  };
}
const usu = {
  usu: "",
}
const item = [
  getItem('Home', '1', '/sicader/home', <HomeOutlined />,),
  getItem('Carga Archivos', '2', '/sicader/cargaArchivo', <FolderOpenOutlined />),
  getItem('Información SIDECA', '3', '/sicader/infSIDECA', <PieChartOutlined />),
  getItem('DMAT', '4', '/sicader/Dmat', <BarChartOutlined />),
  getItem('Catalogos Coberturas', '5', '/sicader/catCoberturas', <ContainerOutlined />),
  getItem('Cierre Jornada', '6', '/sicader/CierreJornada', <BlockOutlined />),
  getItem('Ganancia-Pérdida UDI-TIIE', '7', '/sicader/GananciaPerdida', <FundOutlined />),
  getItem('Cuentas a Conciliar', '8', '/sicader/cuentasConciliar', <InteractionOutlined />),
  getItem('Conciliación Contable', '9', '/sicader/conciliacionContable', <InteractionOutlined />),

];


const MenuLeft = (componente) => {
  const [collapsed, setCollapsed] = useState(false);
  let menuSele = componente.val;
  let compo = componente.componente;
  const [usuario, setUsuario] = useState(usu);
  const logout = () => {
    sessionStorage.clear();
    window.location.href = "/sicader/login"
  };

  useEffect(() => {
    setUsuario({
      usu: sessionStorage.getItem('usuario'),
      letra:sessionStorage.getItem('usuario').charAt(0)
    });
  
  }, []);
  const items = [
    {
      key: '1',
      label: (
        <a>
        {usuario.usu}
        </a>
      ),
    },
    {
      key: '2',
      label: (
        <a onClick={logout}>
         salir
        </a>
      ),
    },
  ];


  return (
    <div id="home" >
      <Layout
        style={{
          minHeight: '100vh',
        }}
      >
        <Sider collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)}>
          <div className="logo" > <img width="60px" height="60px" alt="logo" src={logo}></img></div>
          <Menu theme="dark" defaultSelectedKeys={[menuSele]} mode="inline"  >
            <> {
              item.map(item => {
                return (
                  <Menu.Item key={item.key} icon={item.icon} >
                    <Link to={item.route}>

                      {item.label}
                    </Link>

                  </Menu.Item>)
              })
            }
            </>
          </Menu>
        </Sider>
        <Layout className="site-layout">
          <Header
            className="site-layout-background"
            style={{
            
              color: "#FFFFFF"
            }}
          >    <Row gutter={8}>
            <Col span={6} ></Col>
                <Col span={10} align="center">
                SISTEMA DE CONCILIACIÓN AUTOMÁTICA DE DERIVADOS
                </Col>
                <Col span={7} align="right">
                <Dropdown
                menu={{
                  items,
                }}
                placement="bottom"
                arrow
              >
                 
               <Avatar  size={41}style={{ color: 'white', backgroundColor: '#179ba0' }}>{usuario.letra}</Avatar>
               
                </Dropdown>
 
              
                </Col>
              </Row>
           
           
          </Header>
          <Content>
            <div >
              <>
                <br></br>
                {compo}
              

              </>
            </div>


          </Content>
          <Footer
            theme="dark"
            style={{
              textAlign: 'center',
            }}
          >
            Nafin.com. Copyright. ©2022.
          </Footer>
        </Layout>
      </Layout>

    </div>
  )
}

export default MenuLeft;
