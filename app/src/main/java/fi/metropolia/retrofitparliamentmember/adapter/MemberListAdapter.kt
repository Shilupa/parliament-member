package fi.metropolia.retrofitparliamentmember.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import fi.metropolia.retrofitparliamentmember.R
import fi.metropolia.retrofitparliamentmember.model.PmModel

private const val BASE_URL = "https://avoindata.eduskunta.fi/"

class MemberListAdapter(private val memberList: MutableList<PmModel>) : RecyclerView.Adapter<MemberListAdapter.ViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.member_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.firstName.text = memberList[position].first
        holder.lastName.text = memberList[position].last
        holder.constituency.text = memberList[position].constituency
        holder.partyName.text = memberList[position].party

        val imageUrl:String = BASE_URL + memberList[position].picture
        Log.d("image", imageUrl)
        Glide.with(holder.itemView).load(imageUrl).into(holder.image)
        val bundle = Bundle()
        bundle.putInt("personId",memberList.elementAt(position).personNumber)
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(R.id.action_memberListFragment_to_detailFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
       return memberList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstName: TextView = itemView.findViewById(R.id.first)
        val lastName: TextView = itemView.findViewById(R.id.last)
        val constituency: TextView = itemView.findViewById(R.id.constituency)
        val partyName: TextView = itemView.findViewById(R.id.partyName)
        val image: ImageView = itemView.findViewById(R.id.memberImage)
    }
}