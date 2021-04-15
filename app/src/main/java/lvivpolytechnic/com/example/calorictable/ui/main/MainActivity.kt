package lvivpolytechnic.com.example.calorictable.ui.main

import android.content.Intent
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.ERROR_NEGATIVE_BUTTON
import androidx.biometric.BiometricPrompt.PromptInfo
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lvivpolytechnic.com.example.calorictable.CaloricTableApplication
import lvivpolytechnic.com.example.calorictable.R
import lvivpolytechnic.com.example.calorictable.databinding.ActivityMainBinding
import lvivpolytechnic.com.example.calorictable.ui.main.fragments.commoninformation.CommonInformationFragment
import lvivpolytechnic.com.example.calorictable.ui.main.fragments.ration.RationFragment
import lvivpolytechnic.com.example.calorictable.ui.registration.RegistrationActivity
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    //доступ до view -> ViewBinding
    private lateinit var binding: ActivityMainBinding

    //архітектура MVVM (Model-View-ViewModel)
    private lateinit var viewModel: MainViewModel
    @Inject lateinit var factory: MainViewModelFactory

    private var biometricPrompt: BiometricPrompt? = null
    private val executor: Executor = Executors.newSingleThreadExecutor()

    var isCommonFragment = false

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        preInit()
        init()
    }

    private fun inject() {
        (applicationContext as CaloricTableApplication).getAppComponent()
            .createMainComponent()
            .create(this)
            .inject(this)
    }

    private fun preInit() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    private fun init() {
        initObservers()
        initBottomNavigationView()
        if (biometricPrompt == null) {
            biometricPrompt = BiometricPrompt(this, executor, callback)
        }
    }

    private fun initBottomNavigationView() = binding.bottomNavigationMenu.setOnNavigationItemSelectedListener(this)

    private fun initObservers() {
        viewModel.currentUser.observe(this, Observer {currentUser ->
            if(currentUser == null) {
                startActivity(Intent(this, RegistrationActivity::class.java))
            } else (
                    if(!viewModel.isAuthorized.value!!) checkAccess())
        })

        viewModel.isCommonFragmentVisible.observe(this, Observer {
            if(it) {
                loadFragment(CommonInformationFragment())
            } else {
                loadFragment(RationFragment())
            }
        })
    }

    private fun checkAccess() {
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                val promptInfo = buildBiometricPrompt()
                biometricPrompt!!.authenticate(promptInfo!!)
            }
//            else -> {
//                loadFragment(CommonInformationFragment())
//                viewModel.isAuthorized = true
//            }
        }
    }

    private fun buildBiometricPrompt(): PromptInfo? {
        return PromptInfo.Builder()
                .setTitle("Авторизуйтесь")
                .setSubtitle("FingerPrint Авторизація")
                .setDescription("Будь ласка, доторкніться пальцем до сканеру відбитків для входу")
                .setNegativeButtonText("Cancel")
                .build()
    }

    private val callback: BiometricPrompt.AuthenticationCallback =
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                        errorCode: Int,
                        errString: CharSequence
                ) {
                    if (errorCode == ERROR_NEGATIVE_BUTTON && biometricPrompt != null) biometricPrompt!!.cancelAuthentication()
                    runOnUiThread { snack((errString as String))}
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    runOnUiThread {
                        snack("Авторизовано")
                        viewModel.isUserAuthorized(true)
                        viewModel.setIsCommonFragmentVisible(true)
                    }
                }

                override fun onAuthenticationFailed() {
                    runOnUiThread { snack("Палець не розпізнано. Спробуйте ще раз")}
                }
            }

    private fun snack(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        if(!isFragmentAlreadySet(fragment)) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit()
            isCommonFragment = !isCommonFragment
        }
        return true
    }

    private fun isFragmentAlreadySet(fragment: Fragment): Boolean {
        return isCommonFragment == when(fragment) {
            is CommonInformationFragment -> true
            else -> false
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.navigationCommonInformation -> {
                viewModel.setIsCommonFragmentVisible(true)
                true
            }
            else -> {
                viewModel.setIsCommonFragmentVisible(false)
                true
            }
        }
    }
}