package id.itborneo.crud_penjualan.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.itborneo.crud_penjualan.data.PenjualanRepository
import id.itborneo.crud_penjualan.data.di.Injection
import id.itborneo.crud_penjualan.ui.home.home.HomeViewModel
import id.itborneo.crud_penjualan.ui.home.report.ReportViewModel
import id.itborneo.crud_penjualan.ui.home.transaction.TransactionViewModel
import id.itborneo.crud_penjualan.ui.home.transactionInput.TransactionInputViewModel
import id.itborneo.crud_penjualan.ui.main.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val repository: PenjualanRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        private val instance: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository(application))
            }

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(repository) as T
            modelClass.isAssignableFrom(TransactionInputViewModel::class.java) ->
                TransactionInputViewModel(repository) as T
            modelClass.isAssignableFrom(TransactionViewModel::class.java) ->
                TransactionViewModel(repository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel() as T
            modelClass.isAssignableFrom(ReportViewModel::class.java) ->
                TransactionViewModel(repository) as T
            else -> throw Throwable("ViewModelFactory Tidak diketahui ViewModel")
        }


    }

}