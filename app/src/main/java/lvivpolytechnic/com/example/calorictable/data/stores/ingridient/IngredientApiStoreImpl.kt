package lvivpolytechnic.com.example.calorictable.data.stores.ingridient

import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientinfo.IngredientInfo
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientinfo.IngredientInfoAPI
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientinfo.Nutrition
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientSearch
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientSearchAPI
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientsResult

class IngredientApiStoreImpl(
    private val ingredientSearchAPI: IngredientSearchAPI,
    private val ingredientInfoAPI: IngredientInfoAPI
) : IngredientApiStore {

    override suspend fun getProductsForName(product: String): List<IngredientsResult> {
        val response = ingredientSearchAPI.getIngredientSearch(product)
        val a = 5
        return if(response.body() != null) {
            response.body()!!.results
        } else {
            listOf()
        }
    }

    override suspend fun getProductsInfoForId(id: Int): IngredientInfo {
        val response = ingredientInfoAPI.getIngredientInfo(id)
        return if(response.body() != null) {
            response.body()!!
        } else {
            IngredientInfo(0, "", Nutrition(listOf()))
        }
    }

}