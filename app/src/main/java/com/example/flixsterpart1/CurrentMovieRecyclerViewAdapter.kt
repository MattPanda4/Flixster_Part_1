package com.example.flixsterpart1

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


private const val TAG = "MovieAdapter"
const val MOVIE = "MOVIE"
class CurrentMovieRecyclerViewAdapter(private val context: Context, private val movies: MutableList<CurrentMovie>) :
    RecyclerView.Adapter<CurrentMovieRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_current_movies, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position $position")
        val currentMovies = movies[position]
        holder.bind(currentMovies)
    }

    override fun getItemCount() = movies.size

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val mPoster = itemView.findViewById<ImageView>(R.id.movie_image)
        private val mTitle = itemView.findViewById<TextView>(R.id.movie_title)
        private val mOverview = itemView.findViewById<TextView>(R.id.movie_description)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: CurrentMovie){
            mTitle.text = movie.title
            mOverview.text = movie.overview
            Glide.with(context)
                .load(movie.posterImageURL)
                .centerCrop()
                .into(mPoster)
        }

        override fun onClick(p0: View?) {
            //1. get notified of particular movie which was clicked on
            val movie = movies[adapterPosition]
            Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE,movie)
            context.startActivity(intent)
        }
    }
}
