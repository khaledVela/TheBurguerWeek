package com.moviles.practicanavegacioncasera.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Restaurante
import com.moviles.practicanavegacioncasera.repository.RestauranteRepository
import com.moviles.practicanavegacioncasera.ui.adapters.RestauranteAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [RestauranteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestauranteFragment : Fragment(), RestauranteAdapter.OnRestauranteClickListener {

    private lateinit var adapter: RestauranteAdapter
    private lateinit var lstRestaurante: RecyclerView
    private lateinit var btnCreateRestaurante: FloatingActionButton
    private lateinit var btnback: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurante, container, false)
        lstRestaurante = view.findViewById(R.id.restauranteList)
        btnCreateRestaurante = view.findViewById(R.id.btnCreateRestaurante)
        btnback = view.findViewById(R.id.backrestaurante)
        setupRecyclerView()
        setupEventListeners()
        return view
    }

    private fun setupEventListeners() {
        btnCreateRestaurante.setOnClickListener {
            val action = RestauranteFragmentDirections.actionRestauranteFragment2ToFormRestauranteFragment()
            findNavController().navigate(action)
        }
        btnback.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        val restaurante = RestauranteRepository.getAllRestaurantes(requireContext())
        adapter = RestauranteAdapter(restaurante as ArrayList<Restaurante>, this)
        lstRestaurante.adapter = adapter
        lstRestaurante.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun refreshData() {
        val restaurante = RestauranteRepository.getAllRestaurantes(requireContext())
        adapter.refreshData(restaurante as ArrayList<Restaurante>)
    }

    override fun onRestauranteEdit(restaurante: Restaurante) {
        val datos=Bundle()
        datos.putInt("idRestaurante",restaurante.id)
        val action = RestauranteFragmentDirections.actionRestauranteFragment2ToFormRestauranteFragment()
        action.arguments?.putAll(datos)
        findNavController().navigate(action)
    }

    override fun onRestauranteDelete(restaurante: Restaurante) {
        RestauranteRepository.delete(restaurante, requireContext())
        adapter.removeItem(restaurante)
    }
}