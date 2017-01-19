
package com.snubee.baselibrary.config;

import android.content.Context;
import android.os.Environment;


import com.snubee.baselibrary.utils.ALLog;

import java.io.File;
import java.io.IOException;

/**
 * 应用程序配置文件
 *
 * @author snubee
 * @company 深圳利民网
 * @time 2016/12/6 17:32
 **/
public class ApplicationConfig {

    /**
     * 日志打印开关 false 不打印 true 打印
     */
    public static final boolean DEBUG_FLAG = false;

    /**
     * 是否是运营环境 fasle測試環境 true正式環境
     */
    public static final boolean IS_OFFICIAL = false;

    // 图片保存路径
    public static String IMAGE_PATH = "images";
    // 语音保存路径
    public static String AUDIO_PATH = "audio";
    // 视频保存路径
    public static String VIDIO_PATH = "video";

    // 更新安装包
    public static String UPDATE_APK_PATH = "apks";
    // 启动界面
    public static String SPLASH_PATH = "splash";
    //根目录
    public static final String APP_DIR = "2kmall";

    private ApplicationConfig() {

    }

    public static ApplicationConfig getInstance() {
        return VersionConfigHolder.holder;
    }

    private static class VersionConfigHolder {
        private static ApplicationConfig holder = new ApplicationConfig();
    }

    /**
     * @param context
     */
    public void init(Context context) {
        checkDir(context);
        // 设置日志输出功能
        ALLog.setDebug(DEBUG_FLAG);
    }

    /**
     * 生成app文相关的件夹
     * @param context
     */
    private void checkDir(Context context) {
        IMAGE_PATH = getExternalCacheDir(context, APP_DIR, IMAGE_PATH);
        AUDIO_PATH = getExternalCacheDir(context, APP_DIR, AUDIO_PATH);
        UPDATE_APK_PATH = getExternalCacheDir(context, APP_DIR,
                UPDATE_APK_PATH);
        SPLASH_PATH = getExternalCacheDir(context, APP_DIR, SPLASH_PATH);
        VIDIO_PATH = getExternalCacheDir(context, APP_DIR, VIDIO_PATH);
    }

    private String getExternalCacheDir(Context context, String path, String dir) {
        File dataDir = new File(Environment.getExternalStorageDirectory(), path);
        File appCacheDir = new File(dataDir, dir);
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                ALLog.w("Unable to create external cache directory");
                return null;
            }
            try {
                new File(appCacheDir, ".nomedia").createNewFile();
            } catch (IOException e) {
                ALLog.i("Can't create \".nomedia\" file in application external cache directory");
            }
        }
        return appCacheDir.getAbsolutePath();
    }


}
