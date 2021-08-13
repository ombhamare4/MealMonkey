package com.rubiks.mealmonkey.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.rubiks.mealmonkey.R
import com.rubiks.mealmonkey.adapter.HomeHorizontalAdapter
import com.rubiks.mealmonkey.adapter.HomePopularRestaurantAdapter
import com.rubiks.mealmonkey.models.foodtype
import com.rubiks.mealmonkey.models.popularRestaurant
import com.rubiks.mealmonkey.util.ConnectionManager
import org.json.JSONException

class HomeFragment : Fragment() {

    lateinit var horizontalRecylerAdapter: HomeHorizontalAdapter
    lateinit var HomePopularRestaurantAdapter: HomePopularRestaurantAdapter
    lateinit var firstRecyclerView: RecyclerView
    lateinit var flayoutManger: RecyclerView.LayoutManager
    lateinit var PopularRestaurantRecylerView: RecyclerView
    lateinit var PopularRestaurantlayoutManger: RecyclerView.LayoutManager
    lateinit var MostPopularPopularRestaurantRecylerView: RecyclerView
    lateinit var MostPopularRestaurantlayoutManger: RecyclerView.LayoutManager

    lateinit var progressBar: ProgressBar
    lateinit var progressLayout: RelativeLayout


    val horizontalList = arrayListOf<foodtype>()
    val popularRestaurantList = arrayListOf<popularRestaurant>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        firstRecyclerView = view.findViewById(R.id.firstRecylerView)
        PopularRestaurantRecylerView = view.findViewById(R.id.PopularRestaurantRecylerView)
        MostPopularPopularRestaurantRecylerView =
            view.findViewById(R.id.mostPopularRestaurantRecyclerView)

        progressBar = view.findViewById(R.id.progressBar)
        progressLayout = view.findViewById(R.id.progressLayout)

        progressLayout.visibility = View.VISIBLE


        val queue = Volley.newRequestQueue(activity as Context)
        val url = " https://api.npoint.io/ee1e861f6b8bd76bacd2"

        if (ConnectionManager().checkConnectivity(activity as Context)) {

            val jsonObjectRequets =
                object : JsonObjectRequest(
                    Request.Method.GET, url, null, Response.Listener {
                        println("Response is $it")
                        try {
                            progressLayout.visibility = View.GONE
                            val dataJsonObject = it.getJSONObject("data")
                            val success = dataJsonObject.getBoolean("success")
                            if (success) {
                                val foodtypeDataJsonArray =
                                    dataJsonObject.getJSONArray("foodtypelist")
                                for (i in 0 until foodtypeDataJsonArray.length()) {
                                    val foodtypeJsonObject = foodtypeDataJsonArray.getJSONObject(i)
                                    val foodObject = foodtype(
                                        foodtypeJsonObject.getString("id"),
                                        foodtypeJsonObject.getString("img"),
                                        foodtypeJsonObject.getString("name")
                                    )
                                    horizontalList.add(foodObject)
                                }
                                val popularRestaurantDataJsonArray =
                                    dataJsonObject.getJSONArray("popularrestauarntlist")
                                for (i in 0 until popularRestaurantDataJsonArray.length()) {
                                    val popularRestaurantJsonObject =
                                        popularRestaurantDataJsonArray.getJSONObject(i)
                                    val popularRestaurantObject = popularRestaurant(
                                        popularRestaurantJsonObject.getString("id"),
                                        popularRestaurantJsonObject.getString("img"),
                                        popularRestaurantJsonObject.getString("name"),
                                        popularRestaurantJsonObject.getString("rating"),
                                        popularRestaurantJsonObject.getString("total_rating"),
                                        popularRestaurantJsonObject.getString("restaurant_type"),
                                        popularRestaurantJsonObject.getString("food_type"),
                                    )
                                    popularRestaurantList.add(popularRestaurantObject)
                                }
                                flayoutManger = LinearLayoutManager(
                                    activity,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                                horizontalRecylerAdapter =
                                    HomeHorizontalAdapter(activity as Context, horizontalList)
                                firstRecyclerView.adapter = horizontalRecylerAdapter
                                firstRecyclerView.layoutManager = flayoutManger

                                PopularRestaurantlayoutManger = LinearLayoutManager(activity)

                                HomePopularRestaurantAdapter =
                                    HomePopularRestaurantAdapter(
                                        activity as Context,
                                        popularRestaurantList
                                    )
                                PopularRestaurantRecylerView.adapter = HomePopularRestaurantAdapter
                                PopularRestaurantRecylerView.layoutManager =
                                    PopularRestaurantlayoutManger

                                MostPopularRestaurantlayoutManger = LinearLayoutManager(
                                    activity,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                                MostPopularPopularRestaurantRecylerView.adapter =
                                    HomePopularRestaurantAdapter
                                MostPopularPopularRestaurantRecylerView.layoutManager =
                                    MostPopularRestaurantlayoutManger
                            } else {
                                Toast.makeText(
                                    activity as Context,
                                    "Something went wrong sucess",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        } catch (e: JSONException) {
                            Toast.makeText(
                                activity as Context,
                                "Something Went Wrong!!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    Response.ErrorListener {

                        println("Error is $it")

                    }) {
                    override fun getHeaders(): MutableMap<String, String> {
                        val header = HashMap<String, String>()
                        header["Content-type"] = "application/json"
                        header["token"] = "e5d8446ee8b0e3"
                        return header
                    }
                }
            queue.add(jsonObjectRequets)
        } else {
            val dialog = AlertDialog.Builder(activity as Context)
            dialog.setTitle("Failed")
            dialog.setMessage("Internet Not Found")
            dialog.setPositiveButton("Setting") { text, listner ->
                val settingIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingIntent)
                activity?.finish()
            }
            dialog.setNegativeButton("Exit") { text, listner ->

                //It close all running activity
                ActivityCompat.finishAffinity(activity as Activity)
            }
            dialog.create()
            dialog.show()
        }
        return view
    }
}