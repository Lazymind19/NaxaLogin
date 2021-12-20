package com.lazymindapps.naxamobile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lazymindapps.naxamobile.repo.NaxaRepository

class NaxaViewModelFactory(val repository: NaxaRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NaxaViewModel(repository) as T
    }
}