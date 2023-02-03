import { message } from 'antd';
import axios from 'axios';

const errorInterceptor = () => {
    axios.interceptors.response.use(
        function(successRes) {
          
          return successRes;
        }, 
        function(error) {
         
            if(error.response.status === 403){
                message.warning('La sesión ha expirado');
                sessionStorage.clear();
                var redireccion= window.location.protocol+'//'+window.location.host +'/sicader-api/init';
                console.log(redireccion);
                window.location.href = redireccion
            }else{
                return error;
            }
        }
      );
}



export default errorInterceptor;