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
import com.moviles.practicanavegacioncasera.dal.entities.Review
import com.moviles.practicanavegacioncasera.repository.ReviewRepository
import com.moviles.practicanavegacioncasera.ui.adapters.HamburguesaAdapter
import com.moviles.practicanavegacioncasera.ui.adapters.ReviewAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewFragment : Fragment(), ReviewAdapter.OnReviewClickListener {

    private lateinit var adapter: ReviewAdapter
    private lateinit var lstReview: RecyclerView
    private lateinit var btnback: Button
    private lateinit var btnAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_review, container, false)
        lstReview = view.findViewById(R.id.reviewList)
        btnback = view.findViewById(R.id.backReview)
        btnAdd = view.findViewById(R.id.createReview)
        setupRecyclerView()
        setupEventListeners()
        return view
    }

    private fun setupEventListeners() {
        btnAdd.setOnClickListener {
            val action = ReviewFragmentDirections.actionReviewFragmentToFormReviewFragment()
            findNavController().navigate(action)
        }
        btnback.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        val review = ReviewRepository.getAllReviews(requireContext())
        adapter = ReviewAdapter(review as ArrayList<Review>, this)
        lstReview.adapter = adapter
        lstReview.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onReviewEdit(review: Review) {
        val datos =Bundle()
        datos.putInt("idReview", review.id)
        val action = ReviewFragmentDirections.actionReviewFragmentToFormReviewFragment()
        action.arguments?.putAll(datos)
        findNavController().navigate(action)
    }

    override fun onReviewDelete(review: Review) {
        ReviewRepository.delete(review, requireContext())
        adapter.removeItem(review)
    }
}