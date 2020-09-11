package id.itborneo.crud_penjualan.ui.home.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.itborneo.crud_penjualan.R
import id.itborneo.crud_penjualan.ui.home.transaction.TransactionViewModel
import id.itborneo.crud_penjualan.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_report.*


class ReportFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        (activity as AppCompatActivity).supportActionBar?.title = "Report"

        attachViewModel()
    }

    private lateinit var viewModel: TransactionViewModel

    private fun attachViewModel() {

        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[TransactionViewModel::class.java]

        viewModel.getTransactionItems().observe(this, { items ->

            tvTotalTransaction.text = items.size.toString()

        })
    }

}