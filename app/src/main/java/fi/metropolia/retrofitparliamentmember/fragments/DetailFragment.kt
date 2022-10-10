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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import fi.metropolia.retrofitparliamentmember.R
import fi.metropolia.retrofitparliamentmember.databinding.FragmentDetailBinding
import fi.metropolia.retrofitparliamentmember.model.Review
import fi.metropolia.retrofitparliamentmember.viewmodel.ParliamentMemberViewModel
import fi.metropolia.retrofitparliamentmember.viewmodel.ReviewViewModel

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

// Parliament members image url base link
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

    // Live data to store rating values
    private var _pmRating: MutableLiveData<Float?> = MutableLiveData()
    private val pmRating: LiveData<Float?> = _pmRating

    // Setting minimum rating value
    private val minRatingValue = 0.0F

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        // Storing data, passed from previous fragment
        val id = arguments?.getInt("personId")

        // Initializing view models
        parliamentMemberViewModel = ViewModelProvider(this)[ParliamentMemberViewModel::class.java]
        reviewViewModel = ViewModelProvider(this)[ReviewViewModel::class.java]

        // Getting pm details and extras by pm hetekeId passed from previous fragment
        val pmDetails = id?.let { parliamentMemberViewModel.pmRepository.getPmByPmId(it) }
        val pmExtras = id?.let { parliamentMemberViewModel.pmRepository.getPmExtrasByPmId(id) }

        // Displaying parliament members details in UI
        pmDetails?.observe(viewLifecycleOwner) {
            val url: String = BASE_URL + it.pictureUrl
            Glide.with(this).load(url).into(binding.pmImage)
            if (it.minister) {
                binding.memberType.text = getString(R.string.minister)
            } else {
                binding.memberType.text = getString(R.string.no_minister)
            }
            binding.region.text = getString(R.string.partyName, it.party)
            binding.pmName.text = getString(R.string.pmName, it.firstname, it.lastname)
        }

        // Displaying parliament members extra details in UI
        pmExtras?.observe(viewLifecycleOwner) {
            binding.bornYear.text = getString(R.string.bornYear, it.bornYear.toString())
            if (it.twitter == "") {
                binding.twitterLink.text = getString(R.string.account_no)
            } else {
                binding.twitterLink.text = getString(R.string.account_yes, it.twitter)
            }
            binding.region.text = getString(R.string.constituency, it.constituency)
        }

        // Storing rating value to live data in onClickListener
        binding.ratingStar.setOnRatingBarChangeListener { _, fl, _ ->
            if (fl == 0.0F) {
                _pmRating.value = null
            } else {
                _pmRating.value = fl
            }
        }

        // Review is added through onClickListener when required conditions are met
        binding.submit.setOnClickListener {
            val comment = binding.comment.text.toString()
            // Checking if Edit text field is empty & rating is a number string
            if (TextUtils.isEmpty(comment) || pmRating.value == null) {
                Toast.makeText(
                    requireContext(), "Please fill all the field",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                var reviewAdded = false
                // If every filed is okay, review is added to review database
                pmRating.observe(viewLifecycleOwner) {
                    if (id != null) {
                        val review =
                            pmRating.value?.let { rating -> Review(0, id, comment, rating) }
                        if (review != null) {
                            reviewViewModel.reviewRepo.addReview(review)
                        }
                        reviewAdded = true
                        Toast.makeText(
                            requireContext(), "Review added",
                            Toast.LENGTH_LONG
                        ).show()

                    }

                }
                if (reviewAdded) {
                    // Clearing edit text after review is added
                    binding.comment.text.clear()
                    // Setting rating star value to 0 after review is submitted
                    binding.ratingStar.rating = minRatingValue
                    _pmRating.value = null
                }
            }
        }

        val bundle = Bundle()
        if (id != null) {
            bundle.putInt("personId", id)
        }
        // Navigation to review fragment to view the review list of certain parliament member
        binding.viewReview.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_reviewListFragment, bundle)
        }
        return binding.root
    }
}