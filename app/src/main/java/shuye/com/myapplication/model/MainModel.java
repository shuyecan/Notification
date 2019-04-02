package shuye.com.myapplication.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shuye.com.myapplication.R;
import shuye.com.myapplication.base.ICallback;
import shuye.com.myapplication.contract.MainContract;
import shuye.com.myapplication.entity.NotificationEntity;

public class MainModel implements MainContract.Model {


    @Override
    public void getnotificationlist(Context context, boolean isshow,ICallback<List<NotificationEntity>> callback) {
        List<NotificationEntity> list = new ArrayList<>();
        for (int i = 0;i<100;i++){
            NotificationEntity data = new NotificationEntity();
            data.setNot_titel("独家直播！雪未来第十年演唱会");
            data.setNot_content("在雪未来(SNOW MIKU)不如第10年之际，bilibili将独家线上放送夜间演唱会，给你一个冰雪之夜！");
            data.setNot_time("五天前");
            data.setIsshow(isshow);
            data.setId(i);
            data.setIscheck(false);
            list.add(data);
        }
        callback.onSucceed(list);

    }




    public static class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Viewholder> {
        Context context;
        List<NotificationEntity> list;
        //        private static onselect select;
        public NotificationAdapter(Context context, List<NotificationEntity> list) {
            this.context = context;
            this.list = list;
        }

//        public interface onselect{
//            void onclicke(int s);
//        }
//
//        public static void setischeck(onselect myListener) {
//            select = myListener;
//        }


        @NonNull
        @Override
        public NotificationAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(context==null){
                context = parent.getContext();
            }
            View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
            final NotificationAdapter.Viewholder holder = new NotificationAdapter.Viewholder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull final NotificationAdapter.Viewholder holder, final int position) {
            final NotificationEntity notification =  list.get(position);
            holder.text_notcontent_item.setText(notification.getNot_content());
            holder.text_nottitle_item.setText(notification.getNot_titel());
            holder.text_nottime_item.setText(notification.getNot_time());
            if(notification.isIsshow()){
                holder.check_not_itme.setVisibility(View.VISIBLE);
            }else {
                holder.check_not_itme.setVisibility(View.GONE);
            }

            if(notification.isIscheck()){
                holder.check_not_itme.setChecked(true);
            }else {
                holder.check_not_itme.setChecked(false);
            }


            holder.check_not_itme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.check_not_itme.isChecked()){
                        notification.setIscheck(true);
                    }else {
                        notification.setIscheck(false);
                    }
                }
            });



            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(notification.isIsshow()){
                        if(holder.check_not_itme.isChecked()){
                            notification.setIscheck(false);
                            holder.check_not_itme.setChecked(false);
                        }else {
                            notification.setIscheck(true);
                            holder.check_not_itme.setChecked(true);
                        }
                    }else {

                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
        /**
         *
         *@作者 Administrator
         *@时间 2019/4/1 0001 下午 4:00
         *  批量删除
         */
        public void deleteItem(){
            Iterator<NotificationEntity> iterator = list.iterator();
            while (iterator.hasNext()){
                NotificationEntity notificationEntity = iterator.next();
                if(notificationEntity.isIscheck()){
                    iterator.remove();
                }
            }
            notifyDataSetChanged();
        }
        /**
         *
         *@作者 shuye
         *@时间 2019/4/1 0001 下午 4:00
         *  全选所有的通知
         */
        public void chechall(boolean t){
            Iterator<NotificationEntity> iterator = list.iterator();
            while (iterator.hasNext()){
                NotificationEntity notificationEntity = iterator.next();
                if(t){
                    if(!notificationEntity.isIscheck()){
                        notificationEntity.setIscheck(true);
                    }
                }else {
                    if (notificationEntity.isIscheck()){
                        notificationEntity.setIscheck(false);
                    }
                }
            }
            notifyDataSetChanged();
        }
        /**
         *
         *@作者 shuye
         *@时间 2019/4/1 0001 下午 4:06
         *  是否显示单选框
         */
        public void isshowcheck(boolean t){
            Iterator<NotificationEntity> iterator = list.iterator();
            while (iterator.hasNext()){
                NotificationEntity notificationEntity = iterator.next();
                if(t){
                    if(!notificationEntity.isIsshow()){
                        notificationEntity.setIsshow(true);
                    }
                }else {
                    if (notificationEntity.isIsshow()){
                        notificationEntity.setIsshow(false);
                    }
                }
            }
            notifyDataSetChanged();
        }


        public class Viewholder extends RecyclerView.ViewHolder {
            TextView text_nottitle_item,text_notcontent_item,text_nottime_item;
            CheckBox check_not_itme;
            RelativeLayout relativeLayout;
            public Viewholder(@NonNull View itemView) {
                super(itemView);
                relativeLayout = (RelativeLayout) itemView;
                text_nottitle_item = itemView.findViewById(R.id.text_nottitle_item);
                text_notcontent_item = itemView.findViewById(R.id.text_notcontent_item);
                text_nottime_item = itemView.findViewById(R.id.text_nottime_item);
                check_not_itme = itemView.findViewById(R.id.check_not_itme);
            }
        }
    }
}
