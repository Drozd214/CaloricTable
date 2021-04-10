package lvivpolytechnic.com.example.calorictable.models

import android.net.Uri

data class User(
    val id: Int,
    val profileImage: Uri?,
    val sex: Sex,
    val height: Int,
    val weight: Int,
    val yearOfBirth: Int,
    val goal: Goal,
    val productsList: List<Product>
)