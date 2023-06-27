package com.ayberk.valorantapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.valorantapp.R
import com.bumptech.glide.Glide


class MapsAdapter: RecyclerView.Adapter<MapsAdapter.MapsViewHolder>() {

    var liveData : List<com.ayberk.valorantapp.models.maps.Data>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsAdapter.MapsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.maps_item,parent,false)
        return MapsAdapter.MapsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MapsAdapter.MapsViewHolder, position: Int) {
        holder.bind(liveData!!.get(position))

    }

    override fun getItemCount(): Int {
        if(liveData == null){
            return 0
        }
        else{
            return liveData!!.size
        }
    }

    class MapsViewHolder(val view: View):
        RecyclerView.ViewHolder(view) {

        val txtmaps = view.findViewById<TextView>(R.id.txtMaps)
        val imageMaps = view.findViewById<ImageView>(R.id.imgMaps)



        fun bind(data:com.ayberk.valorantapp.models.maps.Data){

            txtmaps.text = data.displayName
            Glide.with(imageMaps)
                .load(data.splash)
               // .placeholder(R.drawable.ic_launcher_background) // Resim yüklenene kadar gösterilecek placeholder
                .into(imageMaps) // Resmin yükleneceği ImageView

        }
    }


    fun setLists(liveData: List<com.ayberk.valorantapp.models.maps.Data>){
        this.liveData=liveData
        notifyDataSetChanged()
    }
}

