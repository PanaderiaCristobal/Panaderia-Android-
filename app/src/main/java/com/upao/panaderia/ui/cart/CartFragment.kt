package com.upao.panaderia.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.upao.panaderia.NiubizActivity
import com.upao.panaderia.R
import com.upao.panaderia.adapters.CartAdapter
import com.upao.panaderia.databinding.FragmentCartBinding
import com.upao.panaderia.helpers.SharedPreferencesManager
import com.upao.panaderia.models.adaptersModel.ProductAdapterModel
import java.util.ArrayList

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private lateinit var cartAdapter: CartAdapter
    private lateinit var products: ArrayList<ProductAdapterModel>

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        products = ArrayList();
        cartAdapter = CartAdapter(products);

        showProduct()

        binding.rvProductos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProductos.setHasFixedSize(true)
        binding.rvProductos.adapter = cartAdapter

        if (products.size > 0) {
            binding.btnSiguiente.visibility = View.VISIBLE
        } else {
            binding.btnSiguiente.visibility = View.GONE
        }

        binding.btnSiguiente.setOnClickListener {
            val i = Intent(requireContext(), NiubizActivity::class.java)
            startActivity(i)
            requireActivity().finish()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showProduct() {
        val savedProducts = SharedPreferencesManager.getCartProducts(requireContext())
        println(savedProducts)
        savedProducts?.forEach {
            val productData = it.split(",")
            if (productData.size == 4){
                val title = productData[0]
                val description = productData[1]
                val image = productData[2].toIntOrNull() ?: R.drawable.huevos1
                val price = productData[3].toDoubleOrNull() ?: 0.0
                products.add(ProductAdapterModel(title, description, image, price))
            }
        }
    }
}