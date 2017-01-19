package com.blankj.utilcode.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashSet;
import java.util.Set;

/**
 * 共享方式数据存储类
 *
 * @author snubee
 */
public class SharedPreferenceUtils {

    public final static String DEFAULT_NAME = "DEFAULT_NAME";


    private static SharedPreferenceUtils mSharedPreference;

    public static SharedPreferenceUtils getInstance() {
        if (mSharedPreference == null) {
            synchronized (SharedPreferenceUtils.class) {
                if (mSharedPreference == null) {
                    mSharedPreference = new SharedPreferenceUtils();
                }
            }
        }
        return mSharedPreference;
    }

    /**
     * 获取数据
     *
     * @param c
     * @param prefsName xml名称
     * @param key
     * @return
     */
    public String getString(Context c, String prefsName, String key, String defValue) {
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public String getString(Context c, String key, String defValue) {
        return getString(c, DEFAULT_NAME, key, defValue);
    }

    public boolean getBoolean(Context c, String prefsName, String key, boolean defValue) {
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public boolean getBoolean(Context c, String key, boolean defValue) {
        return getBoolean(c, DEFAULT_NAME, key, defValue);
    }

    public boolean getContains(Context c, String prefsName, String key) {
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    public int getInt(Context c, String prefsName, String key, int defValue) {
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public int getInt(Context c, String key, int defValue) {
        return getInt(c, DEFAULT_NAME, key, defValue);
    }

    public static int getInt(Context c, String prefsName, String key) {
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    public long getLong(Context c, String prefsName, String key, long defValue) {
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    public long getLong(Context c, String key, long defValue) {
        return getLong(c, DEFAULT_NAME, key, defValue);
    }

    public float getFloat(Context c, String prefsName, String key, float defValue) {
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    public float getFloat(Context c, String key, float defValue) {
        return getFloat(c, DEFAULT_NAME, key, defValue);
    }


    /**
     * 获取数据
     *
     * @param c
     * @param prefsName xml名称
     * @return
     */
    public String[] getString(Context c, String prefsName, String key[]) {
        String str[];
        if (key != null && key.length > 0) {
            int lenght = key.length;
            str = new String[lenght];
            SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
            for (int i = 0; i < lenght; i++) {
                str[i] = sp.getString(key[i], "");
            }
            return str;
        } else {
            return null;
        }
    }

    public String[] getStrings(Context c, String key[]) {
        return getString(c, DEFAULT_NAME, key);
    }


    /**
     * 保存数据
     *
     * @param c
     * @param prefsName xml名称
     * @param value     要保存的值
     */
    public void saveString(Context c, String prefsName, String key, String value) {
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void saveString(Context c, String key, String value) {
        saveString(c, DEFAULT_NAME, key, value);
    }

    public void saveBoolean(Context c, String key, String value) {
        saveString(c, DEFAULT_NAME, key, value);
    }

    public void saveBoolean(Context c, String prefsName, String key, boolean value) {
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void saveBoolean(Context c, String key, boolean value) {
        saveBoolean(c, DEFAULT_NAME, key, value);
    }

    public <E> void saveObject(Context c, String prefsName, String key, E e) {
        if (e == null) return;
        Gson gson = new Gson();
        final String value = gson.toJson(e);
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public <E> void saveObject(Context c, String key, E e) {
        saveObject(c, DEFAULT_NAME, key, e);
    }

    public <E> E getObject(Context c, String prefsName, String key, Class<E> obj) {
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);

        try {
            Gson gson = new Gson();
            final String value = sp.getString(key, "");
            if (!TextUtils.isEmpty(value)) {
                return gson.fromJson(value, obj);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public <E> E getObject(Context c, String key, Class<E> obj) {
        return getObject(c, DEFAULT_NAME, key, obj);
    }

    /**
     * 保存数据
     *
     * @param c
     * @param prefsName xml名称
     * @param value     要保存的值
     */
    public void saveInt(Context c, String prefsName, String key, int value) {
        SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void saveInt(Context c, String key, int value) {
        saveInt(c, DEFAULT_NAME, key, value);
    }

    /**
     * 保存数据
     *
     * @param c
     * @param prefsName
     * @param name
     * @param values
     */
    public void saveString(Context c, String prefsName, String name[], String values[]) {
        if (name != null && name.length > 0) {
            int length = name.length;
            SharedPreferences sp = c.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            for (int i = 0; i < length; i++) {
                if (name[i] != null && values[i] != null) {
                    editor.putString(name[i], values[i]);
                }
            }
            editor.commit();
        }
    }

    public void saveString(Context c, String name[], String values[]) {
        saveString(c, DEFAULT_NAME, name, values);
    }

    /**
     * 保存Set<String>
     *
     * @param context
     * @param values
     */
    public void saveStringSet(Context context, String key, Set<String> values) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key, values);
        editor.commit();

    }

    /**
     * @param context
     * @param key
     * @return
     */
    public Set<String> getStringSet(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_NAME, Context.MODE_PRIVATE);
        return sp.getStringSet(key, new HashSet<String>());
    }


}
