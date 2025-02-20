import React from "react";
import { Col, Row, Card } from "react-bootstrap";
import RatingStars from "../common/RatingStars";
import { ImageDownloader, PreparationDetails } from "../common";
import { Link } from "react-router-dom";
import Like from "../common/Like";

const RecipeCard = ({ recipe }) => {
  return (
    <Row className="justify-content-center mb-5">
      <Col>
        <Card className="text-center">
          <Card.Body>
            <h2 className="recipe-title">{recipe.title}</h2>
            <hr />
            <Link>
              <div className="image-container">
                {recipe.imageDto && (
                  <ImageDownloader recipeId={recipe.imageDto.div} />
                )}
              </div>
            </Link>

            <Row className="mt-4">
              <Col>
                <Like recipeId={recipe.id} likes={recipe.likeCount} />
              </Col>

              <Col>
                <Card.Text className="rating">
                  <RatingStars rating={recipe.averageRating} />
                </Card.Text>
              </Col>
            </Row>
            <hr />

            <p className="cuidine-text left-align">{recipe.cuisine}</p>
            <p className="sub-titles">Preparation: </p>
            <PreparationDetails
              prepTime={recipe.prepTime}
              cookTime={recipe.cookTime}
              category={recipe.category}
            />
          </Card.Body>

          <Link
            to={`recipe/$recipe.id}/recipe-details`}
            className="btn btn-secondary btn-sm"
            style={{ backgroundColor: "#562f63b5" }}
          >
            View Recipe Details
          </Link>
        </Card>
      </Col>
    </Row>
  );
};

export default RecipeCard;
