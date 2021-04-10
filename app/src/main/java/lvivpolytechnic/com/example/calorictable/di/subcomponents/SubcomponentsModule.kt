package lvivpolytechnic.com.example.calorictable.di.subcomponents

import dagger.Module
import lvivpolytechnic.com.example.calorictable.di.subcomponents.activities.ActivitiesModule
import lvivpolytechnic.com.example.calorictable.di.subcomponents.fragments.FragmentsModule

@Module(
    includes = [
        ActivitiesModule::class,
        FragmentsModule::class
    ]
)
object SubcomponentsModule