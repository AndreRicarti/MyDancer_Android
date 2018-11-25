package br.com.mydancer.mydancer.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String currentDateFormat() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);

        return strDate;
    }

}
