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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.pdm.shifter.R;
import com.pdm.shifter.ViewModel.PunchInViewModel;
import com.pdm.shifter.databinding.PunchInFragmentBinding;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PunchInFragment extends Fragment {

    private final String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    private PunchInFragmentBinding binding;
    private PunchInViewModel mViewModel;
    private FirebaseFirestore db;
    private CollectionReference punchesRef;
    private String lastPunchType = "IN";

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
        db = FirebaseFirestore.getInstance();
        punchesRef = db.collection("punches");

        getPunchType();

        binding.btnPunchIn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Map<String, Object> punch = new HashMap<>();
                punch.put("type", lastPunchType);
                punch.put("time", LocalDateTime.now());
                punch.put("userId", userId);

                punchesRef
                        .add(punch)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getContext(), R.string.puch_added, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), R.string.punch_add_error, Toast.LENGTH_SHORT).show();
                            }
                        });

                return true;
            }
        });
        return binding.getRoot();
    }

    private void getPunchType() {
        punchesRef
                .whereEqualTo("userId", userId)
                .orderBy("time", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!Objects.requireNonNull(task.getResult()).isEmpty()) {
                                DocumentSnapshot punch = task.getResult().getDocuments().get(0);

                                lastPunchType = punch.get("type") == "IN" ? "IN" : "OUT";
                            }
                        }
                    }
                });
    }
}