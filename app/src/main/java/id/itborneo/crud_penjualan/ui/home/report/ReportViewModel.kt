package id.itborneo.crud_penjualan.ui.home.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.itborneo.crud_penjualan.data.PenjualanRepository
import id.itborneo.crud_penjualan.data.local.entity.ItemEntity

class ReportViewModel(private val repository: PenjualanRepository) : ViewModel() {


    fun getTransactionItems(): LiveData<List<ItemEntity>> {

        return repository.getTransactionItems()
    }




}