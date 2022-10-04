package fi.metropolia.retrofitparliamentmember.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model of Parliament members database
 */
@Entity
data class PmModel(
    val bornYear: Int,
    val constituency: String,
    val first: String,
    val last: String,
    val minister: Boolean,
    val party: String,
    @PrimaryKey
    val personNumber: Int,
    val picture: String,
    val seatNumber: Int,
    val twitter: String
)