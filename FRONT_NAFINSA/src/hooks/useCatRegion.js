import Axios from "axios";
import { useEffect, useState } from 'react';
import API from '../utils/Api';
const useCatRegion=()=>{
    const [portafoliosData, setPortafoliosData] = useState([]);
  const [portafoliosInformacion, setPortafoliosInformacion] = useState([]);

  useEffect(async () => {
    try {
      const { data } = await API.get('/getRegion');
      const { portafolios } = data
      

      const portafolioSelect = portafolios.map(portafolio => {
        return {
          id_region: portafolio.id_region,
          nombre_region: portafolio.nombre_region,
          estatus: portafolio.estatus,
        };
      })
      setPortafoliosData(portafolioSelect);

    } catch (error) {
      console.log("catch", error)
    }
  }, []);

  return {
    portafolios: portafoliosData
  };

}


export default useCatRegion;

