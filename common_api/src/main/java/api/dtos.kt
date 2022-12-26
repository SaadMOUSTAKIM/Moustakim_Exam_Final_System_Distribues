package api

import java.time.Instant

data class CustomerDTO(
    var nom : String ="",
    var address : String ="",
    var mail :String ="",
    var tel : String ="",
);

data class ProductDTO(
    var productId: String="",
    var catID:String="",
    var nom : String ="",
    var prix : Double,
    var quntity :Int,
    var status: ProduitStatus = ProduitStatus.Production
);
data class CategoryDTO(
    var categorieID:String="",
    var nom : String ="",
    var description : String ="",
);



data class CustomerResponseDTO(
    var customerId : String ="",
    var nom : String ="",
    var address : String ="",
    var mail :String ="",
    var tel : String ="",
);

data class ProductResponseDTO(
    var productId: String="",
    var nom : String ="",
    var prix : Double,
    var quntity :Int,
    var status: ProduitStatus
);
data class CategoryResponseDTO(
    var categorieID:String="",
    var nom : String ="",
    var description : String ="",
);

data class EditCustomerDTO(
    var address : String ="",
    var mail :String ="",
    var tel : String ="",
);

data class DataResponseDTO<T>(
    var type : String="",
    var eventData : T ,
);
