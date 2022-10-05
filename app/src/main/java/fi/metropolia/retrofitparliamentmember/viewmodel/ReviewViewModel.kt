package fi.metropolia.retrofitparliamentmember.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import fi.metropolia.retrofitparliamentmember.repository.ReviewRepository

/**
 * @param application
 * Used for accessing review database via reviewRepository
 */
class ReviewViewModel(application: Application): AndroidViewModel(application) {
    val reviewRepo =  ReviewRepository(application)
}