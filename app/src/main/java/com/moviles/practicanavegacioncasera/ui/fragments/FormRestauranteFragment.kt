package com.moviles.practicanavegacioncasera.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Restaurante
import com.moviles.practicanavegacioncasera.repository.RestauranteRepository

/**
 * A simple [Fragment] subclass.
 * Use the [FormRestauranteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormRestauranteFragment : Fragment() {

    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private lateinit var txtName: EditText
    private var idRestaurante = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form_restaurante, container, false)

        btnSave = view.findViewById(R.id.btnSaveReceta)
        btnCancel = view.findViewById(R.id.btnCancelReceta)
        txtName = view.findViewById(R.id.txtRecetaName)
        idRestaurante = arguments?.getInt("idRestaurante") ?: 0

        if (idRestaurante > 0) {
            loadProductoForm()
        }
        setupEventListeners()

        return view
    }

    private fun setupEventListeners() {
        btnSave.setOnClickListener {
            saveProducto()
        }
        btnCancel.setOnClickListener {
            cancelProducto()
        }
    }

    private fun cancelProducto() {
        requireActivity().onBackPressed()
    }

    private fun saveProducto() {

        val restaurante = Restaurante(
            txtName.text.toString()
        )
        if(idRestaurante ==0){
            RestauranteRepository.insert(restaurante,requireContext())
        }else{
            restaurante.id = idRestaurante
            RestauranteRepository.update(restaurante,requireContext())
        }
        requireActivity().onBackPressed()
    }

    private fun loadProductoForm() {
        val restaurante =
            RestauranteRepository.getRestauranteById(idRestaurante, requireContext()) ?: return
        txtName.setText(restaurante.nombre)
    }

}