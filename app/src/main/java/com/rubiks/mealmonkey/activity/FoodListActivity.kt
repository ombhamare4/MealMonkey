package com.rubiks.mealmonkey.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rubiks.mealmonkey.R
import com.rubiks.mealmonkey.adapter.FoodMenuAdapter
import com.rubiks.mealmonkey.models.foodmenu
import com.rubiks.mealmonkey.util.ConnectionManager
import org.json.JSONException

class FoodListActivity : AppCompatActivity() {

    lateinit var imgBackButton:ImageView
    lateinit var foodMenuRecylerView: RecyclerView
    lateinit var foodMenuLayoutManager : RecyclerView.LayoutManager
    lateinit var foodMenuAdapter: FoodMenuAdapter

    var foodMenuList = arrayListOf<foodmenu>()

    var foodtypeID: String? = "100"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)

        imgBackButton = findViewById(R.id.imgBackButton)
        foodMenuRecylerView = findViewById(R.id.foodMenuRecylerView)
        foodMenuLayoutManager =LinearLayoutManager(this@FoodListActivity)

        imgBackButton.setOnClickListener{
            val intent = Intent(this@FoodListActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        if(intent!=null){
            foodtypeID =intent.getStringExtra("foodtypeID")
        }else{
            Toast.makeText(this@FoodListActivity, "Some Error Occured 1", Toast.LENGTH_SHORT)
                .show()
        }

        if (foodtypeID == "100") {
            finish()
            Toast.makeText(this@FoodListActivity, "Some Error Occured 2", Toast.LENGTH_SHORT)
                .show()

        }

        val queue =Volley.newRequestQueue(this@FoodListActivity)
        val foodMenuURL = "https://api.npoint.io/eac7a25cc5fb685927a7/data/foodmenu/$foodtypeID"

        if (ConnectionManager().checkConnectivity(this@FoodListActivity)) {
            val jsonObjectRequets =
                object : JsonObjectRequest(
                    Request.Method.GET, foodMenuURL, null, Response.Listener {
                        try {

                            val dataJsonObject = it.getJSONObject("data")
                            val success = dataJsonObject.getBoolean("success")
                            if (success) {

                                val dataJsonArray = dataJsonObject.getJSONArray("foodmenu")
                                for (i in 0 until dataJsonArray.length()) {
                                    val restaurantJsonObject = dataJsonArray.getJSONObject(i)
                                    val restaurantObject = foodmenu(
                                        restaurantJsonObject.getString("id"),
                                        restaurantJsonObject.getString("foodtypeID"),
                                        restaurantJsonObject.getString("name"),
                                        restaurantJsonObject.getString("description"),
                                        restaurantJsonObject.getString("price"),
                                        restaurantJsonObject.getString("img")
                                    )
                                    foodMenuList.add(restaurantObject)
                                }

                                foodMenuAdapter = FoodMenuAdapter(
                                    this@FoodListActivity,
                                    foodMenuList
                                )
                                foodMenuRecylerView.adapter = foodMenuAdapter
                                foodMenuRecylerView.layoutManager = foodMenuLayoutManager
                            } else {
                                println("Error is $it")
                                Toast.makeText(

                                    this@FoodListActivity,
                                    "Something went wrong 1",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        } catch (e: JSONException) {
                            println("Json Exception is $it")
                            Toast.makeText(
                                this@FoodListActivity,
                                "Something Went Wrong 2!!",
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
            val dialog = AlertDialog.Builder(this@FoodListActivity)
            dialog.setTitle("Failed")
            dialog.setMessage("Internet Not Found")
            dialog.setPositiveButton("Setting") { text, listner ->
                val settingIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingIntent)
                this@FoodListActivity.finish()
            }
            dialog.setNegativeButton("Exit") { text, listner ->

                //It close all running activity
                ActivityCompat.finishAffinity(this@FoodListActivity)
            }
            dialog.create()
            dialog.show()
        }


    }


}