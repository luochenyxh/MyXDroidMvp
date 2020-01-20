package com.demo.yxhmvp.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.yxhmvp.R;
import com.demo.yxhmvp.model.FindHeaderBean;

import java.util.List;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;


public class FindHeaderGalleryAdapter extends BaseQuickAdapter<FindHeaderBean.RetBean.BannerListBean, BaseViewHolder> {


    public FindHeaderGalleryAdapter(int layoutResId, @Nullable List<FindHeaderBean.RetBean.BannerListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindHeaderBean.RetBean.BannerListBean item) {
        ImageView cover = (ImageView) helper.getView(R.id.iv_find_head_cover);
        ILFactory.getLoader().loadNet(cover, item.getPic(), null);
    }
}
