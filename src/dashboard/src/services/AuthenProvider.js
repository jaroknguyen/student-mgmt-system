import axios from 'axios';
import { setReqAuthHeader, setToken } from "../helpers/AuthToken"

export const authen = (username, password) => {
  return axios.post("http://localhost:8080/api/authenticate", {username, password});
}