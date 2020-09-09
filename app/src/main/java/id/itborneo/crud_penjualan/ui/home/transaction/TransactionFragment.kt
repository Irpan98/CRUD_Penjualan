package id.itborneo.crud_penjualan.ui.home.transaction

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.crud_penjualan.R
import id.itborneo.crud_penjualan.data.local.entity.ItemEntity
import id.itborneo.crud_penjualan.ui.home.transactionInput.TransactionInputActivity
import id.itborneo.crud_penjualan.ui.home.transactionInput.TransactionInputActivity.Companion.EXTRA_ITEM
import id.itborneo.crud_penjualan.ui.home.transactionInput.TransactionInputActivity.Companion.REQUEST_ADD
import id.itborneo.crud_penjualan.utils.ViewsUtils
import id.itborneo.crud_penjualan.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_transaction.*

class TransactionFragment : Fragment() {

    private val TAG = "TransactionFragment"

    private lateinit var adapter: TransactionAdapter

    private var items = listOf<ItemEntity>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Transaction"

        attachViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachRecyclerView()
        attachFab()
    }

    private lateinit var viewModel: TransactionViewModel

    private fun attachViewModel() {

        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[TransactionViewModel::class.java]

        viewModel.getTransactionItems().observe(requireActivity(), { items ->

            Log.d(TAG, items.toString())

            if (::adapter.isInitialized) {
                this.items = items
                adapter.setItems(items)
            }

        })
    }

    private fun attachRecyclerView() {
        rvItems.layoutManager = LinearLayoutManager(requireContext())
        adapter = TransactionAdapter(requireContext()) {
            moveActivity(it)
        }
        rvItems.adapter = adapter

        ViewsUtils.attachOnSwipe(requireContext(), rvItems) {

            ViewsUtils.setDialogComfirm(requireContext(), {
                Log.d(TAG, "setupOnSwipe + delete ${it.adapterPosition}")
                //delete
                val items = items[it.adapterPosition]
                removeItem(items)

            }, {
                adapter.notifyDataSetChanged()

            })
        }

    }

    private fun removeItem(item: ItemEntity) {
        viewModel.removeItems(item).observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Berhasil Menghapus Item", Toast.LENGTH_SHORT).show()
        })
    }

    private fun attachFab() {
        fabInputItem.setOnClickListener {
            moveActivity()
        }
    }


    private fun moveActivity(itemEntity: ItemEntity? = null) {
        Log.d(TAG, "moveActivity $itemEntity")
        val intent = Intent(context, TransactionInputActivity::class.java)
        if (itemEntity != null) {
            intent.putExtra(EXTRA_ITEM, itemEntity)
        }
        startActivityForResult(intent, REQUEST_ADD)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            Log.d(TAG, "resultCode ${data?.getLongExtra(EXTRA_ITEM, -1)}")
//            adapter.notifyDataSetChanged()
        }

    }

}