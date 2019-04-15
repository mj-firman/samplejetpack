package com.example.jetpackapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class FourthFragment : Fragment() {
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(FourthFragment::class.java.simpleName, "ADD DATA")

        firestore = FirebaseFirestore.getInstance()
        val user = HashMap<String, Any>().apply {
            put("first", "Ardzi")
            put("Second", "Firman")
            put("Third", "Ihtiyar")
        }

        firestore.collection("users")
            .add(user)
            .addOnSuccessListener {
                val id = it.id
                Log.i(FourthFragment::class.java.simpleName, "DocumentSapshot added with Id $id") }
            .addOnFailureListener {
                Log.i(FourthFragment::class.java.simpleName, "DocumentSapshot Error addidng document${it.localizedMessage}")
            }
    }
}
