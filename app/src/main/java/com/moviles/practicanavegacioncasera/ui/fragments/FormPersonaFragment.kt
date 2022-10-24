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
import com.moviles.practicanavegacioncasera.dal.entities.Persona
import com.moviles.practicanavegacioncasera.repository.PersonaRepository

/**
 * A simple [Fragment] subclass.
 * Use the [FormPersonaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormPersonaFragment : Fragment() {

    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private lateinit var txtName: EditText
    private lateinit var txtPassword: EditText
    private var idPersona = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form_persona, container, false)

        btnSave = view.findViewById(R.id.btnSavePersona)
        btnCancel = view.findViewById(R.id.btnCancelPersona)
        txtName = view.findViewById(R.id.nameformpersona)
        txtPassword = view.findViewById(R.id.contraformpersona)
        idPersona = arguments?.getInt("idPersona") ?: 0

        if (idPersona > 0) {
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
        val persona = Persona(
            txtName.text.toString(),
            txtPassword.text.toString()
        )
        if (idPersona == 0) {
            PersonaRepository.insert(persona, requireContext())
        } else {
            persona.id = idPersona
            PersonaRepository.update(persona, requireContext())
        }
        requireActivity().onBackPressed()
    }

    private fun loadProductoForm() {
        val persona = PersonaRepository.getPersonaById(idPersona, requireContext()) ?: return
        txtName.setText(persona.nombre)
        txtPassword.setText(persona.contrasena)
    }

}