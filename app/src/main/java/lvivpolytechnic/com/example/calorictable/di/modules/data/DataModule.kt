package lvivpolytechnic.com.example.calorictable.di.modules.data

import dagger.Module
import lvivpolytechnic.com.example.calorictable.di.modules.data.database.DatabaseModule
import lvivpolytechnic.com.example.calorictable.di.modules.data.remote.RemoteModule
import lvivpolytechnic.com.example.calorictable.di.modules.data.repositories.RepositoriesModule
import lvivpolytechnic.com.example.calorictable.di.modules.data.repositories.StoresModule

@Module(
    includes = [
        DatabaseModule::class,
        RemoteModule::class,
        StoresModule::class,
        RepositoriesModule::class
    ]
)
object DataModule