package com.lin.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CommonDialogFmt extends BaseCommonDialogFmt implements View.OnClickListener {

    TextView tvTitle;
    TextView tvMessage;

    Button btnSure;
    Button btnCancel;

    String key;

    public interface OnCommonDialogSureListener {
        void onClick(String key);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_common, container);

        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvMessage = (TextView) view.findViewById(R.id.tv_message);
        btnSure = (Button) view.findViewById(R.id.btn_sure);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        Bundle bundle = getArguments();
        key = bundle.getString("key");
        tvTitle.setText(bundle.getString("title"));
        tvMessage.setText(bundle.getString("msg"));
        btnSure.setText(bundle.getString("sure_text"));
        btnCancel.setText(bundle.getString("cancel_text"));

        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:
                OnCommonDialogSureListener onSureListener = (OnCommonDialogSureListener) getActivity();
                onSureListener.onClick(key);
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
        }
    }
}
