import { Carousel,   Typography, } from 'antd';
import React, { useState } from 'react';

import bono from '../../assest/bono.jpg'
import afiliacion  from '../../assest/afiliacion.jpg'
import mercados from '../../assest/mercados.jpg'
import operadora from '../../assest/operadora.jpg'

  const { Title } = Typography;
const Home = () => {
    


    return (
        <>
     <Title level={2} align="center">Bienvenido a SICADER</Title>
        
        <Carousel dotPosition="left" >
          <div>
          <img   width= "1719vw" height= "400vh" src={bono}></img>
          </div>
          <div>
          <img width= "1719vw" height= "400vh" src={afiliacion}></img>
          </div>
          <div>
          <img  width= "1719vw" height= "400vh" src={mercados}></img>
          </div>
          <div>
          <img  width= "1719vw" height= "400vh" src={operadora}></img>
          </div>
        </Carousel>
      </>

    );
  }


export default Home;