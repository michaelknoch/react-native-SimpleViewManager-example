package com.reproduction;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.RelativeLayout;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;


public class NativeViewManager extends SimpleViewManager<View> {
    @Override
    public String getName() {
        return "RCTNativeView";
    }

    Context context;
    ViewGroup nativeView;

    @Override
    public View createViewInstance(ThemedReactContext context) {
        this.context = context;
        Log.d("NativeViewManager", "createViewInstance");

        nativeView = new ForcedLayout(context);
        nativeView.setBackgroundColor(Color.RED);

        return nativeView;
    }

    @ReactProp(name = "someRandomProp")
    public void setSomeRandomProp(View view, @Nullable String string) {
        Log.d("NativeViewManager", "setSomeRandomProp");

        if (string == null) {
            return;
        }

        Handler mainHandler = new Handler(context.getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                View anotherView = new View(context);
                anotherView.setBackgroundColor(Color.GREEN);
                nativeView.addView(anotherView);

                System.out.println("sachen sachen");
                System.out.println(anotherView.getMeasuredHeight());
                System.out.println(anotherView.getMeasuredWidth());


            } // This is your code
        };
        mainHandler.post(myRunnable);


    }
}

class ForcedLayout extends RelativeLayout {

    public ForcedLayout(Context context) {
        super(context);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();

        // The spinner relies on a measure + layout pass happening after it calls requestLayout().
        // Without this, the widget never actually changes the selection and doesn't call the
        // appropriate listeners. Since we override onLayout in our ViewGroups, a layout pass never
        // happens after a call to requestLayout, so we simulate one here.
        post(measureAndLayout);
    }

    private final Runnable measureAndLayout = new Runnable() {
        @Override
        public void run() {
            measure(
                    MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY)
            );
            layout(getLeft(), getTop(), getRight(), getBottom());
        }
    };
}