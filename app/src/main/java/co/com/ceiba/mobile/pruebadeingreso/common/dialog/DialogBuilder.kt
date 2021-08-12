package co.com.ceiba.mobile.pruebadeingreso.common.dialog

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.*
import co.com.ceiba.mobile.pruebadeingreso.R

class DialogBuilder {

    companion object {

        fun showAlertDialog(context: Context, dataBuilder: DataBuilderDialog): Dialog {

            val dialog = Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.design_dialog_message_alert)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

            val tvMessageDialog : TextView =  dialog.findViewById(R.id.et_message_dialog_alert)
            tvMessageDialog.text = dataBuilder.message
            manageCancellable(dialog, dataBuilder.cancellable)

            return dialog
        }

        private fun manageCancellable(dialog: Dialog, cancellable: Boolean) {
            dialog.setCancelable(cancellable)
            if (cancellable) {
                val flBackgroundTotal: FrameLayout = dialog.findViewById(R.id.fl_background_dialog)
                flBackgroundTotal.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }
    }

}