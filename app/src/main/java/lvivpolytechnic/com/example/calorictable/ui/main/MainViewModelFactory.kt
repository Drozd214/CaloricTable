package lvivpolytechnic.com.example.calorictable.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.ui.registration.RegistrationViewModel

class MainViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(userRepository) as T
    }

}