export default function Categories({name,image}) {


  return (
    // <section className="bg-yellow-50 py-12 px-6 ">
    //    <h2 className="text-2xl font-bold mb-6 text-yellow-800 text-center">
    //     Shop by Category
    //   </h2> 
    //    <div className="grid  grid-cols-1 md:grid-cols-4 gap-6"> 

          <div
        
            className="border rounded-lg overflow-hidden hover:shadow-lg cursor-pointer"
          >
            <img
              src={image}
              alt={name}
              className="h-auto w-full  object-cover"
            />
            <div className="text-center py-2 font-semibold">{name}</div>
          </div>
        
      // </div>
    // </section>
  );
}
