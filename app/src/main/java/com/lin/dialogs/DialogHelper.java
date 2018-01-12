package com.lin.dialogs;

import android.app.Activity;
import android.os.Bundle;

/**
 *
 */

public class DialogHelper {

    /**
     * 显示一般的对话框
     */
    public static void showCommon(Activity activity, String key, String title, String msg,
                                  String sure_text, String cancel_text) {

        CommonDialogFmt commonDialogFmt = new CommonDialogFmt();
        Bundle bundle = new Bundle();
        bundle.putString("key", key);
        bundle.putString("title", title);
        bundle.putString("msg", msg);
        bundle.putString("sure_text", sure_text);
        bundle.putString("cancel_text", cancel_text);
        commonDialogFmt.setArguments(bundle);
        commonDialogFmt.show(activity.getFragmentManager(), "commonDialogFmt");

    }

    /**
     * 显示一般的对话框 只设置 msg
     */

    public static void showCommonWithOnlyMsg(Activity activity, String key, String msg) {
        showCommon(activity, key, "提示", msg, "确定", "取消");
    }
}
