import React, { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import { getProductById } from "../api/productApi";
import NotFoundPage from "./NotFoundPage";

const ProductDetailPage = () => {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await getProductById(id);
        setProduct(response.data);
      } catch (err) {
        setError(true);
      } finally {
        setLoading(false);
      }
    };
    fetchProduct();
  }, [id]);

  if (loading) return <div>Loading...</div>;
  if (error || !product) return <NotFoundPage />;

  return (
    <div>
      <nav>
        <Link to="/">Home</Link> <Link to="/products">Products</Link>{" "}
        {product.name}
      </nav>

      <h1>{product.name}</h1>

      <div>
        {product.images &&
          product.images.map((img) => (
            <img key={img} src={img} alt={product.name} />
          ))}
      </div>

      <p>{product.fullDescription}</p>
      <p>Price: ${product.price}</p>

      <h3>Technical Specifications</h3>
      <table>
        <tbody>
          {Object.entries(product.technicalSpecifications).map(
            ([key, value]) => (
              <tr key={key}>
                <td>
                  <strong>{key}</strong>
                </td>
                <td>{value}</td>
              </tr>
            )
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ProductDetailPage;
