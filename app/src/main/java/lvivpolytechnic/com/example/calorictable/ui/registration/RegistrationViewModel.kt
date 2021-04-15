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
            val products: List<Product> = listOf()
            usersProductsRepository.addProducts(products)
            withContext(Dispatchers.Main) {
                isRegisterSuccess.value = true
            }
        }
    }
}