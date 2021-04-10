package lvivpolytechnic.com.example.calorictable.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import lvivpolytechnic.com.example.calorictable.database.models.UsersProductDatabase.Companion.TABLE_NAME
import lvivpolytechnic.com.example.calorictable.models.EatingTime
import java.io.Serializable

@Entity(
        tableName = TABLE_NAME
)
class UsersProductDatabase(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = ID) var id: Int = 0,
    @ColumnInfo(name = USER_ID) var userId: Int,
    @ColumnInfo(name = NAME) var name: String,
    @ColumnInfo(name = IMAGE) var imageUrl: String,
    @ColumnInfo(name = CAPACITY) var capacityInGrams: Int,
    @ColumnInfo(name = CALORIES) var calories: Int,
    @ColumnInfo(name = CARBOHYDRATES) var carbohydrates: Int,
    @ColumnInfo(name = PROTEIN) var protein: Int,
    @ColumnInfo(name = FAT) var fat: Int,
    @ColumnInfo(name = EATING_TIME) var eatingTime: EatingTime
) : Serializable {

    companion object {
        const val TABLE_NAME = "users_products"
        const val ID = "id"
        const val USER_ID = "user_id"
        const val NAME = "name"
        const val IMAGE = "image"
        const val CAPACITY = "capacity"
        const val CALORIES = "calories"
        const val CARBOHYDRATES = "carbohydrates"
        const val PROTEIN = "protein"
        const val FAT = "fat"
        const val EATING_TIME = "eating_time"
    }

    constructor(): this(
        id = 0,
        userId = 0,
        name = "",
        imageUrl = "",
        capacityInGrams = 0,
        calories = 0,
        carbohydrates = 0,
        protein = 0,
        fat = 0,
        eatingTime = EatingTime.BREAKFAST
    )

}