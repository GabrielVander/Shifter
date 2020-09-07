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
import com.pdm.shifter.model.Punch;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private final List<Punch> mValues;
    private final Context mContext;

    public TimelineAdapter(List<Punch> items, Context context) {
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
//        final Punch item = mValues.get(position);
//        holder.mItem = item;
//        final LocalDateTime content = (LocalDateTime) item.getSnapshot().get("time");
//        assert content != null;
//        if (item.isDatePunch()) {
//            holder.mPunch.setText(content.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault())));
//            holder.mContent.setVisibility(View.GONE);
//        } else {
//            holder.mContent.setText(content.format(DateTimeFormatter.ofPattern("hh:mm a", Locale.getDefault())));
//            holder.mPunch.setText(
//                    item.getSnapshot().get("type") == "IN"
//                            ? mContext.getResources().getString(R.string.punched_in)
//                            : mContext.getResources().getString(R.string.punched_out)
//            );
//        }

    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addItem(Punch item) {
//        if (!isFromToday(item)) {
//            Punch dateItem = new Punch();
//            dateItem.setSnapshot(item.getSnapshot());
//            dateItem.setDatePunch(true);
//            mValues.add(dateItem);
//            notifyItemInserted(mValues.size() - 1);
//        }
//        mValues.add(item);
//        notifyItemInserted(mValues.size() - 1);
    }

//    private boolean isFromToday(Punch item) {
////        final LocalDateTime now = LocalDateTime.now();
////        LocalDateTime time = (LocalDateTime) item.getSnapshot().get("time");
////
////        assert time != null;
////        return time.getYear() != now.getYear() || time.getDayOfYear() != now.getDayOfYear();
//    }

    private PunchType getPunchType(int position) {
        return position % 2 == 0 ? PunchType.OUT : PunchType.IN;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TimelineView mTimelineView;
        public TextView mContent;
        public TextView mPunch;
        public Punch mItem;

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