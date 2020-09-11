package id.itborneo.crud_penjualan.ui.main.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.itborneo.crud_penjualan.R
import id.itborneo.crud_penjualan.ui.main.MainViewModel
import id.itborneo.crud_penjualan.utils.EXTRA_REMOVE_LAST_LOGIN
import id.itborneo.crud_penjualan.utils.EXTRA_USER
import id.itborneo.crud_penjualan.utils.LAST_LOGIN
import id.itborneo.crud_penjualan.utils.ViewsUtils
import id.itborneo.crud_penjualan.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private val TAG = "LoginFragment"
    private lateinit var navController: NavController
    private lateinit var viewModel: MainViewModel

    private lateinit var sharePref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)


        attachEditTextListener()
        attachMainViewModel()
        attachButtonLogin()
        checkLastLogin()

    }

    private fun attachEditTextListener() {

        etEmail.addTextChangedListener {
            ViewsUtils.validateInput(etEmail, "Email Tidak boleh Kosong", ilEmail)
        }

        etPassword.addTextChangedListener {
            ViewsUtils.validateInput(etPassword, "Passwod Tidak boleh Kosong", ilPassword)
        }
    }

    private fun checkLastLogin() {
        val lastLogin = getLastLogin()?.toList()
        if (lastLogin != null) {
            Log.d(TAG, "getLogin $lastLogin")
            loginValidation(lastLogin[0], lastLogin[1])
        }
    }

    private fun attachButtonLogin() {
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            loginValidation(email, password)
        }
        btnRegister.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun checkIsFromHomeActivity() { //logout user
        val intentUser = arguments?.getBoolean(EXTRA_REMOVE_LAST_LOGIN)
        Log.d(TAG, "checkIsFromHomeActivity $intentUser")


        if (intentUser != null) {
            removeLastLogin()
        }
    }


    private fun loginValidation(email: String, password: String) {

        Log.d(TAG, "lloginValidation $email dan $password")

        spinKitLoading.visibility = View.VISIBLE

        viewModel.getUser(email).observe(viewLifecycleOwner, { user ->
            spinKitLoading.visibility = View.GONE

            if (user != null) {
                if (password == user.password) {
                    setLastLogin(email, password)
                    //password benar

                    val bundle = bundleOf(
                        EXTRA_USER to user,
                    )

                    ViewsUtils.navigateTo(
                        navController,
                        R.id.action_loginFragment_to_homeActivity,
                        bundle
                    )
                    //TODO - coba kirim data lewat intent aja soalnya gagal lewat navigatio
                    requireActivity().finish()

                } else {
                    //password salah
                    Log.d(TAG, "user password salah")
                    showToastSalahEmailOrPassword()

                }
            } else {
                Log.d(TAG, "user email salah")
                showToastSalahEmailOrPassword()
                //user tidak ada
            }
        })
    }

    private fun showToastSalahEmailOrPassword() {
        Toast.makeText(context, "Email atau Password Salah", Toast.LENGTH_SHORT).show()
    }

    private fun attachMainViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity().application)

        viewModel = activity?.run {
            ViewModelProvider(this, factory)[MainViewModel::class.java]
        } ?: throw Exception("Salah Activity")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharePref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        checkIsFromHomeActivity()

    }

    private fun getLastLogin(): MutableSet<String>? {
        Log.d(TAG, " getLastLogin() " + sharePref.getStringSet(LAST_LOGIN, null).toString())
        return sharePref.getStringSet(LAST_LOGIN, null)
    }

    private fun setLastLogin(email: String, pass: String) {
        sharePref.edit {
            putStringSet(LAST_LOGIN, mutableSetOf(email, pass))
            Log.d(TAG, "setLastLogin $email and $pass")
        }
    }

    private fun removeLastLogin() {
        sharePref.edit().clear().apply()
    }
}