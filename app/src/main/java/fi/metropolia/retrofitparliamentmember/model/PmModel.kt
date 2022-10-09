package fi.metropolia.retrofitparliamentmember.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.squareup.moshi.Json

/**
 * Model of Parliament members database
 * Reference : https://www.geeksforgeeks.org/android-entity-relationship-in-room/
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

/*@Entity(
    foreignKeys = [ForeignKey(
        entity = PmModel::class,
        onDelete = CASCADE,
        parentColumns = ["hetekaId"],
        childColumns = ["hetekaId"]
    )]
)*/
/*
@Entity
data class PmExtras(
    val hetekaId: Int,
    @PrimaryKey
    val twitter: String,
    val bornYear: Int,
    val constituency: String,
)
*/

/*
class PmAndExtras(
    @Embedded
    var pm: PmModel? = null,
    @Relation(parentColumn = "hetekaId", entityColumn = "hetekaId")
    var extras: PmExtras? = null
)*/
