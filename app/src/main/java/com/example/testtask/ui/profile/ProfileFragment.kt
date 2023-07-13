package com.example.testtask.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testtask.NavigationGraphDirections
import com.example.testtask.databinding.FragmentProfileBinding
import com.example.testtask.db.entities.ProfileEntity
import com.example.testtask.ui.base.BaseFragment
import com.example.testtask.ui.components.ViewModelParams
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate
    override val viewModelParams: ViewModelParams<ProfileViewModel> =
        ViewModelParams(this, ProfileViewModel::class.java)

    override fun init() {
        observeViewModel()
        binding.buttonEdit.setOnClickListener {
            val action = NavigationGraphDirections.toEditNameFragment()
            findNavController().navigate(action)
        }
    }

    private fun observeViewModel() = lifecycleScope.launch {
        viewModel.profile.collect { updateProfile(it) }
    }

    private fun updateProfile(profile: ProfileEntity) {
        binding.textName.text = profile.name
        binding.textSurname.text = profile.surname
        binding.textDate.text = profile.birthDate
        binding.textAbout.text = profile.description
    }

}