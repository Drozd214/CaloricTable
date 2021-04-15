package lvivpolytechnic.com.example.calorictable.di.subcomponents.activities

import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import lvivpolytechnic.com.example.calorictable.data.repositories.ingredient.IngredientRepository
import lvivpolytechnic.com.example.calorictable.ui.productpicker.ProductPickerActivity
import lvivpolytechnic.com.example.calorictable.ui.productpicker.ProductPickerViewModelFactory

@Subcomponent(modules = [ProductPickerComponent.ComponentModule::class])
interface ProductPickerComponent {

    fun inject(activity: ProductPickerActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance view: ProductPickerActivity): ProductPickerComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideProductPickerViewModelFactory(ingredientRepository: IngredientRepository): ProductPickerViewModelFactory {
            return ProductPickerViewModelFactory(ingredientRepository)
        }
    }
}