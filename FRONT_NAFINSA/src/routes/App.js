import React from "react";
import { useState, useEffect } from 'react';
import Login from '../components/Login/Login';
import DasboardComponen from './DasboardComponen';
import NotFound from "../components/NotFound/NotFound";
import './App.css';
import { BrowserRouter, Switch, Route } from 'react-router-dom';

const App = () => {
  const [login, setLogin] = useState(false);
  useEffect(() => {
 let sesion=  sessionStorage.getItem('access')
    setLogin(sesion)
   
  }, []);
  return (
    <BrowserRouter>
      <Switch>
      {login ?<Route path={"/"}><DasboardComponen /></Route>:
      <><Route exact path={"/sicader/login"}><Login /></Route><Route><Login /></Route></>
      }

      </Switch>
    </BrowserRouter>
  );
}


export default App;
