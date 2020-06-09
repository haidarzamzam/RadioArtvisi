package com.haidev.radioartvisi.main.home.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.haidev.radioartvisi.R
import com.haidev.radioartvisi.databinding.FragmentHomeBinding
import com.haidev.radioartvisi.main.home.adapters.ItemRadioAdapter
import com.haidev.radioartvisi.main.home.adapters.ItemTvAdapter
import com.haidev.radioartvisi.main.home.models.ItemMainDataModel
import com.haidev.radioartvisi.main.home.models.ListMainData
import com.haidev.radioartvisi.main.home.viewmodels.HomeViewModel
import com.haidev.radioartvisi.utils.AppHelper

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private lateinit var adapterRadio: ItemRadioAdapter
    private lateinit var adapterTv: ItemTvAdapter
    private var listRadioModel: MutableList<ListMainData> = mutableListOf()
    private var listTvModel: MutableList<ListMainData> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.data = viewModel
        binding.lifecycleOwner = activity

        initTabClick()

        context?.let { AppHelper.showLoading(it) }
        viewModel.getListMainDataAsync()
        viewModel.mainDataResponse.observe(requireActivity(), Observer {
            onGetListMainDataSuccess(it)
        })

        viewModel.mainDataError.observe(requireActivity(), Observer {
            onGetListMainDataError(it)
        })

        initRecyclerView()
        return binding.root
    }

    private fun initTabClick() {
        binding.btnRadio.setOnClickListener {
            binding.txtTitleRadio.setTextColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.colorPrimaryDark
                )
            )
            binding.txtTitleTv.setTextColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.colorTextSecondary
                )
            )

            binding.indicatorRadio.setBackgroundResource(R.drawable.dots_active)
            binding.indicatorTv.setBackgroundResource(R.drawable.dots_unactive)

            binding.viewTv.visibility = View.GONE
            binding.viewRadio.visibility = View.VISIBLE
        }

        binding.btnTv.setOnClickListener {
            binding.txtTitleTv.setTextColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.colorPrimaryDark
                )
            )
            binding.txtTitleRadio.setTextColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.colorTextSecondary
                )
            )

            binding.indicatorTv.setBackgroundResource(R.drawable.dots_active)
            binding.indicatorRadio.setBackgroundResource(R.drawable.dots_unactive)

            binding.viewRadio.visibility = View.GONE
            binding.viewTv.visibility = View.VISIBLE
        }
    }

    private fun onGetListMainDataSuccess(it: ItemMainDataModel?) {
        AppHelper.hideLoading()
        listRadioModel.clear()
        it?.records?.filter { it.kategori == "radio" }?.let { it1 -> listRadioModel.addAll(it1) }

        listTvModel.clear()
        it?.records?.filter { it.kategori == "tv" }?.let { it1 -> listTvModel.addAll(it1) }

        binding.rvRadio.post {
            adapterRadio.notifyDataSetChanged()
        }
    }

    private fun onGetListMainDataError(it: Throwable?) {
        AppHelper.hideLoading()
        AppHelper.snackyError(view!!, it?.message.toString())
    }

    private fun initRecyclerView() {
        val lManagerRadio = LinearLayoutManager(context)
        binding.rvRadio.layoutManager = lManagerRadio
        binding.rvRadio.setHasFixedSize(true)

        adapterRadio = context?.let { ItemRadioAdapter(it, listRadioModel) }!!
        binding.rvRadio.adapter = adapterRadio

        val lManagerTv = LinearLayoutManager(context)
        binding.rvTv.layoutManager = lManagerTv
        binding.rvTv.setHasFixedSize(true)

        adapterTv = context?.let { ItemTvAdapter(it, listTvModel) }!!
        binding.rvTv.adapter = adapterTv
    }


}
