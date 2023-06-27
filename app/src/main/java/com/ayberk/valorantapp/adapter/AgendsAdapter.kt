package com.ayberk.valorantapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ayberk.valorantapp.AgentsFragmentDirections
import com.ayberk.valorantapp.R
import com.ayberk.valorantapp.Room.DataDao
import com.ayberk.valorantapp.Room.RoomDataBase
import com.ayberk.valorantapp.models.Agends.Data
import com.ayberk.valorantapp.models.roomdata.FavoriAgends
import com.bumptech.glide.Glide


class AgendsAdapter: RecyclerView.Adapter<AgendsAdapter.AgendsViewHolder>() {

    private lateinit var db : RoomDataBase
    private lateinit var dataDao: DataDao
    var liveData : List<Data>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendsAdapter.AgendsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.agends_item,parent,false)
        return AgendsAdapter.AgendsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AgendsAdapter.AgendsViewHolder, position: Int) {
        holder.bind(liveData!!.get(position))
        holder.imgagendsss.setOnClickListener{
            val action = AgentsFragmentDirections.actionAgentsFragmentToDetailsFragment(position)
            holder.imagegrid.findNavController().navigate(action)
        }
        db = Room.databaseBuilder(holder.view.context.applicationContext,
            RoomDataBase::class.java,"FavoriAgends")
            .allowMainThreadQueries()
            .build()
        dataDao = db.dataDao()

         val dataExists = dataDao.checkIfDataExists(liveData!![position].uuid) > 0

        if (dataExists == true) {
            // veritabanında veri varsa, image görüntülenecek
            holder.imageLike.visibility = ViewGroup.VISIBLE
            holder.imageDislike.visibility = ViewGroup.GONE
        } else {
            // veritabanında veri yoksa, image gizlenecek
            holder.imageLike.visibility = ViewGroup.GONE
            holder.imageDislike.visibility = ViewGroup.VISIBLE
        }

            holder.imageDislike.setOnClickListener {
            val data = FavoriAgends(
                liveData!!.get(position).uuid,
                liveData!!.get(position).displayName,
                liveData!!.get(position).displayIcon,

                )
            dataDao.insert(data)
            notifyDataSetChanged()
            Toast.makeText(holder.itemView.context, "Favorilere Eklendi", Toast.LENGTH_SHORT).show()
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
        val imageLike = view.findViewById<ImageView>(R.id.imgLike)
        val imageDislike = view.findViewById<ImageView>(R.id.imgDislike)
        val imagegrid = view.findViewById<GridLayout>(R.id.image_grid)
        val imgagendsss = view.findViewById<ImageView>(R.id.imgAgends)


        fun bind(data: Data){

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

