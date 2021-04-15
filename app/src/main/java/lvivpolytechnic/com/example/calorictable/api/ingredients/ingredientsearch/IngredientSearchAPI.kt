package lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch

import lvivpolytechnic.com.example.calorictable.api.SpoonacularAPI.Companion.API_KEY
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IngredientSearchAPI {

    @GET("/food/ingredients/search?apiKey=${API_KEY}&number=10")
    suspend fun getIngredientSearch(@Query("query") ingredient: String): Response<IngredientSearch>
}