package com.wangxia.battle.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.Toast;

import com.wangxia.battle.globe.App;

/**
 * 安全吐司的重要性
 * Toast 工具类.简化Toast的使用，并且不用关心线程的问题。
 * Toast的初始化会创建默认构造Handler。Handler默认构造会使用当前线程Looper。如果没有，抛异常。
 * 调用了Looper.prepare和Looper.loop以后Toast才可以生效。但是子线程调用了loop方法就阻塞了。
 * 所以选择抛到主线程执行。
 *
 * @author Seny
 */
public class MyToast {
    private static Toast mToast;
    public static final int TOAST_TIME = 200;

    /**
     * 安全弹出Toast。处理线程的问题。
     */
    public static void safeShow(final Context context, final String text) {
        if (Looper.myLooper() != Looper.getMainLooper()) {//如果不是在主线程弹出吐司，那么抛到主线程弹
            new Handler(Looper.getMainLooper()).post(
                    new Runnable() {
                        @Override
                        public void run() {
                            if (context == null){
                                showToast(App.context, text, TOAST_TIME);
                            }else{
                                showToast(context, text, TOAST_TIME);
                            }
                        }
                    }
            );
        } else {
            if (context == null){
                showToast(App.context, text, TOAST_TIME);
            }else{
                showToast(context, text, TOAST_TIME);
            }
        }
    }

    /**
     * 弹出Toast，处理单例的问题。----如果是在主线程,可以用这个,子线程就不要用这个,不安全
     *
     * @param context
     * @param text
     * @param lengthShort
     */
    public static void showToast(Context context, String text, int lengthShort) {
        if (context == null){
            context = App.context;
        }
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        mToast.setDuration(lengthShort);
        mToast.setText(text);
        mToast.setGravity(Gravity.CENTER,0,0);//屏幕中间显示
        mToast.show();
    }

    public static void s(final Context context, final String text) {
        if (Looper.myLooper() != Looper.getMainLooper()) {//如果不是在主线程弹出吐司，那么抛到主线程弹
            new Handler(Looper.getMainLooper()).post(
                    new Runnable() {
                        @Override
                        public void run() {
                            if (context == null) {
                                showToast(App.context, text, TOAST_TIME);
                            } else {
                                showToast(context, text, TOAST_TIME);
                            }
                        }
                    }
            );
        } else {
            if (context == null) {
                showToast(App.context, text, TOAST_TIME);
            } else {
                showToast(context, text, TOAST_TIME);
            }
        }
    }
}
