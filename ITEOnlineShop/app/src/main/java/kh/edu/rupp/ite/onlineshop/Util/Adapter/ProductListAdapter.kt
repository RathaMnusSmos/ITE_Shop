package kh.edu.rupp.ite.onlineshop.Util.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.onlineshop.Model.PojoData.ProductModel
import kh.edu.rupp.ite.onlineshop.R
import kh.edu.rupp.ite.onlineshop.Util.Listener.OnItemClickedListener
import java.util.LinkedList

class ProductListAdapter(
    var context: Context?,
    var listItem:LinkedList<ProductModel>,
    var listener:OnItemClickedListener): Adapter<ProductListAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.productName.setText(listItem[position].name)
        holder.productPrice.setText("$${listItem[position].price.toString()}")
        Picasso.get().load(listItem[position].image_url).into(holder.productImage)

        holder.itemView.setOnClickListener{
            listener.onProductClick(position, listItem[position])
        }
    }

    override fun getItemCount(): Int {
       return listItem.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun filterList(list: LinkedList<ProductModel>){
        listItem = list
        notifyDataSetChanged()
    }




         class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
             var productImage: ImageView = itemView.findViewById(R.id.product_image)
             var productName: TextView = itemView.findViewById(R.id.product_name)
             var productPrice: TextView = itemView.findViewById(R.id.product_price)

         }





}