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
import com.moviles.practicanavegacioncasera.dal.entities.Hamburguesa
import com.moviles.practicanavegacioncasera.repository.HamburguesaRepository
import com.moviles.practicanavegacioncasera.ui.principal.adapters.ListHambAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [ListHambFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListHambFragment : Fragment(), ListHambAdapter.OnHamburguesaClickListener {

    private lateinit var adapter: ListHambAdapter
    private lateinit var lstHamburguesa: RecyclerView
    private lateinit var btnback: FloatingActionButton
    private var idPersona = 0
    private var idRestaurante = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_hamb, container, false)
        idPersona = arguments?.getInt("idPersona")!!
        idRestaurante= arguments?.getInt("idRestaurante")!!
        lstHamburguesa = view.findViewById(R.id.listHamb)
        btnback = view.findViewById(R.id.goBack2)
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
        val hamburguesa = HamburguesaRepository.getHamburguesaByRestaurante(idRestaurante, requireContext())
        adapter = ListHambAdapter(hamburguesa as ArrayList<Hamburguesa>, this)
        lstHamburguesa.adapter = adapter
        lstHamburguesa.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onHamburguesaVer(hamburguesa: Hamburguesa) {
        val datos= Bundle()
        datos.putInt("idPersona", idPersona)
        datos.putInt("idHamburguesa", hamburguesa.id)
        val action = ListHambFragmentDirections.actionListHambFragmentToVerHambFragment()
        action.arguments?.putAll(datos)
        findNavController().navigate(action)    }

}