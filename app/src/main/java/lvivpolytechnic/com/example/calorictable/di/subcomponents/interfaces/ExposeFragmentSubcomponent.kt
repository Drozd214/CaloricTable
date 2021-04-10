package lvivpolytechnic.com.example.calorictable.di.subcomponents.interfaces

import lvivpolytechnic.com.example.calorictable.di.subcomponents.fragments.CommonInformationComponent
import lvivpolytechnic.com.example.calorictable.di.subcomponents.fragments.RationComponent

interface ExposeFragmentSubcomponent {

    fun createCommonInformationComponent(): CommonInformationComponent.Factory

    fun createRationComponent(): RationComponent.Factory

}