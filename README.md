# Dialogs
DialogFragment实现统一对话框


 // 基础 dialog 
 

public class BaseCommonDialogFmt extends DialogFragment {
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
            Log.v("Lin","Dialog error:common");
        }
    }
}



 // 底部dialog
 

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
            Log.v("Lin2", "Dialog error:bottom");
        }
    }

}
