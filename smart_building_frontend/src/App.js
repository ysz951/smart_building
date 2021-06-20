import React, { Component } from 'react';
import { Switch, Link, Route, withRouter } from 'react-router-dom';
import './App.css';
import Header from './Header/Header';
import HomePage from './HomePage/HomePage';
import Login from './Login/Login';
import MainPage from './MainPage/MainPage';
import PrivateRoute from './Route/PrivateRoute';
import PublicOnlyRoute from './Route/PublicOnlyRoute';
import TokenService from './services/token-service';
import IdleService from './services/idle-service';
import AuthApiService from './services/auth-api-service';
import config from './config';
import OAuth2RedirectHandler from './oauth2/OAuth2RedirectHandler';
import RegisterUser from './RegisterUser/RegisterUser';
import DepartmentItemPage from './DepartmentItemPage/DepartmentItemPage';
import RoomListPage from './RoomListPage/RoomListPage';
import RoomPage from './RoomPage/RoomPage';
import CreateDepartment from './CreateDepartment/CreateDepartment';
import MeetingRESTService from './RESTService/MeetingRESTService';
import MeetingPage from './MeetingPage/MeetingPage';
import MeetingItemPage from './MeetingItemPage/MeetingItemPage';
import AirPressure from './AirPressure/AirPressure';
class App extends Component {
  componentDidMount() {
    /*
      set the function (callback) to call when a user goes idle
      we'll set this to logout a user when they're idle
    */
    IdleService.setIdleCallback(this.logoutFromIdle);

    /* if a user is logged in */
    if (TokenService.hasAuthToken()) {
      // this.forceUpdate()
      /*
        tell the idle service to register event listeners
        the event listeners are fired when a user does something, e.g. move their mouse
        if the user doesn't trigger one of these event listeners,
          the idleCallback (logout) will be invoked
      */
      IdleService.regiserIdleTimerResets();

      /*
        Tell the token service to read the JWT, looking at the exp value
        and queue a timeout just before the token expires
      */
      TokenService.queueCallbackBeforeExpiry(() => {
        /* the timoue will call this callback just before the token expires */
        AuthApiService.postRefreshToken();
      });
    }
  }

  componentWillUnmount() {
    /*
      when the app unmounts,
      stop the event listeners that auto logout (clear the token from storage)
    */
    IdleService.unRegisterIdleResets();
    /*
      and remove the refresh endpoint request
    */
    TokenService.clearCallbackBeforeExpiry();
  }

  logoutFromIdle = () => {
    console.log("logout idle");
    /* remove the token from localStorage */
    TokenService.clearAuthToken();
    /* remove any queued calls to the refresh endpoint */
    TokenService.clearCallbackBeforeExpiry();
    /* remove the timeouts that auto logout when idle */
    IdleService.unRegisterIdleResets();
    /*
      react won't know the token has been removed from local storage,
      so we need to tell React to rerender
    */
    this.forceUpdate();
    // this.props.history.push('/');
  }

  render() {
    return (
      <>
        <Switch>
          <Route exact path={["/main", "/room", "/meeting", "/airpressure"]} component={Header} />
          <Route component={NoContent} />
        </Switch>
        <Switch>
          <PublicOnlyRoute exact path="/" component={HomePage} />
          <PublicOnlyRoute path="/login" component={Login} />
          <PrivateRoute path="/main" component={MainPage} />
          <PrivateRoute path="/department/:id" component={DepartmentItemPage} />
          <PrivateRoute path="/room/:id" component={RoomPage} />
          <PrivateRoute exact path="/room" component={RoomListPage} />
          <PrivateRoute path="/new_department" component={CreateDepartment} />
          <PrivateRoute exact path="/meeting" component={MeetingPage} />
          <PrivateRoute path="/meeting/:id" component={MeetingItemPage} />
          <PrivateRoute path="/airpressure" component={AirPressure} />
          <PublicOnlyRoute path="/register_user" component={RegisterUser} />
          <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}></Route>  
          <Route component={NotFoundPage} />
        </Switch>
        
      </>
    )
  }
}

function NoContent() {
  return (
    <p></p>
  )
}

function NotFoundPage() {
  return (
    <>
      <h1>Not Found</h1>
      <Link to="/">Go Back</Link>
    </>

  )
}

export default withRouter(App);
