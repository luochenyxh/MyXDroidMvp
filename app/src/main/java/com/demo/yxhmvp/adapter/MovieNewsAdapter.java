package com.demo.yxhmvp.adapter;

import android.content.Context;
import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.yxhmvp.R;
import com.demo.yxhmvp.model.HomeListBean;

import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

public class MovieNewsAdapter extends BaseQuickAdapter<HomeListBean.RetBean.ListBean.ChildListBean, BaseViewHolder> {

    private Context context;
    public MovieNewsAdapter(int layoutResId, @Nullable List<HomeListBean.RetBean.ListBean.ChildListBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeListBean.RetBean.ListBean.ChildListBean item) {
        ImageView cover = (ImageView) helper.getView(R.id.iv_home_movie_news_cover);
        ILFactory.getLoader().loadNet(cover, item.getPic(), null);
        helper.setText(R.id.tv_home_movie_news_title,item.getTitle());
        helper.setText(R.id.tv_home_movie_news_decr,item.getDescription());
        helper.setText(R.id.tv_home_movie_news_date,item.getLoadURL());
    }
}
