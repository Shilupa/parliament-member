package fi.metropolia.retrofitparliamentmember.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fi.metropolia.retrofitparliamentmember.repository.PmRepository

/**
 * @param application
 * Used for accessing pm database via PmRepository
 */
class ParliamentMemberViewModel(application: Application): AndroidViewModel(application) {
     val pmRepository = PmRepository(application)
     val getPmList = pmRepository.pmListFromDb
}