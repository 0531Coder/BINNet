package com.sheyuan.baselibrary.picture.observable;


public interface SubjectListener {
    void add(ObserverListener observerListener);

    void remove(ObserverListener observerListener);
}
