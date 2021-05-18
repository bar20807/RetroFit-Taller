package com.prueba.taller

import com.google.gson.annotations.SerializedName

data class NewResponse (
    @SerializedName("status") val status:String,
    @SerializedName("totalResults") val totalResults:String,
    @SerializedName("articles") val articles: List<Articules>,

        )