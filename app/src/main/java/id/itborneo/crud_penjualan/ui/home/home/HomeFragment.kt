package id.itborneo.crud_penjualan.ui.home.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.itborneo.crud_penjualan.R
import id.itborneo.crud_penjualan.data.local.entity.UserEntity
import id.itborneo.crud_penjualan.utils.EXTRA_REMOVE_LAST_LOGIN
import id.itborneo.crud_penjualan.utils.EXTRA_USER
import id.itborneo.crud_penjualan.utils.ViewsUtils
import id.itborneo.crud_penjualan.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private val TAG = "HomeFragment"
    private var intentUser: UserEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        attachViewModel()

        attachViewText()

        attachLogoutBtn(navController)
    }

    private fun attachViewText() {

        intentUser = arguments?.getParcelable(EXTRA_USER)
        Log.d(TAG, "intentUser" + arguments?.getParcelable<UserEntity>(EXTRA_USER).toString())
        intentUser?.let { viewModel.setUser(it) }

        if (intentUser != null) {
            tvUser.text = intentUser?.name
            tvWelcomeUser.text = intentUser?.name
            tvEmail.text = intentUser?.email
        }

    }

    private fun attachLogoutBtn(navController: NavController) {
        btnLogOut.setOnClickListener {

            val bundle = bundleOf(
                EXTRA_REMOVE_LAST_LOGIN to true,
            )

            ViewsUtils.navigateTo(
                navController,
                R.id.action_homeFragment_to_mainActivity,
                bundle
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Home"
    }


    private fun attachViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]

        viewModel.getUser().observe(requireActivity()) { items ->
            Log.d(TAG, items.toString())
            tvUser.text = items!!.name
        }
    }


}