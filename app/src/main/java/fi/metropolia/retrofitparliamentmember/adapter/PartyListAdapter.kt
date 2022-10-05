package fi.metropolia.retrofitparliamentmember.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fi.metropolia.retrofitparliamentmember.R
import fi.metropolia.retrofitparliamentmember.model.PmModel

private const val TAG = "Adapter"

class PartyListAdapter() : RecyclerView.Adapter<PartyListAdapter.ViewHolder> (){
    private var partyList = mutableSetOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.party_list_card_view, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, partyList.elementAt(position))
        holder.partyName.text = partyList.elementAt(position)
    }

    override fun getItemCount(): Int {
        return partyList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val partyName: TextView = itemView.findViewById(R.id.party_name)
    }

    /**
     * @param pmList
     * Creating new list of non duplicate party list
     */
    fun setData(pmList: List<PmModel>) {
        pmList.forEach{
            partyList.add(it.party)
        }
        partyList.forEach{
            Log.d(TAG, it)
        }
    }
}