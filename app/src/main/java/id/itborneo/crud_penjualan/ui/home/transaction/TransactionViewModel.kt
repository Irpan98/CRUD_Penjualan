package id.itborneo.crud_penjualan.ui.home.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.itborneo.crud_penjualan.data.PenjualanRepository
import id.itborneo.crud_penjualan.data.local.entity.ItemEntity

class TransactionViewModel(private val repository: PenjualanRepository) : ViewModel() {

    private lateinit var items: LiveData<List<ItemEntity>>

    fun getTransactionItems(): LiveData<List<ItemEntity>> {

//        if (!::items.isInitialized) {
//            repository.getTransactionItems()
//        }

        return repository.getTransactionItems()
    }

    fun removeItems(itemEntity: ItemEntity): LiveData<Int> {
        return repository.removeItem(itemEntity)

    }


}