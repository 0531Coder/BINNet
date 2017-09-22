package com.sheyuan.hybridlibrary.core;

import java.util.HashMap;

/**
 * Created by moutain on 17-9-21 14:21.
 */

public class HybridTagMapping {
    private static HashMap<String, Class> mMap;

    static {
        mMap = new HashMap<>();

    }

    public static Class mapping(String method) {
            return mMap.get(method);
    }

}
