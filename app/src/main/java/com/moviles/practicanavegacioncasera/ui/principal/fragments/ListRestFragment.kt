package com.moviles.practicanavegacioncasera.ui.principal.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Restaurante
import com.moviles.practicanavegacioncasera.repository.RestauranteRepository
import com.moviles.practicanavegacioncasera.ui.adapters.RestauranteAdapter
import com.moviles.practicanavegacioncasera.ui.fragments.HomeFragmentDirections
import com.moviles.practicanavegacioncasera.ui.principal.adapters.ListRestAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [ListRestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListRestFragment : Fragment(),ListRestAdapter.OnRestauranteClickListener{

    private lateinit var adapter: ListRestAdapter
    private lateinit var lstRestaurante: RecyclerView
    private lateinit var btnback: FloatingActionButton
    private var idPersona = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_rest, container, false)
        idPersona = arguments?.getInt("idPersona")!!
        lstRestaurante = view.findViewById(R.id.todosLosRest)
        btnback = view.findViewById(R.id.goBack)
        setupRecyclerView()
        setupEventListeners()
        return view
    }

    private fun setupEventListeners() {
        btnback.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        val restaurante = RestauranteRepository.getAllRestaurantes(requireContext())
        adapter = ListRestAdapter(restaurante as ArrayList<Restaurante>, this)
        lstRestaurante.adapter = adapter
        lstRestaurante.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onRestauranteVer(restaurante: Restaurante) {
        val datos= Bundle()
        datos.putInt("idRestaurante", restaurante.id)
        datos.putInt("idPersona", idPersona)
        val action = ListRestFragmentDirections.actionListRestFragmentToListHambFragment()
        action.arguments.putAll(datos)
        findNavController().navigate(action)
    }

}