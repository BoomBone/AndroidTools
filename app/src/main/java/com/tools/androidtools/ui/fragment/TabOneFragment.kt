package com.tools.androidtools.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tools.androidtools.R
import com.tools.androidtools.ui.fragment.viewModel.TabOneViewModel

class TabOneFragment : Fragment() {

    companion object {
        fun newInstance() = TabOneFragment()
    }

    private lateinit var viewModel: TabOneViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tab_one_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TabOneViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
