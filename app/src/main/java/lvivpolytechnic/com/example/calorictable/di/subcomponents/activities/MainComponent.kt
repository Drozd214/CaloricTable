package lvivpolytechnic.com.example.calorictable.di.subcomponents.activities

import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import lvivpolytechnic.com.example.calorictable.data.repositories.user.UserRepository
import lvivpolytechnic.com.example.calorictable.ui.main.MainActivity
import lvivpolytechnic.com.example.calorictable.ui.main.MainViewModelFactory

@Subcomponent(modules = [MainComponent.ComponentModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance view: MainActivity): MainComponent
    }

    @Module
    object ComponentModule {
        @Provides
        fun provideMainViewModelFactory(userRepository: UserRepository): MainViewModelFactory {
            return MainViewModelFactory(userRepository)
        }
    }
}