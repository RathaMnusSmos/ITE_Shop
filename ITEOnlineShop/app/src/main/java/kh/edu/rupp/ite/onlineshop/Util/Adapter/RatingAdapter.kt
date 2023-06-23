package kh.edu.rupp.ite.onlineshop.Util.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.onlineshop.R
import java.util.LinkedList

class RatingAdapter(var context: Context, var listItem:Int):Adapter<RatingAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rating_layout,parent,false)
        return MyViewHolder(view)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.img_star.setImageResource(R.drawable.ic_star)
    }

    override fun getItemCount(): Int {
        return listItem
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var img_star: ImageView = itemView.findViewById(R.id.star_img)
    }



}