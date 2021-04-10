package lvivpolytechnic.com.example.calorictable.di.subcomponents.activities

import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepository
import lvivpolytechnic.com.example.calorictable.ui.registration.RegistrationActivity
import lvivpolytechnic.com.example.calorictable.ui.registration.RegistrationViewModelFactory

@Subcomponent(modules = [RegistrationComponent.ComponentModule::class])
interface RegistrationComponent {

    fun inject(activity: RegistrationActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance view: RegistrationActivity): RegistrationComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideRegistrationViewModelFactory(userRepository: UserRepository, usersProductRepository: UsersProductRepository): RegistrationViewModelFactory {
            return RegistrationViewModelFactory(userRepository, usersProductRepository)
        }
    }

}