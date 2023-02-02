import React, { useState } from 'react';

const Login = ({
}) => {

  console.log('Entra Login.js:::::');
  const querystring = window.location.search;
  console.log(querystring);
  const params = new URLSearchParams(querystring);
  let token_user = params.get('token');
  let user = params.get('user');
  console.log('VALOR TOKEN:'+token_user);
  console.log('VALOR USER:'+user);

  if(token_user!=null){
    sessionStorage.setItem('toke',token_user);
    sessionStorage.setItem('access',true);
    sessionStorage.setItem('usuario',user);
    window.location.href = "/sicader/home";
  }  

  return (
    <div>
    </div>
  )
}

export default Login
