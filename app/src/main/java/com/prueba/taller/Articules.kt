package com.prueba.taller

import com.google.gson.annotations.SerializedName

data class Articules (

    @SerializedName("author") val author:String,
    @SerializedName("title") val title:String,
    @SerializedName("description") val description:String,
    @SerializedName("urlToImage") val urlToImage:String

        )