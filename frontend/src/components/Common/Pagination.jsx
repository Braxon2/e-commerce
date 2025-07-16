import React from "react";

const Pagination = ({ currentPage, totalPages, onPageChange }) => {
  // Don't render pagination if there's only one page or none
  if (totalPages <= 1) {
    return null;
  }

  const handlePageClick = (page) => {
    // The backend uses 0-indexed pages, so we subtract 1
    onPageChange(page - 1);
  };

  const renderPageNumbers = () => {
    const pageNumbers = [];
    const pageLimit = 5; // Max page numbers to show
    const ellipsis = (
      <span key="ellipsis" className="pagination-ellipsis">
        ...
      </span>
    );

    // The current page from the parent is 0-indexed, so we add 1 for display
    const displayCurrentPage = currentPage + 1;

    if (totalPages <= pageLimit + 2) {
      // If total pages is small, show all numbers
      for (let i = 1; i <= totalPages; i++) {
        pageNumbers.push(
          <button
            key={i}
            onClick={() => handlePageClick(i)}
            className={`pagination-btn ${
              displayCurrentPage === i ? "active" : ""
            }`}
          >
            {i}
          </button>
        );
      }
    } else {
      // Logic for handling many pages with ellipses
      pageNumbers.push(
        <button
          key={1}
          onClick={() => handlePageClick(1)}
          className={`pagination-btn ${
            displayCurrentPage === 1 ? "active" : ""
          }`}
        >
          1
        </button>
      );

      if (displayCurrentPage > 3) {
        pageNumbers.push(ellipsis);
      }

      let startPage = Math.max(2, displayCurrentPage - 1);
      let endPage = Math.min(totalPages - 1, displayCurrentPage + 1);

      if (displayCurrentPage <= 3) {
        startPage = 2;
        endPage = 4;
      }
      if (displayCurrentPage >= totalPages - 2) {
        startPage = totalPages - 3;
        endPage = totalPages - 1;
      }

      for (let i = startPage; i <= endPage; i++) {
        pageNumbers.push(
          <button
            key={i}
            onClick={() => handlePageClick(i)}
            className={`pagination-btn ${
              displayCurrentPage === i ? "active" : ""
            }`}
          >
            {i}
          </button>
        );
      }

      if (displayCurrentPage < totalPages - 2) {
        pageNumbers.push(ellipsis);
      }

      pageNumbers.push(
        <button
          key={totalPages}
          onClick={() => handlePageClick(totalPages)}
          className={`pagination-btn ${
            displayCurrentPage === totalPages ? "active" : ""
          }`}
        >
          {totalPages}
        </button>
      );
    }

    return pageNumbers;
  };

  return (
    <nav className="pagination">
      <button
        onClick={() => onPageChange(currentPage - 1)}
        disabled={currentPage === 0}
        className="pagination-btn nav-btn"
      >
        Previous
      </button>

      {renderPageNumbers()}

      <button
        onClick={() => onPageChange(currentPage + 1)}
        disabled={currentPage >= totalPages - 1}
        className="pagination-btn nav-btn"
      >
        Next
      </button>
    </nav>
  );
};

export default Pagination;
