package lvivpolytechnic.com.example.calorictable.di.subcomponents.activities

import dagger.Module

@Module(
    subcomponents = [
        RegistrationComponent::class,
        MainComponent::class,
        ProductPickerComponent::class,
        ProductInfoComponent::class
    ]
)
object ActivitiesModule