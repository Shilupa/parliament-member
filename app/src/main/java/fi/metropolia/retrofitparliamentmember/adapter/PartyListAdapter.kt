package fi.metropolia.retrofitparliamentmember.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import fi.metropolia.retrofitparliamentmember.R
import fi.metropolia.retrofitparliamentmember.model.PmModel

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

/**
 * Recycler view adapter to display party list
 */

class PartyListAdapter : RecyclerView.Adapter<PartyListAdapter.ViewHolder>() {
    private var partyList = mutableSetOf<String>()

    private val partyImageList = listOf(
        R.drawable.sd,
        R.drawable.vihr,
        R.drawable.ps,
        R.drawable.kok,
        R.drawable.r,
        R.drawable.kesk,
        R.drawable.kd,
        R.drawable.vas,
        R.drawable.wr,
        R.drawable.liik,
        R.drawable.vkk
    )

    //var partyImageList = mutableListOf<String>(R.drawable.sd)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.party_list_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.partyName.text = partyList.elementAt(position)
        holder.partyImage.setImageResource(partyImageList[position])

        val bundle = Bundle()
        bundle.putString("partyName", partyList.elementAt(position))

        // Navigation to next fragment along with passing value as bundle
        holder.itemView.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_partyListFragment_to_memberListFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return partyList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val partyName: TextView = itemView.findViewById(R.id.partyName)
        val partyImage: ImageView = itemView.findViewById(R.id.partyImage)
    }

    /**
     * @param pmList
     * Creating new list of non duplicate party list
     */
    fun setData(pmList: List<PmModel>) {
        pmList.forEach {
            partyList.add(it.party)
        }
    }
}