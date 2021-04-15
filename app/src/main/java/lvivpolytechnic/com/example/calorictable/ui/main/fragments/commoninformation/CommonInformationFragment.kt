package lvivpolytechnic.com.example.calorictable.ui.main.fragments.commoninformation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import lvivpolytechnic.com.example.calorictable.CaloricTableApplication
import lvivpolytechnic.com.example.calorictable.R
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientinfo.IngredientInfoAPI
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientSearch
import lvivpolytechnic.com.example.calorictable.api.ingredients.ingredientsearch.IngredientSearchAPI
import lvivpolytechnic.com.example.calorictable.databinding.FragmentCommonInformationBinding
import lvivpolytechnic.com.example.calorictable.models.User
import retrofit2.await
import retrofit2.awaitResponse
import java.time.LocalDate
import javax.inject.Inject

class CommonInformationFragment : Fragment() {

    private lateinit var binding: FragmentCommonInformationBinding

    private lateinit var viewModel: CommonInformationViewModel
    @Inject lateinit var factory: CommonInformationViewModelFactory

    @Inject lateinit var ingredientSearchAPI: IngredientSearchAPI
    @Inject lateinit var ingredientInfoAPI: IngredientInfoAPI

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCommonInformationBinding.inflate(inflater, container, false)
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
                .createCommonInformationComponent()
                .create(this)
                .inject(this)
    }

    private fun preInit() {
        viewModel = ViewModelProviders.of(this, factory).get(CommonInformationViewModel::class.java)
    }

    private fun init() {
        initUI()
        initObservers()

        CoroutineScope(Dispatchers.Unconfined).launch {
            val ingredients = ingredientSearchAPI.getIngredientSearch("banana")
            val ing = ingredients.body()?.results
            if(ing != null) {
                val id = ing[0].id
                val ingredientInfo = ingredientInfoAPI.getIngredientInfo(id)
                val info = ingredientInfo.body()
            }
        }
    }

    private fun initUI() {
        binding.ageItem.parameterName.text = "Рік: "
        binding.weightItem.parameterName.text = "Вага: "

        binding.totalCaloricItem.progressTitle.text = "Загальна\nкалорійність"
        binding.carbohydrateItem.progressTitle.text = "Вуглеводи"
        binding.proteinItem.progressTitle.text = "Білки"
        binding.fatItem.progressTitle.text = "Жири"

        binding.ageItem.addParameterButton.setOnClickListener {
            Toast.makeText(requireContext(), "Функціонал у розробці", Toast.LENGTH_SHORT).show()
        }

        binding.weightItem.addParameterButton.setOnClickListener {
            Toast.makeText(requireContext(), "Функціонал у розробці", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initObservers() {
        viewModel.currentUser.observe(requireActivity(), Observer { currentUser ->
            updateUi(currentUser)
        })
    }

    private fun updateUi(user: User) {
        binding.ageItem.parameterValue.text = user.yearOfBirth.toString()
        binding.weightItem.parameterValue.text = user.weight.toString()

        if(user.profileImage != null) {
            Glide.with(this)
                    .load(user.profileImage)
                    .into(binding.profileImageView)
        } else binding.profileImageView.setBackgroundResource(R.drawable.default_user_image)

        var totalCalories = 0
        var carbohydrates = 0
        var protein = 0
        var fat = 0

        for(product in user.productsList) {
            val productsCapacity = product.capacity
            totalCalories += productsCapacity * product.calories / 100
            carbohydrates += productsCapacity * product.carbohydrates / 100
            protein += productsCapacity * product.protein / 100
            fat += productsCapacity * product.fat / 100
        }

        //Apply that 100 grams is recomended value and 2000 kcal too
        with(binding.totalCaloricItem) {
            progressCurrentValue.text = "$totalCalories ккал з"
            progressRecommendedValue.text = "1800"
            progressValue.text = "${totalCalories * 100 / 1800} %"
            progressBar.progress = totalCalories * 100 / 1800  //in percentage
        }

        with(binding.carbohydrateItem) {
            progressCurrentValue.text = "$carbohydrates г. з"
            progressRecommendedValue.text = "200"
            progressValue.text = "${carbohydrates * 100 / 200} %"
            progressBar.progress = carbohydrates * 100 / 200
        }

        with(binding.proteinItem) {
            progressCurrentValue.text = "$protein г. з"
            progressRecommendedValue.text = "130"
            progressValue.text = "${protein * 100 / 130} %"
            progressBar.progress = protein * 100 / 130
        }

        with(binding.fatItem) {
            progressCurrentValue.text = "$fat г. з"
            progressRecommendedValue.text = "60"
            progressValue.text = "${fat * 100 / 60} %"
            progressBar.progress = fat * 100 / 60
        }
    }

}