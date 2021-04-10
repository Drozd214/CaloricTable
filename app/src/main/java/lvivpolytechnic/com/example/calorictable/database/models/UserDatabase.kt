package lvivpolytechnic.com.example.calorictable.database.models

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import lvivpolytechnic.com.example.calorictable.database.models.UserDatabase.Companion.TABLE_NAME
import lvivpolytechnic.com.example.calorictable.models.Goal
import lvivpolytechnic.com.example.calorictable.models.Sex
import java.io.Serializable

@Entity(
    tableName = TABLE_NAME
)
data class UserDatabase(
    @PrimaryKey @ColumnInfo(name = ID) val id: Int,
    @ColumnInfo(name = PROFILE_IMAGE) var profileImage: Uri?,
    @ColumnInfo(name = SEX) var sex: Sex,
    @ColumnInfo(name = HEIGHT) var height: Int,
    @ColumnInfo(name = WEIGHT) var weight: Int,
    @ColumnInfo(name = YEAR_OF_BIRTH) var yearOfBirth: Int,
    @ColumnInfo(name = GOAL) var goal: Goal
) : Serializable {

    companion object {
        const val TABLE_NAME = "users"
        const val ID = "id"
        const val PROFILE_IMAGE = "profile_image"
        const val SEX = "sex"
        const val HEIGHT = "height"
        const val WEIGHT = "weight"
        const val YEAR_OF_BIRTH = "year_of_birth"
        const val GOAL = "goal"
    }

    constructor(id: Int): this(
        id = id,
        profileImage = null,
        sex = Sex.MALE,
        height = 0,
        weight = 0,
        yearOfBirth = 0,
        goal = Goal.KEEP_FIT
    )
}