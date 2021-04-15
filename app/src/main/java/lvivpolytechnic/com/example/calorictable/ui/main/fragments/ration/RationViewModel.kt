package lvivpolytechnic.com.example.calorictable.ui.main.fragments.ration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepository
import lvivpolytechnic.com.example.calorictable.models.EatingTime
import lvivpolytechnic.com.example.calorictable.models.Product
import lvivpolytechnic.com.example.calorictable.models.User

class RationViewModel(
        private val userRepository: UserRepository,
        private val usersProductRepository: UsersProductRepository
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _isBreakfastProductsVisible = MutableLiveData<Boolean>(false)
    val isBreakfastProductsVisible: LiveData<Boolean> = _isBreakfastProductsVisible

    private val _isDinnerProductsVisible = MutableLiveData<Boolean>(false)
    val isDinnerProductsVisible: LiveData<Boolean> = _isDinnerProductsVisible

    private val _isSecondDinnerProductsVisible = MutableLiveData<Boolean>(false)
    val issecondDinnerProductsVisible: LiveData<Boolean> = _isSecondDinnerProductsVisible

    private val _isSupperProductsVisible = MutableLiveData<Boolean>(false)
    val isSupperProductsVisible: LiveData<Boolean> = _isSupperProductsVisible

    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            val gettingUser = userRepository.getUser()
            val gettingProducts = usersProductRepository.getProducts(gettingUser!!.id)
            withContext(Dispatchers.Main) {
                _user.value = gettingUser!!
                _products.value = gettingProducts
                //mockData()
            }
        }
    }

    fun mockData() {
        val mockedProducts = mutableListOf<Product>()
        mockedProducts.apply {
            val product = Product(0, 0, "", "", 0, 0, 0, 0, 0, EatingTime.BREAKFAST)
            add(product.copy(userId = user.value!!.id, calories = 150, capacity = 50, name = "1", eatingTime = EatingTime.BREAKFAST))
            add(product.copy(userId = user.value!!.id, calories = 400, capacity = 25, name = "2", eatingTime = EatingTime.SUPPER))
            add(product.copy(userId = user.value!!.id, calories = 527, capacity = 10, name = "3", eatingTime = EatingTime.SECOND_DINNER))
            add(product.copy(userId = user.value!!.id, calories = 50, capacity = 50, name = "4", eatingTime = EatingTime.BREAKFAST))
            add(product.copy(userId = user.value!!.id, calories = 15, capacity = 100, name = "5", eatingTime = EatingTime.DINNER))
            add(product.copy(userId = user.value!!.id, calories = 540, capacity = 15, name = "6", eatingTime = EatingTime.SUPPER))
            add(product.copy(userId = user.value!!.id, calories = 300, capacity = 30, name = "7", eatingTime = EatingTime.BREAKFAST))
            add(product.copy(userId = user.value!!.id, calories = 230, capacity = 50, name = "8", eatingTime = EatingTime.BREAKFAST))
            add(product.copy(userId = user.value!!.id, calories = 100, capacity = 61, name = "9", eatingTime = EatingTime.SECOND_DINNER))
            add(product.copy(userId = user.value!!.id, calories = 143, capacity = 35, name = "10", eatingTime = EatingTime.SUPPER))
            add(product.copy(userId = user.value!!.id, calories = 278, capacity = 50, name = "11", eatingTime = EatingTime.SUPPER))
            add(product.copy(userId = user.value!!.id, calories = 119, capacity = 123, name = "12", eatingTime = EatingTime.BREAKFAST))
        }
        _products.value = mockedProducts
    }

    fun isBreakfastItemClicked() {
        _isBreakfastProductsVisible.value = !_isBreakfastProductsVisible.value!!
    }

    fun isDinnerItemClicked() {
        _isDinnerProductsVisible.value = !_isDinnerProductsVisible.value!!
    }

    fun isSecondDinnerItemClicked() {
        _isSecondDinnerProductsVisible.value = !_isSecondDinnerProductsVisible.value!!
    }

    fun isSupperItemClicked() {
        _isSupperProductsVisible.value = !_isSupperProductsVisible.value!!
    }

    fun deleteProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            usersProductRepository.deleteProductFromId(product.id)
            val gettingProduct = usersProductRepository.getProducts(user.value!!.id)
            withContext(Dispatchers.Main) {
                _products.value = gettingProduct
            }
        }
    }

}