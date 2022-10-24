package com.moviles.practicanavegacioncasera.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Persona
import com.moviles.practicanavegacioncasera.dal.entities.Restaurante
import com.moviles.practicanavegacioncasera.repository.PersonaRepository
import com.moviles.practicanavegacioncasera.repository.RestauranteRepository
import com.moviles.practicanavegacioncasera.ui.adapters.PersonaAdapter
import com.moviles.practicanavegacioncasera.ui.adapters.RestauranteAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [PersonaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonaFragment : Fragment(), PersonaAdapter.OnPersonaClickListener {
    private lateinit var adapter: PersonaAdapter
    private lateinit var lstPersona: RecyclerView
    private lateinit var btnCreatePersona: FloatingActionButton
    private lateinit var btnback: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_persona, container, false)
        lstPersona = view.findViewById(R.id.personaList)
        btnCreatePersona = view.findViewById(R.id.btnCreatePersona)
        btnback = view.findViewById(R.id.backpersona)
        setupRecyclerView()
        setupEventListeners()
        return view
    }

    private fun setupEventListeners() {
        btnCreatePersona.setOnClickListener {
            val action = PersonaFragmentDirections.actionPersonaFragmentToFormPersonaFragment()
            findNavController().navigate(action)
        }
        btnback.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        val persona = PersonaRepository.getAllPersonas(requireContext())
        adapter = PersonaAdapter(persona as ArrayList<Persona>, this)
        lstPersona.adapter = adapter
        lstPersona.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun refreshData() {
        val persona = PersonaRepository.getAllPersonas(requireContext())
        adapter.refreshData(persona as ArrayList<Persona>)
    }

    override fun onPersonaEdit(persona: Persona) {
        val datos= Bundle()
        datos.putInt("idPersona", persona.id)
        val action = PersonaFragmentDirections.actionPersonaFragmentToFormPersonaFragment()
        action.arguments?.putAll(datos)
        findNavController().navigate(action)
    }

    override fun onPersonaDelete(persona: Persona) {
        PersonaRepository.delete(persona, requireContext())
        adapter.removeItem(persona)
    }
}