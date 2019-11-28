package com.leave;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class IdUtil {

    private static String LOCAL_IP_23 = null;
    private static int[] SEQUENCE_NUMBER = new int[1];
    private static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyMMddHHmmssSSS");
    
    public static String fetchLocalIP23() {
        if (LOCAL_IP_23 == null) {
            try {
                byte[] ips = InetAddress.getLocalHost().getAddress();
                int ip2 = (new Byte(ips[2])).intValue();
                ip2 = ip2 < 0 ? 0x100 + ip2 : ip2;
                String cip2 = Integer.toHexString(ip2 & 0x0F);
                int ip3 = (new Byte(ips[3])).intValue();
                ip3 = ip3 < 0 ? 0x100 + ip3 : ip3;
                String cip3 = ip3 > 0xf ? Integer.toHexString(ip3) : '0' + Integer.toHexString(ip3);
                LOCAL_IP_23 = cip2 + cip3;
            } catch (Exception e) {
                LOCAL_IP_23 = "000";
            }
        }
        return LOCAL_IP_23;
    }
    
    public static String fetchGMTTimestamp() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return IdUtil.FORMATTER.format(ts);
    }

    private static String fetchSequence() {
        int seqNo = 0;
        synchronized (SEQUENCE_NUMBER) {
            seqNo = (SEQUENCE_NUMBER[0] + 1);
            SEQUENCE_NUMBER[0] = seqNo;
        }
       return  String.format("%1$03d", seqNo);
    }

    public static String generateId() {
        return ("YP" + fetchGMTTimestamp() + fetchSequence());
    }
}