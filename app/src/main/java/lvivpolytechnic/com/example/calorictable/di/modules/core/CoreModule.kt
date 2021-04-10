package lvivpolytechnic.com.example.calorictable.di.modules.core

import dagger.Module
import lvivpolytechnic.com.example.calorictable.di.subcomponents.SubcomponentsModule

@Module(
    includes = [
        SubcomponentsModule::class
    ]
)
object CoreModule