package lvivpolytechnic.com.example.calorictable

import android.app.Application
import lvivpolytechnic.com.example.calorictable.di.CaloricTableAppComponent
import lvivpolytechnic.com.example.calorictable.di.DaggerCaloricTableAppComponent

class CaloricTableApplication : Application() {

    private lateinit var appComponent: CaloricTableAppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerCaloricTableAppComponent.factory().create(applicationContext)
    }

    fun getAppComponent(): CaloricTableAppComponent = appComponent

}