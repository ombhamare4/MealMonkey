package com.rubiks.mealmonkey.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rubiks.mealmonkey.R
import com.rubiks.mealmonkey.models.popularRestaurant
import com.squareup.picasso.Picasso
import org.w3c.dom.Text


class   HomePopularRestaurantAdapter(val context: Context, val PopularRestaurantList: ArrayList<popularRestaurant>): RecyclerView.Adapter<HomePopularRestaurantAdapter.PopularRestaurantViewHolder>() {

    class PopularRestaurantViewHolder(view: View):RecyclerView.ViewHolder(view){

        val txtRestaurantName:TextView = view.findViewById(R.id.txtpopularRestaurantName)
        val imgRestaurantImage: ImageView = view.findViewById(R.id.imgRestaurantImage)
        val txtRating : TextView = view.findViewById(R.id.txtRating)
        val txtTotalRating: TextView = view.findViewById(R.id.txtTotalRating)
        val txtRestaurantType: TextView = view.findViewById(R.id.txtRestaurantType)
        val txtRestaurantFoodType: TextView = view.findViewById(R.id.txtRestaurantFoodType)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularRestaurantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyler_popular_restaurant,parent,false)
        return PopularRestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularRestaurantViewHolder, position: Int) {
        val textRes = PopularRestaurantList[position]
        holder.txtRestaurantName.text = textRes.name
        holder.txtRating.text = textRes.rating
        holder.txtTotalRating.text= textRes.total_rating
        holder.txtRestaurantType.text = textRes.restaurant_type
        holder.txtRestaurantFoodType.text = textRes.food_type
        Picasso.get().load(textRes.img).error(R.drawable.burger)
            .into(holder.imgRestaurantImage)


    }

    override fun getItemCount(): Int {
        return PopularRestaurantList.size
    }
}