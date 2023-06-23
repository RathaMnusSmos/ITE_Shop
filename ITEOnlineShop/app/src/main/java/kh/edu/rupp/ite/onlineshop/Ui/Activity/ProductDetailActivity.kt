package kh.edu.rupp.ite.onlineshop.Ui.Activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.onlineshop.Model.SingleProduct
import kh.edu.rupp.ite.onlineshop.R
import kh.edu.rupp.ite.onlineshop.Ui.Fragment.ProductFragment
import kh.edu.rupp.ite.onlineshop.Util.Adapter.RatingAdapter
import kh.edu.rupp.ite.onlineshop.databinding.ActivityProductDetailBinding
import kotlin.math.roundToInt

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProductDetailBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.extras != null){
            val product: SingleProduct? = intent.getParcelableExtra(ProductFragment.PRODUCT_KEY.toString())
            Picasso.get().load(product!!.img_url.toString()).into(binding.imgProductDetail)
            //rating
            val rating = product.rating.roundToInt()
            val layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.HORIZONTAL,false)
            val recycler = binding.ratingRecycler
            val adapter = RatingAdapter(baseContext, rating)
            recycler.layoutManager = layoutManager
            recycler.adapter = adapter
            binding.price.text = "$${product.price}"
            binding.description.text = product.description
            binding.priceProduct.text = "$${product.price}"
            binding.backBtn.setOnClickListener{
                finish()
            }
        }
    }
}