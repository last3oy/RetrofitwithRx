package com.example.choco3.rxretrofitlab;


import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.choco3.rxretrofitlab.databinding.ActivityMain2Binding;
import com.example.choco3.rxretrofitlab.http.Contextor;
import com.example.choco3.rxretrofitlab.http.HttpManager;
import com.example.choco3.rxretrofitlab.model.GitHubUser;
import com.squareup.picasso.Picasso;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Main2Activity extends AppCompatActivity {

    private ActivityMain2Binding mBinding;
    private String user;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        Bundle bundle = getIntent().getBundleExtra("bundle");

        if (bundle != null) {
            user = bundle.getString("user");
        }


        initInstances();
    }

    private void initInstances() {
        Observable<GitHubUser> call = HttpManager.getInstance().getServices().loadUser(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());

        subscription = call
                .subscribe(new Observer<GitHubUser>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GitHubUser gitHubUser) {
                        Picasso.with(Contextor.getInstance().getContext())
                                .load(gitHubUser.getAvatarUrl())
                                .resize(200, 200)
                                .into(mBinding.ivAvatar);

                        mBinding.tvId.setText(gitHubUser.getId().toString());
                        mBinding.tvName.setText(gitHubUser.getLogin());


                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }
}
