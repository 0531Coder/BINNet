package com.sheyuan.hybridlibrary.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sheyuan.hybridlibrary.R;
import com.sheyuan.hybridlibrary.param.HybridParamAnimation;

import java.util.List;


/**
 * Created by vane on 16/6/3.
 */

public class ActivityUtil {

    public static void toSimpleActivity(Context context, Class clazz, HybridParamAnimation animation, Bundle bundle) {
        final Intent intent = new Intent(context, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        appendAnimation(context, animation);
    }

    private static void appendAnimation(Context context, HybridParamAnimation animation) {
        if (context instanceof Activity) {
            if (null == animation || animation.equals(HybridParamAnimation.PUSH)) {
                ((Activity) context).overridePendingTransition(R.anim.hybrid_right_in, R.anim.hybrid_left_out);
            } else if (animation.equals(HybridParamAnimation.POP)) {
                ((Activity) context).overridePendingTransition(R.anim.hybrid_left_in, R.anim.hybrid_right_out);
            } else if (animation.equals(HybridParamAnimation.PRESENT)) {
                ((Activity) context).overridePendingTransition(R.anim.hybrid_bottom_in, R.anim.hybrid_top_out);
            }
        }
    }

    public static void toActivity(Context context, Intent intent, HybridParamAnimation animation) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        appendAnimation(context, animation);
    }


    public static boolean isForeground(Context context, String className) {
        if (context == null || TextUtils.isEmpty(className)) {
            return false;
        }

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }

        return false;
    }
 
    
    
    
    
}
