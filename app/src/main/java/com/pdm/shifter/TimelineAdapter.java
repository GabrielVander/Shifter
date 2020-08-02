package com.pdm.shifter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.vipulasri.timelineview.TimelineView;
import com.pdm.shifter.dummy.DummyContent.DummyItem;
import com.pdm.shifter.dummy.PunchType;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final Context mContext;

    public TimelineAdapter(List<DummyItem> items, Context context) {
        mValues = items;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_timeline, parent, false);
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DummyItem item = mValues.get(position);
        holder.mItem = item;
        holder.mContent.setText(item.content);
        holder.mPunch.setText(
                item.punchType == PunchType.IN
                ? mContext.getResources().getString(R.string.punched_in)
                : mContext.getResources().getString(R.string.punched_out)
        );

    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addItem(DummyItem item) {
        mValues.add(item);
        notifyItemInserted(mValues.size() - 1);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TimelineView mTimelineView;
        public TextView mContent;
        public TextView mPunch;
        public DummyItem mItem;

        public ViewHolder(View view, int viewType) {
            super(view);
            mView = view;
            mContent = (TextView) view.findViewById(R.id.lblTime);
            mPunch = (TextView) view.findViewById(R.id.lblPunched);
            mTimelineView = (TimelineView) view.findViewById(R.id.timeline_view);
            mTimelineView.initLine(viewType);
        }

        @NonNull
        @Override
        public String toString() {
            return "ViewHolder{" +
                    "mView=" + mView +
                    ", mTimelineView=" + mTimelineView +
                    ", mContent=" + mContent +
                    ", mItem=" + mItem +
                    ", itemView=" + itemView +
                    '}';
        }
    }
}