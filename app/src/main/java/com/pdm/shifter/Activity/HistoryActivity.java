package com.pdm.shifter.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.pdm.shifter.Adapter.TimelineAdapter;
import com.pdm.shifter.R;
import com.pdm.shifter.databinding.HistoryActivityBinding;
import com.pdm.shifter.model.Punch;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class HistoryActivity extends AppCompatActivity {

    private HistoryActivityBinding binding;
    private static final String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    private FirestoreRecyclerAdapter<Punch, TimelineAdapter.ViewHolder> adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference punchesRef = db.collection("punches");
    private final Query allPunchesQuery = punchesRef
            .whereEqualTo("userId", userId)
            .orderBy("time", Query.Direction.DESCENDING);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HistoryActivityBinding.inflate(getLayoutInflater());

        FirestoreRecyclerOptions<Punch> options = new FirestoreRecyclerOptions.Builder<Punch>()
                .setQuery(allPunchesQuery, Punch.class)
                .build();

        // Create a new instance of the ViewHolder, in this case we are using a custom
        // layout called R.layout.message for each item
        adapter = new FirestoreRecyclerAdapter<Punch, TimelineAdapter.ViewHolder>(options) {
            @Override
            public void onBindViewHolder(@NonNull TimelineAdapter.ViewHolder holder, int position, @NonNull Punch model) {
                holder.mItem = model;
                final LocalDateTime content = LocalDateTime.ofInstant(Instant.ofEpochSecond(model.getTime()), ZoneId.systemDefault());
                assert content != null;
                final String formattedDate = content.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault()));
                if (model.isDatePunch()) {
                    holder.mPunch.setText(formattedDate);
                    holder.mContent.setVisibility(View.GONE);
                } else {
                    holder.mContent.setText(content.format(DateTimeFormatter.ofPattern("hh:mm a", Locale.getDefault())));
                    holder.mPunch.setText(
                            model.getType().equals("IN")
                                    ? getBaseContext().getResources().getString(R.string.punched_in_date, formattedDate)
                                    : getBaseContext().getResources().getString(R.string.punched_out_date, formattedDate)
                    );
                }
            }

            @NonNull
            @Override
            public TimelineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.fragment_timeline, group, false);

                return new TimelineAdapter.ViewHolder(view, i);
            }
        };
        RecyclerView recyclerView = binding.historyList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getBaseContext()));
        recyclerView.setAdapter(adapter);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                recyclerView.smoothScrollToPosition(0);
            }
        });

        setContentView(binding.getRoot());
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}