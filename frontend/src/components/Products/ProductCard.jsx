import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useCart } from "../../context/CartContext";

const ProductCard = ({ product }) => {
  const [quantity, setQuantity] = useState(1);
  const { addToCart } = useCart();

  if (!product) {
    return null;
  }

  const handleAddToCart = () => {
    addToCart(product.id, quantity);
    alert(`${quantity} x ${product.name} added to cart!`);
  };

  const handleQuantityChange = (e) => {
    const value = parseInt(e.target.value, 10);
    if (value > 0) {
      setQuantity(value);
    }
  };

  // Use a placeholder if no images are available
  const imageUrl =
    product.images && product.images.length > 0
      ? product.images[0]
      : "https://via.placeholder.com/300x200?text=No+Image";

  return (
    <div className="product-card">
      <Link to={`/product/${product.id}`} className="product-card-link">
        <img
          src={imageUrl}
          alt={product.name || "Product Image"}
          className="product-card-image"
        />
      </Link>
      <div className="product-card-content">
        <h3 className="product-card-name">
          <Link to={`/product/${product.id}`}>
            {product.name || "Unnamed Product"}
          </Link>
        </h3>
        <p className="product-card-desc">{product.shortDescription || ""}</p>
        <p className="product-card-price">
          ${product.price?.toFixed(2) || "0.00"}
        </p>
        <div className="product-card-actions">
          <input
            type="number"
            min="1"
            value={quantity}
            onChange={handleQuantityChange}
            className="quantity-input"
          />
          <button onClick={handleAddToCart} className="btn btn-success">
            Add to Cart
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProductCard;
