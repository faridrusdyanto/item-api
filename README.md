# Note
### Create database MySql "itemapi"

### Services Item
  - (GET) -> getAlldata -> http://localhost:8080/api/item
  - (GET) -> getById -> http://localhost:8080/api/item/{id}
  - (POST) -> create -> http://localhost:8080/api/item
  - (PUT) -> update -> http://localhost:8080/api/item
  - (DELETE) -> delete -> http://localhost:8080/api/item/{id}
  
### Services Cart
  - (POST) addToCart -> http://localhost:8080/api/cart/addOne
  - (GET) finalize -> http://localhost:8080/api/cart/finalize
