package shuye.com.myapplication.presenter;

import android.content.Context;

import java.util.List;

import shuye.com.myapplication.base.ICallback;
import shuye.com.myapplication.contract.MainContract;
import shuye.com.myapplication.entity.NotificationEntity;
import shuye.com.myapplication.model.MainModel;

public class MainPresenter implements MainContract.Presenter {

    private  MainContract.View mView;
    private MainModel notModel;


    public MainPresenter(MainContract.View mView) {
        notModel = new MainModel();
        this.mView = mView;
        this.mView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void getnotificationlist(Context context, boolean isshow) {
        notModel.getnotificationlist(context,isshow ,new ICallback<List<NotificationEntity>>() {
            @Override
            public void onSucceed(List<NotificationEntity> data) {
                mView.shownotificationlist(data);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
