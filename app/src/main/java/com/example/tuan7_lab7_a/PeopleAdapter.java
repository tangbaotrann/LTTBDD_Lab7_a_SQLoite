package com.example.tuan7_lab7_a;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class PeopleAdapter extends BaseAdapter {

    private Context context;
    private int idLayout;
    private List<People> listPeople;
//  private int index=-1;

    public PeopleAdapter(Context context, int idLayout, List<People> listPeople) {
        this.context = context;
        this.idLayout = idLayout;
        this.listPeople = listPeople;
    }

    @Override
    public int getCount() {
        if(listPeople.size() != 0 && !listPeople.isEmpty()) {
            return  listPeople.size();
        }

        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout, viewGroup, false);
        }

        TextView tvName = view.findViewById(R.id.tvName);
//      final ConstraintLayout idConstraintLayout = view.findViewById(R.id.idConstraintLayout);
        final People people = listPeople.get(i);

        if(listPeople != null && !listPeople.isEmpty()) {
            // set tv
            tvName.setText(people.get_name());
        }

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                index = i;
//                notifyDataSetChanged();
//            }
//        });
//        if(index == i) {
//            idConstraintLayout.setBackgroundColor(Color.rgb(32, 140, 200));
//        } else {
//            idConstraintLayout.setBackgroundColor(Color.WHITE);
//        }

        return view;
    }
}
