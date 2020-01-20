package com.demo.yxhmvp.present;

import android.content.Context;

import com.demo.yxhmvp.base.BasePresent;
import com.demo.yxhmvp.http.RetrofitManager;
import com.demo.yxhmvp.http.URLConfig;
import com.demo.yxhmvp.model.BaseModel;
import com.demo.yxhmvp.model.HistoryListBean;
import com.demo.yxhmvp.ui.activity.VideoHistoryActivity;

import java.util.List;

import cn.droidlover.xdroidmvp.kit.Kits;
import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class VideoHistoryPresent extends BasePresent<VideoHistoryActivity> {
    public void getHistoryData(final int pageNum, Context context) {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).getHistoryList(Kits.Package.getIMEICode(context), pageNum)
                .compose(XApi.<HistoryListBean>getApiTransformer())
                .compose(XApi.<HistoryListBean>getScheduler())
                .compose(getV().<HistoryListBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<HistoryListBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().setRetryView(error);
                    }

                    @Override
                    protected void onSuccess(HistoryListBean historyListBean) {
                        if (historyListBean.getCode() == 200) {
                            getV().hideLoading();
                            HistoryListBean.RetBean ret = historyListBean.getRet();
                            List<HistoryListBean.RetBean.ListBean> list = ret.getList();
                            int pnum = ret.getPnum();
                            if (pnum != pageNum) {
                                getV().setHistoryData(list, false);
                            } else {
                                getV().setHistoryData(list, true);
                            }
                        }
                    }
                });
    }


    public void deleteHistory(Context context) {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).deleteHistory(Kits.Package.getIMEICode(context))
                .compose(XApi.<BaseModel>getApiTransformer())
                .compose(XApi.<BaseModel>getScheduler())
                .compose(getV().<BaseModel>bindToLifecycle())
                .subscribe(new ApiSubscriber<BaseModel>() {
                    @Override
                    protected void onFail(NetError error) {

                    }

                    @Override
                    protected void onSuccess(BaseModel historyListBean) {
                        if (historyListBean.getCode() == 200) {
                            getV().deleteToast(historyListBean.getMsg(), true);
                        } else {
                            getV().deleteToast(historyListBean.getMsg(), false);
                        }
                    }
                });
    }

}
