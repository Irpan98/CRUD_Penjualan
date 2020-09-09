package id.itborneo.crud_penjualan.ui.main.registerCompleted

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.itborneo.crud_penjualan.R
import id.itborneo.crud_penjualan.data.local.entity.UserEntity
import id.itborneo.crud_penjualan.ui.main.MainViewModel
import id.itborneo.crud_penjualan.utils.EXTRA_USER
import id.itborneo.crud_penjualan.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_register_completed.*


class RegisterCompletedFragment : Fragment() {

    private val TAG = "RegisterCompletedFragment"

    private lateinit var viewModel: MainViewModel
    private lateinit var user: UserEntity
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_completed, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intentUser = arguments?.getParcelable<UserEntity>(EXTRA_USER)
        if (intentUser != null) {
            Log.d(TAG, arguments?.getParcelable<UserEntity>(EXTRA_USER).toString())
            user = intentUser
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachBackButton()
        attachView(view)
        attachMainViewModel()
    }

    private fun attachMainViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity().application)

        viewModel = activity?.run {
            ViewModelProvider(this, factory)[MainViewModel::class.java]
        } ?: throw Exception("Salah Activity")

    }

    private fun attachView(view: View) {
        val intentUser = arguments?.getParcelable<UserEntity>(EXTRA_USER)
        if (intentUser != null) {

            etEmail.setText(intentUser.email)
            etName.setText(intentUser.name)

        }
        navController = Navigation.findNavController(view)

        btnHome.setOnClickListener {
            insertUser()
        }
    }


    private fun insertUser() {
        if (!::user.isInitialized) return
        viewModel.insertUser(user).observe(viewLifecycleOwner, {
            Log.d(TAG, "inser User Succeed : id $it")
            Toast.makeText(requireContext(), "User  ${user.name} Created", Toast.LENGTH_SHORT)
                .show()
            navController.navigate(R.id.action_registerCompletedFragment_to_loginFragment)
        })
    }

//    private fun getLastLogin(): MutableSet<String>? {
//        val sharePref = requireActivity().getPreferences(Context.MODE_PRIVATE)
//        Log.d(TAG, " getLastLogin() " + sharePref.getStringSet(LAST_LOGIN, null).toString())
//        return sharePref.getStringSet(LAST_LOGIN, null)
//    }

    private fun attachBackButton() {
        back.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}