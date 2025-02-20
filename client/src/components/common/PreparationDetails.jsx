import React from "react";
import { Card } from "react-bootstrap";

const PreparationDetails = ({ prepTime, cookTime, category }) => {
  return (
    <section>
      <Card.Text>
        <strong>-- Prep Time : {prepTime}</strong>
      </Card.Text>
      <Card.Text>
        <strong>-- Cook Time : {cookTime}</strong>
      </Card.Text>
      <Card.Text>
        <strong>-- Category : {category}</strong>
      </Card.Text>
    </section>
  );
};

export default PreparationDetails;
