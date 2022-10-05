package fi.metropolia.retrofitparliamentmember

import android.app.Application
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import fi.metropolia.retrofitparliamentmember.viewmodel.ParliamentMemberViewModel


/**
 * Work Manager implementation
 */
private const val TAG = "MyWorker"

class MyWorker(private val application: Application, workerParameter: WorkerParameters) :
    Worker(application, workerParameter) {
    lateinit var parliamentMemberViewModel: ParliamentMemberViewModel

    override fun doWork(): Result {
        return try {
            Log.d(TAG, "Working now")
            parliamentMemberViewModel = ParliamentMemberViewModel(application)
            Result.success()
        }catch (throwable: Throwable) {
            Log.e(TAG, "Error applying blur")
            Result.failure()
        }
    }
}