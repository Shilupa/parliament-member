package fi.metropolia.retrofitparliamentmember.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val personId: Int,
    val comment: String,
    val rating: Int
)