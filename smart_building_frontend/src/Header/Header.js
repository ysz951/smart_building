import TokenService from '../services/token-service';
import { Link, NavLink } from 'react-router-dom';
import IdleService from '../services/idle-service';
import MemberRESTService from '../RESTService/MemberRESTService';
import { useState, useEffect } from 'react';
import './Header.css';
function Header() {
    const [userName, setUserName] = useState("");
    useEffect(() => {
        MemberRESTService.getCurrentUser()
            .then(res => {
                setUserName(res.data.username);
                // console.log(res.data);
            })
            .catch(err => console.log(err))
    }, []);
    const handleLogoutClick = () => {
        TokenService.clearAuthToken();
        TokenService.clearCallbackBeforeExpiry();
        IdleService.unRegisterIdleResets();
    }
    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav  mr-auto mt-2 mt-lg-0">
                        <li className="nav-item">
                            <NavLink to="/main" className="nav-link" activeClassName="active"> Department </NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink to="/room" className="nav-link" activeClassName="active"> Room </NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink to="/meeting" className="nav-link" activeClassName="active"> Meeting </NavLink>
                        </li>
                        <li className="nav-item">
                            <NavLink to="/airpressure" className="nav-link" activeClassName="active"> Airpressure </NavLink>
                        </li>
                    </ul>
                    {TokenService.hasAuthToken() && <Link onClick={handleLogoutClick} to="/">Log out</Link>}
                </div>
            </nav>
            <p>Current user: <span className="current-username">{userName}</span></p>
        </>
    )
}

export default Header;