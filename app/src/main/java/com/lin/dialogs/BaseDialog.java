package com.lin.dialogs;


import android.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;

/**
 * 基础 dialog
 */

public class BaseDialog extends DialogFragment {
    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            //设置统一的宽度
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            window.setLayout((int) (dm.widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            Log.v("Lin","CommonDialog error:common");
        }
    }
}
