package com.moviles.practicanavegacioncasera.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Hamburguesa
import com.moviles.practicanavegacioncasera.dal.entities.Persona
import com.moviles.practicanavegacioncasera.dal.entities.Review
import com.moviles.practicanavegacioncasera.repository.HamburguesaRepository
import com.moviles.practicanavegacioncasera.repository.PersonaRepository
import com.moviles.practicanavegacioncasera.repository.ReviewRepository

/**
 * A simple [Fragment] subclass.
 * Use the [FormReviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormReviewFragment : Fragment() {

    private lateinit var spinnerAdapter: TipoSpinnerPersonaAdapter
    private lateinit var spinnerAdapter2: TipoSpinnerHamburgueraAdapter
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private lateinit var txtPuntuacion: EditText
    private lateinit var txtDescripcion: EditText
    private lateinit var spPersona: Spinner
    private lateinit var spHamburguesa: Spinner
    private var idReview = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form_review, container, false)
        btnSave = view.findViewById(R.id.guardarReview)
        btnCancel = view.findViewById(R.id.cancelarReview)
        txtPuntuacion = view.findViewById(R.id.puntuacionFormReview)
        txtDescripcion = view.findViewById(R.id.descripcionFormReview)
        spPersona = view.findViewById(R.id.spPersona)
        spHamburguesa = view.findViewById(R.id.spHamburguesa)
        idReview = arguments?.getInt("idReview") ?: 0
        loadHamburguesas()
        loadPersonas()

        if(idReview != 0){
            loadReview()
        }
        setupEventListeners()
        return view
    }

    private fun loadReview() {
        val review = ReviewRepository.getReviewById(idReview, requireContext())?: return
        txtPuntuacion.setText(review.calificacion.toString())
        txtDescripcion.setText(review.comentario)
        val idHamburguesa = review.idHamburguesa
        val idPersona = review.idPersona
        val HamburguesaIndex= spinnerAdapter2.getIndexForHamburguera(idHamburguesa)
        val PersonaIndex= spinnerAdapter.getIndexForPersona(idPersona)
        spPersona.setSelection(PersonaIndex)
        spHamburguesa.setSelection(HamburguesaIndex)
    }

    private fun setupEventListeners() {
        btnSave.setOnClickListener {
            saveReview()
        }
        btnCancel.setOnClickListener {
            cancelReview()
        }
    }

    private fun cancelReview() {
        requireActivity().onBackPressed()
    }

    private fun saveReview() {
        val puntuacion = txtPuntuacion.text.toString().toInt()
        val descripcion = txtDescripcion.text.toString()
        val selectedPersonaPosition = spPersona.selectedItemPosition
        val selectedHamburguesaPosition = spHamburguesa.selectedItemPosition
        val persona = spPersona.adapter.getItem(selectedPersonaPosition) as Persona
        val hamburguesa = spHamburguesa.adapter.getItem(selectedHamburguesaPosition) as Hamburguesa
        val review = Review(puntuacion, descripcion, persona.id, hamburguesa.id)
        if(idReview == 0){
            ReviewRepository.insert(review, requireContext())
        }else{
            review.id = idReview
            ReviewRepository.update(review, requireContext())
        }
        requireActivity().onBackPressed()
    }

    private fun loadPersonas() {
        val personas = PersonaRepository.getAllPersonas(requireContext())
        spinnerAdapter = TipoSpinnerPersonaAdapter(requireContext(), personas as MutableList<Persona>)
        spPersona.adapter = spinnerAdapter
    }

    private fun loadHamburguesas() {
        val hamburguesas = HamburguesaRepository.getAllHamburguesas(requireContext())
        spinnerAdapter2 = TipoSpinnerHamburgueraAdapter(requireContext(), hamburguesas as MutableList<Hamburguesa>)
        spHamburguesa.adapter = spinnerAdapter2
    }
}