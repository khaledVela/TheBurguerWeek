package com.moviles.practicanavegacioncasera.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Hamburguesa
import com.moviles.practicanavegacioncasera.repository.PersonaRepository

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var btnGoToRestaurante: Button
    private lateinit var btnGoToPersona: Button
    private lateinit var btnGoToHamburguesa: Button
    private lateinit var btnGoToReview: Button
    private lateinit var usuario: EditText
    private lateinit var contrasena: EditText
    private lateinit var btnGoToList: Button
    private lateinit var btnGoToFormPersona: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        btnGoToRestaurante = view.findViewById(R.id.goToRestaurante)
        btnGoToPersona = view.findViewById(R.id.GoToPersona)
        btnGoToHamburguesa = view.findViewById(R.id.goToHamburguesa)
        btnGoToReview = view.findViewById(R.id.goToReviews)
        usuario = view.findViewById(R.id.usuario)
        contrasena = view.findViewById(R.id.contrasena)
        btnGoToList = view.findViewById(R.id.goToList)
        btnGoToFormPersona = view.findViewById(R.id.GoToFormPersona)

        setupListeners()
        return view
    }

    private fun setupListeners() {
        btnGoToList.setOnClickListener {
            val persona = PersonaRepository.getAllPersonas(requireContext())
            for (p in persona) {
                if (p.nombre == usuario.text.toString() && p.contrasena == contrasena.text.toString()) {
                    val datos= Bundle()
                    datos.putInt("idPersona", p.id)
                    val action = HomeFragmentDirections.actionHomeFragmentToListRestFragment()
                    action.arguments?.putAll(datos)
                    findNavController().navigate(action)
                }
            }
        }

        btnGoToFormPersona.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToFormPersonaFragment()
            findNavController().navigate(action)
        }

        btnGoToRestaurante.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToRestauranteFragment2()
            findNavController().navigate(action)
        }
        btnGoToPersona.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToPersonaFragment()
            findNavController().navigate(action)
        }
        btnGoToHamburguesa.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToHamburguesaFragment()
            findNavController().navigate(action)
        }
        btnGoToReview.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToReviewFragment2()
            findNavController().navigate(action)
        }
    }
}