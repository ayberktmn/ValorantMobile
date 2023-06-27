package com.ayberk.valorantapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.ayberk.valorantapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.Agendsimg.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_agentsFragment)
        }
        binding.weaponsimg.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_weaponsFragment)
        }
        binding.mapsimg.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mapsFragment)
        }
        binding.imageView5.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cardsFragment)
        }
        binding.imageView6.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_competitiveFragment)
        }

       // binding.bottomBar.setOnNavigationItemReselectedListener {}
        binding.bottomBar.setOnItemSelectedListener {

            when (it.itemId) {-

                R.id.home -> {
                    Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_self)
                    true
                }

                R.id.maps -> {
                    Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_mapsFragment)
                    true
                }

                R.id.Competitives -> {
                    Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_competitiveFragment)
                    true
                }

                R.id.Cards -> {
                    Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_cardsFragment)
                    true
                }

                R.id.favoriler -> {
                    Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_favoriFragment)
                    true
                }

                else -> {

                }
            }
            true
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }
}