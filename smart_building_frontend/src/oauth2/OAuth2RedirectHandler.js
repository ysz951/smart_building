import React, { Component } from 'react';
import { Redirect } from 'react-router-dom'
import AuthApiService from '../services/auth-api-service';
import IdleService from '../services/idle-service';
import TokenService from '../services/token-service';

class OAuth2RedirectHandler extends Component {
    getUrlParameter(name) {
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

        var results = regex.exec(this.props.location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    };

    render() {        
        const token = this.getUrlParameter('token');
        const error = this.getUrlParameter('error');
        if(token) {
            TokenService.saveAuthToken(token);
            IdleService.regiserIdleTimerResets();
            TokenService.queueCallbackBeforeExpiry(() => {
                AuthApiService.postRefreshToken();
            })
            return <Redirect to={{
                pathname: "/main",
                state: { from: this.props.location }
            }}/>; 
        } else {
            return <Redirect to={{
                pathname: "/login",
                state: { 
                    from: this.props.location,
                    error: error 
                }
            }}/>; 
        }
    }
}

export default OAuth2RedirectHandler;