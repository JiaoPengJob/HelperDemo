package com.helper.jiaop.helpers;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.File;

/**
 * Created by jiaop on 2016/6/2.
 * 检查系统root权限
 */
public class ShellUtils {

    /**
     * 判断当前手机是否有ROOT权限
     *
     * @return
     */
    public static boolean isRoot() {
        boolean bool = false;
        try {
            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
                bool = false;
            } else {
                bool = true;
            }
        } catch (Exception e) {

        }
        return bool;
    }

}
