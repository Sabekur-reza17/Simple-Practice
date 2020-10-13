package com.sabekur2017.tenminuteschooltask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ChapterModel> chapterModels = new ArrayList<>();

    public RecyclerAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chapter_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ChapterModel chapterModel = chapterModels.get(position);
        holder.txtTopic.setText(chapterModel.name);
        holder.itemView.setTag(R.string.MODEL, chapterModel);
        holder.itemView.setTag(R.string.position, position);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag(R.string.position);
                ChapterModel chapterModel1 = (ChapterModel) v.getTag(R.string.MODEL);
                if (chapterModel1.chapterModels.isEmpty()) {
                    return;
                }
                switch (chapterModel.state) {
                    case CLOSED:
                        chapterModels.addAll(position + 1, chapterModel1.chapterModels);
                        notifyItemRangeInserted(position + 1, chapterModel1.chapterModels.size());
                        notifyItemRangeChanged(position + chapterModel1.chapterModels.size(), chapterModels.size() - (position + chapterModel1.chapterModels.size()));
                        notifyItemRangeChanged(position, chapterModels.size() - position);
                        chapterModel1.state = ChapterModel.STATE.OPENED;
                        break;
                    case OPENED:
                        int start = position + 1;
                        int end = chapterModels.size();
                        for (int i = start; i < chapterModels.size(); i++) {

                            ChapterModel model1 = chapterModels.get(i);
                            if (model1.level <= chapterModel1.level) {
                                end = i;
                                break;
                            } else {
                                if (model1.state == ChapterModel.STATE.OPENED) {
                                    model1.state = ChapterModel.STATE.CLOSED;
                                }
                            }
                        }
                        if (end != -1) {
                            chapterModels.subList(start, end).clear();
                            notifyItemRangeRemoved(start, end - start);
                            notifyItemRangeChanged(start, end - start);
                            notifyItemRangeChanged(position, chapterModels.size() - position);
                        }

                        chapterModel1.state = ChapterModel.STATE.CLOSED;
                        break;


                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return chapterModels.size();
    }

    public void setData(ArrayList<ChapterModel> list) {
        chapterModels = list;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayoutd;
        TextView txtTopic;
        ImageView imgTopic;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTopic = itemView.findViewById(R.id.txt_list_topic);
            imgTopic = itemView.findViewById(R.id.list_icon);
            linearLayoutd = itemView.findViewById(R.id.layout_item);

        }
    }
}
