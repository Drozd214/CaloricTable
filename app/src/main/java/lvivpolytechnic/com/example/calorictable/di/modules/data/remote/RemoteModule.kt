package lvivpolytechnic.com.example.calorictable.di.modules.data.remote

import dagger.Module
import dagger.Provides
import lvivpolytechnic.com.example.calorictable.api.SpoonacularAPI
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientinfo.IngredientInfoAPI
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientSearchAPI
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object RemoteModule {

    @Singleton
    @Provides
    fun provideSpoonacularRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(SpoonacularAPI.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideIngredientSearchApi(retrofit: Retrofit): IngredientSearchAPI {
        return retrofit.create(IngredientSearchAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideIngredientInfoApi(retrofit: Retrofit): IngredientInfoAPI {
        return retrofit.create(IngredientInfoAPI::class.java)
    }

}