package com.example.myannounce;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NewAnnounce extends Fragment {
    private DBHelper helper;
    private ListView listView;
    private ImageButton Add;
    private List<announceList> list;
    private List<announceList> Rlist;
    private View root;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstenceState){

        if (root==null) {
            root = inflater.inflate(R.layout.fragment_new_announce, container, false);
        }
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        Add=(ImageButton) root.findViewById(R.id.add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnnounce(v);
            }
        });
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

    private void initView() {
        helper=new DBHelper(getActivity());
        listView= getActivity().findViewById(R.id.list_view);
        Add= getActivity().findViewById(R.id.add);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1&&requestCode==1)
        {
            this.initData();
        }
    }
    public void addAnnounce(View view){
        Intent intent=new Intent(getActivity(),new_announce.class);
        startActivityForResult(intent,1);
    }

}