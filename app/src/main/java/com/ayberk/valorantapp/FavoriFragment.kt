package com.ayberk.valorantapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.ayberk.valorantapp.Room.DataDao
import com.ayberk.valorantapp.Room.RoomDataBase
import com.ayberk.valorantapp.adapter.AgendsAdapter
import com.ayberk.valorantapp.adapter.FavoriteRecyclerAdapter
import com.ayberk.valorantapp.databinding.FragmentFavoriBinding
import com.ayberk.valorantapp.models.roomdata.FavoriAgends
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriFragment : Fragment() {

    private lateinit var db: RoomDataBase
    private lateinit var adventDao: DataDao
    private lateinit var binding: FragmentFavoriBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Room.databaseBuilder(requireContext().applicationContext, RoomDataBase::class.java, "FavoriAgends")
            .allowMainThreadQueries()
            .build()
        adventDao = db.dataDao()

        val recyclerViewAdapter = FavoriteRecyclerAdapter(adventDao.getAll() as ArrayList<FavoriAgends>)
        binding.rcylerFavorite.adapter = recyclerViewAdapter

        binding.rcylerFavorite.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        var _binding = null
    }
}