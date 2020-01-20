package com.demo.yxhmvp.present;

import com.demo.yxhmvp.base.BasePresent;
import com.demo.yxhmvp.http.RetrofitManager;
import com.demo.yxhmvp.http.URLConfig;
import com.demo.yxhmvp.model.MovieListBean;
import com.demo.yxhmvp.ui.activity.MovieTypeActivity;

import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class MovieTypePresent extends BasePresent<MovieTypeActivity> {

    public void getMovieList(String dataID, int pageNum) {
        RetrofitManager.getInstance().getApiService(URLConfig.BASE_MOVIE_URL).getMovieList(dataID, "infomation", pageNum)
                .compose(XApi.<MovieListBean>getApiTransformer())
                .compose(XApi.<MovieListBean>getScheduler())
                .compose(getV().<MovieListBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<MovieListBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().setRetryView(error);
                    }

                    @Override
                    protected void onSuccess(MovieListBean movieListBean) {
                        if (movieListBean.getCode() == 200) {
                            getV().hideLoading();
                            MovieListBean.RetBean ret = movieListBean.getRet();
                            List<MovieListBean.RetBean.ListBean> list = ret.getList();
                            getV().setMovieData(list);
                        }
                    }
                });
    }
}
