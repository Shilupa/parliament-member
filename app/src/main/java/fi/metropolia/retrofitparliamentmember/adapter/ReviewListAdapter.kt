package fi.metropolia.retrofitparliamentmember.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fi.metropolia.retrofitparliamentmember.R
import fi.metropolia.retrofitparliamentmember.model.Review

class ReviewListAdapter(private val reviews: List<Review>) :
    RecyclerView.Adapter<ReviewListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.review_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.review.text = reviews[position].comment
        holder.rating.text = reviews[position].rating.toString()
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val review: TextView = itemView.findViewById(R.id.review)
        val rating: TextView = itemView.findViewById(R.id.rating)
    }
}