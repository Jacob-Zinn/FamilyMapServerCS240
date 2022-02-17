package com.example.kotlintesting

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import kotlinx.android.synthetic.main.fragment_b.*


class FragmentB : Fragment() {

    private val TAG = "FragmentB"
    private var trackName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use the Kotlin extension in the fragment-ktx artifact
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result = bundle.getBundle("bundleKey")
            Log.d(TAG, "onCreate: result : $result")
            trackName = result!!.getString("email")
            Log.d(TAG, "onCreate: trackName : $trackName")
            // Do something with the result
        }
        setView()
    }

    private fun setView() {
        Log.d(TAG, "setView: trackName: $trackName")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: trackname: $trackName")

    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "onstart: trackName: $trackName")
       
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: trackName: $trackName")
        fragment_b_text.text = trackName

    }
}