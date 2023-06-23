package kh.edu.rupp.ite.onlineshop.Model.PojoData

import com.google.gson.annotations.SerializedName


data class ProductModel (

  @SerializedName("id"          ) var id          : String? = null,
  @SerializedName("sku"         ) var sku         : String? = null,
  @SerializedName("name"        ) var name        : String? = null,
  @SerializedName("description" ) var description : String? = null,
  @SerializedName("price"       ) var price       : Int?    = null,
  @SerializedName("image-url"   ) var image_url   : String? = null,
  @SerializedName("rating"      ) var rating      : Double? = null

)