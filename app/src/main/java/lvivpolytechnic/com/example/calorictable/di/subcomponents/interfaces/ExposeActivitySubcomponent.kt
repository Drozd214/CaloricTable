package lvivpolytechnic.com.example.calorictable.di.subcomponents.interfaces

import lvivpolytechnic.com.example.calorictable.di.subcomponents.activities.MainComponent
import lvivpolytechnic.com.example.calorictable.di.subcomponents.activities.RegistrationComponent

interface ExposeActivitySubcomponent {

    fun createRegistrationComponent(): RegistrationComponent.Factory
    fun createMainComponent(): MainComponent.Factory

}