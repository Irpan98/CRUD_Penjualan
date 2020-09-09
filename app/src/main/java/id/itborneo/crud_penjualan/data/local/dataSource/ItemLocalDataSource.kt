package id.itborneo.crud_penjualan.data.local.dataSource

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.itborneo.crud_penjualan.data.local.entity.ItemEntity
import id.itborneo.crud_penjualan.data.local.dao.ItemDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ItemLocalDataSource(private val dao: ItemDao) {

    private val TAG = "LocalDataSource"

    companion object {

        @Volatile
        private var INSTANCE: ItemLocalDataSource? = null

        fun getInstance(dao: ItemDao): ItemLocalDataSource {
            Log.d("LocalDataSource", "getInstance called $INSTANCE")


            return INSTANCE ?: ItemLocalDataSource(dao)
        }
    }

    init {
        Log.d(TAG, "init called")
    }

//    val database: PenjualanDatabase = PenjualanDatabase.instance(dao)
//    val itemDao = database.itemDao()

    @SuppressLint("CheckResult")
    fun insertItem(item: ItemEntity): LiveData<Long> {

        val idResult = MutableLiveData<Long>()


        Observable.fromCallable { dao.insertItem(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, it.toString())

                idResult.postValue(it)
            }, {
            })
        return idResult

    }

    @SuppressLint("CheckResult")
    fun getTransactionItems(): LiveData<List<ItemEntity>> {
        val items = MutableLiveData<List<ItemEntity>>()

        dao.getItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                items.postValue(it)
                Log.d(TAG, " getItem$it")
            }

        return items

    }

    @SuppressLint("CheckResult")
    fun editItem(item: ItemEntity): LiveData<Int> {
        val idResult = MutableLiveData<Int>()

        Observable.fromCallable { dao.editItem(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                idResult.postValue(it)
                Log.d(TAG, " getItem$it")
            }

        return idResult

    }

    @SuppressLint("CheckResult")
    fun removeItem(item: ItemEntity): LiveData<Int> {
        val idResult = MutableLiveData<Int>()

        Observable.fromCallable { dao.removeItem(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                idResult.postValue(it)
                Log.d(TAG, " getItem$it")
            }

        return idResult
    }
}