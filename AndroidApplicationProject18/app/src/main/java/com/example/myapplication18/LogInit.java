package com.example.myapplication18;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import me.pqpo.librarylog4a.Level;
import me.pqpo.librarylog4a.Log4a;
import me.pqpo.librarylog4a.LogData;
import me.pqpo.librarylog4a.appender.AndroidAppender;
import me.pqpo.librarylog4a.appender.FileAppender;
import me.pqpo.librarylog4a.formatter.DateFileFormatter;
import me.pqpo.librarylog4a.interceptor.Interceptor;
import me.pqpo.librarylog4a.logger.AppenderLogger;
import me.pqpo.librarylog4a.logger.Logger;

public class LogInit {

    public static final int BUFFER_SIZE = 1024 * 400; //400k
    private static final String TAG = "LogInit";

    public static void init(Context context) {
        int level = Level.DEBUG;
        Interceptor wrapInterceptor = new Interceptor() {
            @Override
            public boolean intercept(LogData logData) {
                logData.tag = "Log4a-" + logData.tag;
                return true;
            }
        };
        AndroidAppender androidAppender = new AndroidAppender.Builder()
                .setLevel(level)
                .addInterceptor(wrapInterceptor)
                .create();

        File log = getLogDir(context);
        String buffer_path = log.getAbsolutePath() + File.separator + ".logCache";
        String time = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(new Date());
        String log_path = log.getAbsolutePath() + File.separator + time + ".txt";
        FileAppender fileAppender = new FileAppender.Builder(context)
                .setLogFilePath(log_path)
                .setLevel(level)
                .addInterceptor(wrapInterceptor)
                .setBufferFilePath(buffer_path)
                .setFormatter(new DateFileFormatter())
                .setCompress(false)
                .setBufferSize(BUFFER_SIZE)
                .create();

        AppenderLogger logger = new AppenderLogger.Builder()
                .addAppender(androidAppender)
                .addAppender(fileAppender)
                .create();
        Log4a.setLogger(logger);
    }

    protected static String createOutPublicDir() {
        String fileOutName = Environment.getExternalStorageDirectory() + File.separator+"早起的虫儿"+File.separator+"有鸟吃";
        File filePublic = new File(fileOutName);   //   /storage/emulated/0/AutPublic/outDir
        if (!filePublic.exists()) {
            filePublic.mkdirs();
        }
        else
        {
            Log.i(TAG, "createOutPublicDir: "+filePublic.getAbsolutePath());
        }
        return fileOutName;
    }
    
    public static File getLogDir(Context context) {
        String fileOutName = createOutPublicDir();
        File log = new File(fileOutName, "log");
        if (log == null) {
            log = new File(fileOutName, "log");
        }
        if (!log.exists()) {
            log.mkdir();
        }
        return log;
    }
}
