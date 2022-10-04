package fi.metropolia.retrofitparliamentmember.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fi.metropolia.retrofitparliamentmember.model.PmModel
import fi.metropolia.retrofitparliamentmember.database.PmDatabase
import fi.metropolia.retrofitparliamentmember.service.PmApi
import kotlinx.coroutines.*

private const val TAG = "pmRepo"

/***
 * @param application
 * Used for fetching pm data from internet
 * Used for accessing pm database: adding data, deleting data and fetching data
 */
class PmRepository(application: Application) : AndroidViewModel(application) {
    // Reference to database
    private val pmDatabase = PmDatabase.getInstance(application)
    val pmListFromDb: LiveData<List<PmModel>> = pmDatabase.pmDao.getAll()

    private var _fetchedPmList: MutableLiveData<List<PmModel>> = MutableLiveData()
    // Storing copy of data fetched from internet to compare with room data
    private val fetchedPmList: LiveData<List<PmModel>> = _fetchedPmList

    // Initializing the function
    init {
        getParliamentMembers()
        // Delete query to clear database
        /*viewModelScope.launch {
            pmDatabase.pmDao.deleteAll()
        }*/
    }

    /**
     *  Gets the list of pm from internet
     *  Then adds the pm to the database
     */
    private fun getParliamentMembers() {
        viewModelScope.launch {
            try {
                _fetchedPmList.value = PmApi.retrofitService.getPmList()
                // Waits until president list is fetched from internet
                withContext(Dispatchers.IO) {
                    _fetchedPmList.value?.forEach {
                        pmDatabase.pmDao.addPmToDB(it)
                    }
                }
                Log.d(
                    TAG,
                    "internet: ${fetchedPmList.value?.size}, database: ${pmListFromDb.value?.size}"
                )
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }
}