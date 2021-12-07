import { React, useEffect, useState } from 'react';
import { Link, useHistory } from "react-router-dom";

const NavBar = () => {

    return (
        <nav className=" navbar navbar-light bg-light">
            <ul className="d-flex align-items-center flex-row ">
                <li className="nav-item">
                    <Link className="nav-link" to={`/Folders/`}>
                        Home
                    </Link>
                </li>
            </ul>
        </nav>
    );
};

export default NavBar;