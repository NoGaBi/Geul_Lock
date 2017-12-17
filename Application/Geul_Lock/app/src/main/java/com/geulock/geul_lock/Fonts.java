package com.geulock.geul_lock;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by sihwan on 2017. 12. 13..
 */

public class Fonts {
    private static Typeface bradhitc, mn;

    public static Typeface getBradhitc(Context context) {
        if (bradhitc == null)
            bradhitc = Typeface.createFromAsset(context.getAssets(), "BRADHITC.TTF");
        return bradhitc;
    }

    public static Typeface getMn(Context context) {
        if (mn == null)
            mn = Typeface.createFromAsset(context.getAssets(), "MN.TTF");
        return mn;
    }
}
