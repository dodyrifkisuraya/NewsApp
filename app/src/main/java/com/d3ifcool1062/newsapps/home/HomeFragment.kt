package com.d3ifcool1062.newsapps.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3ifcool1062.newsapps.R
import com.d3ifcool1062.newsapps.databinding.FragmentHomeBinding
import com.d3ifcool1062.newsapps.domain.NewsProperty


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    //Initialize ViewModel
    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, HomeViewModel.Factory(activity.application))
            .get(HomeViewModel::class.java)
    }

    //Initialize item Adapter Recycleview
    private var viewModelAdapter: NewsAdapter? = null
    private var vmAdapterGeneral: GeneralNewsAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Input LiveData(List<News>) to Adapter
        viewModel.listNewsGeneral.observe(viewLifecycleOwner, Observer<List<NewsProperty>> {
            it?.apply {
                vmAdapterGeneral?.news = it
            }
        })

        viewModel.listNewsTechnology.observe(viewLifecycleOwner, Observer<List<NewsProperty>> {
            it?.apply {
                viewModelAdapter?.news = it
            }
        })

        //category button
        binding.ctgrEntertain.setOnClickListener{
            switchContent("Entertain")
            setNonActiveButton()
            binding.ctgrEntertain.setBackgroundResource(R.drawable.active)
        }
        binding.ctgrTechnology.setOnClickListener {
            switchContent("Tech")
            setNonActiveButton()
            binding.ctgrTechnology.setBackgroundResource(R.drawable.active)
        }
        binding.ctgrBusiness.setOnClickListener {
            switchContent("Business")
            setNonActiveButton()
            binding.ctgrBusiness.setBackgroundResource(R.drawable.active)
        }
        binding.ctgrHealth.setOnClickListener {
            switchContent("Health")
            setNonActiveButton()
            binding.ctgrHealth.setBackgroundResource(R.drawable.active)
        }
        binding.ctgrSport.setOnClickListener {
            switchContent("Sport")
            setNonActiveButton()
            binding.ctgrSport.setBackgroundResource(R.drawable.active)
        }
        binding.ctgrScience.setOnClickListener {
            switchContent("Science")
            setNonActiveButton()
            binding.ctgrScience.setBackgroundResource(R.drawable.active)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.viewModel = viewModel

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

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

        vmAdapterGeneral = GeneralNewsAdapter(NewsClickListener { news ->
            viewModel.onItemSelected(news)
        })

        //Setting RecycleView layoutManager and Adapter
        binding.rvRegular.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = viewModelAdapter
        }

        binding.rvGeneral.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = vmAdapterGeneral
        }




        return binding.root
    }

    fun setNonActiveButton(){
        binding.ctgrEntertain.setBackgroundResource(R.drawable.no_active)
        binding.ctgrScience.setBackgroundResource(R.drawable.no_active)
        binding.ctgrSport.setBackgroundResource(R.drawable.no_active)
        binding.ctgrHealth.setBackgroundResource(R.drawable.no_active)
        binding.ctgrBusiness.setBackgroundResource(R.drawable.no_active)
        binding.ctgrTechnology.setBackgroundResource(R.drawable.no_active)
    }

    fun switchContent(category : String){
        when(category){
            "Tech" -> viewModel.listNewsTechnology.observe(viewLifecycleOwner, Observer<List<NewsProperty>> {
                it?.apply {
                    viewModelAdapter?.news = it
                }
            })

            "Entertain" -> viewModel.listEntertain.observe(viewLifecycleOwner, Observer<List<NewsProperty>> {
                it?.apply {
                    viewModelAdapter?.news = it
                }
            })

            "Business" -> viewModel.listBusiness.observe(viewLifecycleOwner, Observer<List<NewsProperty>> {
                it?.apply {
                    viewModelAdapter?.news = it
                }
            })

            "Health" -> viewModel.listHealth.observe(viewLifecycleOwner, Observer<List<NewsProperty>> {
                it?.apply {
                    viewModelAdapter?.news = it
                }
            })

            "Sport" -> viewModel.listSport.observe(viewLifecycleOwner, Observer<List<NewsProperty>> {
                it?.apply {
                    viewModelAdapter?.news = it
                }
            })

            "Science" -> viewModel.listScience.observe(viewLifecycleOwner, Observer<List<NewsProperty>> {
                it?.apply {
                    viewModelAdapter?.news = it
                }
            })
        }
    }


}
