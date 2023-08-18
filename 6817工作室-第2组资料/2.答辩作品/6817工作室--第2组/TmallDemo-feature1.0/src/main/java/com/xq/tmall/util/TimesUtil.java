package com.xq.tmall.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimesUtil {
    public static String getTimes(){
        SimpleDateFormat times = new SimpleDateFormat("yyyy-MM-dd");
        return times.format(new Date());
    }
}
