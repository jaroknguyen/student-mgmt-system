import './App.css';

import Routers from './routes'

import {setReqAuthHeader, getToken} from './helpers/AuthToken'

function App() {

  //check jwt token
  const token = getToken("token");
  if (token) {
    setReqAuthHeader(token);
  }

  return (
    <div>
      <Routers/>
    </div>
  );
}

export default App;
