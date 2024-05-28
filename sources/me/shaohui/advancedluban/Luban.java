package me.shaohui.advancedluban;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Luban {
    public static final int CUSTOM_GEAR = 4;
    private static String DEFAULT_DISK_CACHE_DIR = "luban_disk_cache";
    public static final int FIRST_GEAR = 1;
    private static final String TAG = "Luban";
    public static final int THIRD_GEAR = 3;
    private LubanBuilder mBuilder;
    private File mFile;
    private List<File> mFileList;

    private Luban(File file) {
        this.mBuilder = new LubanBuilder(file);
    }

    public static Luban compress(Context context, File file) {
        Luban luban = new Luban(getPhotoCacheDir(context));
        luban.mFile = file;
        luban.mFileList = Collections.singletonList(file);
        return luban;
    }

    public static Luban compress(Context context, List<File> list) {
        Luban luban = new Luban(getPhotoCacheDir(context));
        luban.mFileList = new ArrayList(list);
        luban.mFile = list.get(0);
        return luban;
    }

    public static Luban compress(File file, File file2) {
        if (!isCacheDirValid(file2)) {
            throw new IllegalArgumentException("The cacheDir must be Directory");
        }
        Luban luban = new Luban(file2);
        luban.mFile = file;
        luban.mFileList = Collections.singletonList(file);
        return luban;
    }

    public static Luban compress(List<File> list, File file) {
        if (!isCacheDirValid(file)) {
            throw new IllegalArgumentException("The cacheDir must be Directory");
        }
        Luban luban = new Luban(file);
        luban.mFile = list.get(0);
        luban.mFileList = new ArrayList(list);
        return luban;
    }

    private static boolean isCacheDirValid(File file) {
        return file.isDirectory() && (file.exists() || file.mkdirs());
    }

    public Luban putGear(int i) {
        this.mBuilder.gear = i;
        return this;
    }

    public Luban setCompressFormat(Bitmap.CompressFormat compressFormat) {
        this.mBuilder.compressFormat = compressFormat;
        return this;
    }

    public Luban setMaxSize(int i) {
        this.mBuilder.maxSize = i;
        return this;
    }

    public Luban setMaxWidth(int i) {
        this.mBuilder.maxWidth = i;
        return this;
    }

    public Luban setMaxHeight(int i) {
        this.mBuilder.maxHeight = i;
        return this;
    }

    public void launch(final OnCompressListener onCompressListener) {
        asObservable().observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() { // from class: me.shaohui.advancedluban.Luban.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Disposable disposable) throws Exception {
                onCompressListener.onStart();
            }
        }).subscribe(new Consumer<File>() { // from class: me.shaohui.advancedluban.Luban.1
            @Override // io.reactivex.functions.Consumer
            public void accept(File file) throws Exception {
                onCompressListener.onSuccess(file);
            }
        }, new Consumer<Throwable>() { // from class: me.shaohui.advancedluban.Luban.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                onCompressListener.onError(th);
            }
        });
    }

    public void launch(final OnMultiCompressListener onMultiCompressListener) {
        asListObservable().observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() { // from class: me.shaohui.advancedluban.Luban.6
            @Override // io.reactivex.functions.Consumer
            public void accept(Disposable disposable) throws Exception {
                onMultiCompressListener.onStart();
            }
        }).subscribe(new Consumer<List<File>>() { // from class: me.shaohui.advancedluban.Luban.4
            @Override // io.reactivex.functions.Consumer
            public void accept(List<File> list) throws Exception {
                onMultiCompressListener.onSuccess(list);
            }
        }, new Consumer<Throwable>() { // from class: me.shaohui.advancedluban.Luban.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                onMultiCompressListener.onError(th);
            }
        });
    }

    public Observable<File> asObservable() {
        return new LubanCompresser(this.mBuilder).singleAction(this.mFile);
    }

    public Observable<List<File>> asListObservable() {
        return new LubanCompresser(this.mBuilder).multiAction(this.mFileList);
    }

    private static File getPhotoCacheDir(Context context) {
        return getPhotoCacheDir(context, DEFAULT_DISK_CACHE_DIR);
    }

    private static File getPhotoCacheDir(Context context, String str) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File file = new File(cacheDir, str);
            if (file.mkdirs() || (file.exists() && file.isDirectory())) {
                return file;
            }
            return null;
        }
        if (Log.isLoggable(TAG, 6)) {
            Log.e(TAG, "default disk cache dir is null");
        }
        return null;
    }

    public Luban clearCache() {
        if (this.mBuilder.cacheDir.exists()) {
            deleteFile(this.mBuilder.cacheDir);
        }
        return this;
    }

    private void deleteFile(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                deleteFile(file2);
            }
        }
        file.delete();
    }
}
