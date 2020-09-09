package id.itborneo.crud_penjualan.data.di

import android.content.Context
import id.itborneo.crud_penjualan.data.PenjualanRepository
import id.itborneo.crud_penjualan.data.local.dataSource.ItemLocalDataSource
import id.itborneo.crud_penjualan.data.local.PenjualanDatabase
import id.itborneo.crud_penjualan.data.local.dataSource.UserLocalDataSource

object Injection {
    fun provideRepository(context: Context): PenjualanRepository {

        val db = PenjualanDatabase.instance(context)
        val localDataSource = ItemLocalDataSource.getInstance(db.itemDao())
        val userLocalDataSource = UserLocalDataSource.getInstance(db.userDao())


        return PenjualanRepository.getInstance(localDataSource,userLocalDataSource)
    }
}