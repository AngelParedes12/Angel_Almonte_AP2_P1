package edu.ucne.angel_almonte_ap2_p1.presentantion.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.angel_almonte_ap2_p1.domain.usaCase.GetBorrarUsaCase
import javax.inject.Inject

@HiltViewModel
class listViewModel @Inject constructor(
    private val getBorrarUseCase: GetBorrarUsaCase
) : ViewModel()