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
import com.ayberk.valorantapp.WeaponsFragmentDirections
import com.ayberk.valorantapp.WeponsDetailsFragment
import com.ayberk.valorantapp.models.DataX
import com.ayberk.valorantapp.models.Skin
import com.ayberk.valorantapp.models.skin.Data
import com.bumptech.glide.Glide

class WeaponSkinsAdapter: RecyclerView.Adapter<WeaponSkinsAdapter.WeaponsViewHolder>() {

    var liveData : List<Data>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponSkinsAdapter.WeaponsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weaponskin_item,parent,false)
        return WeaponSkinsAdapter.WeaponsViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeaponSkinsAdapter.WeaponsViewHolder, position: Int) {
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

    class WeaponsViewHolder(val view: View):
        RecyclerView.ViewHolder(view) {

        val txttitle = view.findViewById<TextView>(R.id.txtSkinName)
        val imageWeapons = view.findViewById<ImageView>(R.id.imgSkins)



        fun bind(data: Data){

            txttitle.text = data.displayName

            Glide.with(imageWeapons)
                .load(data.displayIcon)
                // .placeholder(R.drawable.ic_launcher_background) // Resim yüklenene kadar gösterilecek placeholder
                .into(imageWeapons) // Resmin yükleneceği ImageView


        }
    }

    fun setListsWeaponSkin(liveData: List<Data>){
        this.liveData=liveData
        notifyDataSetChanged()
    }
}

