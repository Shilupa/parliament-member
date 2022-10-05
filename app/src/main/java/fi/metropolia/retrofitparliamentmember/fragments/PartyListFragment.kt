package fi.metropolia.retrofitparliamentmember.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fi.metropolia.retrofitparliamentmember.R
import fi.metropolia.retrofitparliamentmember.adapter.PartyListAdapter
import fi.metropolia.retrofitparliamentmember.databinding.FragmentPartyListBinding
import fi.metropolia.retrofitparliamentmember.viewmodel.ParliamentMemberViewModel

private const val TAG = "PartyList"
class PartyListFragment : Fragment() {
    companion object {
        private lateinit var parliamentMemberViewModel: ParliamentMemberViewModel
    }
    private lateinit var binding: FragmentPartyListBinding
    private lateinit var partyListAdapter: PartyListAdapter
    // List to store non duplicate party list
    var partyList = mutableSetOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_party_list, container, false)
        parliamentMemberViewModel = ViewModelProvider(this)[ParliamentMemberViewModel::class.java]
        // Implementing RecyclerView
        binding.pmListRecyclerView.hasFixedSize()
        binding.pmListRecyclerView.layoutManager = LinearLayoutManager(view?.context)
        partyListAdapter = PartyListAdapter()


        parliamentMemberViewModel.getPmList.observe(viewLifecycleOwner){ pmList ->
         /*   pmList.forEach{
                Log.d(TAG, it.bornYear.toString())
                partyList.add(it.party)
            }*/
            partyListAdapter.setData(pmList)
            binding.pmListRecyclerView.adapter = partyListAdapter
        }
        return  binding.root
    }
}