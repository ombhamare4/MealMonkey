package com.rubiks.mealmonkey.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rubiks.mealmonkey.R
import com.rubiks.mealmonkey.models.foodmenu
import com.squareup.picasso.Picasso

class FoodMenuAdapter(val context: Context, val FoodList: ArrayList<foodmenu>):RecyclerView.Adapter<FoodMenuAdapter.FoodMenuViewHolder>() {

    class FoodMenuViewHolder(view: View):RecyclerView.ViewHolder(view){
        val txtFoodName: TextView = view.findViewById(R.id.txtFoodName)
        val txtPrice : TextView = view.findViewById(R.id.txtFoodPrice)
        val imgFood: ImageView = view.findViewById(R.id.imgFood)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_foodmenu,parent,false)
        return FoodMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodMenuViewHolder, position: Int) {
        val txtfoodlist = FoodList[position]
        holder.txtFoodName.text = txtfoodlist.name
        holder.txtPrice.text = txtfoodlist.price
        Picasso.get().load(txtfoodlist.img).error(R.drawable.burger).into(holder.imgFood)
    }

    override fun getItemCount(): Int {
        return FoodList.size
    }
}