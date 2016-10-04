package com.example.choco3.rxretrofitlab;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.choco3.rxretrofitlab.databinding.ActivityMainBinding;
import com.example.choco3.rxretrofitlab.http.HttpManager;
import com.example.choco3.rxretrofitlab.model.GitHubFollowerUser;
import com.example.choco3.rxretrofitlab.recycler.FollowerUserListAdapter;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewBeforeTextChangeEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private FollowerUserListAdapter adapter;
    private String user;
    private LinearLayoutManager mLayoutManager;
    private Observable<CharSequence> userChangeObservable;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initInstances();


    }

    private void initInstances() {
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mBinding.listTest.setLayoutManager(mLayoutManager);
        adapter = new FollowerUserListAdapter();
        mBinding.listTest.setAdapter(adapter);


        mBinding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(user);
                mBinding.swipeRefreshLayout.setRefreshing(false);
            }
        });


        mBinding.btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = mBinding.edtUser.getText().toString();
                if (!user.equals("")) {
                    loadData(user);

                }
            }
        });

        adapter.setListener(new FollowerUserListAdapter.itemListener() {
            @Override
            public void onItemClick(String user) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("user", user);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    private void loadData(String user) {
        Observable<List<GitHubFollowerUser>> call = HttpManager.getInstance().getServices().loadFollowerUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());

        subscription = call
                .subscribe(new Observer<List<GitHubFollowerUser>>() {

                    @Override
                    public void onCompleted() {
                        Log.i("onCompleted", "Done");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error", "Connect is problem. Please check your word");
                    }

                    @Override
                    public void onNext(List<GitHubFollowerUser> followUserGitHubs) {
                        adapter.setListData(followUserGitHubs);
                        adapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }
}
