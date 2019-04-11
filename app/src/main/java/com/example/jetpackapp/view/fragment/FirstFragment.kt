package com.example.jetpackapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.jetpackapp.R
import com.example.jetpackapp.viewmodel.FirstFragmentViewModel


class FirstFragment : Fragment() {
    private var firstFragmentViewModel: FirstFragmentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firstFragmentViewModel = ViewModelProviders.of(this).get(FirstFragmentViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstFragmentViewModel?.login()
        firstFragmentViewModel?.getUser()?.observe(this, Observer {
            val accessToken = it.accessToken
            Log.i(FirstFragment::class.java.simpleName, "accessToken " + accessToken)
            var bundle = bundleOf("accessToken" to accessToken)
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle)
        })

        firstFragmentViewModel?.getErrorLogin()?.observe(this, Observer {
            Toast.makeText(context as Context, it, Toast.LENGTH_SHORT).show()
        })

    }
}
