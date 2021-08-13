package com.rubiks.mealmonkey.models

import java.io.FileDescriptor

data class foodmenu(
    val id: String,
    val foodtypeId : String,
    val name:String,
    val description : String,
    val price :String,
    val img : String
)
