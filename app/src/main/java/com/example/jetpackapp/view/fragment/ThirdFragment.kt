package com.example.jetpackapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackapp.R
import com.example.jetpackapp.view.adapter.HeroAdapter
import com.example.jetpackapp.viewmodel.ThirdFragmentViewModel
import kotlinx.android.synthetic.main.fragment_third.view.*

class ThirdFragment : Fragment() {
    private lateinit var viewModel: ThirdFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ThirdFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnAction.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_firstFragment)
        }

        view.btnToFourth.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_fourthFragment)
        }

        view.rvHeros.layoutManager = LinearLayoutManager(context)
        view.rvHeros.setHasFixedSize(true)

        viewModel.getHeroes().observe(this, Observer {
            view.rvHeros.adapter = object : HeroAdapter(context, it) {

            }
        })
    }
}
