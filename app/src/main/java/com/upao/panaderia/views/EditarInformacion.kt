package com.upao.panaderia.views

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.upao.panaderia.R
import com.upao.panaderia.databinding.ActivityEditarInformacionBinding

class EditarInformacion : AppCompatActivity() {

    private lateinit var binding: ActivityEditarInformacionBinding
    private lateinit var fireBaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarInformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fireBaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)


        cargarInformacion()

        //Volver
        binding.IbRegresar.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnActualizar.setOnClickListener {
            validarInformacion()
        }

    }

    private var nombres = ""
    private var apellidos = ""


    private fun validarInformacion() {
        nombres = binding.etNombres.text.toString().trim()
        apellidos = binding.etApellidos.text.toString().trim()

        if (nombres.isEmpty()) {
            binding.etNombres.error = "Ingrese Nombres"
            binding.etNombres.requestFocus()
        } else if (apellidos.isEmpty()) {
            binding.etApellidos.error = "Ingrese Apellidos"
            binding.etApellidos.requestFocus()
        } else {
            actualizarInformacion()
        }
    }

    private fun actualizarInformacion() {
        progressDialog.setMessage("Actualizando Información")
        progressDialog.show()

        val hasMap: HashMap<String, Any> = HashMap()

        hasMap["nombres"] = nombres
        hasMap["Apellidos"] = apellidos

        val ref = FirebaseDatabase.getInstance().getReference("Usuarios")
        ref.child(fireBaseAuth.uid!!)
            .updateChildren(hasMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(
                    applicationContext,
                    "Se actualizó su información",
                    Toast.LENGTH_SHORT
                ).show()
            }

            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(
                    applicationContext,
                    "${e.message}",
                    Toast.LENGTH_SHORT
                ).show()

            }
    }

    private fun cargarInformacion() {

        val ref = FirebaseDatabase.getInstance().getReference("Usuarios")
        ref.child("${fireBaseAuth.uid}")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    // Obtener los datos del usuario desde el snapshot
                    val nombres = "${snapshot.child("nombres").value}"
                    val apellidos = "${snapshot.child("Apellidos").value}"
                    val imagen = "${snapshot.child("imagen").value}"


                    // Actualizar los campos de texto con los nombres y apellidos del usuario
                    binding.etNombres.setText(nombres)
                    binding.etApellidos.setText(apellidos)

                    try {
                        // Cargar la imagen de perfil del usuario usando Glide
                        Glide.with(applicationContext)
                            .load(imagen)
                            .placeholder(R.drawable.ic_img_perfil)
                            .into(binding.ivPerfil)
                    } catch (e: Exception) {
                        Toast.makeText(
                            applicationContext,
                            "${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

    }


}