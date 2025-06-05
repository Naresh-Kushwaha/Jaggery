import { useEffect, useState } from "react";

import ProductCard from "../components/ProductCard";
import SearchSortBar from "../components/SearchSortBar";
import FilterSidebar from "../components/FilterSidebar";
import { Link, useParams } from "react-router-dom";
const categories = [
   {id:1,
    name: "Jaggery Powder",
    "description": "Fine powdered jaggery ideal for tea, coffee, or cooking. Easily dissolves and adds a rich sweetness."
  },
  {id:2,
    name: "Jaggery Blocks",
    description: "Traditional jaggery blocks made from sugarcane juice, used in Indian sweets and daily cooking."
  },
  {id:3,
    name: "Flavored Jaggery",
    description: "Jaggery infused with natural flavors like ginger, cardamom, or turmeric for added health benefits and taste."
  },
  {id:4,
    name: "Liquid Jaggery",
    "description": "Thick syrupy jaggery perfect for drizzling on pancakes, parathas, or using in desserts."
  },
  {id:5,
    name: "Palm Jaggery",
    "description": "Jaggery made from palm tree sap, known for its darker color and slightly earthy flavor."
  },
  {id:6,
    name: "Organic Jaggery",
    description: "Pure and chemical-free jaggery produced using organic farming practices for health-conscious consumers."
  },
  {id:7,
    "name": "Granular Jaggery",
    "description": "Small grain-like jaggery pieces that are easy to use and store, ideal for snacks and baking."
  },
  {id:8,
    "name": "Herbal Jaggery",
    "description": "Infused with ayurvedic herbs like tulsi, ashwagandha, and mulethi to boost immunity and digestion."
  },
  {id:9,
    name: "Date Palm Jaggery",
    description: "Special winter jaggery made from date palm sap, popular in Eastern India for its unique taste."
  }
];
const products =[
  {
    id: 1,
    categoryId:5,
    name: "Palm Jaggery ",
    price: "150",
    image: "https://spicecliq.com/wp-content/uploads/2021/07/palm-jaggery.jpg",
    description: "Organic palm jaggery made from natural palm sap. Rich in minerals and iron.",
  ingredients: "Palm sap, no additives",
  stock: 15
  },
  {
    id: 2,
    categoryId:4,
    name: "Liquid Jaggery",
    price: "120",
    image: "https://nuttyyogi.com/cdn/shop/products/LiquidJaggery_DSC8312.jpg?v=1680766195",
    description: "Organic palm jaggery made from natural palm sap. Rich in minerals and iron.",
  ingredients: "Palm sap, no additives",
  stock: 15
  },
  {
    id: 3,
    categoryId:6,
    name: "Organic Jaggery ",
    price: "99",
    image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSak-JC9JllMu2jufy_MCA2Gksl4CxCDNIkqA&s",
    description: "Organic palm jaggery made from natural palm sap. Rich in minerals and iron.",
  ingredients: "Palm sap, no additives",
  stock: 15
  },
  {
    id: 4,
    categoryId:2,
    name: " Jaggery Blocks",
    price: "110",
    image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6MhsjlRTOwgnU3Ay6kZi3deUwnsZk6wKt8g&s",
    description: "Organic palm jaggery made from natural palm sap. Rich in minerals and iron.",
  ingredients: "Palm sap, no additives",
  stock: 15
  },
];


export default function ProductListingPage() {
    const { id } = useParams();
  

  const [searchTerm, setSearchTerm] = useState("");
  const [sort, setSort] = useState("default");
  const [selectedCategory, setSelectedCategory] = useState("All");



  const filteredProducts = products
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
              categories={categories}
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
