package id.itborneo.crud_penjualan.data.local.dao

import androidx.room.*
import id.itborneo.crud_penjualan.data.local.entity.ItemEntity
import id.itborneo.crud_penjualan.data.local.entity.UserEntity
import io.reactivex.Flowable

@Dao
interface ItemDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(itemEntity: ItemEntity): Long


    @Query("SELECT * FROM ItemEntity")
    fun getItems(): Flowable<List<ItemEntity>>


    @Update(entity = ItemEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun editItem(item: ItemEntity): Int

    @Delete
    fun removeItem(item: ItemEntity): Int

}