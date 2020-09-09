package id.itborneo.crud_penjualan.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.itborneo.crud_penjualan.data.local.dao.ItemDao
import id.itborneo.crud_penjualan.data.local.dao.UserDao
import id.itborneo.crud_penjualan.data.local.entity.ItemEntity
import id.itborneo.crud_penjualan.data.local.entity.UserEntity

@Database(version = 1, entities = [ItemEntity::class, UserEntity::class])
abstract class PenjualanDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun userDao(): UserDao


    companion object {
        private var INSTANCE: PenjualanDatabase? = null

        @JvmStatic
        fun instance(context: Context): PenjualanDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    PenjualanDatabase::class.java, "db_movie_catalogue"
                ).build()
            }
            return INSTANCE as PenjualanDatabase
        }
    }

}