package fi.metropolia.retrofitparliamentmember.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fi.metropolia.retrofitparliamentmember.R
import fi.metropolia.retrofitparliamentmember.model.Review

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

/**
 * Recycler view adapter to review list
 */
class ReviewListAdapter(private val reviews: List<Review>) :
    RecyclerView.Adapter<ReviewListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.review_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.review.text = reviews[position].comment
        holder.ratingStar.rating = reviews[position].rating
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val review: TextView = itemView.findViewById(R.id.review)
        val ratingStar: RatingBar = itemView.findViewById(R.id.rating)
    }
}