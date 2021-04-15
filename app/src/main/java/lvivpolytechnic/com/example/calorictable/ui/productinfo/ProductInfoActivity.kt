package lvivpolytechnic.com.example.calorictable.ui.productinfo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lvivpolytechnic.com.example.calorictable.CaloricTableApplication
import lvivpolytechnic.com.example.calorictable.R
import lvivpolytechnic.com.example.calorictable.data.repositories.ingredient.IngredientRepository
import lvivpolytechnic.com.example.calorictable.databinding.ActivityProductInfoBinding
import lvivpolytechnic.com.example.calorictable.models.EatingTime
import lvivpolytechnic.com.example.calorictable.models.Product
import java.lang.Error
import javax.inject.Inject

class ProductInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductInfoBinding

    private lateinit var viewModel: ProductInfoViewModel
    @Inject lateinit var factory: ProductInfoViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        preInit()
        init()
    }

    private fun inject() {
        (applicationContext as CaloricTableApplication).getAppComponent()
            .createProductInfoComponent()
            .create(this)
            .inject(this)
    }

    private fun preInit() {
        binding = ActivityProductInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this, factory).get(ProductInfoViewModel::class.java)
    }

    private fun init() {
        initViews()
        initObservers()
    }

    private fun initObservers() {
        viewModel.product.observe(this, Observer {
            if(viewModel.isDataLoaded) {
                updateUI(it)
            }
        })

        viewModel.isDataSaved.observe(this, Observer {
            if(it) {
                if(intent.getBooleanExtra("isNewProduct", false)) {
                    setResult(Activity.RESULT_OK)
                }
                finish()
            }
        })
    }

    private fun updateUI(product: Product) {
        with(binding) {
            productName.text = product.name
            productCapacity.setText(product.capacity.toString())
            eatingTimeSpinner.setSelection(when(product.eatingTime) {
                EatingTime.BREAKFAST -> 0
                EatingTime.DINNER -> 1
                EatingTime.SECOND_DINNER -> 2
                EatingTime.SUPPER -> 3
            })
        }
    }

    private fun initViews() {
        val isNewProduct = intent.getBooleanExtra("isNewProduct", true)
        val id = intent.getIntExtra("productId", 0)
        val eatingTime = intent.getSerializableExtra("eatingTime") as EatingTime
        viewModel.getProduct(isNewProduct, id, eatingTime)

        val eatingTimes = listOf(
            "Сніданок",
            "Обід",
            "Другий обід",
            "Вечеря"
        )
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, eatingTimes)
        binding.eatingTimeSpinner.adapter = adapter

        binding.saveButton.setOnClickListener {
            val capacity = binding.productCapacity.text.toString()
            val eatingTime = when(binding.eatingTimeSpinner.selectedItemPosition) {
                0 -> EatingTime.BREAKFAST
                1 -> EatingTime.DINNER
                2 -> EatingTime.SECOND_DINNER
                else -> EatingTime.SUPPER
            }
            viewModel.saveButtonClicked(capacity.toInt(), eatingTime)
        }
    }

}