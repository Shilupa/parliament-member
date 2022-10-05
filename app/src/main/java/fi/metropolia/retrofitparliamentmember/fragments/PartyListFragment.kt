package fi.metropolia.retrofitparliamentmember.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fi.metropolia.retrofitparliamentmember.DataBinderMapperImpl
import fi.metropolia.retrofitparliamentmember.R
import fi.metropolia.retrofitparliamentmember.databinding.FragmentMainBinding
import fi.metropolia.retrofitparliamentmember.databinding.FragmentPartyListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PartyListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PartyListFragment : Fragment() {
    private lateinit var binding: FragmentPartyListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_party_list, container, false)
        binding.second.text = "Second page"
        return  binding.root
    }
}