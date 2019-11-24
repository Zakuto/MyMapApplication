package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.ListOfActivity;

import java.util.List;


public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {
    private List<ListOfActivity> mListOfActivities;
    private Context mContext;

    public ActivityAdapter (Context context,List<ListOfActivity> listOfActivities){
        mContext = context;
        mListOfActivities = listOfActivities;
    }

   class ActivityViewHolder extends RecyclerView.ViewHolder{
       public final View mView;
       TextView textView;
       RelativeLayout relativeLayout;

       public ActivityViewHolder(View itemView) {
           super(itemView);
           mView = itemView;

            textView = mView.findViewById(R.id.titleActivity);
            relativeLayout = mView.findViewById(R.id.parent_layout);
       }
   }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int position){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_main_row, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder holder, final int position) {
            holder.textView.setText(mListOfActivities.get(position).getActivityName());
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        String activityToStart =  mListOfActivities.get(position).getActivityName();

                        Intent intent = new Intent();
                        intent.setClassName(mContext, mContext.getPackageName() + "." + activityToStart);
                        mContext.startActivity(intent);
                }
            });

    }

    @Override
    public int getItemCount() {
        return mListOfActivities.size();
    }
}
