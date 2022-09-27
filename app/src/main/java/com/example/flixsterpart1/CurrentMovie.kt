package com.example.flixsterpart1

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel
import org.json.JSONArray
import org.json.JSONObject

@Parcelize

data class CurrentMovie (
    private val posterPath: String,
    val title: String,
    val overview: String,
) : Parcelable
{
    @IgnoredOnParcel val posterImageURL = "https://image.tmdb.org/t/p/w342/$posterPath"

    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<CurrentMovie> {
            val movies = mutableListOf<CurrentMovie>()
            for(i in 0 until movieJsonArray.length()){
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    CurrentMovie(
                        movieJson.getString("poster_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview")
                    )
                )
            }
            return movies
        }
    }
}