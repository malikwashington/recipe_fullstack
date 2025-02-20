import React from "react";
import { useState, useRef } from "react";
import { Form, Toast } from "react-bootstrap";
import { toast, ToastContainer } from "react-toastify";
import { uploadImage, updateImage } from "../../services/Service";

const ImageUploader = ({ recipeId, existingImageId }) => {
  const fileInputRef = useRef(null);
  const [image, setImage] = useState(null);
  const [preview, setPreview] = useState("");

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setImage(file);
      setPreview(URL.createObjectURL(file));
    }
  };

  const handleImageUpload = async (e) => {
    e.preventDefault();
    if (!image) {
      toast.warn("Please select an image");
      return;
    }
    try {
      let res;
      if (existingImageId) {
        res = await updateImage({ imageId: existingImageId, file: image });
        toast.success("Image updated successfully");
      } else {
        res = await uploadImage({ recipeId, file: image });
        toast.success(res.message);
      }
      clearFileInput();
    } catch (err) {
      toast.error(err.message);
    }
  };
};

const clearFileInput = () => {
  if (fileInputRef.current) fileInputRef.current.value = null;
  setImage(null);
  setPreview("");
};

return (
  <Form onSubmit={handleImageUpload}>
    <ToastContainer />

    <div className="mb-4"></div>
    <div className="mb-4 mt-4">
      <div className="mt-2 mb-2">
        <div className="d-flex align-items-center mb-2 input-group">
          <input
            type="file"
            accept="image/*"
            onChange={handleImageChange}
            className="me-2 form-control"
            ref={fileInputRef}
          />
        </div>
      </div>

      {preview && (
        <div className="image-preview mb-4">
          <img src={preview} alt="preview" />
        </div>
      )}

      <button className="btn btn-outline-secondary btn-sm">Upload Image</button>
    </div>
  </Form>
);

export default ImageUploader;
