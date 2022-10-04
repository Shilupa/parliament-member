package fi.metropolia.retrofitparliamentmember

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import fi.metropolia.retrofitparliamentmember.viewmodel.ParliamentMemberViewModel


/**
 * @param application
 * @param workerParameter
 * Work Manager implementation
 */
private const val TAG = "MyWorker"

class MyWorker(application: Application, workerParameter: WorkerParameters) :
    Worker(application, workerParameter) {
    private val localApplication = application
    lateinit var parliamentMemberViewModel: ParliamentMemberViewModel

    override fun doWork(): Result {
        return try {
            Log.d(TAG, "Working now")
            parliamentMemberViewModel = ParliamentMemberViewModel(localApplication)
            Result.success()
        }catch (throwable: Throwable) {
            Log.e(TAG, "Error applying blur")
            Result.failure()
        }
    }
}