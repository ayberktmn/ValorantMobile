package com.ayberk.valorantapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.valorantapp.R
import com.ayberk.valorantapp.WeaponsFragmentDirections
import com.ayberk.valorantapp.models.Agends.DataX
import com.bumptech.glide.Glide

class WeaponsAdapter: RecyclerView.Adapter<WeaponsAdapter.WeaponsViewHolder>() {

    var liveData : List<DataX>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponsAdapter.WeaponsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weapons_item,parent,false)
        return WeaponsAdapter.WeaponsViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeaponsAdapter.WeaponsViewHolder, position: Int) {
        holder.bind(liveData!!.get(position))
        holder.imagegrid.setOnClickListener{
            val action = WeaponsFragmentDirections.actionWeaponsFragmentToWeponsDetailsFragment(position)
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

    class WeaponsViewHolder(val view: View):
        RecyclerView.ViewHolder(view) {

        val txttitle = view.findViewById<TextView>(R.id.txtWeapons)
        val txtrol = view.findViewById<TextView>(R.id.txtWeaponsRole)
        val imageWeapons = view.findViewById<ImageView>(R.id.imgWeapons)
        val imagegrid = view.findViewById<GridLayout>(R.id.image_grid)


        fun bind(data: DataX){

            txttitle.text = data.displayName
            if (data.category == "EEquippableCategory::Melee"){
                txtrol.text = "MELEE"
            }else{
                txtrol.text = data.shopData.category
            }
            Glide.with(imageWeapons)
                .load(data.displayIcon)
                // .placeholder(R.drawable.ic_launcher_background) // Resim yüklenene kadar gösterilecek placeholder
                .into(imageWeapons) // Resmin yükleneceği ImageView


        }
    }

    fun setListsWeapons(liveData: List<DataX>){
        this.liveData=liveData
        notifyDataSetChanged()
    }
}

