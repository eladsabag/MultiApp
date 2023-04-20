package com.guy.common;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.guy.common.utils.Constants;
import com.guy.common.utils.ConverterUtil;

public abstract class Activity_HomeParent extends AppCompatActivity {
    private MaterialTextView home_LBL_from;
    private ImageView home_IMG_replace;
    private MaterialButton home_BTN_convert;
    private boolean isFirstTitle = true;
    private boolean isHeightOrWeight; // true for height, false for weight
    private EditText home_EDT_from;

    protected abstract String getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        isHeightOrWeight = getData().equals("height");
        findViews();
        initViews();
    }

    private void initViews() {
        home_LBL_from.setText(isHeightOrWeight ? getString(R.string.from_cm_to_feet) : getString(R.string.from_kg_to_lbs));
        home_BTN_convert.setOnClickListener(v -> onConvertClicked());
        home_IMG_replace.setOnClickListener(v -> onReplaceClicked());
    }

    private void onReplaceClicked() {
        isFirstTitle = !isFirstTitle;
        String title = isFirstTitle ?
                (isHeightOrWeight ? getString(R.string.from_cm_to_feet) : getString(R.string.from_kg_to_lbs)) :
                (isHeightOrWeight ? getString(R.string.from_feet_to_cm) : getString(R.string.from_lbs_to_kg));
        home_LBL_from.setText(title);
    }

    private void onConvertClicked() {
        Editable from = home_EDT_from.getText();
        if (from != null && !from.toString().isEmpty()) {
            try {
                double fromVal = Double.parseDouble(from.toString());
                double result = getConvertResult(fromVal);
                if (result != -1)
                    startStatsActivity(result);
                else
                    Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private double getConvertResult(double fromVal) {
        int method = isFirstTitle ? // 0 for cm to feet, 1 for kg to lbs, 2 for feet to cm, 3 for lbs to kg
                (isHeightOrWeight ? 0 : 1) :
                (isHeightOrWeight ? 2 : 3);

        switch (method) {
            case 0:
                return ConverterUtil.cmToFeet(fromVal);
            case 1:
                return ConverterUtil.kgToLbs(fromVal);
            case 2:
                return ConverterUtil.feetToCm(fromVal);
            case 3:
                return ConverterUtil.lbsToKg(fromVal);
            default:
                return -1;
        }
    }

    private void startStatsActivity(double method) {
        Intent intent = new Intent(this, Activity_Stats.class);
        intent.putExtra(Constants.CONVERT_RESULT, method);
        startActivity(intent);
    }

    private void findViews() {
        home_IMG_replace = findViewById(R.id.home_IMG_replace);
        home_LBL_from = findViewById(R.id.home_LBL_from);
        home_EDT_from = findViewById(R.id.home_EDT_from);
        home_BTN_convert = findViewById(R.id.home_BTN_convert);
    }
}