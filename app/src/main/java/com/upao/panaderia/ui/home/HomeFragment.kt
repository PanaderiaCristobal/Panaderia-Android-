package com.upao.panaderia.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.upao.panaderia.R
import com.upao.panaderia.adapters.ProductsAdapter
import com.upao.panaderia.databinding.FragmentHomeBinding
import com.upao.panaderia.helpers.SharedPreferencesManager
import com.upao.panaderia.models.adaptersModel.ProductAdapterModel
import java.util.ArrayList
import java.util.Date

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var products: ArrayList<ProductAdapterModel>

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val getUser = SharedPreferencesManager.getUserData(requireContext())
        val user = getUser?.split(",")
        val userLogged = getString(R.string.user_logged, user?.get(0), user?.get(1))
        binding.tvClientName.text = userLogged

        initializeButtons()
        setButtonListeners()

        products = ArrayList();
        productsAdapter = ProductsAdapter(products);
        uploadProducts()

        binding.rvProductos.layoutManager = GridLayoutManager(context, 2)
        binding.rvProductos.setHasFixedSize(true)
        binding.rvProductos.adapter = productsAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun uploadProducts() {
        products.add(ProductAdapterModel("Pan de molde", "Pan de molde", R.drawable.alfajor1, 2.5))
        products.add(ProductAdapterModel("Pan de molde integral", "Pan de molde integral", R.drawable.cocada, 3.0))
        products.add(ProductAdapterModel("Pan de molde con pasas", "Pan de molde con pasas", R.drawable.empanada1, 3.5))
        products.add(ProductAdapterModel("Pan de molde con nueces", "Pan de molde con nueces", R.drawable.huevos1, 4.0))
        products.add(ProductAdapterModel("Pan de molde con pasas y nueces", "Pan de molde con pasas y nueces", R.drawable.pan10, 4.5))
    }

    private fun initializeButtons() {
        setButtonState(binding.btnTortas, R.drawable.button_state_selected, R.color.button)
        setButtonState(binding.btnPanes, R.drawable.button_state_selected, R.color.no_select)
        setButtonState(binding.btnBocaditos, R.drawable.button_state_selected, R.color.no_select)
        setButtonState(binding.btnPiononos, R.drawable.button_state_selected, R.color.no_select)
    }

    private fun setButtonState(button: View, backgroundRes: Int, tintRes: Int) {
        button.setBackgroundResource(backgroundRes)
        button.backgroundTintList = ContextCompat.getColorStateList(requireContext(), tintRes)
    }

    private fun setButtonListeners() {
        binding.btnTortas.setOnClickListener {
            updateButtonStates(binding.btnTortas)
        }
        binding.btnPanes.setOnClickListener {
            updateButtonStates(binding.btnPanes)
        }
        binding.btnBocaditos.setOnClickListener {
            updateButtonStates(binding.btnBocaditos)
        }
        binding.btnPiononos.setOnClickListener {
            updateButtonStates(binding.btnPiononos)
        }
    }

    private fun updateButtonStates(selectedButton: View) {
        val buttons = listOf(binding.btnTortas, binding.btnPanes, binding.btnBocaditos, binding.btnPiononos)
        buttons.forEach { button ->
            if (button == selectedButton) {
                setButtonState(button, R.drawable.button_state_selected, R.color.button)
            } else {
                setButtonState(button, R.drawable.button_state_selected, R.color.no_select)
            }
        }
    }
}