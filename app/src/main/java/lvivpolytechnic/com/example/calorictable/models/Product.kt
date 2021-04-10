package lvivpolytechnic.com.example.calorictable.models

data class Product(
        val id: Int = 0,
        val userId: Int,
        val name: String,
        val imageUrl: String,
        val capacity: Int,
        val calories: Int,
        val carbohydrates: Int,
        val protein: Int,
        val fat: Int,
        val eatingTime: EatingTime
)