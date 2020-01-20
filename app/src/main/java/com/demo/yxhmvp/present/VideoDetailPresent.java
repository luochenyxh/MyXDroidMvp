package com.demo.yxhmvp.present;

import android.content.Context;

import com.demo.yxhmvp.base.BasePresent;
import com.demo.yxhmvp.http.RetrofitManager;
import com.demo.yxhmvp.http.URLConfig;
import com.demo.yxhmvp.model.BaseModel;
import com.demo.yxhmvp.model.VideoDetailBean;
import com.demo.yxhmvp.ui.activity.VideoDetailActivity;
import com.demo.yxhmvp.utils.ToastUtil;

import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class VideoDetailPresent extends BasePresent<VideoDetailActivity> {

    /**
     * 获取数据详情
     *
     * @param mediaID
     */
    public void getVideoDetail(String mediaID, Context context) {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL)
                .getVideoDetail(mediaID, Kits.Package.getIMEICode(context))
                .compose(XApi.<VideoDetailBean>getApiTransformer())
                .compose(XApi.<VideoDetailBean>getScheduler())
                .compose(getV().<VideoDetailBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<VideoDetailBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().setRetryView(error);
                    }

                    @Override
                    protected void onSuccess(VideoDetailBean videoDetailBean) {
                        if (videoDetailBean.getCode() == 200) {
                            getV().hideLoading();
                            VideoDetailBean.RetBean ret = videoDetailBean.getRet();
                            getV().setPlayData(ret);
                        } else {
                            ToastUtil.showToast(videoDetailBean.getMsg());
                        }
                    }
                });
    }


    public void getVideoAuth(String dataId, Context context) {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).addVideoList(dataId, Kits.Package.getIMEICode(context))
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(BaseModel historyListBean) {

                    }
                });
    }


    /**
     * 获取评论数据
     */
    public void getCommentData() {


    }


}
