package id.itborneo.crud_penjualan.ui.home.transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.itborneo.crud_penjualan.R
import id.itborneo.crud_penjualan.data.local.entity.ItemEntity
import kotlinx.android.synthetic.main.item.view.*

class TransactionAdapter(
    private val context: Context,
    private val clickListener: (ItemEntity) -> Unit
) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    private var items = listOf<ItemEntity>()
    fun setItems(items: List<ItemEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemEntity: ItemEntity) {
            itemView.tvAmount.text = itemEntity.amount
            itemView.tvName.text = itemEntity.name
            itemView.tvPrice.text = itemEntity.price

            itemView.setOnClickListener {
                clickListener(itemEntity)
            }
        }
    }
}