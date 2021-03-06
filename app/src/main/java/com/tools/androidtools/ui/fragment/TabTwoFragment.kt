package com.tools.androidtools.ui.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tools.androidtools.R
import com.tools.androidtools.ui.fragment.viewModel.TabTwoViewModel

class TabTwoFragment : Fragment() {

    companion object {
        fun newInstance() = TabTwoFragment()
    }

    private lateinit var viewModel: TabTwoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tab_two_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TabTwoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
