package lvivpolytechnic.com.example.calorictable.di.subcomponents.fragments

import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepository
import lvivpolytechnic.com.example.calorictable.ui.main.fragments.commoninformation.CommonInformationViewModelFactory
import lvivpolytechnic.com.example.calorictable.ui.main.fragments.ration.RationFragment
import lvivpolytechnic.com.example.calorictable.ui.main.fragments.ration.RationViewModelFactory

@Subcomponent(modules = [RationComponent.ComponentModule::class])
interface RationComponent {

    fun inject(fragment: RationFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance view: RationFragment): RationComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideRationViewModelFactory(
                usersProductRepository: UsersProductRepository,
                usersRepository: UserRepository
        ): RationViewModelFactory {
            return RationViewModelFactory(
                    usersProductRepository,
                    usersRepository
            )
        }
    }
}