package com.ayberk.valorantapp

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ayberk.valorantapp.ViewModel.AbilityViewModel
import com.ayberk.valorantapp.ViewModel.AgendsViewModel
import com.ayberk.valorantapp.databinding.FragmentDetailsBinding
import com.ayberk.valorantapp.models.Agends.Agends
import com.ayberk.valorantapp.models.Agends.Data
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewDetailsModel: AgendsViewModel by viewModels()
    private val abilityViewModel: AbilityViewModel by viewModels()
    lateinit var resultList: Data



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        val view = binding.root
        val loading = LoadingDialog(this)
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                loading.isDismiss()
            }

        },1200)

        fetchAgends()

        binding.backButtonImageView.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_agentsFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDetailsModel.getObserverLiveData().observe(viewLifecycleOwner,object : Observer<Agends> {
            override fun onChanged(t: Agends?) {
                //  progressDialog.hide()
                if (t != null) {
                    println(t)

                    arguments?.let {

                        val gelen = DetailsFragmentArgs.fromBundle(it).agend
                        resultList = t.data[gelen]
                        binding.roleTextView.text = resultList.role?.displayName
                        binding.agentNameTextView.text = resultList.displayName
                        binding.txtaciklama.text = resultList.description

                        resultList.abilities.forEachIndexed { index, ability ->
                            when (index) {
                                0 -> binding.txtabilities.text = ability.slot + " :"
                                1 -> binding.txtabilities1.text = ability.slot + " :"
                                2 -> binding.txtabilities3.text = ability.slot + " :"
                                3 -> binding.txtabilities4.text = ability.slot + " :"

                            }
                        }

                        resultList.abilities.forEachIndexed { index, abilities ->
                            when (index) {

                                0 -> Glide.with(binding.imgAbility)
                                    .load(abilities.displayIcon)
                                    .into(binding.imgAbility)
                                1 -> Glide.with(binding.imgAbility1)
                                    .load(abilities.displayIcon)
                                    .into(binding.imgAbility1)
                                2 -> Glide.with(binding.imgAbility2)
                                    .load(abilities.displayIcon)
                                    .into(binding.imgAbility2)
                                3 -> Glide.with(binding.imgAbility3)
                                    .load(abilities.displayIcon)
                                    .into(binding.imgAbility3)
                            }

                            resultList.abilities.forEachIndexed { index, ability ->
                                when (index) {
                                    0 -> binding.txtAbilities.text = ability.displayName
                                    1 -> binding.txtAbilities2.text = ability.displayName
                                    2 -> binding.txtAbilities3.text = ability.displayName
                                    3 -> binding.txtAbilities4.text = ability.displayName
                                }
                            }

                            Glide.with(binding.imgrole)
                                .load(resultList.role?.displayIcon)
                                .into(binding.imgrole)

                            Glide.with(binding.agentIconImageView)
                                .load(resultList.fullPortrait)
                                .into(binding.agentIconImageView)

                            Glide.with(binding.imageView)
                                .load(resultList.displayIcon)
                                // .placeholder(R.drawable.ic_launcher_background) // Resim yüklenene kadar gösterilecek placeholder
                                .into(binding.imageView) // Resmin yükleneceği ImageView

                            binding.btnimageVolume.setOnClickListener {
                                resultList.voiceLine.mediaList.forEachIndexed { index, voiceLine ->
                                    //...
                                    if (index == 0) {
                                        val mediaList = voiceLine.wave
                                        println(mediaList)
                                        val mediaPlayer = MediaPlayer()
                                        mediaPlayer.setDataSource(
                                            requireContext(),
                                            Uri.parse(mediaList)
                                        )
                                        mediaPlayer.setOnPreparedListener { player ->
                                            player.start()
                                        }
                                        mediaPlayer.prepareAsync()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        })
    }

    fun fetchAgends() {
        CoroutineScope(Dispatchers.Main).launch {
            viewDetailsModel.loadAgends("")
            abilityViewModel.loadAbility("")
        }
    }

}

