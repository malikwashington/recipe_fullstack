import React from "react";
import { Fade } from "react-slideshow-image";
import "react-slideshow-image/dist/styles.css";
import cover1 from "../../assets/images/cover1.png";
import cover2 from "../../assets/images/cover2.jpg";
import cover3 from "../../assets/images/cover3.jpg";
import cover4 from "../../assets/images/cover4.jpg";
import cover5 from "../../assets/images/cover5.jpg";

const images = [cover1, cover2, cover3, cover4, cover5];

const HeroImageSlider = () => {
  const settings = {
    duration: 10000,
    transitionDuration: 500,
    infinite: true,
    indicators: true,
    arrows: false,
    autoplay: true,
  };

  return (
    <div className="slide-container">
      <Fade {...settings}>
        {images.map((image, index) => (
          <div className="each-fade" key={index}>
            <div className="image-container">
              <img src={image} alt={`Slide ${index + 1}`} />
            </div>
          </div>
        ))}
      </Fade>
    </div>
  );
};

export default HeroImageSlider;
