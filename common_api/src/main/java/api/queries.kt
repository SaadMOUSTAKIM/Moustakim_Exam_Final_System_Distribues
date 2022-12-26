package api

class GetAllCustomersQuery();
data class GetCustomerById(
    val customerId:String,
);

class GetAllProductsQuery();
data class GetProductById(
    val customerId:String,
);

class SubscribeToEventsQuery();