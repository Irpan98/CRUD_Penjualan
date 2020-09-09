package id.itborneo.crud_penjualan.utils

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout

object ViewsUtils {

    fun setDialogComfirm(context: Context, delete: (() -> Unit), cancelDelete: (() -> Unit)) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setTitle("Apakah Anda Yakin")
        builder.setMessage("Item berikut akan dihapus.")



        builder.setNegativeButton(
            "DELETE"
        ) { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
            delete()

        }
        builder.setPositiveButton(
            "Cancel"
        ) { dialog, _ ->
            dialog.dismiss()
            cancelDelete()
        }
        val dialog: AlertDialog = builder.create() // calling builder.create after adding buttons

        dialog.show()
    }

    //attach on swipe to delete
    fun attachOnSwipe(
        context: Context,
        recyclerView: RecyclerView,
        onSwipeCalled: (RecyclerView.ViewHolder) -> Unit
    ) {
        val swipeHandler = object : SwipeToDeleteCallback(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                onSwipeCalled(viewHolder)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    //check edit text not null
    fun validateInput(editText: EditText, warningText: String, editTextInputLayout: TextInputLayout? =null): Boolean {

        if (editText.text.isEmpty()) {
            if(editTextInputLayout==null){
                editText.error = warningText

            }else{
                editTextInputLayout.error = warningText
            }
            return false
        }else{
            editTextInputLayout?.error = null
            editText.error = null
            return true

        }


    }

    // Fragment main nav
    fun navigateTo(navController: NavController, action: Int, bundle: Bundle? = null) {
        if (bundle != null) {
            navController.navigate(action, bundle)

        } else {
            navController.navigate(action)

        }
    }

}