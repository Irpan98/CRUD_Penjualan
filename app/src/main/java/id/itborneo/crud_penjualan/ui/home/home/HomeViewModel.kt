package id.itborneo.crud_penjualan.ui.home.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.itborneo.crud_penjualan.data.local.entity.UserEntity

class HomeViewModel : ViewModel() {

    private var userEntity = MutableLiveData<UserEntity>()

    fun setUser(userEntity: UserEntity) {
        this.userEntity.postValue(userEntity)
    }

    fun getUser(): MutableLiveData<UserEntity> {
        return userEntity
    }

}