package com.lgh.quicklogin_master.utile;

/**
 * Created by guohu on 2016/10/25.
 */

public class PhoneValid {
    // 校验电话号码是否正确
    public static boolean valid(String phone) {
        if (phone == null || phone.equals("")) {
            return false;
        }
        if (!phone.matches("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$")) {
            return false;
        }
        return true;
    }
}
