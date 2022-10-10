package fi.metropolia.retrofitparliamentmember.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fi.metropolia.retrofitparliamentmember.model.PmModel

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

/**
 * Instance of Parliament members database
 */
@Database(entities = [(PmModel::class)], version = 11, exportSchema = false)
abstract class PmDatabase : RoomDatabase() {
    abstract val pmDao: PmDao

    // context comes from MyApp, may also be provided as parameter
    companion object {
        private var sInstance: PmDatabase? = null

        @Synchronized
        fun getInstance(context: Context): PmDatabase {
            // if the INSTANCE is not null, then returns it, if it is, then creates the database
            if (sInstance == null) {
                sInstance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        PmDatabase::class.java, "pm.db"
                    ).fallbackToDestructiveMigration().build()
            }
            return sInstance!!
        }
    }
}
