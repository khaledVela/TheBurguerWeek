package com.moviles.practicanavegacioncasera.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.practicanavegacioncasera.R
import com.moviles.practicanavegacioncasera.dal.entities.Review

class ReviewAdapter (
    private val reviewList: ArrayList<Review>,
    private val listener: OnReviewClickListener
) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPersona: TextView = itemView.findViewById(R.id.ReviewPersona)
        val tvHamburguesa: TextView = itemView.findViewById(R.id.ReviewHamburguesa)
        val btnEdit: Button = itemView.findViewById(R.id.editaReview)
        val btnDelete: Button = itemView.findViewById(R.id.eliminarReview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.review_item_layout, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviewList[position]
        holder.tvPersona.text = review.getPersona(holder.tvPersona.context)?.nombre
        holder.tvHamburguesa.text = review.getHamburguesa(holder.tvHamburguesa.context)?.nombre
        holder.btnEdit.setOnClickListener {
            listener.onReviewEdit(review)
        }
        holder.btnDelete.setOnClickListener {
            listener.onReviewDelete(review)
        }
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun refreshData(reviews: List<Review>) {
        reviewList.clear()
        reviewList.addAll(reviews)
        notifyDataSetChanged()
    }

    fun removeItem(review: Review) {
        val position = reviewList.indexOf(review)
        reviewList.remove(review)
        notifyItemRemoved(position)
    }

    interface OnReviewClickListener {
        fun onReviewEdit(review: Review)
        fun onReviewDelete(review: Review)
    }
}