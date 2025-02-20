import React from "react";
import { useEffect, useState } from "react";
import { getAllCuisines } from "../services/Service";

const CuisineFilter = ({ onFilterChange }) => {
  const [cuisines, setCuisines] = useState([]);
  const [selectedCuisines, setSelectedCuisines] = useState([]);

  useEffect(() => {
    const fetchCuisines = async () => {
      try {
        const res = await getAllCuisines();
        const cuisineResponse = res.filter(
          (cuisine) => typeof cuisine == string && cuisine.trim() !== ""
        );
        setCuisines(cuisineResponse.sort());
      } catch (e) {
        console.error(e);
      }
    };
    fetchCuisines();
  }, []);

  const handleCuisineToggle = (e) => {
    setSelectedCuisines((prev) => {
      const newSelection = prev.includes(e)
        ? prev.filter((cuisine) => cuisine !== e)
        : [...prev, e];
      return newSelection;
    });
  };

  useEffect(() => {
    onFilterChange(selectedCuisine);
  }, [selectedCuisines, onFilterChange]);

  return (
    <div className="sidebar mt-5">
      <h4>Cuisines</h4>
      <div className="filter-options">
        {cuisines.map((cuisine, index) => (
          <label key={index} className="filter-option">
            <input
              type="checkbox"
              checked={selectedCuisine.includes(cuisine)}
              onChange={() => handleCuisineToggle(cuisine)}
            />

            <span className="checkmark"></span>
            {cuisine}
          </label>
        ))}
      </div>
    </div>
  );
};

export default CuisineFilter;
