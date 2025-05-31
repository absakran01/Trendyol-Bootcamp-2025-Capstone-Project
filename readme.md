📦 Shopping Cart REST API
This project implements a shopping cart system using Spring Boot, PostgreSQL, and JPA, following the DAO pattern. It allows users to browse products, manage a shopping cart, and checkout.

🗃️ Database
Database: PostgreSQL

Schema Name: ShoppingCartDB

Username: postgres

Password: (empty)

JPA Setting: spring.jpa.hibernate.ddl-auto=create (tables created on app startup)

Table Names Prefix: abdulmajeed_alsakran_

🛠️ Features
✅ List Products
✅ Add Products
✅ Search Products
✅ Manage Cart
✅ Checkout

🚀 How to Run
1️⃣ Set up PostgreSQL:

bash
Copy
Edit
CREATE DATABASE "ShoppingCartDB";
2️⃣ Clone the repository:

bash
Copy
Edit
git clone https://github.com/yourusername/yourrepository.git
cd yourrepository
3️⃣ Build & Run:

bash
Copy
Edit
mvn spring-boot:run
🔗 API Endpoints
🛍️ Products
Method	Endpoint	Description
GET	/listproducts	List all products with formatted output.
POST	/addproduct	Add a single product. JSON body required.
POST	/addproducts	Add multiple products at once. JSON array body.
GET	/searchproducts?keyword={keyword}	Search products by name/description keyword.
GET	/searchproduct?id={id}	Get a single product by ID (formatted).
GET	/showproduct?id={id}	Get a single product as JSON.

🛒 Cart
Method	Endpoint	Description
GET	/init	Initialize the cart (must be called before using).
POST	/addtocart?id={id}&quantity={quantity}	Add a product to the cart.
POST	/removefromcart?id={id}	Remove a product from the cart.
GET	/showcart	Show all items currently in the cart.
GET	/emptycart	Empty the entire cart.
GET	/pay	Checkout and get the total bill (cart is cleared).

📦 Sample Data
You can populate the database using:

bash
Copy
Edit
POST /addproducts
Use the following JSON body:

json
Copy
Edit
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
  }
  // ... more products ...
]
⚠️ Important Notes
Always run /init before using the cart endpoints.

Taxes are capped at 0.15 (15%).

Use Postman or a similar tool to test endpoints.

🔧 Technologies
Spring Boot (REST API, embedded Tomcat)

JPA/Hibernate (database interaction)

PostgreSQL (database)

Maven (build tool)

👨‍💻 Developer Notes
Developed using clean code principles and separation of concerns.

DAO pattern used for database operations.

All domain entities prefixed with abdulmajeed_alsakran_.

Easy to extend with more features like user authentication.

📌 Quick Tips
If the app fails to connect to the database, make sure PostgreSQL is running and that the ShoppingCartDB database exists.

Adjust application.properties if your database runs on a non-default port.

📝 License
This project is for educational purposes.