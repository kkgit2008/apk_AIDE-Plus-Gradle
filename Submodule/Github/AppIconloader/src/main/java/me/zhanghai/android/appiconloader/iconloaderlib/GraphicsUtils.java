/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.zhanghai.android.appiconloader.iconloaderlib;

import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;

import androidx.annotation.ColorInt;

public class GraphicsUtils {

    private static final String TAG = "GraphicsUtils";

    public static Runnable sOnNewBitmapRunnable = () -> { };

    /**
     * Set the alpha component of {@code color} to be {@code alpha}. Unlike the support lib version,
     * it bounds the alpha in valid range instead of throwing an exception to allow for safer
     * interpolation of color animations
     */
    @ColorInt
    public static int setColorAlphaBound(int color, int alpha) {
        if (alpha < 0) {
            alpha = 0;
        } else if (alpha > 255) {
            alpha = 255;
        }
        return (color & 0x00ffffff) | (alpha << 24);
    }

    public static int getArea(Region r) {
        RegionIterator itr = new RegionIterator(r);
        int area = 0;
        Rect tempRect = new Rect();
        while (itr.next(tempRect)) {
            area += tempRect.width() * tempRect.height();
        }
        return area;
    }

    /**
     * Utility method to track new bitmap creation
     */
    public static void noteNewBitmapCreated() {
        sOnNewBitmapRunnable.run();
    }
}
