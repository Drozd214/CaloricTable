package lvivpolytechnic.com.example.calorictable.ui.main.fragments.ration

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lvivpolytechnic.com.example.calorictable.CaloricTableApplication
import lvivpolytechnic.com.example.calorictable.databinding.FragmentRationBinding
import lvivpolytechnic.com.example.calorictable.models.EatingTime
import lvivpolytechnic.com.example.calorictable.models.Product
import lvivpolytechnic.com.example.calorictable.ui.productinfo.ProductInfoActivity
import lvivpolytechnic.com.example.calorictable.ui.productpicker.ProductPickerActivity
import javax.inject.Inject

class RationFragment : Fragment() {

    private lateinit var binding: FragmentRationBinding

    private lateinit var viewModel: RationViewModel
    @Inject lateinit var factory: RationViewModelFactory

    private lateinit var breakfastAdapter: ProductAdapter
    private lateinit var dinnerAdapter: ProductAdapter
    private lateinit var secondDinnerAdapter: ProductAdapter
    private lateinit var supperAdapter: ProductAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        inject()
        super.onViewCreated(view, savedInstanceState)
        preInit()
        init()
    }

    private fun inject() {
        (requireContext().applicationContext as CaloricTableApplication)
                .getAppComponent()
                .createRationComponent()
                .create(this)
                .inject(this)
    }

    private fun preInit() {
        viewModel = ViewModelProviders.of(this, factory).get(RationViewModel::class.java)
        viewModel.init()
    }

    private fun init() {
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.breakfastItem.buttonContainer.setOnClickListener {
            viewModel.isBreakfastItemClicked()
        }

        binding.dinnerItem.buttonContainer.setOnClickListener {
            viewModel.isDinnerItemClicked()
        }

        binding.secondDinnerItem.buttonContainer.setOnClickListener {
            viewModel.isSecondDinnerItemClicked()
        }
        binding.supperItem.buttonContainer.setOnClickListener {
            viewModel.isSupperItemClicked()
        }

        with(binding.breakfastItem.itemProductsRecycleView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ProductAdapter(listOf()).apply {
                onItemDeleteButtonCLickedListener = {product, position ->
                    viewModel.deleteProduct(product)
                }

                onItemCLickedListener = {product ->
                    launchProductInfo(product)
                }
            }.also {
                breakfastAdapter = it
            }
        }

        with(binding.dinnerItem.itemProductsRecycleView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ProductAdapter(listOf()).apply {
                onItemDeleteButtonCLickedListener = {product, position ->
                    viewModel.deleteProduct(product)
                }

                onItemCLickedListener = {product ->
                    launchProductInfo(product)
                }
            }.also {
                dinnerAdapter = it
            }
        }

        with(binding.secondDinnerItem.itemProductsRecycleView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ProductAdapter(listOf()).apply {
                onItemDeleteButtonCLickedListener = {product, position ->
                    viewModel.deleteProduct(product)
                }

                onItemCLickedListener = {product ->
                    launchProductInfo(product)
                }
            }.also {
                secondDinnerAdapter = it
            }
        }

        with(binding.supperItem.itemProductsRecycleView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ProductAdapter(listOf()).apply {
                onItemDeleteButtonCLickedListener = {product, position ->
                    viewModel.deleteProduct(product)
                }

                onItemCLickedListener = {product ->
                    launchProductInfo(product)
                }
            }.also {
                supperAdapter = it
            }
        }

        with(binding) {
            breakfastItem.addProductButton.setOnClickListener { addNewProduct(EatingTime.BREAKFAST) }
            dinnerItem.addProductButton.setOnClickListener { addNewProduct(EatingTime.DINNER) }
            secondDinnerItem.addProductButton.setOnClickListener { addNewProduct(EatingTime.SECOND_DINNER) }
            supperItem.addProductButton.setOnClickListener { addNewProduct(EatingTime.SUPPER) }
        }
    }

    private fun initObservers() {
        viewModel.products.observe(requireActivity(), Observer {
            updateUI(it)
        })

        viewModel.isBreakfastProductsVisible.observe(requireActivity(), Observer {
            changeProductsVisibility(binding.breakfastItem.itemProductsRecycleView, it)
        })

        viewModel.isDinnerProductsVisible.observe(requireActivity(), Observer {
            changeProductsVisibility(binding.dinnerItem.itemProductsRecycleView, it)
        })

        viewModel.issecondDinnerProductsVisible.observe(requireActivity(), Observer {
            changeProductsVisibility(binding.secondDinnerItem.itemProductsRecycleView, it)
        })

        viewModel.isSupperProductsVisible.observe(requireActivity(), Observer {
            changeProductsVisibility(binding.supperItem.itemProductsRecycleView, it)
        })
    }

    private fun updateUI(products: List<Product>) {
        val breakfastProducts = mutableListOf<Product>()
        val dinnerProducts = mutableListOf<Product>()
        val secondDinnerProducts = mutableListOf<Product>()
        val supperProducts = mutableListOf<Product>()

        for(product in products) {
            when(product.eatingTime) {
                EatingTime.BREAKFAST -> breakfastProducts.add(product)
                EatingTime.DINNER -> dinnerProducts.add(product)
                EatingTime.SECOND_DINNER -> secondDinnerProducts.add(product)
                EatingTime.SUPPER -> supperProducts.add(product)
            }
        }

        binding.breakfastItem.eatingTimeName.text = "Сніданок"
        binding.breakfastItem.totalCalories.text = calculateTotalCalories(breakfastProducts).toString()
        breakfastAdapter.products = breakfastProducts
        breakfastAdapter.notifyDataSetChanged()

        binding.dinnerItem.eatingTimeName.text = "Обід"
        binding.dinnerItem.totalCalories.text = calculateTotalCalories(dinnerProducts).toString()
        dinnerAdapter.products = dinnerProducts
        dinnerAdapter.notifyDataSetChanged()

        binding.secondDinnerItem.eatingTimeName.text = "Другий обід"
        binding.secondDinnerItem.totalCalories.text = calculateTotalCalories(secondDinnerProducts).toString()
        secondDinnerAdapter.products = secondDinnerProducts
        secondDinnerAdapter.notifyDataSetChanged()

        binding.supperItem.eatingTimeName.text = "Вечеря"
        binding.supperItem.totalCalories.text = calculateTotalCalories(supperProducts).toString()
        supperAdapter.products = supperProducts
        supperAdapter.notifyDataSetChanged()
    }

    private fun calculateTotalCalories(products: List<Product>): Int {
        var totalCalories = 0
        for(product in products) {
            totalCalories += product.calories * product.capacity
        }
        return totalCalories / 100
    }

    private fun changeProductsVisibility(productsRecycleView: RecyclerView, isVisible: Boolean) {
        productsRecycleView.visibility = if(isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun addNewProduct(eatingTime: EatingTime) {
        startActivity(Intent(requireContext(), ProductPickerActivity::class.java).apply {
            putExtra("eatingTime", eatingTime)
        })
    }

    private fun launchProductInfo(product: Product) {
        startActivity(Intent(requireContext(), ProductInfoActivity::class.java).apply {
            putExtra("isNewProduct", false)
            putExtra("productId", product.id)
            putExtra("eatingTime", EatingTime.BREAKFAST)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.init()
    }

}