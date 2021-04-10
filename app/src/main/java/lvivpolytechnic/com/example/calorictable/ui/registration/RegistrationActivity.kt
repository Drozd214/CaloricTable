package lvivpolytechnic.com.example.calorictable.ui.registration

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import lvivpolytechnic.com.example.calorictable.CaloricTableApplication
import lvivpolytechnic.com.example.calorictable.ui.main.MainActivity
import lvivpolytechnic.com.example.calorictable.R
import lvivpolytechnic.com.example.calorictable.databinding.ActivityRegistrationBinding
import lvivpolytechnic.com.example.calorictable.models.Goal
import lvivpolytechnic.com.example.calorictable.models.Sex
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity() {

    //доступ до view -> ViewBinding
    private lateinit var binding: ActivityRegistrationBinding

    //архітектура MVVM (Model-View-ViewModel)
    private lateinit var viewModel: RegistrationViewModel
    @Inject lateinit var factory: RegistrationViewModelFactory

    //шлях до фото користувача
    private var userImageUri: Uri? = null

    //коди для запитів
    companion object {
        private val IMAGE_PICK_CODE = 1000;
        private val PERMISSION_CODE = 1001;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        preInit()
        init()
    }

    //dependency injection
    private fun inject() {
        (applicationContext as CaloricTableApplication).getAppComponent()
            .createRegistrationComponent()
            .create(this)
            .inject(this)
    }

    //створення viewModel і прив'язка до макету
    private fun preInit() {
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this, factory).get(RegistrationViewModel::class.java)
    }

    private fun init() {
        initViews()
        subscribeViews()
    }

    private fun initViews() {
        initGoalEditText()
        initButtons()
    }

    //створення списку цілей
    private fun initGoalEditText() {
        val goals = listOf(
            resources.getString(R.string.lose_weight_goal),
            resources.getString(R.string.gain_weight_goal),
            resources.getString(R.string.keep_fit_goal)
        )

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, goals)
        binding.goalsSpinner.adapter = adapter
    }

    //обробка нажаття на кнопку аватару і реєстрації
    private fun initButtons() {
        binding.profileImageView.setOnClickListener {
            tryPickImage()
        }

        binding.registerButton.setOnClickListener {
            registerUser()
        }
    }

    //перевірка прав доступу до сховищ
    private fun tryPickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, PERMISSION_CODE)
            }
            else { pickImageFromGallery() }
        }
        else {
            pickImageFromGallery()
        }
    }

    //пошук зображеш у сховищі телефону
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    //обробка запитів
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                }
                else{ showShortToast(getString(R.string.permission_denied)) }
            }
        }
    }

    //обробка обрання фото
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            userImageUri = data?.data
            Glide.with(this)
                .load(userImageUri)
                .into(binding.profileImageView)
        }
    }

    //реєстрація користувача
    private fun registerUser() {
        //Will be better generic unic id (UUID) but it's a simple project, so we hard-coded it
        viewModel.register(
            id = 1,
            profileImage = userImageUri,
            sex = if(binding.maleRadioButton.isChecked) Sex.MALE else Sex.FEMALE,
            height = binding.heightEditText.text.toString(),
            weight = binding.weightEditText.text.toString(),
            yearOfBirth = binding.yearOfBirthEditText.text.toString(),
            goal = getGoal()
        )
    }

    private fun getGoal(): Goal {
        return when(binding.goalsSpinner.selectedItemPosition) {
            1 -> Goal.LOSE_WEIGHT
            2 -> Goal.GAIN_WEIGHT
            else -> Goal.KEEP_FIT
        }
    }

    private fun subscribeViews() {
        viewModel.isRegisterSuccess.observe(this, Observer {isRegisterSuccess ->
            if(isRegisterSuccess) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }

    //відображення повідомлення
    private fun showShortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}