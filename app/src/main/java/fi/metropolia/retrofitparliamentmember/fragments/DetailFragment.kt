package fi.metropolia.retrofitparliamentmember.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import fi.metropolia.retrofitparliamentmember.R
import fi.metropolia.retrofitparliamentmember.databinding.FragmentDetailBinding
import fi.metropolia.retrofitparliamentmember.model.Review
import fi.metropolia.retrofitparliamentmember.viewmodel.ParliamentMemberViewModel
import fi.metropolia.retrofitparliamentmember.viewmodel.ReviewViewModel


private const val BASE_URL = "https://avoindata.eduskunta.fi/"

/**
 * Fragment to display parliament member details
 */
class DetailFragment : Fragment() {
    companion object {
        private lateinit var parliamentMemberViewModel: ParliamentMemberViewModel
        private lateinit var reviewViewModel: ReviewViewModel
    }

    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        val id = arguments?.getInt("personId")

        parliamentMemberViewModel = ViewModelProvider(this)[ParliamentMemberViewModel::class.java]
        reviewViewModel = ViewModelProvider(this)[ReviewViewModel::class.java]

        // Getting pm details and extras by pm hetekeId passed from previous fragment
        val pmDetails = id?.let { parliamentMemberViewModel.pmRepository.getPmByPmId(it) }
        val pmExtras = id?.let { parliamentMemberViewModel.pmRepository.getPmExtrasByPmId(id)}

        pmExtras?.observe(viewLifecycleOwner){
           binding.bornYear.text = it.bornYear.toString()
            if(it.twitter == ""){
                binding.twitterLink.text = "No Twitter Account"
            }else{
                binding.twitterLink.text = it.twitter
            }
            binding.region.text = it.constituency
        }
        pmDetails?.observe(viewLifecycleOwner) {
            val url: String = BASE_URL + it.pictureUrl
            Glide.with(this).load(url).into(binding.pmImage)
            if(it.minister){
                binding.memberType.text = getString(R.string.minister)
            }else{
                binding.memberType.text = getString(R.string.no_minister)
            }
            binding.region.text = it.party
            binding.firstName.text = it.firstname
            binding.lastName.text = it.lastname
        }

        binding.submit.setOnClickListener {
            val rating = binding.rating.text.toString()
            val comment = binding.comment.text.toString()
            // Checking if Edit text field is empty & rating is a number string
            if (TextUtils.isEmpty(comment) || TextUtils.isEmpty(rating) || rating.toIntOrNull() == null) {
                Toast.makeText(
                    requireContext(), "Please fill all the field",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                // Checking if rating is less than 1 or greater than 5
                if (rating.toInt() < 1 || rating.toInt() > 5) {
                    Toast.makeText(
                        requireContext(), "Number from 1 to 5 only",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    // If every filed is okay, review is added to review database
                    if (id != null) {
                        val review = Review(0, id, comment, rating.toInt())
                        reviewViewModel.reviewRepo.addReview(review)
                        Toast.makeText(
                            requireContext(), "Review added",
                            Toast.LENGTH_LONG
                        ).show()
                        // Clearing edit text after review is added
                        binding.rating.text.clear()
                        binding.comment.text.clear()
                    }
                }
            }
        }
        val bundle = Bundle()
        if (id != null) {
            bundle.putInt("personId", id)
        }
        // Navigation to review fragment
        binding.viewReview.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_reviewListFragment, bundle)
        }
        return binding.root
    }
}