package id.itborneo.crud_penjualan.ui.home.transactionInput

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.itborneo.crud_penjualan.R
import id.itborneo.crud_penjualan.data.local.entity.ItemEntity
import id.itborneo.crud_penjualan.ui.home.transaction.TransactionFragment
import id.itborneo.crud_penjualan.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_transaction_input.*


class TransactionInputActivity : AppCompatActivity() {

    private val TAG = "TransactionInputActivity"

    companion object {
        const val EXTRA_ITEM = "extra_item_get"

        const val REQUEST_ADD = 100
        const val REQUEST_EDIT = 101

    }

    private lateinit var viewModel: TransactionInputViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_input)

        val getItemIntent = intent.getParcelableExtra<ItemEntity>(EXTRA_ITEM)
        Log.d(TAG, "getItemIntent $getItemIntent")


        if (getItemIntent == null) {
            attachButton {
                insertItem(it)
            }

        } else {
            attachText(getItemIntent)

            attachButton {
                it.id = getItemIntent.id
                editItem(it)

            }
        }


        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory)[TransactionInputViewModel::class.java]

    }

    private fun attachText(item: ItemEntity) {

        edName.setText(item.name)
        edAmount.setText(item.amount)
        edPrice.setText(item.price)
    }

    private fun success(id: Long) {
        val intent = Intent(baseContext, TransactionFragment::class.java)
        intent.putExtra(EXTRA_ITEM, id)
        setResult(RESULT_OK, intent)
        finish()
    }


    private fun attachButton(clickListener: (ItemEntity) -> Unit) {
        btnInsert.setOnClickListener {
            spinKitLoading.visibility = View.VISIBLE

            val item = ItemEntity(
                edName.text.toString(),
                edAmount.text.toString(),
                edPrice.text.toString()
            )
            clickListener(item)

        }
    }

    private fun insertItem(item: ItemEntity) {
        viewModel.insertItem(item).observe(this, {
            success(it)
        })
    }

    private fun editItem(item: ItemEntity) {
        spinKitLoading.visibility = View.GONE

        Log.d(TAG, "editItem $item")
        viewModel.editItem(item).observe(this, {
            success(it.toLong())

        })
    }


}