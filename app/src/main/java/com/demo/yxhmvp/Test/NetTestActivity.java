package com.demo.yxhmvp.Test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.yxhmvp.R;
import com.demo.yxhmvp.base.BaseActivity;

import butterknife.BindView;

public class NetTestActivity extends BaseActivity<NetTestPresent> {

    @BindView(R.id.NT_bt_test)
    Button button;
    @BindView(R.id.NT_txt_test)
    TextView textView;

    @Override
    public void getNetData() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getP().getNetDate();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_net_test;
    }

    @Override
    public NetTestPresent newP() {
        return new NetTestPresent();
    }

    public void setStr(NetTestBean netTestBean) {
        textView.setText(netTestBean.getBooklist().get(0).getBookImg());
    }
}
