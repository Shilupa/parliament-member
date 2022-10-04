package fi.metropolia.retrofitparliamentmember.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fi.metropolia.retrofitparliamentmember.model.PmModel

/**
 * Instance of Parliament members database
 */
@Database(entities = [PmModel::class], version = 3, exportSchema = false)
abstract class PmDatabase : RoomDatabase() {
    abstract val pmDao: PmDao

    // context comes from MyApp, may also be provided as parameter
    companion object {
        private var sInstance: PmDatabase? = null

        @Synchronized
        fun getInstance(context: Context): PmDatabase {
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
