import { React, useEffect, useState, useContext } from 'react';
import { Link, useHistory } from "react-router-dom";
import { AuthContext } from '../contexts/Auth.jsx';


const NavBar = () => {
    const { loggedUser } = useContext(AuthContext);

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav m-3">
                    <li className="navbar-item ">
                        <Link className="navbar-link mr-4" to={`/Folders/`}>
                            Home
                        </Link>
                    </li>
                    {loggedUser ? (
                        <li className="navbar-item">
                            <Link className="navbar-link mr-4" exact to="/logout">LogOut</Link>
                        </li>
                    ) :
                        (

                            <></>


                        )}
                </ul>
            </div>
        </nav>
    );
};

export default NavBar;