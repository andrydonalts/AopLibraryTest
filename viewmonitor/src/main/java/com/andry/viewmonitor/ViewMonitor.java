package com.andry.viewmonitor;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class ViewMonitor {

    @Pointcut("execution(* *(..))")
    private void callAllMethods() {}

    @Pointcut("execution(* android.app.Activity+.onCreate(..))")
    private void onCreatePointcut() {}

    @Pointcut("execution(* com.andry.aoplibrarytest.MainActivity.onCreate(..))")
    private void logOnCreateWithPackage() {}

    @Pointcut("execution(* *.on*(..))")
    private void logAllOnMethods() {}

//    @Before("logOnCreateWithPackage()")
//    public void logOnCreateWithPackAspect(JoinPoint jp) {
//        System.out.println("-> library before onCreate with package");
//    }

//    @Before("callAllMethods()")
//    public void logAllMethodAspect(JoinPoint jp) {
//        System.out.println("-> Library Before each method - " + jp.getSignature());
//    }

//    @Before("logAllOnMethods()")
//    public void logAllOnMethodsAspect(JoinPoint jp) {
//        System.out.println("-> Library before on method " + jp.getSignature());
//    }

    @After("onCreatePointcut()")
    public void logAfterOnCreateAspect(JoinPoint jp) {
        System.out.println("-> After onCreate - " + jp.getSignature());
        Activity activity = (Activity) jp.getTarget();
        System.out.println(activity.getPackageName());
        getViewsInfo(activity);
    }

    private void getViewsInfo(Activity activity) {
        final ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        System.out.println("rootview not null " + (rootView != null));
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                toNextView(rootView.getChildAt(0));
            }
        });
    }

    private void toNextView(View view) {
        int[] locationOnScreen = {-1, -1};
        int[] locationInWindow = {-2, -2};
        view.getLocationOnScreen(locationOnScreen);
        view.getLocationInWindow(locationInWindow);

        Log.d("Aspect - toNextView", "==== " + Integer.toString(view.getId()) +
                " locationOnScreen " + Arrays.toString(locationOnScreen) +
                " locationInWindow " + Arrays.toString(locationInWindow) +
                " height " + view.getMeasuredHeight() + " width " + view.getMeasuredWidth());

        if (view instanceof ViewGroup) {
            int childAmount = ((ViewGroup) view).getChildCount();
            Log.d("Amount of Child", Integer.toString(childAmount));
            for (int i = 0; i < childAmount; i++) {
                toNextView(((ViewGroup) view).getChildAt(i));
            }
        }
    }

}
