package co.tiagoaguiar.netflixremake

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import co.tiagoaguiar.netflixremake.model.Movie
import co.tiagoaguiar.netflixremake.util.DownloadImageTask

class MovieAdapter(private val movies: List<Movie>,
@LayoutRes private val layoutId: Int) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(movie: Movie){
            val imgCover: ImageView = itemView.findViewById(R.id.img_cover)

            //outro modo de fazer, mas com o mesmo fundamento
            DownloadImageTask(object : DownloadImageTask.Callback{
                override fun onResult(bitmap: Bitmap) {
                    imgCover.setImageBitmap(bitmap)
                }
            }).execute(movie.coverUrl)

            //usando Picasso: apenas uma linha e tem as imagens na tela
//            Picasso.get().load(movie.coverUrl).into(imgCover)
        }
    }

}