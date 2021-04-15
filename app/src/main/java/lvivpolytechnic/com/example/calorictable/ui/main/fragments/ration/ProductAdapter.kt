package lvivpolytechnic.com.example.calorictable.ui.main.fragments.ration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lvivpolytechnic.com.example.calorictable.R
import lvivpolytechnic.com.example.calorictable.databinding.ItemProductBinding
import lvivpolytechnic.com.example.calorictable.models.Product

class ProductAdapter(
        var products: List<Product>
) : RecyclerView.Adapter<ProductHolder>() {

    private lateinit var binding: ItemProductBinding
    var onItemDeleteButtonCLickedListener: ((Product, Int) -> Unit)? = null
    var onItemCLickedListener: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = products[position]
        holder.bind(product, onItemDeleteButtonCLickedListener, onItemCLickedListener, position)
    }
}

class ProductHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        product: Product,
        deleteListener: ((Product, Int) -> Unit)?,
        itemClickedListener: ((Product) -> Unit)?,
        position: Int
    ) {
        //TODO bind button listener of delete action
        with(binding) {
            productName.text = product.name
            productValue.text = "${product.capacity} x 1g"
            calories.text = (product.calories * product.capacity / 100).toString()
            deleteProductButton.setOnClickListener {
                deleteListener?.invoke(product, position)
            }
            rootLayout.setOnClickListener {
                itemClickedListener?.invoke(product)
            }

        }
    }
}