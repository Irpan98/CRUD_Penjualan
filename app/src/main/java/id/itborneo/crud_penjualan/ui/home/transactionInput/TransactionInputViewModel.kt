package id.itborneo.crud_penjualan.ui.home.transactionInput

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.itborneo.crud_penjualan.data.PenjualanRepository
import id.itborneo.crud_penjualan.data.local.entity.ItemEntity

class TransactionInputViewModel(private val repository: PenjualanRepository) : ViewModel() {


    fun insertItem(item: ItemEntity) : LiveData<Long> {
        return repository.insertItem(item)
    }

    fun editItem(item: ItemEntity) : LiveData<Int> {
        return repository.editItem(item)
    }


}