package lvivpolytechnic.com.example.calorictable.ui.productinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lvivpolytechnic.com.example.calorictable.data.repositories.ingredient.IngredientRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepository
import lvivpolytechnic.com.example.calorictable.models.EatingTime
import lvivpolytechnic.com.example.calorictable.models.Product

class ProductInfoViewModel(
    private val ingredientRepository: IngredientRepository,
    private val usersProductRepository: UsersProductRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    var isDataLoaded = false

    private var _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    private var _isDataSaved = MutableLiveData<Boolean>(false)
    val isDataSaved: LiveData<Boolean> = _isDataSaved

    fun getProduct(isNewProduct: Boolean, id: Int, eatingTime: EatingTime) {
        if(isNewProduct) {
            CoroutineScope(Dispatchers.IO).launch {
                val ingredientInfo = ingredientRepository.getProductForId(id)
                val product = Product(
                    0,
                    userRepository.getUser()!!.id,
                    ingredientInfo.name,
                    "",
                    1,
                    ingredientInfo.nutrition.nutrients.find { it.name == "Calories" }?.amount!!.toInt(),
                    ingredientInfo.nutrition.nutrients.find { it.name == "Carbohydrates" }?.amount!!.toInt(),
                    ingredientInfo.nutrition.nutrients.find { it.name == "Protein" }?.amount!!.toInt(),
                    ingredientInfo.nutrition.nutrients.find { it.name == "Fat" }?.amount!!.toInt(),
                    eatingTime
                )
                withContext(Dispatchers.Main) {
                    isDataLoaded = true
                    _product.value = product
                }
            }
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                val product = usersProductRepository.getProductFromId(id)
                withContext(Dispatchers.Main) {
                    isDataLoaded = true
                    _product.value = product
                }
            }
        }
    }

    fun saveButtonClicked(capacity: Int, eatingTime: EatingTime) {
        val product = _product.value!!.copy(capacity = capacity, eatingTime = eatingTime)
        CoroutineScope(Dispatchers.IO).launch {
            usersProductRepository.addProduct(product)
            withContext(Dispatchers.Main) {
                _isDataSaved.value = true
            }
        }
    }

}