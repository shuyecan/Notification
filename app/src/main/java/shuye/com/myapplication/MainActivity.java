package shuye.com.myapplication;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shuye.com.myapplication.contract.MainContract;
import shuye.com.myapplication.entity.NotificationEntity;
import shuye.com.myapplication.model.MainModel;
import shuye.com.myapplication.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.View {


    MainContract.Presenter mpresenter;
    @BindView(R.id.recy_not_list)
    RecyclerView recyNotList;
    @BindView(R.id.text_not_compile)
    TextView textNotCompile;
    @BindView(R.id.checkbox_not_checkall)
    CheckBox checkboxNotCheckall;
    @BindView(R.id.Rel_notbottom)
    RelativeLayout RelNotbottom;
    @BindView(R.id.line_not_compile)
    LinearLayout lineNotCompile;
    @BindView(R.id.text_not_delete)
    TextView textNotDelete;
    @BindView(R.id.swf_not_getdata)
    SwipeRefreshLayout swfNotGetdata;
    private MainModel.NotificationAdapter adapter;
    private List<NotificationEntity> datalist;
    boolean isshow = false;
    BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new MainPresenter(this);
        inittooler();
        initdata();
    }

    private void initdata() {
        datalist = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
//        recyNotList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyNotList.setLayoutManager(layoutManager);
        adapter = new MainModel.NotificationAdapter(this, datalist);
        recyNotList.setAdapter(adapter);
        mpresenter.getnotificationlist(this, isshow);
        checkboxNotCheckall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    adapter.chechall(true);
                } else {
                    adapter.chechall(false);
                }
            }
        });
        swfNotGetdata.setColorSchemeResources(R.color.view_blue);
        swfNotGetdata.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mpresenter.getnotificationlist(MainActivity.this, isshow);
                swfNotGetdata.setRefreshing(false);
            }
        });
    }

    private void inittooler() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("系统通知");
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        assert ab != null;

        ab.setDisplayHomeAsUpEnabled(true);

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.Rel_notbottom));
        bottomSheetBehavior.setSkipCollapsed(true);
        bottomSheetBehavior.setHideable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mpresenter = presenter;
    }

    @Override
    public void shownotificationlist(List<NotificationEntity> list) {
        datalist.clear();
        datalist.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.line_not_compile, R.id.text_not_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.line_not_compile:
                if (isshow) {
                    isshow = false;
                    adapter.isshowcheck(false);
                    textNotCompile.setText("编辑");
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                } else {
                    isshow = true;
                    adapter.isshowcheck(true);
                    textNotCompile.setText("取消");
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }


                break;
            case R.id.text_not_delete:
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
                builder.setMessage("是否清除已选中的消息通知？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.deleteItem();
                        checkboxNotCheckall.setChecked(false);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
        }
    }

}
