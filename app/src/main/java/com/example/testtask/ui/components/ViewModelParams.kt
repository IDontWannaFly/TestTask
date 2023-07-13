package com.example.testtask.ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner

data class ViewModelParams<VM : ViewModel>(
    val owner: ViewModelStoreOwner,
    val viewModelClass: Class<VM>
)