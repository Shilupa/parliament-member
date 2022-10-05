package fi.metropolia.retrofitparliamentmember.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fi.metropolia.retrofitparliamentmember.R
import fi.metropolia.retrofitparliamentmember.adapter.ReviewListAdapter
import fi.metropolia.retrofitparliamentmember.databinding.FragmentReviewListBinding
import fi.metropolia.retrofitparliamentmember.viewmodel.ReviewViewModel

/**
 * Fragment to display review via ReviewListAdapter
 */
class ReviewListFragment : Fragment() {
    companion object {
        private lateinit var reviewViewModel: ReviewViewModel
    }
    private lateinit var binding: FragmentReviewListBinding
    private lateinit var reviewListAdapter: ReviewListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_review_list, container, false)
        val personId = arguments?.getInt("personId")

        reviewViewModel = ViewModelProvider(this)[ReviewViewModel::class.java]
        // Implementing RecyclerView
        binding.reviewListRecyclerView.hasFixedSize()
        binding.reviewListRecyclerView.layoutManager = LinearLayoutManager(view?.context)

        if (personId != null) {
            reviewViewModel.reviewRepo.getAllReviews(personId).observe(viewLifecycleOwner){
                reviewListAdapter = ReviewListAdapter(it)
                binding.reviewListRecyclerView.adapter = reviewListAdapter
            }
        }
        return binding.root
    }
}