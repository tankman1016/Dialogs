package com.lin.dialogs;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * 底部dialog
 */

public class BottomDialogFmt extends DialogFragment {

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.BOTTOM;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(params);
            //设置透明 需要自己配置背景色
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置动画
            window.setWindowAnimations(R.style.BottomDialogAnimation);
        } else {
            Log.v("Lin2", "CommonDialog error:bottom");
        }
    }

}
