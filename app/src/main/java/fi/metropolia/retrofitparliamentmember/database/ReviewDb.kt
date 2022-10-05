package fi.metropolia.retrofitparliamentmember.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fi.metropolia.retrofitparliamentmember.model.Review

/**
 * Instance of review database
 */
@Database(entities = [Review::class], version = 2, exportSchema = false)
abstract class ReviewDb : RoomDatabase() {
    abstract val reviewDao: ReviewDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: ReviewDb? = null

        fun getInstance(context: Context): ReviewDb {
            // if the INSTANCE is not null, then returns it, if it is, then creates the database
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ReviewDb::class.java, "review.db"
                    )
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}