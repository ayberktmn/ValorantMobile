package com.ayberk.valorantapp.adapter
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ayberk.valorantapp.R
import com.ayberk.valorantapp.Room.DataDao
import com.ayberk.valorantapp.Room.RoomDataBase
import com.ayberk.valorantapp.databinding.AgendsItemBinding
import com.ayberk.valorantapp.databinding.FavoriagendsItemBinding
import com.ayberk.valorantapp.models.Agends.Data
import com.ayberk.valorantapp.models.roomdata.FavoriAgends
import com.bumptech.glide.Glide


class FavoriteRecyclerAdapter(private val FavoriteAgendsList:ArrayList<FavoriAgends>) : RecyclerView.Adapter<FavoriteRecyclerAdapter.AgendsFavoriHolder>() {
        private lateinit var db : RoomDataBase
        private lateinit var adventDao: DataDao

        class AgendsFavoriHolder (val binding : FavoriagendsItemBinding): RecyclerView.ViewHolder(binding.root) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendsFavoriHolder {
            val binding = FavoriagendsItemBinding.inflate(LayoutInflater.from(parent.context))
            return AgendsFavoriHolder(binding)

        }

        override fun getItemCount(): Int {
            return FavoriteAgendsList.size
        }

        override fun onBindViewHolder(holder: AgendsFavoriHolder, position: Int) {



            val link = FavoriteAgendsList[position].displayIcon
            val displayName = link.substringAfterLast("/").substringBeforeLast(".")

            if (displayName == "O"){
                holder.binding.txtAgends.text = "KAY/O"
                Glide.with(holder.binding.imgAgends)
                    .load("https://media.valorant-api.com/agents/" + FavoriteAgendsList[position].uuid + "/displayicon.png")
                    // .placeholder(R.drawable.ic_launcher_background) // Resim yüklenene kadar gösterilecek placeholder
                    .into(holder.binding.imgAgends) // Resmin yükleneceği ImageView

            }else {

                holder.binding.txtAgends.text = displayName
                println(displayName)

                Glide.with(holder.binding.imgAgends)
                    .load("https://media.valorant-api.com/agents/" + FavoriteAgendsList[position].uuid + "/displayicon.png")
                    // .placeholder(R.drawable.ic_launcher_background) // Resim yüklenene kadar gösterilecek placeholder
                    .into(holder.binding.imgAgends) // Resmin yükleneceği ImageView
            }
                db =
                    Room.databaseBuilder(
                        holder.binding.root.context,
                        RoomDataBase::class.java,
                        "FavoriAgends"
                    )
                        .allowMainThreadQueries()
                        .build()
                adventDao = db.dataDao()


            holder.binding.imgLike.setOnClickListener {
                val favoriMusic = FavoriteAgendsList[position]
                adventDao.delete(favoriMusic)
                Toast.makeText(holder.itemView.context, "Favorilerden Silindi", Toast.LENGTH_SHORT)
                    .show()

                // Favori müzik öğesini listenizden kaldırın ve RecyclerView'i güncelleyin
                FavoriteAgendsList.removeAt(position)
                notifyItemRemoved(position)
                holder.itemView.findNavController().navigate(R.id.action_favoriFragment_self)
            }
        }
}