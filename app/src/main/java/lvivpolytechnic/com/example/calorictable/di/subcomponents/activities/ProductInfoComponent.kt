package lvivpolytechnic.com.example.calorictable.di.subcomponents.activities

import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import lvivpolytechnic.com.example.calorictable.data.repositories.ingredient.IngredientRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepository
import lvivpolytechnic.com.example.calorictable.ui.productinfo.ProductInfoActivity
import lvivpolytechnic.com.example.calorictable.ui.productinfo.ProductInfoViewModelFactory
import javax.inject.Inject

@Subcomponent(modules = [ProductInfoComponent.ComponentModule::class])
interface ProductInfoComponent {

    fun inject(activity: ProductInfoActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance view: ProductInfoActivity): ProductInfoComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideProductInfoModelViewFactory(
            ingredientRepository: IngredientRepository,
            usersProductRepository: UsersProductRepository,
            userRepository: UserRepository
        ): ProductInfoViewModelFactory {
            return ProductInfoViewModelFactory(
                ingredientRepository,
                usersProductRepository,
                userRepository
            )
        }
    }
}