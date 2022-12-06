import { Carousel,   Typography, } from 'antd';
import React, { useState } from 'react';



  const { Title } = Typography;
const Home = () => {
    


    return (
        <>
     <Title level={2} align="center">Bienvenido a SICADER</Title>
        
        <Carousel dotPosition="left" >
          <div>
          <img   width= "1719vw" height= "400vh" src="https://www.nafin.com/portalnf/files/secciones/piso_financiero/imagenes/bono_social/bono_social_Contenido.jpg"></img>
          </div>
          <div>
          <img width= "1719vw" height= "400vh" src="https://www.nafin.com/portalnf/files/secciones/piso_financiero/imagenes/mercados.jpg"></img>
          </div>
          <div>
          <img  width= "1719vw" height= "400vh" src=" https://www.nafin.com/portalnf/files/secciones/piso_financiero/imagenes/operadora.jpg"></img>
          </div>
          <div>
          <img  width= "1719vw" height= "400vh" src=" https://www.nafin.com/portalnf/files/secciones/Cadenas/imagenes/afiliacion_gobierno_contenido.jpg"></img>
          </div>
        </Carousel>
      </>

    );
  }


export default Home;