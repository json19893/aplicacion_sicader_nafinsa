import React from "react";
import Login from '../components/Login/Login';
import DasboardComponen from './DasboardComponen';
import './App.css';
import { BrowserRouter, Switch, Route } from 'react-router-dom';

const App = () => {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path={"/sicader/login"}><Login /></Route>
        <Route path={"/"}><DasboardComponen /></Route>

      </Switch>
    </BrowserRouter>
  );
}


export default App;
