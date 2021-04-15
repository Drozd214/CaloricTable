package lvivpolytechnic.com.example.calorictable.ui.productinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import lvivpolytechnic.com.example.calorictable.data.repositories.ingredient.IngredientRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepository
import lvivpolytechnic.com.example.calorictable.ui.productpicker.ProductPickerViewModel

class ProductInfoViewModelFactory(
    private val ingredientRepository: IngredientRepository,
    private val usersProductRepository: UsersProductRepository,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductInfoViewModel(
            ingredientRepository,
            usersProductRepository,
            userRepository
        ) as T
    }

}