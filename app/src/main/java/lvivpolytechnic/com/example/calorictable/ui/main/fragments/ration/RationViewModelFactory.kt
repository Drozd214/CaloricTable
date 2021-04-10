package lvivpolytechnic.com.example.calorictable.ui.main.fragments.ration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepository
import lvivpolytechnic.com.example.calorictable.ui.main.fragments.commoninformation.CommonInformationViewModel

class RationViewModelFactory(
    private val usersProductRepository: UsersProductRepository,
    private val userRepository: UserRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RationViewModel(
                userRepository,
                usersProductRepository
        ) as T
    }

}