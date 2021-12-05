package com.example.myannounce;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NewAnnounce extends Fragment implements AdapterView.OnItemClickListener{
    private DBHelper helper;
    private ListView listView;
    private ImageButton Add;
    private ImageButton BTop;
    private TextView Delete;
    private List<announceList> list;
    private List<announceList> Rlist;
    private View root;
    private boolean scrollFlag = false;
    private int lastVisibleItemPosition = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstenceState){
        if(root==null){
            root=inflater.inflate(R.layout.fragment_new_announce,container,false);
        }

        listView=root.findViewById(R.id.list_view);
        helper=new DBHelper(getActivity());
        Add=root.findViewById(R.id.add);
        BTop=root.findViewById(R.id.backtop);
        Delete=root.findViewById(R.id.delete);
        listView.setOnItemClickListener(this);
        //点击添加新的内容
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),new_announce.class);
                startActivityForResult(intent,1);
            }
        });
        //滑动时处与顶部不显示返回按键，其他时候显示。
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch(scrollState){
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        scrollFlag=false;
                        if(listView.getLastVisiblePosition()==listView.getCount()-1){
                            BTop.setVisibility(View.VISIBLE);
                        }
                        if(listView.getFirstVisiblePosition()==0){
                            BTop.setVisibility(View.GONE);
                        }
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        scrollFlag=true;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        scrollFlag=false;
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (scrollFlag
                        && ScreenUtil.getScreenViewBottomHeight(listView) >= ScreenUtil
                        .getScreenHeight(getActivity())) {
                    if (firstVisibleItem > lastVisibleItemPosition) {// 上滑
                        BTop.setVisibility(View.VISIBLE);
                    } else if (firstVisibleItem < lastVisibleItemPosition) {// 下滑
                        BTop.setVisibility(View.GONE);
                    } else {
                        return;
                    }
                    lastVisibleItemPosition = firstVisibleItem;
                }

            }
        });
        //点击返回顶部
        BTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.backtop:
                        setListViewPos(0);
                        break;
                }
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    @SuppressLint("Range")
    private void initData() {
        list=new ArrayList<>();
        Rlist=new ArrayList<>();
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.query("announce",null,null,null,null,
                null,null);
        while (cursor.moveToNext()) {
            announceList clist = new announceList();
            clist.set_id(cursor.getString(cursor.getColumnIndex("_id")));
            clist.setUName(cursor.getString(cursor.getColumnIndex("uName")));
            clist.setTitle(cursor.getString(cursor.getColumnIndex("Title")));
            clist.setDetails(cursor.getString(cursor.getColumnIndex("Details")));
            clist.setDate(cursor.getString(cursor.getColumnIndex("Date")));
            Rlist.removeAll(Rlist);
            list.add(clist);
            Rlist.addAll(list);
            Collections.reverse(Rlist);

        }
        listView.setAdapter(new ListAdapter(getActivity(),Rlist));
        db.close();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1&&requestCode==1)
        {
            this.initData();
        }
    }
    private void setListViewPos(int pos){
        if(Build.VERSION.SDK_INT>=3){
            listView.smoothScrollToPosition(pos);
        }else{
            listView.setSelection(pos);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}