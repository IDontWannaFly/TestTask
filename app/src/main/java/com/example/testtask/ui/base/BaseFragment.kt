package com.example.testtask.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.example.testtask.ui.components.ViewModelParams
import kotlin.reflect.typeOf

abstract class BaseFragment<VM : ViewModel, VB: ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding!!

    protected abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    private var _viewModel: VM? = null
    protected val viewModel: VM
        get() = _viewModel!!

    protected abstract val viewModelParams: ViewModelParams<VM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = bindingInflater(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel = ViewModelProvider(viewModelParams.owner)[viewModelParams.viewModelClass]
        init()
    }

    protected abstract fun init()

}