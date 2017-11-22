package com.reproduction;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;


public class NativeViewManager extends SimpleViewManager<View> {
    @Override
    public String getName() {
        return "RCTNativeView";
    }

    ViewGroup nativeView;

    @Override
    public View createViewInstance(ThemedReactContext context) {
        Log.d("NativeViewManager", "createViewInstance");

        nativeView = new RelativeLayout(context);
        nativeView.setBackgroundColor(Color.RED);

        return nativeView;
    }

    @ReactProp(name = "someRandomProp")
    public void setSomeRandomProp(View view, @Nullable String string) {
        Log.d("NativeViewManager", "setSomeRandomProp");

        View anotherView = new View(view.getContext());
        anotherView.setBackgroundColor(Color.GREEN);
        nativeView.addView(anotherView);
    }
}