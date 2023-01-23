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
                window.location.href = "/sicader/login"
            }else{
                return error;
            }
        }
      );
}



export default errorInterceptor;