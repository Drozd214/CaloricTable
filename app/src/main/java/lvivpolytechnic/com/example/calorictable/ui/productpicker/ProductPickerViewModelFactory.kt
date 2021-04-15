package lvivpolytechnic.com.example.calorictable.ui.productpicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lvivpolytechnic.com.example.calorictable.data.repositories.ingredient.IngredientRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.ui.main.MainViewModel

class ProductPickerViewModelFactory(private val ingredientRepository: IngredientRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductPickerViewModel(ingredientRepository) as T
    }

}