package com.example.modellitpromx;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class FeedRecViewAdapter extends RecyclerView.Adapter<FeedRecViewAdapter.ViewHolder> {

    private static final String TAG = "FeedRecViewAdapter";
    private ArrayList<Feed> feedData;
    private OnFeedListener mOnFeedListener;
    private Context context;

    public FeedRecViewAdapter(Context context, OnFeedListener onFeedListener) {
        this.context = context;
        this.mOnFeedListener = onFeedListener;
    }

    @NonNull
    @Override
    public FeedRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_feed,parent,false);
        ViewHolder holder = new ViewHolder(view, mOnFeedListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedRecViewAdapter.ViewHolder holder, int position) {
        holder.lapTime.setText(String.valueOf(feedData.get(position).getLapTime()));
        holder.numLaps.setText(String.valueOf(feedData.get(position).getLapCount()));
        holder.sessionLength.setText(feedData.get(position).getSessionLength());
        holder.ranking.setText(String.valueOf(feedData.get(position).getRanking()));

        Glide.with(context)
                .asBitmap()
                .load(feedData.get(position).getImgUrl())
                .into(holder.feedImg);
    }

    @Override
    public int getItemCount() {
        return feedData.size();
    }

    public void setFeed(ArrayList<Feed> feedData) {
        Log.d(TAG, "setFeed: start");
        this.feedData = feedData;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView lapTime, numLaps, sessionLength, ranking;
        private LinearLayout sessionSummary;
        private ImageView feedImg;
        private OnFeedListener onFeedListener;

        public ViewHolder(@NonNull View itemView, OnFeedListener onFeedListener) {
            super(itemView);
            sessionSummary = itemView.findViewById(R.id.sessionSummary);
            lapTime = itemView.findViewById(R.id.lapTime);
            numLaps = itemView.findViewById(R.id.numLaps);
            sessionLength = itemView.findViewById(R.id.sessionLength);
            ranking = itemView.findViewById(R.id.ranking);
            feedImg = itemView.findViewById(R.id.feedImg);
            this.onFeedListener = onFeedListener;

            feedImg.setOnClickListener(this);
            sessionSummary.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == sessionSummary) {
                onFeedListener.onFeedClick(getAbsoluteAdapterPosition(), "singleDay");
            }
            else {
                onFeedListener.onFeedClick(getAbsoluteAdapterPosition(), "allDays");
            }
        }
    }

    public interface OnFeedListener {
        void onFeedClick(int position, String filterSetting);
    }
}
