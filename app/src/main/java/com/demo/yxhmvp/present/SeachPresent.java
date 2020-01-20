package com.demo.yxhmvp.present;

import com.demo.yxhmvp.base.BasePresent;
import com.demo.yxhmvp.http.RetrofitManager;
import com.demo.yxhmvp.http.URLConfig;
import com.demo.yxhmvp.model.SeachListBean;
import com.demo.yxhmvp.ui.activity.SeachActivity;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by admin on 2018/6/13.
 */

public class SeachPresent extends BasePresent<SeachActivity> {

    public void getSeachData(String textData) {
        RetrofitManager.getInstance()
                .getApiService(URLConfig.BASE_MOVIE_URL)
                .querySeach(textData)
                .compose(XApi.<SeachListBean>getApiTransformer())
                .compose(XApi.<SeachListBean>getScheduler())
                .compose(getV().<SeachListBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<SeachListBean>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(SeachListBean seachListBean) {
                        List<SeachListBean.RetBean.ListBean> list = seachListBean.getRet().getList();
                        getV().setData(list);
                    }
                });
    }

}
