import React from "react";
import { useEffect, useState } from "react";
import Hero from "../hero/Hero";
import RecipeCard from "../recipe/RecipeCard";
import { getAllRecipes } from "../services/Service";
import { Container, Row, Col } from "react-bootstrap";
import CategoryFilter from "../common/CategoryFilter";
import CuisineFilter from "../common/CuisineFilter";

const Home = () => {
  const [recipes, setRecipes] = useState([]);
  const [filteredRecipes, setFilteredRecipes] = useState([]);
  const [activeCategory, setActiveCategory] = useState(null);
  const [selectedCuisines, setSelectedCuisines] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchRecipies = async () => {
      try {
        const res = await getAllRecipes();
        setRecipes(res);
        setFilteredRecipes(res);
        setLoading(false);
      } catch (e) {
        setError(e.message);
      }
    };
    fetchRecipies;
  }, []);

  useEffect(() => {
    let filtered = recipes;
    if (activeCategory) {
      filtered = filtered.filter((r) => r.category == activeCategory);
    }
    if (selectedCuisines.length > 0) {
      filtered = filtered.filter((r) => selectedCuisines.includes(r.cuisine));
    }
    setFilteredRecipes(filtered);
  }, [activeCategory, selectedCuisines, recipes]);

  const handleCategoryClick = (category) => {
    setActiveCategory((current) => (current == category ? null : category));
  };

  const handleCuisinefilter = (cuisines) => {
    setSelectedCuisines(cuisines);
  };

  return (
    <>
      <Hero />
      <main className="home-container">
        <CuisineFilter onFilterChange={handleCuisinefilter} />
        <Container className="main-content text-center mb-4" id="explore">
          <h2 className="text-center mb-4 home-title">
            Explore Recipes on Smorgasborg
          </h2>

          <CategoryFilter
            onCategoryClick={handleCategoryClick}
            activeCategory={activeCategory}
          />

          {loading && <div>Loading Recipes</div>}
          {error && <div className="text danger mb-4">{error}</div>}

          <Row>
            {filteredRecipes && filteredRecipes.length > 0 ? (
              filteredRecipes.map((recipe) => (
                <Col key={recipe.id} md={4} lg={3} sm={12} xs={12}>
                  <RecipeCard recipe={recipe} />
                </Col>
              ))
            ) : (
              <div className="text-danger mb-4">
                No recipes found at this time , please check again later!
              </div>
            )}
          </Row>
        </Container>
      </main>
    </>
  );
};

export default Home;
