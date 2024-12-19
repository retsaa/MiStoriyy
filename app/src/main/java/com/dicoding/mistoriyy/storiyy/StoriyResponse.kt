package com.dicoding.mistoriyy.storiyy



import com.google.gson.annotations.SerializedName

data class StoriyResponse(
    @field:SerializedName("listStory")
    val listStory: List<ListStoriyItem?>? = null,

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

