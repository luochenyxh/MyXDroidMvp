package com.demo.yxhmvp.ui.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.demo.yxhmvp.R;
import com.demo.yxhmvp.adapter.BookListAdapter;
import com.demo.yxhmvp.base.BaseActivity;
import com.demo.yxhmvp.model.StoryBookList;
import com.demo.yxhmvp.present.StoryListPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;

public class StoryListActivity extends BaseActivity<StoryListPresent> {

    @BindView(R.id.rv_story_book_list)
    RecyclerView rvStoryBookList;
    private int bookID;
    ArrayList<StoryBookList.PartListBean> booklist = new ArrayList<>();
    private BookListAdapter bookListAdapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        bookID = getIntent().getIntExtra("bookID", 0);
        String title = getIntent().getStringExtra("title");
        topBar.setTitle(title);
        initView();
        getP().getBookList(bookID + "");
    }

    private void initView() {
        rvStoryBookList.setLayoutManager(new LinearLayoutManager(context));
        bookListAdapter = new BookListAdapter(R.layout.item_book_list_layout, booklist);
        rvStoryBookList.setAdapter(bookListAdapter);
        bookListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Router.newIntent(context)
                        .putString("title", booklist.get(position).getPartTitle())
                        .putString("downURL", booklist.get(position).getDownUrl())
                        .to(NewsActivity.class)
                        .launch();
            }
        });


    }


    public void setBookList(List<StoryBookList.PartListBean> list) {
        booklist.clear();
        booklist.addAll(list);
        bookListAdapter.notifyDataSetChanged();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_story_list;
    }

    @Override
    public StoryListPresent newP() {
        return new StoryListPresent();
    }

    @Override
    public void getNetData() {
        getP().getBookList(bookID + "");
    }
}
