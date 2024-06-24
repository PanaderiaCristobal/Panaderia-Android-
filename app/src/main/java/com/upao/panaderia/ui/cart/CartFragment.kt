package com.upao.panaderia.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.upao.panaderia.views.NiubizActivity
import com.upao.panaderia.adapters.CartAdapter
import com.upao.panaderia.controllers.PedidoController
import com.upao.panaderia.databinding.FragmentCartBinding
import com.upao.panaderia.helpers.SharedPreferencesManager
import com.upao.panaderia.models.adaptersModel.ProductAdapterModel
import com.upao.panaderia.models.requestModel.PedidosRequest
import java.util.ArrayList

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private lateinit var cartAdapter: CartAdapter
    private lateinit var products: ArrayList<ProductAdapterModel>
    private lateinit var pedidoController: PedidoController

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pedidoController = PedidoController(requireContext())

        var total = 0.0
        val getUser = SharedPreferencesManager.getUserData(requireContext())
        var userLogged = ""
        if(getUser != null) {
            val user = getUser.split(",")
            userLogged = user.get(0)
        }
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
            products.forEach {
                total += it.price
            }
            val pedido = PedidosRequest(total = total, estado = "Pendiente", email= userLogged)

            pedidoController.createPedido(requireContext(), pedido) { idPedido ->
                if (idPedido != "") {
                    val i = Intent(requireContext(), NiubizActivity::class.java)
                    i.putExtra("idPedido", idPedido)
                    startActivity(i)
                    requireActivity().finish()
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showProduct() {
        val savedProducts = SharedPreferencesManager.getCartProducts(requireContext())
        savedProducts?.forEach {
            print(it)
            val productData = it.split(",")
            if (productData.size == 4){
                val title = productData[0]
                val description = productData[1]
                val image = productData[2]
                val price = productData[3].toDoubleOrNull() ?: 0.0
                products.add(ProductAdapterModel(title, description, image, price))
            }
        }
    }
}