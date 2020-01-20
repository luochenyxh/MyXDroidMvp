package com.demo.yxhmvp.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.demo.yxhmvp.R;
import com.demo.yxhmvp.adapter.FindHeaderGalleryAdapter;
import com.demo.yxhmvp.adapter.FindNewsAdapter;
import com.demo.yxhmvp.base.BaseLazyFragment;
import com.demo.yxhmvp.model.FindHeaderBean;
import com.demo.yxhmvp.model.FindNewsBean;
import com.demo.yxhmvp.present.FindPresent;
import com.demo.yxhmvp.ui.activity.NewsActivity;
import com.demo.yxhmvp.ui.activity.VideoDetailActivity;
import com.demo.yxhmvp.widget.GalleryRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.router.Router;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class FindFragment extends BaseLazyFragment<FindPresent> {
    @BindView(R.id.rv_find)
    RecyclerView rvFind;
    @BindView(R.id.refresh_find_layout)
    public SmartRefreshLayout refreshFindLayout;
    private ArrayList<FindHeaderBean.RetBean.BannerListBean> bannerList = new ArrayList<>();
    private ArrayList<FindNewsBean.RetBean.FindListBean> newsList = new ArrayList<>();
    private FindHeaderGalleryAdapter galleryAdapter;
    private int itemPosition = 0;
    private FindNewsAdapter newsAdapter;
    private int pageNum = 1;
    private ImageView headCover;
    private TextView headTitle;
    private GalleryRecyclerView bannerRv;
    private boolean refreshTag = true;

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
        initListener();
        getNetData();
    }

    @Override
    public void getNetData() {
        getP().getHeaderData();
        getP().getFindNewsData(pageNum);
    }

    private void initListener() {
        refreshFindLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                refreshTag = false;
                getP().getFindNewsData(pageNum);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                refreshTag = true;
                getP().getFindNewsData(pageNum);
            }
        });

        newsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String loadType = newsList.get(position).getLoadType();
                if (TextUtils.equals(loadType, "info_web")) {
                    Router.newIntent(context)
                            .to(NewsActivity.class)
                            .putString("title", newsList.get(position).getTitle())
                            .putString("dataID", newsList.get(position).getDataId())
                            .launch();
                } else {
                    Router.newIntent(context)
                            .to(VideoDetailActivity.class)
                            .putString("mediaID", newsList.get(position).getDataId())
                            .launch();
                }
            }
        });

        galleryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String loadType = bannerList.get(position).getLoadType();
                if (TextUtils.equals(loadType, "video")) {
                    Router.newIntent(context)
                            .to(VideoDetailActivity.class)
                            .putString("mediaID", bannerList.get(position).getDataId())
                            .launch();
                } else {
                    getvDelegate().toastShort("暂未开启");
                }
            }
        });


    }

    private void initView() {
        refreshFindLayout.setPrimaryColorsId(R.color.colorPrimary);
        rvFind.setLayoutManager(new LinearLayoutManager(context));
        newsAdapter = new FindNewsAdapter(R.layout.item_find_news_layout, newsList);
        View header = View.inflate(context, R.layout.head_find_banner_layout, null);
        bannerRv = (GalleryRecyclerView) header.findViewById(R.id.grv_find_head);
        headCover = (ImageView) header.findViewById(R.id.iv_back_find_head);
        headTitle = (TextView) header.findViewById(R.id.tv_find_news_head_title);
        bannerRv.setCanAlpha(true);
        bannerRv.setCanScale(true);
        bannerRv.setBaseScale(0.5f);
        bannerRv.setBaseAlpha(0.7f);
        bannerRv.addItemDecoration(new GalleryDecoration((int) getResources().getDimension(R.dimen.find_header_space)));
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        bannerRv.setLayoutManager(linearLayoutManager);
        galleryAdapter = new FindHeaderGalleryAdapter(R.layout.item_find_header_layout, bannerList);
        bannerRv.setAdapter(galleryAdapter);
        bannerRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int currentPosition = bannerRv.getCurrentPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && currentPosition != itemPosition) { //说明位置改变
                    itemPosition = currentPosition;
                    Glide.with(context).load(bannerList.get(currentPosition).getPic())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .transform(new BlurTransformation(context,25,3))
                            .into(headCover);
                    headTitle.setText(bannerList.get(currentPosition).getTitle());
                }
            }
        });
        newsAdapter.addHeaderView(header);
        rvFind.setAdapter(newsAdapter);


    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_find_layout;
    }

    @Override
    public FindPresent newP() {
        return new FindPresent();
    }


    public void setBannerData(List<FindHeaderBean.RetBean.BannerListBean> list) {
        bannerList.clear();
        bannerList.addAll(list);
        galleryAdapter.notifyDataSetChanged();
        bannerRv.setSelectPosition(0);
        Glide.with(context).load(bannerList.get(0).getPic())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new BlurTransformation(context, 25, 3))
                .into(headCover);
        headTitle.setText(bannerList.get(0).getTitle());

    }


    public void setFindListData(List<FindNewsBean.RetBean.FindListBean> list) {
        if (refreshTag) {
            refreshFindLayout.finishRefresh(1000);
            newsList.clear();
        } else {
            refreshFindLayout.finishLoadMore();
        }
        newsList.addAll(list);
        newsAdapter.notifyDataSetChanged();
    }


    class GalleryDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public GalleryDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (parent.getChildPosition(view) != 0)
                outRect.left = space;
            outRect.right = space;
        }
    }

}
