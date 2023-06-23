package kh.edu.rupp.ite.onlineshop.Util.API

import com.example.example.UserProfileModel
import kh.edu.rupp.ite.onlineshop.Model.PojoData.ProductModel
import retrofit2.Call
import retrofit2.http.GET
import java.util.LinkedList

interface RetrofitClient {

    @GET("kimsongsao/ferupp/main/products.json ")
    fun getProduct() : Call<LinkedList<ProductModel>>
    @GET("kimsongsao/ferupp/main/profile.json ")
    fun getUserProfile(): Call<UserProfileModel>
}