package lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientinfo

import com.squareup.moshi.Json

data class IngredientInfo(
        @field:Json(name = "id") val id: Int,
        @field:Json(name = "name") val name: String,
        @field:Json(name = "nutrition") val nutrition: Nutrition
)

data class Nutrition(
        @field:Json(name = "nutrients") val nutrients: List<Nutrient>
)

data class Nutrient(
        @field:Json(name = "name") val name: String,
        @field:Json(name = "amount") val amount: Double
)