package fi.metropolia.retrofitparliamentmember.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fi.metropolia.retrofitparliamentmember.model.PmModel

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

/**
 * All the queries for pm database is done here
 */
@Dao
interface PmDao {
    /**
     * Selecting List of all Parliament members
     */
    @Query("SELECT * FROM PmModel")
    fun getAll(): LiveData<List<PmModel>>

    /**
     * @param pm
     * Adding parliament member to database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPmToDB(pm: PmModel): Long

    /**
     * @param hetekeId
     * Fetching parliament member by their hetekaId
     */
    @Query("SELECT * FROM PmModel WHERE hetekaId = :hetekeId")
    fun getPmById(hetekeId: Int): LiveData<PmModel>

    /**
     * Deleting parliament members List from database(Just in case on need)
     */
    @Query("DELETE FROM PmModel")
    suspend fun deleteAll()
}