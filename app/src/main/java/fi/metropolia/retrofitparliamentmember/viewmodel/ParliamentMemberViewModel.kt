package fi.metropolia.retrofitparliamentmember.viewmodel

import android.app.Application
import fi.metropolia.retrofitparliamentmember.repository.PmRepository

/**
 * @param application
 * Used for accessing database via PmRepository
 */
class ParliamentMemberViewModel(application: Application) {
    private val pmRepository = PmRepository(application)
    val getPmList = pmRepository.pmListFromDb
}