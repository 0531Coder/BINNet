package com.sheyuan.baselibrary.picture.observable;


import com.sheyuan.baselibrary.picture.entity.LocalMedia;
import com.sheyuan.baselibrary.picture.entity.LocalMediaFolder;

import java.util.List;

public interface ObserverListener {
    void observerUpFoldersData(List<LocalMediaFolder> folders);

    void observerUpSelectsData(List<LocalMedia> selectMedias);
}
