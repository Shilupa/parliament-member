package fi.metropolia.retrofitparliamentmember.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model of PmExtras database database
 */
@Entity
data class PmExtrasModel(
    @PrimaryKey
    val hetekaId: Int,
    val twitter: String,
    val bornYear: Int,
    val constituency: String,
)
