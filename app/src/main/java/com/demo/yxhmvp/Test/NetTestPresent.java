package com.demo.yxhmvp.Test;

import com.demo.yxhmvp.base.BasePresent;
import com.demo.yxhmvp.http.RetrofitManager;
import com.demo.yxhmvp.http.URLConfig;

import cn.droidlover.xdroidmvp.log.XLog;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class NetTestPresent extends BasePresent<NetTestActivity> {


    public void getNetDate(){
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_STORY_URL).getNetTestList(2, 1)
                .compose(XApi.<NetTestBean>getApiTransformer())
                .compose(XApi.<NetTestBean>getScheduler())
                .compose(getV().<NetTestBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<NetTestBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        XLog.d("网络异常：",error.getMessage());
                        getV().setRetryView(error);
                    }

                    @Override
                    protected void onSuccess(NetTestBean netTestBean) {
                        XLog.d("网络成功：",netTestBean.getMessage());
                        if (netTestBean.getErrcode() == 0) {
                                getV().setStr(netTestBean);
                        }
                    }
                });
    }


}
