package com.helper.jiaop.helpers;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by jiaop on 2016/6/2.
 * 手机信息管理
 */
public class PhoneUtils {
    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机身份证IMEI
     *
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * 获取手机号码，很多手机获取不到
     *
     * @param context
     * @return
     */
    public static String getTel(Context context) {
        TelephonyManager telManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telManager.getLine1Number();
    }

}
