package com.example.comics.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.comics.databinding.ItemListBinding
import com.example.comics.repository.ItemModel
import com.example.comics.repository.ResultModel

class Adapter(
    val itens: List<ResultModel>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(item = itens[position])
    }

    override fun getItemCount(): Int = itens.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getItemHolder(parent = parent)

    private fun getItemHolder(parent: ViewGroup) = ItemViewHolder(
        itemBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    private class ItemViewHolder(val itemBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(
            item: ResultModel,
        ) = itemBinding.run {
            Glide.with(itemBinding.root)
                .load("https://image.tmdb.org/t/p/original${item.poster_path}")
                .centerCrop()
                .into(itemBinding.actionImage)

            actionTitle.text = item.title
            actionSubTitle.text = item.overview ?: ""
        }
    }

}
