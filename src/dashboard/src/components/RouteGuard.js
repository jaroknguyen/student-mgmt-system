import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { TOKEN_KEY } from '../config/Constant';
import { getToken } from '../services/StorageService';

const RouteGuard = ({ component: Component, ...rest }) => {

    function hasJWT() {
        let flag = false;

        //check user has JWT token
        getToken(TOKEN_KEY) ? flag=true : flag=false
        
        return flag
    }

    return (
        <Route {...rest}
            render={props => (
                hasJWT() ?
                    <Component {...props} />
                    :
                    <Redirect to={{ pathname: '/login' }} />
            )}
        />
    );
};

export default RouteGuard;