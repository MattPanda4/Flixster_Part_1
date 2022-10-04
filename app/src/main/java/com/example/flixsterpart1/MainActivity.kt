package com.example.flixsterpart1

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

const val NOW_PLAYING_URL= "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivity : AppCompatActivity(){
    private val movies = mutableListOf<CurrentMovie>()
    private lateinit var recyclerviewMovies: RecyclerView

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    recyclerviewMovies = findViewById(R.id.content)

    val movieAdapter = CurrentMovieRecyclerViewAdapter(this, movies)
    recyclerviewMovies.adapter = movieAdapter
    recyclerviewMovies.layoutManager = LinearLayoutManager(this) //puts movies top to bottom

    val client = AsyncHttpClient()
    client.get(NOW_PLAYING_URL, object : JsonHttpResponseHandler(){
        override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
            Log.e(ContentValues.TAG, "onFailure $statusCode")
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
            Log.i(ContentValues.TAG, "onSuccess: JSON data $json")
                val movieJsonArray = json.jsonObject.getJSONArray("results")
                movies.addAll(CurrentMovie.fromJsonArray(movieJsonArray))
                movieAdapter.notifyDataSetChanged() //update movies
                Log.i(ContentValues.TAG, "Movie list $movies")
        }

    })
}
}