package com.ayberk.valorantapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.valorantapp.R
import com.ayberk.valorantapp.models.competitive.Data
import com.ayberk.valorantapp.models.competitive.Tier
import com.bumptech.glide.Glide


class CompetitiveAdapter: RecyclerView.Adapter<CompetitiveAdapter.CompetitiveViewHolder>() {

    var liveData : List<com.ayberk.valorantapp.models.competitive.Tier>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitiveAdapter.CompetitiveViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.competitive_item,parent,false)
        return CompetitiveAdapter.CompetitiveViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompetitiveAdapter.CompetitiveViewHolder, position: Int) {
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

    class CompetitiveViewHolder(val view: View):
        RecyclerView.ViewHolder(view) {

        val txtcompetetive = view.findViewById<TextView>(R.id.txtCompetitive)
        val imageCompetitve = view.findViewById<ImageView>(R.id.imgCompetitive)



        fun bind(data:com.ayberk.valorantapp.models.competitive.Tier){


            if(data.tierName == "Unused1" || data.tierName == "Unused2"){
                txtcompetetive.text = data.tierName
                Glide.with(imageCompetitve)
                    .load("https://media.valorant-api.com/competitivetiers/564d8e28-c226-3180-6285-e48a390db8b1/0/largeicon.png")
                    // .placeholder(R.drawable.ic_launcher_background) // Resim yüklenene kadar gösterilecek placeholder
                    .into(imageCompetitve) //
            }
            else {

                txtcompetetive.text = data.tierName
                Glide.with(imageCompetitve)
                    .load(data.largeIcon)
                    // .placeholder(R.drawable.ic_launcher_background) // Resim yüklenene kadar gösterilecek placeholder
                    .into(imageCompetitve) // Resmin yükleneceği ImageView
            }
        }
    }


    fun setLists(liveData: List<Tier>){
        this.liveData= liveData

        notifyDataSetChanged()
    }
}

