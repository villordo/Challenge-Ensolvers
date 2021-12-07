import React, { useContext, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import { AuthContext } from "../contexts/Auth.jsx";

const Logout = () => {
    const navigate = useNavigate();
    const { logout } = useContext(AuthContext);

    useEffect(() => {
        logout();

        navigate('/');

    }, [])

    return (
        <div className = "container">
            <p>
                Logging out..
            </p>
        </div>
    );
};

export default Logout;