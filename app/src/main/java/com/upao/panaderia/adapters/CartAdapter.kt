package com.upao.panaderia.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.upao.panaderia.R
import com.upao.panaderia.models.adaptersModel.ProductAdapterModel

class CartAdapter(private val cards:ArrayList<ProductAdapterModel>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cards[position])
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImg: ImageView = itemView.findViewById(R.id.ivImagenProducto)
        @SuppressLint("SetTextI18n")
        fun bind(productAdapterModel: ProductAdapterModel) {
            Picasso.get().load(productAdapterModel.image).into(ivImg)
            itemView.findViewById<TextView>(R.id.tvNombreProducto).text = productAdapterModel.title
            itemView.findViewById<TextView>(R.id.tvDescripcionProducto).text = productAdapterModel.description
            itemView.findViewById<TextView>(R.id.tvPrecioProducto).text = "S/ " + productAdapterModel.price
        }
    }
}