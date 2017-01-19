package com.snubee.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;


import com.blankj.utilcode.utils.AppUtils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Cyning
 * @since 2015.09.17
 * Time   下午9:07
 * Desc   <p>多渠道打包，获得包的渠道，为了能够能提高效率，在使用过程中，
 * 只有新版本才进行从从Zip文件中获得安装包的渠道名称，否则都是读取本地缓存的渠道名称</p>
 * <p>
 * 已经总结为博客，博客链接：http://ownwell.github.io/2015/09/28/mutichannel4Android/
 * <p>
 * </p>
 */

public class MutiChannelConfig {

    public static final String Version = "version";

    public static final String Channel = "channel";

    public static final String DEFAULT_CHANNEL = "360";//360默认渠道自己的官方渠道

    public static final String Channel_File = "channel";

    /**
     * 从Zip文件中获得安装包的渠道名称
     */
    public static String getChannelFromMeta(Context context) {
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        String ret = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.startsWith("META-INF") && entryName.contains("channel_")) {
                    ret = entryName;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String[] split = ret.split("_");
        if (split != null && split.length >= 2) {
            return ret.substring(split[0].length() + 1);
        } else {
            return DEFAULT_CHANNEL;
        }
    }

    /**
     * 得到渠道名
     * <p>当时新版本时，就从zip文件中获取，同时保存到本地</p>
     * <p>当不是新版本时，就直接读取本地缓存的文件</p>
     */
    public static String getChannel(Context mContext) {
        String channel = DEFAULT_CHANNEL;
        if (isNewVersion(mContext)) {//是新版本
            channel = getChannelFromMeta(mContext);
            saveChannel(mContext, channel);//保存当前版本
        } else {
            channel = getCachChannel(mContext);
        }
        return channel;
    }

    /**
     * 保存当前的版本号和渠道名
     */
    public static void saveChannel(Context mContext, String channel) {
        SharedPreferences mSettinsSP =
                mContext.getSharedPreferences(Channel_File, Activity.MODE_PRIVATE);
        SharedPreferences.Editor mSettinsEd = mSettinsSP.edit();
        mSettinsEd.putString(Version, AppUtils.getAppVersionName(mContext));
        mSettinsEd.putString(Channel, channel);
        //提交保存
        mSettinsEd.commit();
    }

    /**
     * 判断一下本地缓存的版本号和现在运行的版本号是否一致
     */
    private static boolean isNewVersion(Context mContext) {
        SharedPreferences mSettinsSP =
                mContext.getSharedPreferences(Channel_File, Activity.MODE_PRIVATE);
        String version = AppUtils.getAppVersionName(mContext);// 得到apk的版本信息
        return !mSettinsSP.getString(Version, "").equals(version);
    }

    /**
     * 读取本地缓存的版本号
     */
    private static String getCachChannel(Context mContext) {
        SharedPreferences mSettinsSP =
                mContext.getSharedPreferences(Channel_File, Activity.MODE_PRIVATE);
        return mSettinsSP.getString(Channel, DEFAULT_CHANNEL);
    }
}