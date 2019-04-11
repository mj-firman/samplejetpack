package com.example.jetpackapp.view.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackapp.R
import com.example.jetpackapp.model.NewsResponse
import com.example.jetpackapp.view.adapter.NewsAdapter
import com.example.jetpackapp.viewmodel.SecondFragmentViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*

class SecondFragment : Fragment() {
    private var listData: ArrayList<NewsResponse.NewsEntity> = ArrayList()
    private lateinit var secondFragmentViewModel: SecondFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondFragmentViewModel = ViewModelProviders.of(this).get(SecondFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnAction.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }
        initAdapter()
        loadData()
    }

    private fun initAdapter() {
        rvData.layoutManager = LinearLayoutManager(context)
        rvData.adapter = object : NewsAdapter(context, listData){
        }
    }

    private fun loadData() {
        val token = arguments?.getString("accessToken") ?: ""
        if(!token.isNullOrEmpty())
        secondFragmentViewModel.getNews(token).observe(this, Observer {
            listData.clear()
            listData.addAll(it)
            rvData.adapter?.notifyDataSetChanged()
        })

        secondFragmentViewModel.getErrorData().observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

}
