import React from "react";
import { Link } from "react-router-dom";
import HeroImageSlider from "./HeroImageSlider";

const Hero = () => {
  return (
    <div className="hero">
      <HeroImageSlider />
      <div className="hero-content">
        <h1 className="hero-title">Welcome to smorgasbord</h1>
        <p className="hero-sub-title">
          your go-to platform for discovering delicious recipies
        </p>
        <Link className="hero-button">
          Explore your recipes for your next meal!
        </Link>
      </div>
    </div>
  );
};

export default Hero;
