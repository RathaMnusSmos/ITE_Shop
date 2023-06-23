package kh.edu.rupp.ite.onlineshop.Ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.onlineshop.Model.PojoData.ProductModel
import kh.edu.rupp.ite.onlineshop.Model.SingleProduct
import kh.edu.rupp.ite.onlineshop.R
import kh.edu.rupp.ite.onlineshop.Ui.Activity.ProductDetailActivity
import kh.edu.rupp.ite.onlineshop.Util.API.RetrofitClient
import kh.edu.rupp.ite.onlineshop.Util.API.RetrofitService
import kh.edu.rupp.ite.onlineshop.Util.Adapter.ProductListAdapter
import kh.edu.rupp.ite.onlineshop.Util.Listener.OnItemClickedListener
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProductBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ProductFragment : Fragment(), OnItemClickedListener {
    private var productData: LinkedList<ProductModel> = LinkedList()
    private lateinit var fragmentBinding: FragmentProductBinding
    private lateinit var productRecycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentProductBinding.bind(view)
        fragmentBinding = binding
        //show product list
        productRecycler = binding.productRecycler
        getProductList()


    }
    private fun getProductList(){
        val retrofit = RetrofitService.getRetrofit().create(RetrofitClient::class.java)
        retrofit.getProduct().enqueue(object: Callback<LinkedList<ProductModel>>{

            override fun onResponse(
                call: Call<LinkedList<ProductModel>>,
                response: Response<LinkedList<ProductModel>>
            ) {
               if (response.isSuccessful){
                   fragmentBinding.loadingBar.visibility = View.GONE
                   productData = response.body()!!
                   val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                   productRecycler.layoutManager = layoutManager
                   val adapterProductList = context?.let { ProductListAdapter(it, productData, this@ProductFragment) }
                   productRecycler.adapter = adapterProductList
                   fragmentBinding.searchEdt.addTextChangedListener(object :TextWatcher{
                       override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                       }

                       override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                       }

                       override fun afterTextChanged(p0: Editable?) {
                           val filterList: LinkedList<ProductModel> = LinkedList()
                           for(item in productData){
                               if(item.name?.lowercase(Locale.getDefault())!!.contains(p0.toString().lowercase(Locale.getDefault()))){
                                   filterList.add(item)
                               }
                           }
                           adapterProductList!!.filterList(filterList)
                       }
                   })
               }
                else{
                    Log.d("error", "Fail in service = ${response.message()}")
               }
            }

            override fun onFailure(call: Call<LinkedList<ProductModel>>, t: Throwable) {
               Log.d("error", "Fail in device = ${t.message}")
            }

        })
    }
    override fun onProductClick(pos: Int, data: ProductModel) {
        Toast.makeText(context, "${data.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this.context, ProductDetailActivity::class.java)
        val product = SingleProduct(data.id.toString(),data.sku.toString(),data.name.toString(),data.description.toString(),data.price!!.toInt(),data.image_url.toString(),data.rating!!.toDouble())

        val bundle = Bundle()
        bundle.putParcelable(PRODUCT_KEY, product)
        intent.putExtras(bundle)
        startActivity(intent)
    }
    companion object{
        const val PRODUCT_KEY = "product"
    }

}