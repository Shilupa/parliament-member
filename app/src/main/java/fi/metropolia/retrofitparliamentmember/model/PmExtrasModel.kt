package fi.metropolia.retrofitparliamentmember.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

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
