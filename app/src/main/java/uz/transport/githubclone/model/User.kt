package uz.transport.githubclone.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize

data class User(
    @SerializedName("login")
    val id: Int,
    val username: String,
    val login: String,
    val avatar_url: String,
    val name: String,
    val email: String,
    val bio: String,
    val location: String,
    val followers: Int,
    val following: Int,
) : Parcelable