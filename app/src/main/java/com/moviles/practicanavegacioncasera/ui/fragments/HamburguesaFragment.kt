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
import com.moviles.practicanavegacioncasera.dal.entities.Hamburguesa
import com.moviles.practicanavegacioncasera.repository.HamburguesaRepository
import com.moviles.practicanavegacioncasera.ui.adapters.HamburguesaAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [HamburguesaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HamburguesaFragment : Fragment(), HamburguesaAdapter.OnHamburguesaClickListener {

    private lateinit var adapter: HamburguesaAdapter
    private lateinit var lstHamburguesa: RecyclerView
    private lateinit var btnAdd: FloatingActionButton
    private lateinit var btnback: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hamburguesa, container, false)
        lstHamburguesa = view.findViewById(R.id.hamburgesaList)
        btnAdd = view.findViewById(R.id.createHamburguesa)
        btnback = view.findViewById(R.id.backhamburguesa)
        setupRecyclerView()
        setupEventListeners()
        return view
    }

    private fun setupEventListeners() {
        btnAdd.setOnClickListener {
            val action =
                HamburguesaFragmentDirections.actionHamburguesaFragmentToFormHamburguesaFragment()
            findNavController().navigate(action)
        }
        btnback.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        val hamburguesas = HamburguesaRepository.getAllHamburguesas(requireContext())
        adapter = HamburguesaAdapter(hamburguesas as ArrayList<Hamburguesa>, this)
        lstHamburguesa.adapter = adapter
        lstHamburguesa.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    private fun refreshData() {
        val hamburguesas = HamburguesaRepository.getAllHamburguesas(requireContext())
        adapter.refreshData(hamburguesas as ArrayList<Hamburguesa>)
    }

    override fun onHamburguesaEdit(hamburguesa: Hamburguesa) {
        val datos = Bundle()
        datos.putInt("idHamburguesa", hamburguesa.id)
        val action =
            HamburguesaFragmentDirections.actionHamburguesaFragmentToFormHamburguesaFragment()
        action.arguments?.putAll(datos)
        findNavController().navigate(action)
    }

    override fun onHamburguesaDelete(hamburguesa: Hamburguesa) {
        HamburguesaRepository.delete(hamburguesa, requireContext())
        adapter.removeItem(hamburguesa)
    }

}