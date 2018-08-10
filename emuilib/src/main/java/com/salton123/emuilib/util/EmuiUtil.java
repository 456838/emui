package com.salton123.emuilib.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;


import com.salton123.util.DateUtils;
import com.salton123.util.MLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * User: newSalton@outlook.com
 * Date: 2018/2/7 10:59
 * ModifyTime: 10:59
 * Description:
 */
public class EmuiUtil {

    private static final int TAKE_PHOTO_REQUEST_CODE = 100;
    private static final String TAG = "EmuiUtil";


    public static boolean saveBitmapToSdCard(Bitmap mybitmap, String filePath) {
        MLog.info(TAG, "[saveBitmapToSdCard],filePath:" + filePath);
        boolean result = false;
        if (mybitmap == null) {
            return false;
        }
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.mkdirs();
            }
            try {
                FileOutputStream fileOutputStream = null;
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    fileOutputStream = new FileOutputStream(file);
                    mybitmap.compress(Bitmap.CompressFormat.WEBP, 50, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    result = true;
                } else {
                    MLog.error(TAG, "[saveBitmapToSdCard] can not find sdcard!");
                }
            } catch (FileNotFoundException e) {
                MLog.error(TAG, e);
            } catch (IOException e) {
                MLog.error(TAG, e);
            }
        }
        return result;
    }

    public static void saveBitmap(Context context, Uri uri) {
        saveBitmapToSdCard(getBitMap(context, uri), Environment.getExternalStorageDirectory() + File.separator + "hello.jpg");
    }

    // public static Bitmap getBitMap(Context context, Uri uri) {
    //     MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
    //     mediaMetadataRetriever.setDataSource(context, uri);
    //     Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime(0);
    //     // saveBitmapToSdCard(bitmap, defaultPath() + "/" + uri.getLastPathSegment() + ".jpg");
    //     return bitmap;
    // }

    public static Bitmap getBitMap(Context context, Uri uri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(context, uri);
        Bitmap bitmap = mediaMetadataRetriever.getFrameAtTime(0);
        // saveBitmapToSdCard(bitmap,defaultPath() + "/"+ uri.getPath()+".jpg");
        return bitmap;
    }


    public static String defaultPath() {
        return Environment.getExternalStorageDirectory() + File.separator + "ec" + File.separator + "res";
    }

    public static void asycGetBitmap(final Context context, List<String> paths) {
        Observable.fromIterable(paths).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String url) throws Exception {
                        MLog.info(TAG, "url:" + Uri.parse(UriProvider.defaultPath() + File.separator + url).toString());
                        Uri uri = Uri.parse(UriProvider.defaultPath() + File.separator + url);
                        Bitmap bitmap = getBitMap(context, uri);
                        saveBitmapToSdCard(bitmap, defaultPath() + "/" + uri.getLastPathSegment() + ".jpg");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MLog.error("aa", throwable);
                    }
                });
    }
    
}
