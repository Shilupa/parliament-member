package fi.metropolia.retrofitparliamentmember.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.squareup.moshi.Json

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

/**
 * Model of Parliament members database
 */
@Entity
data class PmModel(
    @PrimaryKey
    val hetekaId: Int,
    val seatNumber: Int,
    val lastname: String,
    val firstname: String,
    val party: String,
    val minister: Boolean,
    val pictureUrl: String,
)
