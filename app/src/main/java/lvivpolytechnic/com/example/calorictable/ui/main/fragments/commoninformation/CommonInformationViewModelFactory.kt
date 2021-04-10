package lvivpolytechnic.com.example.calorictable.ui.main.fragments.commoninformation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepositoryImpl
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepositoryImpl
import lvivpolytechnic.com.example.calorictable.ui.main.MainViewModel

class CommonInformationViewModelFactory(
        private val usersProductRepository: UsersProductRepository,
        private val userRepository: UserRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CommonInformationViewModel(
                usersProductRepository,
                userRepository
        ) as T
    }

}