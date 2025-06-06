import { useEffect, useState } from "react";

import ProductCard from "../components/ProductCard";
import SearchSortBar from "../components/SearchSortBar";
import FilterSidebar from "../components/FilterSidebar";
import { Link, useParams } from "react-router-dom";
import Categories from "../assets/categories"; // Assuming you have a data file for products
import Products from "../assets/products"; // Assuming you have a data file for products



export default function ProductListingPage() {
    const { id } = useParams();
  

  const [searchTerm, setSearchTerm] = useState("");
  const [sort, setSort] = useState("default");
  const [selectedCategory, setSelectedCategory] = useState("All");



  const filteredProducts = Products
    .filter((p) =>
      selectedCategory === 0 ? true : p.categoryId === selectedCategory
    )
    .filter((p) => p.name.toLowerCase().includes(searchTerm.toLowerCase()))
    .sort((a, b) => {
      if (sort === "price_low") return a.price - b.price;
      if (sort === "price_high") return b.price - a.price;
      return 0;
    });


  return (
    <>
      <div className="px-6 py-12 bg-[#FBEAEB]">
        <h1 className="text-2xl font-bold mb-6 text-yellow-800 text-center">
          All Jaggery Products
        </h1>

        <SearchSortBar
          searchTerm={searchTerm}
          setSearchTerm={setSearchTerm}
          sort={sort}
          setSort={setSort}
        />

        <div className="flex flex-col md:flex-row gap-6">
          <aside className="md:w-1/4">
            <FilterSidebar
              categories={Categories}
              selectedCategory={selectedCategory}
              setSelectedCategory={setSelectedCategory}
              categoryId={id}
            />
          </aside>

          <main className="md:w-3/4">
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
              {filteredProducts.length > 0 ? (
                filteredProducts.map((p) => (
                 
                    <ProductCard  product={p} key={p.id}/>
                 
                ))
              ) : (
                <p>No products found</p>
              )}
            </div>
          </main>
        </div>
      </div>
    </>
  );
}

ProductListingPage.propTypes = {};
