package com.demo.yxhmvp.present;

import android.text.TextUtils;

import com.demo.yxhmvp.base.BasePresent;
import com.demo.yxhmvp.http.RetrofitManager;
import com.demo.yxhmvp.http.URLConfig;
import com.demo.yxhmvp.model.BookListBean;
import com.demo.yxhmvp.ui.fragment.BookFragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cn.droidlover.xdroidmvp.net.ApiSubscriber;
import cn.droidlover.xdroidmvp.net.NetError;
import cn.droidlover.xdroidmvp.net.XApi;

public class BookPresent extends BasePresent<BookFragment> {

    public void getBookData() {
        RetrofitManager.getInstance()
                .getApiService(URLConfig.BASE_BOOK_URL)
                .getYiLinBookList()
                .compose(XApi.<BookListBean>getApiTransformer())
                .compose(XApi.<BookListBean>getScheduler())
                .compose(getV().<BookListBean>bindToLifecycle())
                .subscribe(new ApiSubscriber<BookListBean>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().setRetryView(error);
                    }

                    @Override
                    protected void onSuccess(BookListBean bookListBean) {
                        ArrayList<String> yearList = new ArrayList<>();
                        LinkedHashMap<String, ArrayList<BookListBean.DataBean.ListBean>> hashMap = new LinkedHashMap<>();
                        List<BookListBean.DataBean.ListBean> list = bookListBean.getData().getList();
                        getV().hideLoading();
                        //数据分类
                        if (list != null && list.size() > 0) {
                            for (BookListBean.DataBean.ListBean listBean : list) {
                                String year = listBean.getYear();
                                if (!yearList.contains(year)) {
                                    yearList.add(year);
                                } else {
                                    continue;
                                }
                            }
                            getV().setTabData(yearList);

                            for (String s : yearList) {
                                ArrayList<BookListBean.DataBean.ListBean> listBeansItem = new ArrayList<>();
                                for (BookListBean.DataBean.ListBean listBean : list) {
                                    if (TextUtils.equals(listBean.getYear(), s)) {
                                        listBeansItem.add(listBean);
                                    } else {
                                        continue;
                                    }
                                }
                                hashMap.put(s, listBeansItem);
                            }
                            getV().setVPAdapter(hashMap);
                        }
                    }
                });
    }

}
