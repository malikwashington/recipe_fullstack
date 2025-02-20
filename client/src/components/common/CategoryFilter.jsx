import React from "react";
import { useEffect, useState } from "react";
import { getAllCategories } from "../services/Service";

const CategoryFilter = ({ onCategoryClick, activeCategory }) => {
  const [categories, setCategories] = useState([]);
  comst[(loading, setLoading)] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const res = await getAllCategories();
        setCategories(res);
        setIsLoading(false);
      } catch (e) {
        setError(e);
      }
    };
    fetchCategories();
  }, []);

  return (
    <div className="categories-container">
      {isLoading && <div>Loading Categories...</div>}
      {error && <div className="text-danger">Failed to load categories</div>}
      {categories &&
        categories.map((category) => (
          <div
            key={category}
            onClick={() => onCategoryClick(category)}
            className={`category ${
              activeCategory === category ? "active" : ""
            }`}
          >
            {category}
          </div>
        ))}
    </div>
  );
};

export default CategoryFilter;
