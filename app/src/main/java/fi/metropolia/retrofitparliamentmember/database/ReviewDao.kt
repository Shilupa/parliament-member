package fi.metropolia.retrofitparliamentmember.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fi.metropolia.retrofitparliamentmember.model.Review

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

/**
 * All the queries for review database is done here
 */
@Dao
interface ReviewDao {
    /**
     * @param review
     * Adding pm review to the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReview(review: Review)

    /**
     * @param personId
     * Fetching reviews from database by parliament member personId(hetekaID)
     */
    @Query("select * from Review Where personId = :personId")
    fun getAllReview(personId: Int): LiveData<List<Review>>
}