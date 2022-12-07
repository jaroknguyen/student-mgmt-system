import axios from 'axios';
import storage from '../services/StorageService';
import {API_BASE_URL, TOKEN_KEY} from './Constant';

const httpConnector = axios.create({
    baseURL: API_BASE_URL
})

export const configHTTP = http => {
    
    http.interceptors.request.use(
        config => {
            config.headers['Authorization'] = `Bearer ${storage.getToken(TOKEN_KEY)}`
            config.headers['content-type'] = 'application/json; text/plain'
            return config
        },
        error => {
            return Promise.reject(error)
        }
    )

    http.interceptors.response.use(
    (response) => {
        return Promise.resolve(response.data)
    },
    (error) => {
        return Promise.reject(error)
    })
}

configHTTP(httpConnector);

export default httpConnector;
