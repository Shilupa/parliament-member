package fi.metropolia.retrofitparliamentmember.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fi.metropolia.retrofitparliamentmember.model.PmExtrasModel

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

/**
 * All the queries for pmExtras database is done here
 */
@Dao
interface PmExtrasDao {
    /**
     * @param hetekaId
     * Fetching parliament member extra details by hetekaId
     */
    @Query("SELECT * FROM PmExtrasModel WHERE hetekaId = :hetekaId")
    fun getPmExtras(hetekaId: Int): LiveData<PmExtrasModel>

    /**
     * @param pmExtras
     * Adds pmExtras to the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPmExtras(pmExtras: PmExtrasModel): Long
}