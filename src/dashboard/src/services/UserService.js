import httpConnector from '../config/apis';

export const getUsers = () => {
  return httpConnector.get(`/api/users`);
}

export const getUserDetail = (id) => {
  return httpConnector.get(`/api/user/${id}`);
}

export const deleteUser = (id) => {
  return httpConnector.delete(`/api/user/${id}`);
}

export const addUser = (username, password, firstName, lastName, activated = true, roleName) => {
  return httpConnector.post(`/api/user/create`, {username, password, firstName, lastName, activated, roleName});
}

export const editUser = (id, username, password = undefined, firstName, lastName, activated = false, roleName) => {
  return httpConnector.post(`/api/user/edit`, {id, username, password, firstName, lastName, activated, roleName});
}
