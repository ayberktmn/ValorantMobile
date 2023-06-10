package com.ayberk.valorantapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.valorantapp.AgentsFragmentDirections
import com.ayberk.valorantapp.R
import com.ayberk.valorantapp.models.Data
import com.bumptech.glide.Glide


class AgendsAdapter: RecyclerView.Adapter<AgendsAdapter.AgendsViewHolder>() {

    var liveData : List<Data>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendsAdapter.AgendsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.agends_item,parent,false)
        return AgendsAdapter.AgendsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgendsAdapter.AgendsViewHolder, position: Int) {
        holder.bind(liveData!!.get(position))
        holder.imagegrid.setOnClickListener{
            val action = AgentsFragmentDirections.actionAgentsFragmentToDetailsFragment(position)
            holder.imagegrid.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        if(liveData == null){
            return 0
        }
        else{
            return liveData!!.size
        }
    }

    class AgendsViewHolder(val view: View):
        RecyclerView.ViewHolder(view) {

        val txttitle = view.findViewById<TextView>(R.id.txtAgends)
        val txtrol = view.findViewById<TextView>(R.id.txtAgendsRole)
        val imageAgend = view.findViewById<ImageView>(R.id.imgAgends)
        val imagegrid = view.findViewById<GridLayout>(R.id.image_grid)


        fun bind(data:Data){

           txttitle.text = data.displayName
            txtrol.text = data.role?.displayName
            Glide.with(imageAgend)
                .load(data.displayIcon)
               // .placeholder(R.drawable.ic_launcher_background) // Resim yüklenene kadar gösterilecek placeholder
                .into(imageAgend) // Resmin yükleneceği ImageView

        }
    }


    fun setLists(liveData: List<Data>){
        this.liveData=liveData
        notifyDataSetChanged()
    }
}

