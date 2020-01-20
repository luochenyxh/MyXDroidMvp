package com.demo.yxhmvp.present;

import com.demo.yxhmvp.base.BasePresent;
import com.demo.yxhmvp.http.RetrofitManager;
import com.demo.yxhmvp.http.URLConfig;
import com.demo.yxhmvp.model.StoryBookList;
import com.demo.yxhmvp.ui.activity.StoryListActivity;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class StoryListPresent extends BasePresent<StoryListActivity> {
    public void getBookList(String bookID) {
        RetrofitManager.getInstance()
                .getApiService(URLConfig.BASE_STORY_URL)
                .getBookList(bookID)
                .compose(XApi.<StoryBookList>getScheduler())
                .compose(XApi.<StoryBookList>getApiTransformer())
                .compose(getV().<StoryBookList>bindToLifecycle())
                .subscribe(new ApiSubscriber<StoryBookList>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().setRetryView(error);
                    }

                    @Override
                    protected void onSuccess(StoryBookList storyListBean) {
                        getV().hideLoading();
                        List<StoryBookList.PartListBean> partList = storyListBean.getPartList();
                        getV().setBookList(partList);
                    }
                });

    }
}
