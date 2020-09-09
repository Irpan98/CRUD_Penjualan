package id.itborneo.crud_penjualan.ui.main.registerNext

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.itborneo.crud_penjualan.R
import id.itborneo.crud_penjualan.data.local.entity.UserEntity
import id.itborneo.crud_penjualan.utils.EXTRA_USER
import id.itborneo.crud_penjualan.utils.ViewsUtils
import kotlinx.android.synthetic.main.fragment_register_next.*

class RegisterNextFragment : Fragment() {
    private lateinit var userEntity: UserEntity
    private lateinit var navController: NavController


    private val TAG = "RegisterNextFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_next, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        attachButtonNext()
        attachBackButton()

    }

    private fun attachButtonNext() {
        btnFinish.setOnClickListener {

            val isPasswordNotNull = ViewsUtils.validateInput(etPassword, "Nama Tidak boleh kosong")
            val isComfirmPassNotNull =
                ViewsUtils.validateInput(etConfirmPassword, "Email tidak boleh kosong")

            if (isPasswordNotNull &&
                isComfirmPassNotNull
            ) {
                if (etPassword.text.toString() == etConfirmPassword.text.toString()) {
                    userEntity.password = etPassword.text.toString()

                    val bundle = bundleOf(
                        EXTRA_USER to userEntity,
                    )

                    ViewsUtils.navigateTo(
                        navController,
                        R.id.action_registerNextFragment_to_registerCompletedFragment,
                        bundle
                    )
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Password dan Konfirmasi Password harus sama ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getIntentData()
    }

    private fun getIntentData() {
        val intentUser = arguments?.getParcelable<UserEntity>(EXTRA_USER)
        if (intentUser != null) {
            Log.d(TAG, arguments?.getParcelable<UserEntity>(EXTRA_USER).toString())
            userEntity = intentUser
        }
    }

    private fun attachBackButton() {
        back.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}