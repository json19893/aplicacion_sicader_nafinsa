import axios from 'axios';
import * as moment from "moment";
import errorInterceptor from '../interceptors/error';

const baseUrl = process.env.REACT_APP_BASE_URL;

errorInterceptor();

export async function getAllCartaConfirmacion (fechaOpe) {
    try{

        let fechaO;
        if(fechaOpe==null){
        const fecha = new Date();
        const fechaS = moment(fecha).format("YYYY-MM-DD")
        fechaO=fechaS;
        }else{
            fechaO=moment(fechaOpe).format("YYYY-MM-DD");
        }
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/informacion/getCartaConfirmacion`,
            method: 'GET',
            params: {
                //fechaOperacion: '2022-11-18'
                fechaOperacion: fechaO
            },
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function cargaCartaConfirmacion (params) {
    try{
        let token_insert=  sessionStorage.getItem('toke')
        console.log(params.fechaOperacion+' - '+params.forzar+' - '+params.idSocioLiquidador+' - '+params.montoBalance+' - '+params.montoIva+' - '+params.usuario)
        const response = await axios({
            url: `${baseUrl}/sicader/informacion/cartaConfirmacion`,
            method: 'POST',
            data: params,
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response);
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function getAllCuentaAsigna (fechaOpe) {
    try{
        let fechaO;
        if(fechaOpe==null){
        const fecha = new Date();
        const fechaS = moment(fecha).format("YYYY-MM-DD")
        fechaO=fechaS;
        }else{
            fechaO=moment(fechaOpe).format("YYYY-MM-DD");
        }
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/informacion/getCuentaAsigna`,
            method: 'GET',
            params: {
                //fechaOperacion: '2022-11-18'
                fechaOperacion: fechaO
            },
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
    }
}

export async function cargaCuentaAsigna (params) {
    try{
        console.log(params.balanceFinal+' - '+params.fechaOperacion+' - '+params.forzar+' - '+params.idSocioLiquidador+' - '+params.ivaCuenta+' - '+params.ivaOperacion+' - '+params.usuario)
        let token_insert=  sessionStorage.getItem('toke')
        const response = await axios({
            url: `${baseUrl}/sicader/informacion/cuentaAsigna`,
            method: 'POST',
            data: params,
            headers: { "Authorization": `${token_insert}` }
        })
        console.log(response);
        return response;
    } catch (e) {
        console.log(e)
    }
}