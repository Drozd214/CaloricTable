package lvivpolytechnic.com.example.calorictable.data.repositories.ingredient

import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientinfo.IngredientInfo
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientsResult
import lvivpolytechnic.com.example.calorictable.models.Product

interface IngredientRepository {

    suspend fun getProductsForName(product: String): List<IngredientsResult>

    suspend fun getProductForId(id: Int): IngredientInfo
}