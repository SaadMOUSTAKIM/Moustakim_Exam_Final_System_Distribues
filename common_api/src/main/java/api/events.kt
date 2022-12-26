package api

abstract class BaseEvent<T> (
    open val id: T
);
data class CustomerCreatedEvent(
    override val id : String,
    val payload:CustomerDTO
):BaseCommand<String>(id);

data class CustomerEditedEvent(
    override val id : String,
    val payload:EditCustomerDTO
):BaseCommand<String>(id);

data class ProductCreatedEvent(
    override val id : String,
    val payload:ProductDTO
):BaseCommand<String>(id);
data class CategoryCreatedEvent(
    override val id : String,
    val payload:CategoryDTO
):BaseCommand<String>(id);