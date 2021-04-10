package lvivpolytechnic.com.example.calorictable.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import lvivpolytechnic.com.example.calorictable.di.modules.core.CoreModule
import lvivpolytechnic.com.example.calorictable.di.modules.data.DataModule
import lvivpolytechnic.com.example.calorictable.di.subcomponents.interfaces.ExposeActivitySubcomponent
import lvivpolytechnic.com.example.calorictable.di.subcomponents.interfaces.ExposeFragmentSubcomponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreModule::class,
        DataModule::class
    ]
)
interface CaloricTableAppComponent : ExposeActivitySubcomponent, ExposeFragmentSubcomponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CaloricTableAppComponent
    }

}