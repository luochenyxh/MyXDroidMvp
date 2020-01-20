package com.demo.yxhmvp.ui.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.demo.yxhmvp.R;
import com.demo.yxhmvp.base.BaseLazyFragment;
import com.demo.yxhmvp.model.BookListBean;
import com.demo.yxhmvp.present.BookPresent;
import com.demo.yxhmvp.widget.autolayout.AutoTabLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;

public class BookFragment extends BaseLazyFragment<BookPresent> {

    @BindView(R.id.tab_book)
    AutoTabLayout tabBook;
    @BindView(R.id.vp_book)
    ViewPager vpBook;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        getNetData();
    }

    private void initView() {
        tabBook.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpBook.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vpBook.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabBook));
    }

    @Override
    public void getNetData() {
        getP().getBookData();
    }

    public void setTabData(ArrayList<String> set) {
        for (String s : set) {
            tabBook.addTab(tabBook.newTab().setText(s));
        }
    }

    public void setVPAdapter(LinkedHashMap<String, ArrayList<BookListBean.DataBean.ListBean>> hashMap) {
        List<Fragment> bookItemFragments = new ArrayList<>();
        for (String s : hashMap.keySet()) {
            ArrayList<BookListBean.DataBean.ListBean> listBeans = hashMap.get(s);
            BookItemFragment bookItemFragment = new BookItemFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("bookList", listBeans);
            bookItemFragment.setArguments(bundle);
            bookItemFragments.add(bookItemFragment);
        }
        XFragmentAdapter xFragmentAdapter = new XFragmentAdapter(getChildFragmentManager(), bookItemFragments, null);
        vpBook.setAdapter(xFragmentAdapter);
    }


    @Override
    public int getLayoutId() {
        return R.layout.frag_book_layout;
    }

    @Override
    public BookPresent newP() {
        return new BookPresent();
    }
}
