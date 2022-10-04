package com.example.flixsterpart1

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel
import org.json.JSONArray

@Parcelize

data class CurrentMovie (
    private val posterPath: String,
    val title: String,
    val overview: String,
    val voteAverage: Double,
    val language: String
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
                        movieJson.getString("overview"),
                        movieJson.getDouble("vote_average"),
                        movieJson.getString("original_language")
                        )
                )
            }
            return movies
        }
    }
}