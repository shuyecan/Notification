package shuye.com.myapplication.contract;

import android.content.Context;

import java.util.List;

import shuye.com.myapplication.base.BasePresenter;
import shuye.com.myapplication.base.BaseView;
import shuye.com.myapplication.base.ICallback;
import shuye.com.myapplication.entity.NotificationEntity;

public interface MainContract {
    interface Model {
        void getnotificationlist(Context context, boolean isshow,ICallback<List<NotificationEntity> > callback);
    }

    interface View extends BaseView<Presenter> {
        void shownotificationlist(List<NotificationEntity> list);
    }

    interface Presenter extends BasePresenter {
        void getnotificationlist(Context context, boolean isshow);
    }
}
