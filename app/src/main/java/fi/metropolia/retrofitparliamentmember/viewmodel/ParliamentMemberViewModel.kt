package fi.metropolia.retrofitparliamentmember.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fi.metropolia.retrofitparliamentmember.repository.PmRepository

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

/**
 * @param application
 * Used for accessing pm and pmExtras database via PmRepository
 */
class ParliamentMemberViewModel(application: Application) : AndroidViewModel(application) {
    val pmRepository = PmRepository(application)
    val getPmList = pmRepository.pmListFromDb
}