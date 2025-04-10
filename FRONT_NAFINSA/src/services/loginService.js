import axios from 'axios';
const baseUrl = process.env.REACT_APP_BASE_URL;
export async function getLogin (request) {
    try{
        const response = await axios({
            url: `${baseUrl}/init`,
            method: 'POST',
            params:request ,
            mode: 'no-cors',
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
        return e;
    }
}

export async function getSucees (request) {
    try{
        const response = await axios({
            url: `${baseUrl}/success`,
            method: 'POST',
            params:request ,
            mode: 'no-cors',
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
        return e;
    }
}

export async function logout (request) {
    try{
        const response = await axios({
            url: `${baseUrl}/logout`,
            method: 'POST',
            params:request ,
            mode: 'no-cors',
        })
        console.log(response)
        return response;
    } catch (e) {
        console.log(e)
        return e;
    }
}