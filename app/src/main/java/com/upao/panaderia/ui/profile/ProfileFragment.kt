package com.upao.panaderia.ui.profile

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.upao.panaderia.controllers.UserController
import com.upao.panaderia.databinding.FragmentProfileBinding
import com.upao.panaderia.helpers.OpcionesLoginActivity
import com.upao.panaderia.helpers.SharedPreferencesManager
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var userController: UserController

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userController = UserController(requireContext())

        val nombres = binding.tvNombres
        val apellidos = binding.tvApellidos
        val correo = binding.tvEmail
        val ptsFidelidad = binding.tvProveedor
        val tiempoRegistro = binding.tvTRegistro

        val getUser = SharedPreferencesManager.getUserData(requireContext())
        if (getUser != null) {
            val user = getUser.split(",")
            val userLogged = user[0]
            userController.getUser(requireContext(),userLogged) {
                nombres.text = it.nombre
                apellidos.text = it.apellido
                correo.text = it.email
                ptsFidelidad.text = it.ptsFidelidad.toString()
                tiempoRegistro.text = parseTimestampToDate(it.createdAt)
            }
        }

        binding.btnCerrarSesion.setOnClickListener {
            SharedPreferencesManager.removeUserData(requireContext())
            val i = Intent(requireContext(), OpcionesLoginActivity::class.java)
            startActivity(i)
            requireActivity().finish()

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseTimestampToDate(timestamp: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
        val dateTime = LocalDateTime.parse(timestamp, formatter)
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return dateTime.format(dateFormatter)
    }
}