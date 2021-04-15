package lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientinfo

import lvivpolytechnic.com.example.calorictable.api.SpoonacularAPI
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientSearch
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IngredientInfoAPI {

    @GET("/food/ingredients/{id}/information/?apiKey=${SpoonacularAPI.API_KEY}&unit=g&amount=100")
    suspend fun getIngredientInfo(@Path("id") id: Int): Response<IngredientInfo>
}