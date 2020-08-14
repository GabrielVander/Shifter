package com.pdm.shifter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.vipulasri.timelineview.TimelineView;
import com.pdm.shifter.R;
import com.pdm.shifter.dummy.DummyContent.DummyItem;
import com.pdm.shifter.dummy.PunchType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final Context mContext;
    private Date previousDate = new Date();

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
        final LocalDateTime content = item.getContent();
        if (item.isDateItem()) {
            holder.mPunch.setText(content.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault())));
            holder.mContent.setVisibility(View.GONE);
        } else {
            holder.mContent.setText(content.format(DateTimeFormatter.ofPattern("hh:mm a", Locale.getDefault())));
            holder.mPunch.setText(
                    item.getPunchType() == PunchType.IN
                            ? mContext.getResources().getString(R.string.punched_in)
                            : mContext.getResources().getString(R.string.punched_out)
            );
        }

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
        if (item
                .getContent()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .compareTo(
                        mValues.get(mValues.size() - 1)
                                .getContent()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                ) > 0) {
            DummyItem dateItem = new DummyItem();
            dateItem.setContent(item.getContent());
            dateItem.setDateItem(true);
            dateItem.setPunchType(getPunchType(mValues.size() - 1));
            mValues.add(dateItem);
            notifyItemInserted(mValues.size() - 1);
        }
        mValues.add(item);
        notifyItemInserted(mValues.size() - 1);
    }

    private PunchType getPunchType(int position) {
        return position % 2 == 0 ? PunchType.OUT : PunchType.IN;
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
            mContent = view.findViewById(R.id.lblTime);
            mPunch = view.findViewById(R.id.lblPunched);
            mTimelineView = view.findViewById(R.id.timeline_view);
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