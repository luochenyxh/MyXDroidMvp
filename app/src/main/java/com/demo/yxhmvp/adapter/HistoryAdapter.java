package com.demo.yxhmvp.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.yxhmvp.R;
import com.demo.yxhmvp.model.RealmHistoryBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/12 0012.
 */

public class HistoryAdapter extends BaseQuickAdapter<RealmHistoryBean, BaseViewHolder> {

    public HistoryAdapter(int layoutResId, @Nullable List<RealmHistoryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RealmHistoryBean item) {
        helper.setText(R.id.tv_history_text, item.getName());
    }
}
