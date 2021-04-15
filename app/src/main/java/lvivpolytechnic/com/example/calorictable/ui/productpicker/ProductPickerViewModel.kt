package lvivpolytechnic.com.example.calorictable.ui.productpicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientsResult
import lvivpolytechnic.com.example.calorictable.data.repositories.ingredient.IngredientRepository

class ProductPickerViewModel(
    private val ingredientRepository: IngredientRepository
) : ViewModel() {

    var isTextChange = false
    var isProductPicked = false

    private var productsResult = listOf<IngredientsResult>()

    private var _products = MutableLiveData<List<String>>()
    val products: LiveData<List<String>> = _products

    private var _productId = MutableLiveData<Int>()
    val productId: LiveData<Int> = _productId

    fun onResume() {
        isTextChange = false
        isProductPicked = false
    }

    fun afterTextChanged(text: String) {
        isTextChange = true
        CoroutineScope(Dispatchers.IO).launch {
            val gettingProducts = ingredientRepository.getProductsForName(text)
            withContext(Dispatchers.Main) {
                productsResult = gettingProducts
                val productsList = mutableListOf<String>()
                for(product in gettingProducts) {
                    productsList.add(product.name)
                }
                _products.value = productsList
            }
        }
    }

    fun launchInfoActivity(position: Int) {
        _productId.value = productsResult[position].id
    }

}