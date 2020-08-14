package com.pdm.shifter.Fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.pdm.shifter.R;
import com.pdm.shifter.ViewModel.ShareViewModel;
import com.pdm.shifter.databinding.ShareFragmentBinding;

import java.util.UUID;

public class ShareFragment extends Fragment {

    private ShareFragmentBinding binding;
    private ShareViewModel mViewModel;

    public static ShareFragment newInstance() {
        return new ShareFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ShareFragmentBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(ShareViewModel.class);

        binding.lblSharingCodeTitle.setVisibility(View.GONE);
        binding.editTextCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Sharing code", ((EditText) view).getText());
                assert clipboard != null;
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getContext(), R.string.copied_to_clipboard, Toast.LENGTH_SHORT).show();
            }
        });

        binding.sharingToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    compoundButton.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary));
                    compoundButton.setTextColor(Color.WHITE);

                    binding.lblSharingCodeTitle.setVisibility(View.VISIBLE);

                    binding.editTextCode.setText(UUID.randomUUID().toString());
                    binding.editTextCode.setVisibility(View.VISIBLE);
                } else {
                    compoundButton.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.colorSecondary));
                    compoundButton.setTextColor(Color.BLACK);

                    binding.lblSharingCodeTitle.setVisibility(View.GONE);

                    binding.editTextCode.setText(UUID.randomUUID().toString());
                    binding.editTextCode.setVisibility(View.GONE);
                }
            }
        });

        return binding.getRoot();
    }


}