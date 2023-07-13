package com.example.testtask.ui.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testtask.NavigationGraphDirections
import com.example.testtask.databinding.FragmentEditDescriptionBinding
import com.example.testtask.ui.base.BaseFragment
import com.example.testtask.ui.components.ViewModelParams
import kotlinx.coroutines.launch

class EditProfileDescriptionFragment : BaseFragment<EditProfileViewModel, FragmentEditDescriptionBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEditDescriptionBinding
        get() = FragmentEditDescriptionBinding::inflate
    override val viewModelParams: ViewModelParams<EditProfileViewModel>
        get() = ViewModelParams(requireActivity(), EditProfileViewModel::class.java)

    override fun init() {
        observeViewModel()
        binding.buttonNext.setOnClickListener { updateDescription() }
    }

    private fun updateDescription() = lifecycleScope.launch {
        val description = binding.editDescription.text.toString()
        viewModel.updateDescription(description)
        viewModel.updateProfile()
    }

    private fun observeViewModel() = lifecycleScope.launch {
        viewModel.stateFlow.collect { handleState(it) }
    }

    private fun handleState(state: EditProfileViewModel.State) {
        when (state) {
            is EditProfileViewModel.State.UpdateBirthDate -> {
                val action = NavigationGraphDirections.toEditDateFragment()
                findNavController().navigate(action)
            }
            EditProfileViewModel.State.ProfileUpdated -> {
                val action = NavigationGraphDirections.toProfileFragment()
                findNavController().navigate(action)
            }
            EditProfileViewModel.State.UpdateName -> {
                val action = NavigationGraphDirections.toEditNameFragment()
                findNavController().navigate(action)
            }
            else -> Unit
        }
    }
}