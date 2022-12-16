import React from "react";
import Menu from '../components/Menu/Menu';
import { useState, useEffect } from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import CargaArchivo from "../components/cargaArchivo/CargaArchivo";
import Dmat from "../components/Dmat/Dmat";
import InfSideca from "../components/infoSideca/InfSideca";
import CatCoberturas from "../components/catCoberturas/CatCoberturas";
import CierreJornada from "../components/CierreJornada/CierreJornada";
import GananciaPerdida from "../components/GananciaPerdida/GananciaPerdida";
import Login from "../components/Login/Login";
import Home from "../components/home/Home";
import NotFound from "../components/NotFound/NotFound";
import CuentasConciliar from "../components/CuentasConciliar/CuentasConciliar";
import ConciliacionContable from "../components/ConciliacionContable/ConciliacionContable";

const DasboardComponen = () => {
  const [login, setLogin] = useState(false);
  useEffect(() => {
    setLogin(sessionStorage.getItem('access'))
    
  }, []);
  return (
    <>

      <BrowserRouter>
        <Switch>

          <Route path={"/sicader/home"}><Menu componente={<Home />} val={'1'} /></Route>
          <Route exact path={"/sicader/cargaArchivo"}> <Menu componente={<CargaArchivo />} val={'2'} /></Route>
          <Route exact path={"/sicader/infSIDECA"}> <Menu componente={<InfSideca />} val={'3'} /></Route>
          <Route exact path={"/sicader/Dmat"}> <Menu componente={<Dmat />} val={'4'} /></Route>
          <Route exact path={"/sicader/catCoberturas"}> <Menu componente={<CatCoberturas />} val={'5'} /></Route>
          <Route exact path={"/sicader/CierreJornada"}> <Menu componente={<CierreJornada />} val={'6'} /></Route>
          <Route exact path={"/sicader/GananciaPerdida"}> <Menu componente={<GananciaPerdida />} val={'7'} /></Route>
          <Route exact path={"/sicader/CuentasConciliar"}> <Menu componente={<CuentasConciliar />} val={'8'} /></Route>
          <Route exact path={"/sicader/ConciliacionContable"}> <Menu componente={<ConciliacionContable />} val={'9'} /></Route>
          <Route ><Menu componente={<NotFound />} val={'404'} /></Route>


        </Switch>
      </BrowserRouter>
    </>
  );
}


export default DasboardComponen;
