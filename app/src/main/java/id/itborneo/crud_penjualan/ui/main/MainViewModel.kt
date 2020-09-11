package id.itborneo.crud_penjualan.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.itborneo.crud_penjualan.data.PenjualanRepository
import id.itborneo.crud_penjualan.data.local.entity.UserEntity

class MainViewModel(private val repository: PenjualanRepository) : ViewModel() {
    fun insertUser(userEntity: UserEntity): LiveData<Long> =
        repository.insertUser(userEntity)

    fun getUser(email: String) : LiveData<UserEntity> =
        repository.getUser(email)



}