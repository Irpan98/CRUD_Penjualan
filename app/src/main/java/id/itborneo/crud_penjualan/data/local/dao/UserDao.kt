package id.itborneo.crud_penjualan.data.local.dao

import androidx.room.*
import id.itborneo.crud_penjualan.data.local.entity.UserEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(itemEntity: UserEntity): Long

//    @Query("SELECT * FROM UserEntity")
//    fun getItems(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE email=:email")
    fun getUser(email: String): Single<UserEntity>

    @Update(entity = UserEntity::class)
    fun editUser(user: UserEntity): Int

    @Delete
    fun removeUser(user: UserEntity): Int

}