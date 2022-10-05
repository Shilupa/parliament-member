package fi.metropolia.retrofitparliamentmember.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fi.metropolia.retrofitparliamentmember.model.PmModel

/**
 * All the queries for pm database is done here
 */
@Dao
interface PmDao {
    @Query("SELECT * FROM PmModel")
    fun getAll(): LiveData<List<PmModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPmToDB(Pm: PmModel): Long

    @Query("DELETE FROM PmModel")
    suspend  fun deleteAll()
}