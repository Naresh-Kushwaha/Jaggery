import Header from "../components/Header";

import Carousel from "../components/Carousel";
import ProductCard from "../components/ProductCard";
export default function HomePage() {
  return (
    <div className="bg-gray-100 min-h-screen">
      <Header />
      <main className="max-w-7xl mx-auto p-4">
        <Carousel />

        {/* Example content below the carousel */}
        <section className="mt-10">
          <h2 className="text-xl font-bold mb-4">Featured Products</h2>
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
            {/* You can map product cards here */}
            <div className="bg-white p-4 rounded-lg shadow"><ProductCard></ProductCard></div>
            <div className="bg-white p-4 rounded-lg shadow"><ProductCard></ProductCard></div>
            <div className="bg-white p-4 rounded-lg shadow"><ProductCard></ProductCard></div>
          </div>
        </section>
      </main>
    </div>
  );
}
