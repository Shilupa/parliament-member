package fi.metropolia.retrofitparliamentmember.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

/**
 * Model of review database
 */
@Entity
data class Review(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val personId: Int,
    val comment: String,
    val rating: Int
)