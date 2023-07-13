package com.example.testtask.ui.edit

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testtask.NavigationGraphDirections
import com.example.testtask.databinding.FragmentEditDateBinding
import com.example.testtask.ui.base.BaseFragment
import com.example.testtask.ui.components.ViewModelParams
import com.example.testtask.ui.profile.ProfileFragmentDirections
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EditProfileBirthDateFragment : BaseFragment<EditProfileViewModel, FragmentEditDateBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEditDateBinding
        get() = FragmentEditDateBinding::inflate
    override val viewModelParams: ViewModelParams<EditProfileViewModel>
        get() = ViewModelParams(requireActivity(), EditProfileViewModel::class.java)

    override fun init() {
        observeViewModel()
        binding.buttonNext.setOnClickListener { updateDate() }
    }

    private fun updateDate() = lifecycleScope.launch {
        val date = binding.editDate.text.toString()
        viewModel.updateBirthDate(date)
    }

    private fun observeViewModel() = lifecycleScope.launch {
        viewModel.stateFlow.collect { handleState(it) }
    }

    private fun handleState(state: EditProfileViewModel.State) {
        when (state) {
            is EditProfileViewModel.State.UpdateDescription -> {
                val action = NavigationGraphDirections.toEditDescriptionFragment()
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