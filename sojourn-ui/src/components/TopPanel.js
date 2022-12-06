import React from 'react';
import { Link } from 'react-router-dom';
function TopPanel() {
  return (
    <nav className="navbar">
        <div className="navbar-container container">
            <input type="checkbox" name="" id="" />
            <div className="hamburger-lines">
                <span className="line line1"></span>
                <span className="line line2"></span>
                <span className="line line3"></span>
            </div>
            <ul className="menu-items">
                <li><Link to="/">Home</Link></li>
                <li><Link to="/data">Data</Link></li>
                <li><Link to="/profile">Profile</Link></li>
                <li><Link to="/contact">Contact</Link></li>
            </ul>
            <h1 className="logo">SOJOURN</h1>
        </div>
    </nav>
  )
}

export default TopPanel