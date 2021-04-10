package lvivpolytechnic.com.example.calorictable.ui.registration

import android.net.Uri
import android.text.BoringLayout
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepositoryImpl
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepository
import lvivpolytechnic.com.example.calorictable.models.*

class RegistrationViewModel(
    private val userRepository: UserRepository,
    private val usersProductsRepository: UsersProductRepository
) : ViewModel() {

    private val _registrationResult: MutableLiveData<RegistrationResult> = MutableLiveData()
    val registrationResult: LiveData<RegistrationResult> = _registrationResult

    private val _isRegisterSuccess = MutableLiveData<Boolean>()
    val isRegisterSuccess = _isRegisterSuccess

    fun register(
        id: Int,
        profileImage: Uri?,
        sex: Sex,
        height: String,
        weight: String,
        yearOfBirth: String,
        goal: Goal
    ) {
        val user = User(
                id,
                profileImage,
                sex,
                height = if(height.isNotEmpty()) height.toInt() else 0,
                weight = if(weight.isNotEmpty()) weight.toInt() else 0,
                yearOfBirth = if(yearOfBirth.isNotEmpty()) yearOfBirth.toInt() else 0,
                goal = goal,
                productsList = listOf()
        )

        CoroutineScope(Dispatchers.IO).launch {
            userRepository.saveUser(user)
            val products: List<Product> = mockedData(user.id)
            usersProductsRepository.addProducts(products)
            withContext(Dispatchers.Main) {
                isRegisterSuccess.value = true
            }
        }
    }

    private fun mockedData(id: Int): List<Product> {
        val mockedProducts = mutableListOf<Product>()
        mockedProducts.apply {
            val product = Product(0, 0, "", "", 0, 0, 0, 0, 0, EatingTime.BREAKFAST)
            add(product.copy(userId = id, calories = 150, capacity = 50, name = "1", eatingTime = EatingTime.BREAKFAST))
            add(product.copy(userId = id, calories = 400, capacity = 25, name = "2", eatingTime = EatingTime.SUPPER))
            add(product.copy(userId = id, calories = 527, capacity = 10, name = "3", eatingTime = EatingTime.SECOND_DINNER))
            add(product.copy(userId = id, calories = 50, capacity = 50, name = "4", eatingTime = EatingTime.BREAKFAST))
            add(product.copy(userId = id, calories = 15, capacity = 100, name = "5", eatingTime = EatingTime.DINNER))
            add(product.copy(userId = id, calories = 540, capacity = 15, name = "6", eatingTime = EatingTime.SUPPER))
            add(product.copy(userId = id, calories = 300, capacity = 30, name = "7", eatingTime = EatingTime.BREAKFAST))
            add(product.copy(userId = id, calories = 230, capacity = 50, name = "8", eatingTime = EatingTime.BREAKFAST))
            add(product.copy(userId = id, calories = 100, capacity = 61, name = "9", eatingTime = EatingTime.SECOND_DINNER))
            add(product.copy(userId = id, calories = 143, capacity = 35, name = "10", eatingTime = EatingTime.SUPPER))
            add(product.copy(userId = id, calories = 278, capacity = 50, name = "11", eatingTime = EatingTime.SUPPER))
            add(product.copy(userId = id, calories = 119, capacity = 123, name = "12", eatingTime = EatingTime.BREAKFAST))
        }
        return mockedProducts
    }
}