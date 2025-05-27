import { ShoppingCart, User, Search } from 'lucide-react';

export default function Header() {
  return (
    <header className="bg-white shadow-md">
      <div className="max-w-7xl mx-auto px-4 py-3 flex items-center justify-between flex-wrap gap-4">
        
        {/* Logo */}
        <div className="text-2xl font-bold text-indigo-600">ShopLogo</div>

        {/* Search */}
        <div className="flex-1 max-w-md w-full">
          <div className="relative">
            <input
              type="text"
              placeholder="Search products..."
              className="w-full border border-gray-300 rounded-lg px-4 py-2 pl-10 focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
            <Search className="absolute top-2.5 left-3 h-5 w-5 text-gray-400" />
          </div>
        </div>

        {/* Icons */}
        <div className="flex items-center gap-4">
          <button className="text-gray-600 hover:text-indigo-600 transition">
            <ShoppingCart className="w-6 h-6" />
          </button>
          <button className="text-gray-600 hover:text-indigo-600 transition">
            <User className="w-6 h-6" />
          </button>
        </div>
      </div>
    </header>
  );
}
