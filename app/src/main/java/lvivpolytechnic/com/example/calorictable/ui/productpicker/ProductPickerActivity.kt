package lvivpolytechnic.com.example.calorictable.ui.productpicker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import lvivpolytechnic.com.example.calorictable.CaloricTableApplication
import lvivpolytechnic.com.example.calorictable.R
import lvivpolytechnic.com.example.calorictable.databinding.ActivityProductPickerBinding
import lvivpolytechnic.com.example.calorictable.models.EatingTime
import lvivpolytechnic.com.example.calorictable.ui.productinfo.ProductInfoActivity
import javax.inject.Inject

class ProductPickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductPickerBinding

    private lateinit var viewModel: ProductPickerViewModel
    @Inject lateinit var factory: ProductPickerViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        preInit()
        init()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    private fun inject() {
        (applicationContext as CaloricTableApplication).getAppComponent()
            .createProductPickerComponent()
            .create(this)
            .inject(this)
    }

    private fun preInit() {
        binding = ActivityProductPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProviders.of(this, factory).get(ProductPickerViewModel::class.java)
    }

    private fun init() {
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.autoCompleteTextView.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                if(text?.length!! > 2) viewModel.afterTextChanged(text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        binding.autoCompleteTextView.setOnItemClickListener { parent, view, position, _ ->
            viewModel.launchInfoActivity(position)
        }
    }

    private fun initObservers() {
        viewModel.products.observe(this, Observer {
            if(viewModel.isTextChange) {
                val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, it)
                binding.autoCompleteTextView.setAdapter(adapter)
                adapter.notifyDataSetChanged()
                viewModel.isTextChange = false
            }
        })

        viewModel.productId.observe(this, Observer {
            startActivityForResult(Intent(this, ProductInfoActivity::class.java).apply {
                putExtra("productId", it)
                putExtra("isNewProduct", true)
                val eatingTime = intent.getSerializableExtra("eatingTime") as EatingTime
                putExtra("eatingTime", eatingTime)
            }, 1)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK) {
            finish()
            return super.onActivityResult(requestCode, resultCode, data)
        }
    }

}