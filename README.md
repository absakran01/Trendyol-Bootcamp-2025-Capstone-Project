

Table Names Prefix: abdulmajeed_alsakran_

use cases done
✅ List Products
✅ Add Products
✅ Search Products
✅ Manage Cart
✅ Checkout

How to Run
1   Set up PostgreSQL:

    CREATE DATABASE "ShoppingCartDB";

2    Clone the repository:

    git clone https://github.com/absakran01/Trendyol-Bootcamp-2025-Capstone-Project.git
3    Build & Run:

    mvn spring-boot:run


------------>API Endpoints<------------
⚠️ Important Notes
Always run /init before using the cart endpoints.

 Products
Method	Endpoint	Description
GET	/listproducts	List all products with formatted output.
POST	/addproduct	Add a single product. JSON body required.
POST	/addproducts	Add multiple products at once. JSON array body.
GET	/searchproducts?keyword={keyword}	Search products by name/description keyword.
GET	/searchproduct?id={id}	Get a single product by ID (formatted).
GET	/showproduct?id={id}	Get a single product as JSON.

 Cart
Method	Endpoint	Description
GET	/init	Initialize the cart (must be called before using).
POST	/addtocart?id={id}&quantity={quantity}	Add a product to the cart.
POST	/removefromcart?id={id}	Remove a product from the cart.
GET	/showcart	Show all items currently in the cart.
GET	/emptycart	Empty the entire cart.
GET	/pay	Checkout and get the total bill (cart is cleared).

Sample Data
You can populate the database using:


POST /addproducts
Use the following JSON body:

json:
[
  {
    "id": null,
    "name": "Turkish Tea (Rize Çayı)",
    "description": "Traditional black tea from Rize region",
    "price": 25.0,
    "tax": 0.15,
    "quantity": 100
  },
  {
    "id": null,
    "name": "Simit",
    "description": "Crunchy sesame bread ring",
    "price": 5.0,
    "tax": 0.15,
    "quantity": 200
  },
  {
    "id": null,
    "name": "Lokum (Turkish Delight)",
    "description": "Rose-flavored Turkish Delight",
    "price": 15.0,
    "tax": 0.15,
    "quantity": 150
  },
  {
    "id": null,
    "name": "Ayran",
    "description": "Salted yogurt drink",
    "price": 3.5,
    "tax": 0.1,
    "quantity": 120
  },
  {
    "id": null,
    "name": "Sucuk",
    "description": "Dry spicy beef sausage",
    "price": 40.0,
    "tax": 0.15,
    "quantity": 80
  },
  {
    "id": null,
    "name": "Beyaz Peynir",
    "description": "Turkish white cheese, feta-style",
    "price": 30.0,
    "tax": 0.15,
    "quantity": 60
  },
  {
    "id": null,
    "name": "Zeytin",
    "description": "Black olives from Gemlik",
    "price": 20.0,
    "tax": 0.15,
    "quantity": 90
  },
  {
    "id": null,
    "name": "Baklava",
    "description": "Pistachio-layered sweet pastry",
    "price": 50.0,
    "tax": 0.15,
    "quantity": 70
  },
  {
    "id": null,
    "name": "İskender Kebab",
    "description": "Grilled döner with yogurt and tomato sauce",
    "price": 70.0,
    "tax": 0.15,
    "quantity": 40
  },
  {
    "id": null,
    "name": "Lahmacun",
    "description": "Thin spicy meat flatbread",
    "price": 10.0,
    "tax": 0.15,
    "quantity": 100
  },
  {
    "id": null,
    "name": "Tahin",
    "description": "Pure sesame paste",
    "price": 18.0,
    "tax": 0.15,
    "quantity": 75
  },
  {
    "id": null,
    "name": "Nar Ekşisi",
    "description": "Pomegranate molasses",
    "price": 22.0,
    "tax": 0.15,
    "quantity": 55
  },
  {
    "id": null,
    "name": "Menemen Set",
    "description": "Tomato-pepper-egg mix kit",
    "price": 12.0,
    "tax": 0.15,
    "quantity": 85
  },
  {
    "id": null,
    "name": "Künefe",
    "description": "Sweet cheese pastry with syrup",
    "price": 35.0,
    "tax": 0.15,
    "quantity": 30
  },
  {
    "id": null,
    "name": "Kaymak",
    "description": "Clotted cream made from buffalo milk",
    "price": 28.0,
    "tax": 0.15,
    "quantity": 50
  }
]



Taxes are capped at 0.15 (15%).

Use Postman or a similar tool to test endpoints.

Technologies
Spring Boot (REST API, embedded Tomcat)

JPA/Hibernate (database interaction)

PostgreSQL (database)

Maven (build tool)

Developer Notes
Developed using clean code principles and separation of concerns.

DAO pattern used for database operations.

All domain entities prefixed with abdulmajeed_alsakran_.

Easy to extend with more features like user authentication.


