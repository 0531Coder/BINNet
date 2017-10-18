package com.sheyuan.baselibrary.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by moutain on 17-9-12 13:18.
 */

public interface BaseImpl {
    boolean addRxStop(Disposable disposable);

    boolean addRxDestroy(Disposable disposable);

    void remove(Disposable disposable);

    /**
     * 显示ProgressDialog
     */
    void showProgress(String msg);

    /**
     * 取消ProgressDialog
     */
    void dismissProgress();
}
