package id.itborneo.crud_penjualan.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class ItemEntity(

    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "amount")
    var amount: String,
    @ColumnInfo(name = "price")
    var price: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
) : Parcelable