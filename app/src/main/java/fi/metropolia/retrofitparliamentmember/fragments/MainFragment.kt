package fi.metropolia.retrofitparliamentmember.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.work.*
import fi.metropolia.retrofitparliamentmember.MyWorker
import fi.metropolia.retrofitparliamentmember.R
import fi.metropolia.retrofitparliamentmember.databinding.ActivityMainBinding
import fi.metropolia.retrofitparliamentmember.databinding.FragmentMainBinding
import java.util.concurrent.TimeUnit

/**
 * Initial view of the application
 */
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        doWork()
        binding.goToPartyList.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_partyListFragment)
        }
        return binding.root
    }

    /**
     * Implementing work manager
     * Data is fetch and added periodically to db every 1 day
     */
    private fun doWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val myWorkRequest = PeriodicWorkRequest.Builder(
            MyWorker::class.java, 1, TimeUnit.DAYS
        )
            .setConstraints(constraints).addTag("do_work")
            .build()

        WorkManager.getInstance(requireContext())
            .enqueueUniquePeriodicWork("do_Work", ExistingPeriodicWorkPolicy.KEEP, myWorkRequest)
    }
}