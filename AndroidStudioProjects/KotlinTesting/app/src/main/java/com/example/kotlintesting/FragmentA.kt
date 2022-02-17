package com.example.kotlintesting

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.fragment_b.*
import java.util.*

private const val TAG = "FragmentA"
class FragmentA : Fragment() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        to_fragment_b.setOnClickListener {


            val bundle = Bundle()
            bundle.putString("email","jacobpzinn@gmail.com")
            bundle.putString("trackName","QUICK-Session Near Provo")

//            val result = "result"
            // Use the Kotlin extension in the fragment-ktx artifact
            setFragmentResult("requestKey", bundleOf("bundleKey" to bundle))

            navController.navigate(R.id.action_fragmentA_to_fragmentB)
        }

        fragment_a_modal.setOnClickListener{
            ItemListDialogFragment.newInstance(15).show(childFragmentManager, "dialog")
        }
    }

}