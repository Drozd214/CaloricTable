package lvivpolytechnic.com.example.calorictable.data.stores.ingridient

import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientinfo.IngredientInfo
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientinfo.IngredientInfoAPI
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientSearch
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientSearchAPI
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientsResult

interface IngredientApiStore {

    suspend fun getProductsForName(product: String): List<IngredientsResult>

    suspend fun getProductsInfoForId(id: Int): IngredientInfo
}