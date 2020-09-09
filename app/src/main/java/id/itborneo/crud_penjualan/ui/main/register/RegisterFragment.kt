package id.itborneo.crud_penjualan.ui.main.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.itborneo.crud_penjualan.R
import id.itborneo.crud_penjualan.data.local.entity.UserEntity
import id.itborneo.crud_penjualan.utils.EXTRA_USER
import id.itborneo.crud_penjualan.utils.ViewsUtils.navigateTo
import id.itborneo.crud_penjualan.utils.ViewsUtils.validateInput
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {


    private lateinit var navController: NavController

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

                val bundle = bundleOf(
                    EXTRA_USER to userEntity,
                )

                navigateTo(
                    navController,
                    R.id.action_registerFragment_to_registerNextFragment,
                    bundle
                )
            }
        }

    }

    private fun attachBackButton() {
        back.setOnClickListener {
            activity?.onBackPressed()
        }
    }


}