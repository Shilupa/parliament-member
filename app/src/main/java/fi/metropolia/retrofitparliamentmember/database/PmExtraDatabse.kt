package fi.metropolia.retrofitparliamentmember.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fi.metropolia.retrofitparliamentmember.model.PmExtrasModel
import fi.metropolia.retrofitparliamentmember.model.PmModel

/**
 * Instance of Parliament members database
 */
@Database(entities = [(PmExtrasModel::class)], version = 1, exportSchema = false)
abstract class PmExtraDatabase : RoomDatabase() {
    abstract val pmExtrasDao: PmExtrasDao


    // context comes from MyApp, may also be provided as parameter
    companion object {
        private var sInstance: PmExtraDatabase? = null

        @Synchronized
        fun getInstance(context: Context): PmExtraDatabase {
            // if the INSTANCE is not null, then returns it, if it is, then creates the database
            if (sInstance == null) {
                sInstance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        PmExtraDatabase::class.java, "pmExtras.db"
                    ).fallbackToDestructiveMigration().build()
            }
            return sInstance!!
        }
    }
}
