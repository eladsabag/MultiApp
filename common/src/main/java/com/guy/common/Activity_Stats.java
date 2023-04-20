package com.guy.common;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textview.MaterialTextView;
import com.guy.common.utils.Constants;

public class Activity_Stats extends AppCompatActivity {
    private double convertResult;
    private MaterialTextView stats_LBL_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        readExtrasFromIntent();
        findViews();
        updateUI();
    }

    private void readExtrasFromIntent() {
        convertResult = getIntent().getDoubleExtra(Constants.CONVERT_RESULT, 0.0);
    }

    private void updateUI() {

        stats_LBL_save.setText(String.valueOf(convertResult));
    }

    private void findViews() {
        stats_LBL_save = findViewById(R.id.stats_LBL_result);
    }
}