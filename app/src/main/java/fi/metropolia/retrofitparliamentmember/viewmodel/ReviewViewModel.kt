package fi.metropolia.retrofitparliamentmember.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fi.metropolia.retrofitparliamentmember.repository.ReviewRepository

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

/**
 * @param application
 * Used for accessing review database via reviewRepository
 */
class ReviewViewModel(application: Application) : AndroidViewModel(application) {
    val reviewRepo = ReviewRepository(application)
}