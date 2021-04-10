package lvivpolytechnic.com.example.calorictable.database

import android.net.Uri
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import lvivpolytechnic.com.example.calorictable.models.EatingTime
import lvivpolytechnic.com.example.calorictable.models.Goal
import lvivpolytechnic.com.example.calorictable.models.Sex

class Converters {

    @TypeConverter
    fun fromUriToString(uri: Uri?): String {
        return uri?.toString() ?: ""
    }

    @TypeConverter
    fun fromStringToUri(uri: String): Uri? {
         return if(uri.isNullOrEmpty()) null else Uri.parse(uri)
    }

    @TypeConverter
    fun fromSexToString(sex: Sex): String {
        return sex.name
    }

    @TypeConverter
    fun fromStringToSex(sex: String): Sex {
        return when(sex) {
            Sex.MALE.name -> Sex.MALE
            else -> Sex.FEMALE
        }
    }

    @TypeConverter
    fun fromGoalToString(goal: Goal): String {
        return goal.name
    }

    @TypeConverter
    fun fromStringToGoal(goal: String): Goal {
        return when(goal) {
            Goal.KEEP_FIT.name -> Goal.KEEP_FIT
            Goal.GAIN_WEIGHT.name -> Goal.GAIN_WEIGHT
            else -> Goal.LOSE_WEIGHT
        }
    }

    @TypeConverter
    fun fromEatingTimeToString(eatingTime: EatingTime): String {
        return eatingTime.name
    }

    @TypeConverter
    fun fromStringToEatingTime(eatingTime: String): EatingTime {
        return when(eatingTime) {
            EatingTime.BREAKFAST.name -> EatingTime.BREAKFAST
            EatingTime.DINNER.name -> EatingTime.DINNER
            EatingTime.SECOND_DINNER.name -> EatingTime.SECOND_DINNER
            else -> EatingTime.SUPPER
        }
    }

}