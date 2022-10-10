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
import fi.metropolia.retrofitparliamentmember.adapter.MemberListAdapter
import fi.metropolia.retrofitparliamentmember.databinding.FragmentMemberListBinding
import fi.metropolia.retrofitparliamentmember.model.PmModel
import fi.metropolia.retrofitparliamentmember.viewmodel.ParliamentMemberViewModel

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

/**
 * Fragment to display member list of specific party via MemberListAdapter
 */
class MemberListFragment : Fragment() {
    companion object {
        private lateinit var parliamentMemberViewModel: ParliamentMemberViewModel
    }

    private lateinit var binding: FragmentMemberListBinding
    private lateinit var memberListAdapter: MemberListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member_list, container, false)
        // Storing data passed from previous view
        val partyName = arguments?.getString("partyName")
        // Creating mutable list store no duplicates list of parliament members
        val memberList = mutableSetOf<PmModel>()

        // Initializing view model
        parliamentMemberViewModel = ViewModelProvider(this)[ParliamentMemberViewModel::class.java]
        binding.memberRecyclerView.hasFixedSize()
        binding.memberRecyclerView.layoutManager = LinearLayoutManager(view?.context)

        /**
         * Adding pm to the list if he/she belongs to the party passed from previous fragment
         * Passing memberList after adding member to the Adapter
         */
        parliamentMemberViewModel.getPmList.observe(viewLifecycleOwner) { pmList ->
            pmList.map {
                if (it.party == partyName) {
                    memberList.add(it)
                }
            }
            memberListAdapter = MemberListAdapter(memberList)
            binding.memberRecyclerView.adapter = memberListAdapter
        }
        return binding.root
    }
}