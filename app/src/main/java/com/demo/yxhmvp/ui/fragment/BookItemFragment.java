package com.demo.yxhmvp.ui.fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.yxhmvp.R;
import com.demo.yxhmvp.adapter.BookItemAdapter;
import com.demo.yxhmvp.base.BaseLazyFragment;
import com.demo.yxhmvp.model.BookListBean;

import java.util.ArrayList;

import butterknife.BindView;

public class BookItemFragment extends BaseLazyFragment {

    @BindView(R.id.rv_book_item)
    RecyclerView rvBookItem;

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        ArrayList<BookListBean.DataBean.ListBean> bookList = arguments.getParcelableArrayList("bookList");
        rvBookItem.setLayoutManager(new GridLayoutManager(context, 3));
        BookItemAdapter bookItemAdapter = new BookItemAdapter(R.layout.item_layout_intrest_layout, bookList);
        rvBookItem.setAdapter(bookItemAdapter);
        emptyView.hide();
    }

    @Override
    public void getNetData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_frag_book_layout;
    }

    @Override
    public Object newP() {
        return null;
    }
}
