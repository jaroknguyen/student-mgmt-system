import httpConnector from '../config/apis';
import { setToken } from './StorageService';

export const authen = (username, password, isRemember=false) => {
  return httpConnector.post(`/api/authenticate`, {username, password, isRemember})
  .then(({token}) => {
    setToken(token);
    return Promise.resolve({token});
  });
}