package com.moviles.practicanavegacioncasera.ui.principal.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Hamburguesa
import com.moviles.practicanavegacioncasera.dal.entities.Persona
import com.moviles.practicanavegacioncasera.dal.entities.Review
import com.moviles.practicanavegacioncasera.repository.HamburguesaRepository
import com.moviles.practicanavegacioncasera.repository.PersonaRepository
import com.moviles.practicanavegacioncasera.repository.ReviewRepository

/**
 * A simple [Fragment] subclass.
 * Use the [AnadirResenaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnadirResenaFragment : Fragment() {

    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private lateinit var txtPuntuacion: EditText
    private lateinit var txtDescripcion: EditText
    private lateinit var txtPersona: TextView
    private lateinit var txtHamburguesa: TextView

    private var idPersona = 0
    private var idHamburguesa = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_anadir_resena, container, false)
        btnSave = view.findViewById(R.id.Guardar)
        btnCancel = view.findViewById(R.id.back4)
        txtPuntuacion = view.findViewById(R.id.Puntuacion)
        txtDescripcion = view.findViewById(R.id.Descripcion)
        txtPersona = view.findViewById(R.id.Persona)
        txtHamburguesa = view.findViewById(R.id.Hamburguesa)

        idPersona = arguments?.getInt("idPersona")!!
        idHamburguesa = arguments?.getInt("idHamburguesa")!!

        txtPersona.text = getPersona(requireContext())?.nombre
        txtHamburguesa.text = getHamburguesa(requireContext())?.nombre

        setupEventListeners()
        return view
    }

    private fun setupEventListeners() {
        btnSave.setOnClickListener {
            saveReview()
        }
        btnCancel.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    fun getPersona(context: Context): Persona? {
        return PersonaRepository.getPersonaById(idPersona, context)
    }

    fun getHamburguesa(context: Context): Hamburguesa? {
        return HamburguesaRepository.getHamburguesaById(idHamburguesa, context)
    }

    private fun saveReview() {
        val puntuacion = txtPuntuacion.text.toString().toInt()
        val descripcion = txtDescripcion.text.toString()
        val review = Review(puntuacion, descripcion, idPersona, idHamburguesa)
        if (idPersona != 0 && idHamburguesa != 0) {
            ReviewRepository.insert(review, requireContext())
        }
        requireActivity().onBackPressed()
    }

}