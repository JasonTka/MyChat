package com.jason.framework.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import com.jason.framework.BuildConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {

    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    public static void i(String text) {
        if (BuildConfig.LOG_DEBUG) {
            if (!TextUtils.isEmpty(text)) {
                Log.i(BuildConfig.LOG_TAG, text);
                writeToFile(text);
            }
        }
    }

    public static void e(String text) {
        if (BuildConfig.LOG_DEBUG) {
            if (!TextUtils.isEmpty(text)) {
                Log.e(BuildConfig.LOG_TAG, text);
                writeToFile(text);
            }
        }
    }

    public static void w(String text) {
        if (BuildConfig.LOG_DEBUG) {
            if (!TextUtils.isEmpty(text)) {
                Log.w(BuildConfig.LOG_TAG, text);
                writeToFile(text);
            }
        }
    }

    public static void d(String text) {
        if (BuildConfig.LOG_DEBUG) {
            if (!TextUtils.isEmpty(text)) {
                Log.d(BuildConfig.LOG_TAG, text);
                writeToFile(text);
            }
        }
    }

    private static void writeToFile(String text) {
        @SuppressLint("SdCardPath") String fileName = "/sdcard/MyChat/MyChat.log";
        String log = mSimpleDateFormat.format(new Date()) + " " + text + "\n";
        @SuppressLint("SdCardPath") File fileGroup = new File("/sdcard/MyChat/");
        if (!fileGroup.exists()) {
            fileGroup.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = new FileOutputStream(fileName, true);
            bufferedWriter = new BufferedWriter(
                    new OutputStreamWriter(fileOutputStream, Charset.forName("gbk"))
            );
            bufferedWriter.write(log);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
