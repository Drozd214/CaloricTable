package lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch

import com.squareup.moshi.Json

data class IngredientSearch(
        @field:Json(name = "results") val results: List<IngredientsResult>
)

data class IngredientsResult(
        @field:Json(name = "id") val id: Int,
        @field:Json(name = "name") val name: String,
        @field:Json(name = "image") val image: String
)