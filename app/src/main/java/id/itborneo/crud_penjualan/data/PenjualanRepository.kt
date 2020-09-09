package id.itborneo.crud_penjualan.data

import androidx.lifecycle.LiveData
import id.itborneo.crud_penjualan.data.local.dataSource.ItemLocalDataSource
import id.itborneo.crud_penjualan.data.local.dataSource.UserLocalDataSource
import id.itborneo.crud_penjualan.data.local.entity.ItemEntity
import id.itborneo.crud_penjualan.data.local.entity.UserEntity

class PenjualanRepository private constructor(
    private val itemLocalDataSource: ItemLocalDataSource,
    private val userLocalDataSource: UserLocalDataSource
) {

    private val TAG = "PenjualanRepository"


    companion object {

        @Volatile
        var instance: PenjualanRepository? = null

        fun getInstance(
            itemLocalDataSource: ItemLocalDataSource, userLocalDataSource: UserLocalDataSource
        ): PenjualanRepository =
            instance ?: PenjualanRepository(itemLocalDataSource, userLocalDataSource)

    }


    fun insertItem(item: ItemEntity): LiveData<Long> {
        return itemLocalDataSource.insertItem(item)
    }

    fun getTransactionItems(): LiveData<List<ItemEntity>> {
        return itemLocalDataSource.getTransactionItems()
    }

    fun editItem(item: ItemEntity): LiveData<Int> {
        return itemLocalDataSource.editItem(item)

    }

    fun removeItem(item: ItemEntity): LiveData<Int> {
        return itemLocalDataSource.removeItem(item)
    }

    fun insertUser(userEntity: UserEntity): LiveData<Long> =
        userLocalDataSource.insertUser(userEntity)

    fun getUser(email: String): LiveData<UserEntity> =
        userLocalDataSource.getUser(email)


}