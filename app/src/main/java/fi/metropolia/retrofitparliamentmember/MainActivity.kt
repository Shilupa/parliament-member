package fi.metropolia.retrofitparliamentmember

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.work.*
import fi.metropolia.retrofitparliamentmember.databinding.ActivityMainBinding
import fi.metropolia.retrofitparliamentmember.viewmodel.ParliamentMemberViewModel
import java.util.concurrent.TimeUnit

private const val TAG = "FromMain"

class MainActivity : AppCompatActivity() {
    companion object {
        private lateinit var parliamentMemberViewModel: ParliamentMemberViewModel
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doWork()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        parliamentMemberViewModel = ParliamentMemberViewModel(application)

        parliamentMemberViewModel.getPmList.observe(this) { it ->
            it.forEach {
                Log.d(TAG, "${it.first} ${it.personNumber}")
            }
            binding.name.text = "${it.size}"
        }
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

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("do_Work", ExistingPeriodicWorkPolicy.KEEP, myWorkRequest)
    }
}