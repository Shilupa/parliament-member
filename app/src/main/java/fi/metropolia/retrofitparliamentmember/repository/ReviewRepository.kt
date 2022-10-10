package fi.metropolia.retrofitparliamentmember.repository

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fi.metropolia.retrofitparliamentmember.database.ReviewDb
import fi.metropolia.retrofitparliamentmember.model.Review
import kotlinx.coroutines.launch

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

/**
 * @param application
 * Used for adding and fetching data to review database
 */
class ReviewRepository(application: Application) : AndroidViewModel(application) {
    //Reference of the Dao
    private val reviewDao = ReviewDb.getInstance(application).reviewDao

    /**
     * @param review
     * Adding Review to Db
     */
    fun addReview(review: Review) {
        viewModelScope.launch {
            reviewDao.addReview(review)
        }
    }

    /**
     * @param personNumber
     * Fetching all reviews for certain parliament member filtered by personNumber
     */
    fun getAllReviews(personNumber: Int): LiveData<List<Review>> {
        return reviewDao.getAllReview(personNumber)
    }
}