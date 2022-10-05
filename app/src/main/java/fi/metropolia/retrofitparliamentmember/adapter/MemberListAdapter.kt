package fi.metropolia.retrofitparliamentmember.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fi.metropolia.retrofitparliamentmember.R
import fi.metropolia.retrofitparliamentmember.model.PmModel

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
    }

    override fun getItemCount(): Int {
       return memberList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstName: TextView = itemView.findViewById(R.id.first)
        val lastName: TextView = itemView.findViewById(R.id.last)
        val constituency: TextView = itemView.findViewById(R.id.constituency)
        val partyName: TextView = itemView.findViewById(R.id.partyName)
    }
}