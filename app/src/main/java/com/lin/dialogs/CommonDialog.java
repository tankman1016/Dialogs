package com.lin.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.lin.dialogs.listener.OnCancelListener;
import com.lin.dialogs.listener.OnSureListener;

/**
 * 2018-12-07 使用建造者模式重构
 */

public class CommonDialog extends BaseDialog {

    TextView tvTitle;
    TextView tvMessage;

    Button btnSure;
    Button btnCancel;

    OnSureListener onSureListener;
    OnCancelListener onCancelListener;

    public void setOnSureListener(OnSureListener onSureListener) {
        this.onSureListener = onSureListener;
    }

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        Bundle bundle = getArguments();

        if (!bundle.getBoolean("cancelable")) {
            //如果是true则是能取消的，如果不是则不能取消
            setCancelable(false);
            getDialog().setCanceledOnTouchOutside(false);
        }

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_common, container);

        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvMessage = (TextView) view.findViewById(R.id.tv_message);
        btnSure = (Button) view.findViewById(R.id.btn_sure);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);


        if (!TextUtils.isEmpty(bundle.getString("title"))) {
            tvTitle.setText(bundle.getString("title"));
        }
        if (!TextUtils.isEmpty(bundle.getString("msg"))) {
            tvMessage.setText(bundle.getString("msg"));
        }

        if (!TextUtils.isEmpty(bundle.getString("sure_text"))) {
            btnSure.setText(bundle.getString("sure_text"));
        }

        if (!TextUtils.isEmpty(bundle.getString("cancel_text"))) {
            btnCancel.setText(bundle.getString("cancel_text"));
        }

        if (onSureListener != null) {
            btnSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSureListener.onSure();
                }
            });
        }

        if (onCancelListener != null) {
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCancelListener.onCancel();
                }
            });
        }
        return view;
    }


    public static class Builder {

        private String title;
        private String msg;
        private String sure_text;
        private String cancel_text;
        private OnSureListener onSureListener;
        private OnCancelListener onCancelListener;
        private boolean cancelable;


        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder setSureText(String sure_text) {
            this.sure_text = sure_text;
            return this;
        }

        public Builder setCancelText(String cancel_text) {
            this.cancel_text = cancel_text;
            return this;
        }

        public Builder setOnSureListener(OnSureListener onSureListener) {
            this.onSureListener = onSureListener;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            this.onCancelListener = onCancelListener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public CommonDialog create() {
            CommonDialog commonCommonDialog = new CommonDialog();
            Bundle args = new Bundle();
            args.putString("title", this.title);
            args.putString("msg", this.msg);
            args.putString("sure_text", this.sure_text);
            args.putString("cancel_text", this.cancel_text);
            args.putBoolean("cancelable", this.cancelable);
            commonCommonDialog.setArguments(args);
            commonCommonDialog.setOnSureListener(onSureListener);
            commonCommonDialog.setOnCancelListener(onCancelListener);
            return commonCommonDialog;
        }
    }

}
