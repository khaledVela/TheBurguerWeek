package com.moviles.practicanavegacioncasera.ui.principal.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Review
import com.moviles.practicanavegacioncasera.repository.HamburguesaRepository
import com.moviles.practicanavegacioncasera.repository.ImageController
import com.moviles.practicanavegacioncasera.repository.ReviewRepository
import com.moviles.practicanavegacioncasera.ui.principal.adapters.ListReseñaAdapter
import com.moviles.practicanavegacioncasera.ui.principal.adapters.ListRestAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [VerHambFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VerHambFragment : Fragment() {
    private lateinit var lstReview: RecyclerView
    private lateinit var btnback: FloatingActionButton
    private lateinit var img: ImageView
    private lateinit var nombtxt: TextView
    private lateinit var descripcion: TextView
    private lateinit var btnReseña: Button

    private lateinit var adapter: ListReseñaAdapter
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
        val view = inflater.inflate(R.layout.fragment_ver_hamb, container, false)
        idPersona = arguments?.getInt("idPersona")!!
        idHamburguesa= arguments?.getInt("idHamburguesa")!!
        img = view.findViewById(R.id.fotoFin)
        nombtxt = view.findViewById(R.id.nombreFin)
        descripcion = view.findViewById(R.id.descripFin)
        btnReseña = view.findViewById(R.id.añadirReseña)
        lstReview = view.findViewById(R.id.reseñaFin)
        btnback = view.findViewById(R.id.back3)

        val hamburguesa = HamburguesaRepository.getHamburguesaById(idHamburguesa, requireContext())?:return view
        val imageUri = ImageController.getImage(requireContext(), hamburguesa.id)
        img.setImageURI(imageUri)
        nombtxt.setText(hamburguesa.nombre)
        descripcion.setText(hamburguesa.descripcion)

        val res = ReviewRepository.getReviewsByPersonaId(idPersona, idHamburguesa,requireContext())
        if(res.isEmpty()){
            btnReseña.isEnabled = true
        }else{
            btnReseña.isEnabled=false
        }

        setupRecyclerView()
        setupEventListeners()
        return view
    }

    private fun setupRecyclerView() {
        val reseña = ReviewRepository.getReviewsByHamburguesaId(idHamburguesa, requireContext())
        adapter = ListReseñaAdapter(reseña as ArrayList<Review>)
        lstReview.adapter = adapter
        lstReview.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupEventListeners() {
        btnback.setOnClickListener {
            requireActivity().onBackPressed()
        }
        btnReseña.setOnClickListener {
                val datos= Bundle()
                datos.putInt("idPersona", idPersona)
                datos.putInt("idHamburguesa", idHamburguesa)
                val action = VerHambFragmentDirections.actionVerHambFragmentToAnadirResenaFragment()
                action.arguments?.putAll(datos)
                findNavController().navigate(action)
        }
    }

}