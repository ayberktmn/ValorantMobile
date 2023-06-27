package com.ayberk.valorantapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.valorantapp.R
import com.ayberk.valorantapp.models.playerscard.Data
import com.bumptech.glide.Glide


class CardsAdapter: RecyclerView.Adapter<CardsAdapter.MyCompetitivesViewHolder>() {

    var liveData : List<Data>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCompetitivesViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.competitive_item,parent,false)
        return CardsAdapter.MyCompetitivesViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyCompetitivesViewHolder, position: Int) {
        holder.bind(liveData!!.get(position))

    }

    override fun getItemCount(): Int {
        if (liveData == null) {
            return 0
        }
        else {
            return liveData!!.size
        }
    }

    class MyCompetitivesViewHolder(val view:View):
        RecyclerView.ViewHolder(view) {

        val textcompetitive = view.findViewById<TextView>(R.id.txtCompetitive)
        val imageCompetitive = view.findViewById<ImageView>(R.id.imgCompetitive)

        fun bind (data : com.ayberk.valorantapp.models.playerscard.Data){

            textcompetitive.text = data.displayName
            Glide.with(imageCompetitive)
                .load(data.smallArt)
                .into(imageCompetitive)
        }
    }

    fun setLists(liveData: List<com.ayberk.valorantapp.models.playerscard.Data>){
        this.liveData = liveData
        notifyDataSetChanged()
    }
}