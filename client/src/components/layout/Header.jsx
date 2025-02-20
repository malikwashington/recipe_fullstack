import React from "react";
import { Navbar, Nav } from "react-bootstrap";

const Header = () => {
  return (
    // navbar boilerplate code from bootstrap website
    <Navbar expand="lg" className="navbar mt-2">
      <Navbar.Brand
        className="logo"
        style={{ color: 'white, fontSize: "28px"' }}
      >
        <a href="/" className="nav-links">
          Smorgasbord
        </a>
      </Navbar.Brand>
      <Navbar.Toggle />
      <Navbar.Collapse>
        <Nav className="me-5">
          <a href="/aboutus" className="nav-links">
            What We Do At Smorgasbord
          </a>
        </Nav>
        <Nav className="me-5">
          <a href="/login" className="nav-links">
            Login To Share Your Recipes
          </a>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default Header;
