# Dialogs 
DialogFragment实现统一对话框

系统的dialog很丑？那就来自定义吧。
使用DialogFragment来管理对话框，它和Fragment有着基本一致的生命周期。生命周期在我们对dialog的灵活处理时起着重要的作用.

# 首先需要两个监听（可以根据需求加其他的）

点击确定的监听

```
public interface OnSureListener {
    void onSure();
}

```
点击取消的监听

```
public interface OnCancelListener {
    void onCancel();
}
```

设置统一宽度的基础dialog。

```
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

```
底部dialog 往往需要占满横屏 

```
public class BottomDialogFmt extends DialogFragment {

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.BOTTOM;
            //设置占满横屏
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

```
常用的dialog实现：
这里使用了建造者模式 

```
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
```

最后就是调用：掉用的方法类似AlertDialog

```
  CommonDialog.Builder builder = new CommonDialog.Builder()
                        .title("对画框1")
                        .setCancelable(false)
                        .setMsg("你好a")
                        .setSureText("确定文字")
                        .setOnSureListener(new OnSureListener() {
                            @Override
                            public void onSure() {
                                //当前能够点击 说明alertDialog不为空且正常显示
                                Toast.makeText(MainActivity.this, "您点击了确定", Toast.LENGTH_SHORT).show();
                                commonDialog.dismiss();
                            }
                        })
                        .setCancelText("取消文字")
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                Toast.makeText(MainActivity.this, "您点击了取消", Toast.LENGTH_SHORT).show();
                            }
                        });
                commonDialog = builder.create();
                commonDialog.show(getFragmentManager(), "一般对话框");
```                













