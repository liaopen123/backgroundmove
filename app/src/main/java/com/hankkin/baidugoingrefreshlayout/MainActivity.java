package com.hankkin.baidugoingrefreshlayout;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaiDuRefreshListView.OnBaiduRefreshListener{


    private BaiDuRefreshListView mListView;
    private List<String> mDatas;
    private ArrayAdapter<String> mAdapter;

    private final static int REFRESH_COMPLETE = 0;

    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    mListView.setOnRefreshComplete();
                    mAdapter.notifyDataSetChanged();
                    mListView.setSelection(0);
                    break;

                default:
                    break;
            }
        };
    };
    private ImageView iv_back1;
    private Animation animation;
    private ImageView iv_back2;
    private Animation animation1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backgroundview);
        iv_back1 = (ImageView)findViewById(R.id.iv_back1);
        iv_back2 = (ImageView)findViewById(R.id.iv_back2);
         animation = AnimationUtils.loadAnimation(this, R.anim.backgroundmove);
         animation1 = AnimationUtils.loadAnimation(this, R.anim.backgroundmove1);



//        mListView = (BaiDuRefreshListView) findViewById(R.id.lv);
//        String[] data = new String[]{"a","b","c","d",
//                "e","f","g","h","i",
//                "j","k","l","m","n","o","p","q","r","s"};
//        mDatas = new ArrayList<String>(Arrays.asList(data));
//        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mDatas);
//        mListView.setAdapter(mAdapter);
//        mListView.setOnBaiduRefreshListener(this);
    }


    @Override
    protected void onStart() {
        iv_back1.startAnimation(animation);
        iv_back2.startAnimation(animation1);
        super.onStart();
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    mDatas.add(0, "new data");
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
