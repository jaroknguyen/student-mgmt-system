import {TOKEN_KEY} from "../config/Constant";

export const setToken = (token) => {
  sessionStorage.setItem(TOKEN_KEY, token)
}

export const getToken = () => {
  return sessionStorage.getItem(TOKEN_KEY);
}

export const removeToken = () => {
  sessionStorage.removeItem(TOKEN_KEY);
}

export const clearAllStorage = () => {
  localStorage.clear();
  sessionStorage.clear();
}

const storage = {
  setToken,
  getToken,
  removeToken,
  clearAllStorage
};

export default storage;