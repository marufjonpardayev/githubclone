package uz.transport.githubclone.model

import com.google.gson.annotations.SerializedName

data class Repository(
    val id: Int,
    val name: String,
    val full_name: String,
    @SerializedName("private")
    val isPrivate: Boolean,
    val language: String
)