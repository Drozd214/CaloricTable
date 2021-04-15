package lvivpolytechnic.com.example.calorictable.di.subcomponents.interfaces

import lvivpolytechnic.com.example.calorictable.di.subcomponents.activities.MainComponent
import lvivpolytechnic.com.example.calorictable.di.subcomponents.activities.ProductInfoComponent
import lvivpolytechnic.com.example.calorictable.di.subcomponents.activities.ProductPickerComponent
import lvivpolytechnic.com.example.calorictable.di.subcomponents.activities.RegistrationComponent

interface ExposeActivitySubcomponent {

    fun createRegistrationComponent(): RegistrationComponent.Factory
    fun createMainComponent(): MainComponent.Factory
    fun createProductPickerComponent(): ProductPickerComponent.Factory
    fun createProductInfoComponent(): ProductInfoComponent.Factory

}