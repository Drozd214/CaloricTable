package lvivpolytechnic.com.example.calorictable.data.repositories.ingredient

import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientinfo.IngredientInfo
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientsResult
import lvivpolytechnic.com.example.calorictable.data.stores.ingridient.IngredientApiStore
import lvivpolytechnic.com.example.calorictable.models.Product

class IngredientRepositoryImpl(
    private val ingredientApiStore: IngredientApiStore
) : IngredientRepository {

    override suspend fun getProductsForName(product: String): List<IngredientsResult> {
        return ingredientApiStore.getProductsForName(product)
    }

    override suspend fun getProductForId(id: Int): IngredientInfo {
        return ingredientApiStore.getProductsInfoForId(id)
    }
}