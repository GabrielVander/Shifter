package com.pdm.shifter.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.pdm.shifter.R;
import com.pdm.shifter.ViewModel.PunchInViewModel;
import com.pdm.shifter.databinding.PunchInFragmentBinding;
import com.pdm.shifter.model.Punch;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class PunchInFragment extends Fragment {

    private final String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    private PunchInFragmentBinding binding;
    private PunchInViewModel mViewModel;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference punchesRef = db.collection("punches");
    private String lastPunchType = "OUT";

    public static PunchInFragment newInstance() {
        return new PunchInFragment();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PunchInFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(PunchInViewModel.class);

        getPunchType();

        binding.btnPunchIn.setOnLongClickListener(view -> {
            String punchType = lastPunchType.equals("IN") ? "OUT" : "IN";

            Punch punch = new Punch();
            punch.setType(punchType);
            punch.setTime(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());
            punch.setUserId(userId);

            punchesRef
                    .add(punch)
                    .addOnSuccessListener(documentReference -> {
                        lastPunchType = punchType;
                        Toast.makeText(getContext(), R.string.puch_added, Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> Toast.makeText(getContext(), R.string.punch_add_error, Toast.LENGTH_SHORT).show());

            return true;
        });
        return binding.getRoot();
    }

    private void getPunchType() {
        punchesRef
                .whereEqualTo("userId", userId)
                .orderBy("time", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (!Objects.requireNonNull(task.getResult()).isEmpty()) {
                            DocumentSnapshot punch = task.getResult().getDocuments().get(0);

                            lastPunchType = punch.get("type") == "IN" ? "IN" : "OUT";
                        }
                    }
                });
    }
}