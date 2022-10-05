package fi.metropolia.retrofitparliamentmember.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fi.metropolia.retrofitparliamentmember.model.Review

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReview(review: Review)

    @Query("select * from Review Where personId = :personId")
    fun getAllReview(personId: Int): LiveData<List<Review>>
}