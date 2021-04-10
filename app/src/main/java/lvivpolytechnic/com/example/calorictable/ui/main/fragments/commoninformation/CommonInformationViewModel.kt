package lvivpolytechnic.com.example.calorictable.ui.main.fragments.commoninformation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepositoryImpl
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepositoryImpl
import lvivpolytechnic.com.example.calorictable.models.User

class CommonInformationViewModel(
        private val usersProductRepository: UsersProductRepository,
        private val userRepository: UserRepository
) : ViewModel() {

    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User> = _currentUser

    init {
        CoroutineScope(Dispatchers.Main).launch {
            val user = userRepository.getUser()
            val usersProducts = usersProductRepository.getProducts(user!!.id)
            val gettingUser = user.copy(productsList = usersProducts)

            withContext(Dispatchers.Main) {
                _currentUser.value = gettingUser
            }
        }
    }
}