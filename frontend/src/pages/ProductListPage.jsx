import React, { useState, useEffect, useMemo } from "react";
import { getProducts } from "../api/productApi";
import ProductCard from "../components/Products/ProductCard";
import Pagination from "../components/Common/Pagination";
import "./styles/plp.css";

const ProductListPage = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [currentPage, setCurrentPage] = useState(0);
  const [itemsPerPage, setItemsPerPage] = useState(10);
  const [totalPages, setTotalPages] = useState(0);
  const [searchTerm, setSearchTerm] = useState("");
  const [sortOrder, setSortOrder] = useState("");

  useEffect(() => {
    const fetchProducts = async () => {
      setLoading(true);
      try {
        const response = await getProducts(currentPage, itemsPerPage);
        const data = response.data;
        setProducts(data.content);
        setTotalPages(data.totalPages);
        setLoading(false);
      } catch (err) {
        setError("Failed to fetch products.");
        setLoading(false);
      }
    };
    fetchProducts();
  }, [currentPage, itemsPerPage]);

  const processedProducts = useMemo(() => {
    let filtered = products.filter((p) =>
      p.name.toLowerCase().includes(searchTerm.toLowerCase())
    );

    if (sortOrder) {
      filtered.sort((a, b) => {
        const [key, order] = sortOrder.split("-");
        if (key === "price") {
          return order === "asc" ? a.price - b.price : b.price - a.price;
        }
        if (key === "name") {
          return order === "asc"
            ? a.name.localeCompare(b.name)
            : b.name.localeCompare(a.name);
        }
        return 0;
      });
    }
    return filtered;
  }, [products, searchTerm, sortOrder]);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div>
      <h1>Our Products</h1>

      <input
        type="text"
        placeholder="Search products..."
        onChange={(e) => setSearchTerm(e.target.value)}
      />

      <select onChange={(e) => setSortOrder(e.target.value)}>
        <option value="">Sort By</option>
        <option value="price-asc">Price: Low to High</option>
        <option value="price-desc">Price: High to Low</option>
        <option value="name-asc">Name: A to Z</option>
        <option value="name-desc">Name: Z to A</option>
      </select>

      <div className="product-list">
        {processedProducts.length > 0 ? (
          processedProducts.map((product) => (
            <ProductCard key={product.id} product={product} />
          ))
        ) : (
          <p>No products found.</p>
        )}
      </div>

      <Pagination
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={setCurrentPage}
      />
    </div>
  );
};

export default ProductListPage;
