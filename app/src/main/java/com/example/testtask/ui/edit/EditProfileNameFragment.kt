package com.example.testtask.ui.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testtask.databinding.FragmentEditNameBinding
import com.example.testtask.ui.base.BaseFragment
import com.example.testtask.ui.components.ViewModelParams
import kotlinx.coroutines.launch

class EditProfileNameFragment : BaseFragment<EditProfileViewModel, FragmentEditNameBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEditNameBinding
        get() = FragmentEditNameBinding::inflate
    override val viewModelParams: ViewModelParams<EditProfileViewModel>
        get() = ViewModelParams(requireActivity(), EditProfileViewModel::class.java)

    override fun init() {
        observeViewModel()
        binding.buttonNext.setOnClickListener { updateName() }
    }

    private fun updateName() = lifecycleScope.launch {
        val name = binding.editName.text.toString()
        val surname = binding.editSurname.text.toString()
        viewModel.updateName(name, surname)
    }

    private fun observeViewModel() = lifecycleScope.launch {
        viewModel.stateFlow.collect { handleState(it) }
    }

    private fun handleState(state: EditProfileViewModel.State) {
        when (state) {
            is EditProfileViewModel.State.UpdateBirthDate -> {
                val action = EditProfileNameFragmentDirections.toEditDateFragment()
                findNavController().navigate(action)
            }
            is EditProfileViewModel.State.UpdateDescription -> {
                val action = EditProfileBirthDateFragmentDirections.toEditDescriptionFragment()
                findNavController().navigate(action)
            }
            EditProfileViewModel.State.ProfileUpdated -> {
                val action = EditProfileDescriptionFragmentDirections.toProfileFragment()
                findNavController().navigate(action)
            }
            else -> Unit
        }
    }
}