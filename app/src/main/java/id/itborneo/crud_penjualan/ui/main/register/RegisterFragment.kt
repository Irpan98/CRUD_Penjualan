package id.itborneo.crud_penjualan.ui.main.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.itborneo.crud_penjualan.R
import id.itborneo.crud_penjualan.data.local.entity.UserEntity
import id.itborneo.crud_penjualan.ui.main.MainViewModel
import id.itborneo.crud_penjualan.utils.EXTRA_USER
import id.itborneo.crud_penjualan.utils.ViewsUtils.navigateTo
import id.itborneo.crud_penjualan.utils.ViewsUtils.validateInput
import id.itborneo.crud_penjualan.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.etEmail


class RegisterFragment : Fragment() {


    private lateinit var navController: NavController
    private lateinit var viewModel: MainViewModel

    private lateinit var userEntity: UserEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        userEntity = UserEntity("", "", "", "")
        attachMainViewModel()
        attachBtnNext()
        attachBackButton()
    }


    private fun attachBtnNext() {

        btnNext.setOnClickListener {

            val isNameNotNull = validateInput(etName, "Nama Tidak boleh kosong")
            val isEmailNotNull = validateInput(etEmail, "Email tidak boleh kosong")

            if (isNameNotNull && isEmailNotNull) {

                userEntity.name = etName.text.toString()
                userEntity.email = etEmail.text.toString()


                emailChecker(etEmail.text.toString())


            }
        }

    }

    private fun attachBackButton() {
        back.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun emailChecker(email: String) {
//
//        Log.d(TAG, "emailChecker running")

        spinKitLoading.visibility = View.VISIBLE

        viewModel.getUser(email).observe(viewLifecycleOwner, { user ->
            spinKitLoading.visibility = View.GONE

            if (user == null) {


                val bundle = bundleOf(
                    EXTRA_USER to userEntity,
                )


                navigateTo(
                    navController,
                    R.id.action_registerFragment_to_registerNextFragment,
                    bundle
                )

            } else {
                showToastEmailUsed()
                //user tidak ada
            }
        })
    }

    private fun attachMainViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity().application)

        viewModel = activity?.run {
            ViewModelProvider(this, factory)[MainViewModel::class.java]
        } ?: throw Exception("Salah Activity")

    }

    private fun showToastEmailUsed() {
        Toast.makeText(context, "Email telah digunakan", Toast.LENGTH_SHORT).show()
    }


}