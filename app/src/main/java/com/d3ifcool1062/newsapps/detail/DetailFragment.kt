package com.d3ifcool1062.newsapps.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.d3ifcool1062.newsapps.R
import com.d3ifcool1062.newsapps.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentDetailBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_detail, container, false)
        val args = arguments?.let {
            DetailFragmentArgs.fromBundle(
                it
            )
        }

        binding.news = args?.newsProperty

        return binding.root
    }

}
