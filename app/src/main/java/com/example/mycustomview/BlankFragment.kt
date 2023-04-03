package com.example.mycustomview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.mycustomview.databinding.ActivityMainBinding
import com.example.mycustomview.databinding.FragmentBlankBinding


class BlankFragment : Fragment() {

    private lateinit var binding: FragmentBlankBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_blank, container, false)
        binding = FragmentBlankBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button3.setOnClickListener {
            val result = "result"
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        }

    }
}