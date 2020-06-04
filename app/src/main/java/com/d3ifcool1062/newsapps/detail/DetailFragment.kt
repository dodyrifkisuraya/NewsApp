package com.d3ifcool1062.newsapps.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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

        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        binding.news = args?.newsProperty
        binding.btnRead.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(args?.newsProperty?.url)))
        }

        return binding.root
    }

}
