package lvivpolytechnic.com.example.calorictable.di.subcomponents.fragments

import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.data.repositories.usersproduct.UsersProductRepository
import lvivpolytechnic.com.example.calorictable.ui.main.fragments.commoninformation.CommonInformationFragment
import lvivpolytechnic.com.example.calorictable.ui.main.fragments.commoninformation.CommonInformationViewModelFactory

@Subcomponent(modules = [CommonInformationComponent.ComponentModule::class])
interface CommonInformationComponent {

    fun inject(fragment: CommonInformationFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance view: CommonInformationFragment): CommonInformationComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideCommonInformationViewModelFactory(
                usersProductRepository: UsersProductRepository,
                usersRepository: UserRepository
        ): CommonInformationViewModelFactory {
            return CommonInformationViewModelFactory(
                    usersProductRepository,
                    usersRepository
            )
        }
    }
}