package com.rubiks.mealmonkey.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rubiks.mealmonkey.R
import com.rubiks.mealmonkey.activity.FoodListActivity
import com.rubiks.mealmonkey.models.foodtype
import com.squareup.picasso.Picasso


class HomeHorizontalAdapter(val context: Context, val HorizontalItemList:ArrayList<foodtype>): RecyclerView.Adapter<HomeHorizontalAdapter.HomeHorizontalViewHolder>() {

    class HomeHorizontalViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.txttypefood)
        val imgfoodtype: ImageView = view.findViewById(R.id.imgfoodtype1)
        val llcontent : RelativeLayout = view.findViewById(R.id.llcontent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHorizontalViewHolder {
     val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_horizontal_top,parent,false)

        return HomeHorizontalViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeHorizontalViewHolder, position: Int) {
        val text = HorizontalItemList[position]
        holder.textView.text = text.name
        Picasso.get().load(text.img).error(R.drawable.burger)
            .into(holder.imgfoodtype)

        holder.llcontent.setOnClickListener{
            Toast.makeText(context,"Here is ${holder.textView.text}",Toast.LENGTH_SHORT).show()
            val intent = Intent(context,FoodListActivity::class.java)
            intent.putExtra("foodtypeID",text.id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return HorizontalItemList.size
    }

}