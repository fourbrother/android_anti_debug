package cn.wjdiankong.encryptdemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by jiangwei1-g on 2016/5/27.
 */
public final class Utils {

    private static final String APP_SIGN = "aaa";

    public static String getSignature() {
        Context ctx = MyApplication.getMyContext();
        try {
            /** 通过包管理器获得指定包名包含签名的包信息 **/
            PackageInfo packageInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            /******* 通过返回的包信息获得签名数组 *******/
            Signature[] signatures = packageInfo.signatures;
            /******* 循环遍历签名数组拼接应用签名 *******/
            StringBuilder builder = new StringBuilder();
            for (Signature signature : signatures) {
                builder.append(signature.toCharsString());
            }
            /************** 得到应用签名 **************/
            return builder.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isOwnApp(){
        String signStr = getSignature();
        return APP_SIGN.equals(signStr);
    }

    /***
     *  true:already in using  false:not using
     * @param port
     */
    public static boolean isLoclePortUsing(int port){
        boolean flag = true;
        try {
            flag = isPortUsing("127.0.0.1", port);
        } catch (Exception e) {
        }
        return flag;
    }
    /***
     *  true:already in using  false:not using
     * @param host
     * @param port
     * @throws UnknownHostException
     */
    public static boolean isPortUsing(String host,int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        try {
            Socket socket = new Socket(theAddress,port);
            flag = true;
        } catch (IOException e) {
        }
        return flag;
    }

}
