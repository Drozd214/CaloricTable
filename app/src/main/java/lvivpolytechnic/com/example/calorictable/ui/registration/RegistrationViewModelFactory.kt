package lvivpolytechnic.com.example.calorictable.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepository

class RegistrationViewModelFactory(private val userRepository: UserRepository, private val usersProductRepository: UsersProductRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegistrationViewModel(userRepository, usersProductRepository) as T
    }

}