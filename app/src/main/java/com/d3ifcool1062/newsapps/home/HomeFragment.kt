package com.d3ifcool1062.newsapps.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3ifcool1062.newsapps.R
import com.d3ifcool1062.newsapps.databinding.FragmentHomeBinding
import com.d3ifcool1062.newsapps.domain.NewsProperty

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, HomeViewModel.Factory(activity.application))
            .get(HomeViewModel::class.java)
    }

    private var viewModelAdapter: NewsAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.listNews.observe(viewLifecycleOwner, Observer<List<NewsProperty>> {
            it?.apply {
                viewModelAdapter?.news = it
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.viewModel = viewModel

        //Navigate to Detail
        viewModel.navigateToDetailNews.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                )
                viewModel.onFinishItemSelected()
            }
        })

        //binding clickListener di Item News
        viewModelAdapter = NewsAdapter(NewsClickListener { news ->
            viewModel.onItemSelected(news)
        })

        binding.rvRegular.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        return binding.root
    }

}
