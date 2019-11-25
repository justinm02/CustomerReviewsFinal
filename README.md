# CustomerReviewsFinal
Continuation of previous CustomerReviews project for the Udacity midterm, supporting Polyglot persistence with the storing of Review data in both MySQL and MongoDB

## Installation
Download the project via github and open it using an IDE such as IntelliJ

## Usage
Run the project using Maven and use a tool like postman or the localhost on a web browser to compute the following commands:

## Products
Create product (POST) -> http://localhost:8080/products/
```json
{
	"productName": "Thermos"
}
```

Find product by id (GET) -> http://localhost:8080/products/{productId}

Retrieve list of products (GET) -> http://localhost:8080/products/

## Reviews
Create review (POST) -> http://localhost:8080/reviews/products/{productId}
```json
{
	"rate": 5
}
```

Retrieve list of reviews (GET) -> https://localhost:8080/reviews/products/{productId}

## Comments
Create comment (POST) -> http://localhost:8080/comments/reviews/{reviewId}
```json
{
	"commentContent": "Thermos always keeps my lunch warm throughout the day!"
}
```

Retrieve list of comments (GET) -> http://localhost:8080/comments/reviews/{reviewId}
