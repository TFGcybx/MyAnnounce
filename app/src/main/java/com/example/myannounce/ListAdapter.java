package com.example.myannounce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
public class ListAdapter extends BaseAdapter{
    List<announceList> mList;
    public ListAdapter( List<announceList> list)
    {
        mList=list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=mLayoutInflater.inflate(R.layout.list_item,null);
        announceList item=mList.get(position);
        TextView uName=view.findViewById(R.id.uName);
        TextView title=view.findViewById(R.id.title);
        TextView details=view.findViewById(R.id.details);
        TextView date=view.findViewById(R.id.date);

        uName.setText(mList.get(position).getUName());
        title.setText(mList.get(position).getTitle());
        details.setText(mList.get(position).getDetails());
        date.setText(mList.get(position).getDate());
        return view;

    }

    private List<announceList>getmList;
    private LayoutInflater mLayoutInflater;

    public ListAdapter(Context context,List<announceList>list)
    {
        mList=list;
        mLayoutInflater=LayoutInflater.from(context);
    }
}
