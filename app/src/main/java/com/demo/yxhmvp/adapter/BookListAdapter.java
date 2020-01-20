package com.demo.yxhmvp.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.yxhmvp.R;
import com.demo.yxhmvp.model.StoryBookList;

import java.util.List;

public class BookListAdapter extends BaseQuickAdapter<StoryBookList.PartListBean, BaseViewHolder> {

    public BookListAdapter(int layoutResId, @Nullable List<StoryBookList.PartListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoryBookList.PartListBean item) {
        helper.setText(R.id.tv_story_part_list, item.getPartTitle());
    }
}
