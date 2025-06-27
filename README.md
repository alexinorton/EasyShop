### EasyShop E-Commerce API & Site

A Java Spring Boot application providing a RESTful e-commerce back end with a MySQL database and simple web UI.   
This project was created as part of my final capstone at Year Up. It lets users browse and manage product categories, view and search products, and supports secured admin operations for adding, updating, and deleting data.

---

## Features

## - Category management  
  - List all categories  
  - View a single category by ID  
  - Create, update, and delete (admin only)  

## - Product catalog  
  - List products by category  
  - View product details by ID  
  - Search products by name or vendor  

## - REST API endpoints  
  - `GET /categories` → all categories  
  - `GET /categories/{id}` → one category  
  - `GET /categories/{id}/products` → products in category  
  - `GET /products/{id}` → one product  
  - `POST`, `PUT`, `DELETE` (admin only) on both `/categories` and `/products`  

## - Security
  - JWT-based authentication  
  - Method-level `@PreAuthorize` checks  
  - `@CrossOrigin` enabled for front-end access  

## - Database integration  
  - MySQL via Spring JDBC (`JdbcTemplate`)  
  - `CategoryDao` / `ProductDao` interfaces + `@Repository` implementations  
  - Pipe-delimited SQL queries for category & product tables  

---

### 🖼️ Screenshots

Category List  


Product Details  


---

💻 Interesting Code Snippet

I personally found the category-to-product listing endpoint clean and concise:

```java
@GetMapping("{categoryId}/products")
@PreAuthorize("permitAll()")
public List<Product> getProductsByCategory(@PathVariable int categoryId) {
    return productDao.listByCategoryId(categoryId);
}
