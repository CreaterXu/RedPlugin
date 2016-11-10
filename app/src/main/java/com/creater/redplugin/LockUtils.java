package com.creater.redplugin;

import android.content.Context;
import android.os.PowerManager;

/**
 * Created by Administrator on 2016/11/10.
 */

public class LockUtils {
    private static PowerManager.WakeLock mWakeLock;
    //申请设备电源锁
    private  static void acquireWakeLock(Context context)
    {
        if (null == mWakeLock)
        {
            PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK|PowerManager.ON_AFTER_RELEASE, "");
            if (null != mWakeLock)
            {
                mWakeLock.acquire();
            }
        }
    }
    //释放设备电源锁
    private static void releaseWakeLock()
    {
        if (null != mWakeLock)
        {
            mWakeLock.release();
            mWakeLock = null;
        }
    }
}
