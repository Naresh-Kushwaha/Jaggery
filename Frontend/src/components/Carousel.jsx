import { useState } from "react";

const images = [
  "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTG74BEag3ody_-LYTsqljYLZk0VBeNp8_UVw&s",
  "https://www.organicgyaan.com/cdn/shop/files/Jaggery_62b07bbe-8115-4bcb-bbed-66ffb747a573.jpg?v=1721400084&width=1214",
  "https://m.media-amazon.com/images/I/71V7El3oAjS.jpg",
];

export default function Carousel() {
  const [index, setIndex] = useState(0);

  const nextSlide = () => setIndex((index + 1) % images.length);
  const prevSlide = () => setIndex((index - 1 + images.length) % images.length);

  return (
    <div className="relative w-full h-64 overflow-hidden rounded-lg shadow-md">
      <img
        src={images[index]}
        alt="carousel"
        className="w-full h-full object-cover duration-500 transition-all"
      />

      {/* Buttons */}
      <button
        onClick={prevSlide}
        className="absolute top-1/2 left-4 transform -translate-y-1/2 text-white bg-black/50 p-2 rounded-full"
      >
        ‹
      </button>
      <button
        onClick={nextSlide}
        className="absolute top-1/2 right-4 transform -translate-y-1/2 text-white bg-black/50 p-2 rounded-full"
      >
        ›
      </button>
    </div>
  );
}
