package com.moviles.practicanavegacioncasera.ui.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.core.graphics.drawable.toIcon
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Hamburguesa
import com.moviles.practicanavegacioncasera.dal.entities.Restaurante
import com.moviles.practicanavegacioncasera.repository.HamburguesaRepository
import com.moviles.practicanavegacioncasera.repository.ImageController
import com.moviles.practicanavegacioncasera.repository.RestauranteRepository

/**
 * A simple [Fragment] subclass.
 * Use the [FormHamburguesaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormHamburguesaFragment : Fragment() {

    private val select = 50
    private lateinit var imageUri: Uri

    private lateinit var spinnerAdapter: TipoSpinnerRestauranteAdapter
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button
    private lateinit var txtName: EditText
    private lateinit var txtDescripcion: EditText
    private lateinit var spTipos: Spinner
    private lateinit var selectorImagen: ImageView
    private var idHamburguesa = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form_hamburguesa, container, false)
        btnSave = view.findViewById(R.id.guardarHamburguesa)
        btnCancel = view.findViewById(R.id.cancelarhamburguesa)
        txtName = view.findViewById(R.id.hamburgesaNameForm)
        txtDescripcion = view.findViewById(R.id.hamburguesaDescripcionForm)
        spTipos = view.findViewById(R.id.spRestaurant)
        selectorImagen = view.findViewById(R.id.selectfoto)
        idHamburguesa = arguments?.getInt("idHamburguesa") ?: 0

        loadRestaurantes()

        if (idHamburguesa > 0) {
            loadProductoForm()
        }
        setupEventListeners()

        return view
    }

    private fun loadRestaurantes() {
        val restaurantes = RestauranteRepository.getAllRestaurantes(requireContext())
        spinnerAdapter = TipoSpinnerRestauranteAdapter(requireContext(), restaurantes as MutableList<Restaurante>)
        spTipos.adapter = spinnerAdapter
    }

    private fun setupEventListeners() {
        btnSave.setOnClickListener {
            saveProducto()
        }
        btnCancel.setOnClickListener {
            cancelProducto()
        }
        selectorImagen.setOnClickListener {
            ImageController.selectPhotoFromGallery(this, select)
        }
    }

    private fun saveProducto() {
        val name = txtName.text.toString()
        val descripcion = txtDescripcion.text.toString()
        val selectedRestaurantePosition = spTipos.selectedItemPosition
        val selectedRestaurante = spTipos.adapter.getItem(selectedRestaurantePosition) as Restaurante
        val hamburguesa = Hamburguesa(
            name,
            descripcion,
            selectedRestaurante.id
        )
        if(idHamburguesa==0){
            val id_new=HamburguesaRepository.insert(hamburguesa,requireContext())[0]
            imageUri?.let {
                ImageController.saveImage(requireContext(), id_new, it)
            }
        }else{
            hamburguesa.id = idHamburguesa
            HamburguesaRepository.update(hamburguesa,requireContext())
            imageUri?.let {
                ImageController.saveImage(requireContext(),hamburguesa.id,it)
            }
        }
        requireActivity().onBackPressed()
    }

    private fun cancelProducto() {
        requireActivity().onBackPressed()
    }

    private fun loadProductoForm() {
        val hamburguesa = HamburguesaRepository.getHamburguesaById(idHamburguesa, requireContext()) ?: return
        txtName.setText(hamburguesa.nombre)
        txtDescripcion.setText(hamburguesa.descripcion)
        //colocamos imagen
        val imageUri = ImageController.getImage(requireContext(), hamburguesa.id)
        selectorImagen.setImageURI(imageUri)

        val idRestaurant = hamburguesa.idRestaurante
        val restaurantIndex = spinnerAdapter.getIndexForRestaurante(idRestaurant)
        spTipos.setSelection(restaurantIndex)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when{
            requestCode == select && resultCode == Activity.RESULT_OK -> {
                imageUri = data!!.data!!
                selectorImagen.setImageURI(imageUri)
            }
        }
    }
}