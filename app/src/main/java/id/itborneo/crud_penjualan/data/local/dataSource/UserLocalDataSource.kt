package id.itborneo.crud_penjualan.data.local.dataSource

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.itborneo.crud_penjualan.data.local.dao.UserDao
import id.itborneo.crud_penjualan.data.local.entity.UserEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserLocalDataSource(private val dao: UserDao) {

    private val TAG = "UserLocalDataSource"

    companion object {

        @Volatile
        private var INSTANCE: UserLocalDataSource? = null

        fun getInstance(dao: UserDao): UserLocalDataSource {
            Log.d("LocalDataSource", "getInstance called $INSTANCE")


            return INSTANCE ?: UserLocalDataSource(dao)
        }
    }


    @SuppressLint("CheckResult")
    fun insertUser(user: UserEntity): LiveData<Long> {

        val idResult = MutableLiveData<Long>()


        Observable.fromCallable { dao.insertUser(user) }
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
    fun editUser(user: UserEntity): LiveData<Int> {
        val idResult = MutableLiveData<Int>()

        Observable.fromCallable { dao.editUser(user) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                idResult.postValue(it)
                Log.d(TAG, " getUser$it")
            }

        return idResult

    }

    @SuppressLint("CheckResult")
    fun removeUser(user: UserEntity): LiveData<Int> {
        val idResult = MutableLiveData<Int>()

        Observable.fromCallable { dao.removeUser(user) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                idResult.postValue(it)
                Log.d(TAG, " getUser$it")
            }

        return idResult
    }

    @SuppressLint("CheckResult")
    fun getUser(email: String): LiveData<UserEntity> {

        val user = MutableLiveData<UserEntity>()

        dao.getUser(email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                user.postValue(it)
                Log.d(TAG, " getUser$it")
            }, {
                user.postValue(null)
                Log.d("TAG", "getIser" + it.message.toString())
            })

        return user
    }
}