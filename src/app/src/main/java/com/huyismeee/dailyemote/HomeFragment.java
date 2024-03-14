package com.huyismeee.dailyemote;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    EditText editText;
    Button btnSave;
    List<String> calendarStrings;
    View dayContent;
    CalendarView calendarView;
    ImageView imageView;
    FloatingActionButton faBtn;
    RadioGroup radioEmotion, radioWeather;
    int emotion,weather;
    final int d = 3650;
    final int[] days = new int[d], months = new int[d], years = new int[d];
    private int currentYear = 0, currentMonth = 0, currentDay = 0, index = 0;

    private void bindingView(View v){
        editText = v.findViewById(R.id.editText);
        btnSave = v.findViewById(R.id.saveButton);
        calendarStrings = new ArrayList<>();
        dayContent = v.findViewById(R.id.LinearLayout);
        calendarView = v.findViewById(R.id.calendarView);
        imageView = v.findViewById(R.id.shape);
        faBtn = v.findViewById(R.id.camera);
        radioEmotion = v.findViewById(R.id.radioEmotion);
        radioWeather = v.findViewById(R.id.radioWeather);
    }

    private void bindingAction(){
        hideKeyboard();
        calendar();
        btnSave.setOnClickListener(this::onBtnClick);
        readData();
        faBtn.setOnClickListener(this::onBtnImageClick);
        getImage();
    }

    private void onBtnImageClick(View view) {
        try {
            ImagePicker.with(HomeFragment.this)
                    .crop()
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            try {
                Uri uri = data.getData();
                if (uri != null) {
                    imageView.setImageURI(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void getImage() {
        try {
            if (requireActivity().getActionBar() != null) {
                requireActivity().getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void onBtnNowClick(View view) {
        calendarView.setDate(calendarView.getDate());
    }

    private void onBtnClick(View view) {
        days[index] = currentDay; months[index] = currentMonth; years[index] = currentYear;
        calendarStrings.add(index, editText.getText().toString());
        editText.setText(" ");
        hideKeyboard();
        index++;
        dayContent.setVisibility(View.GONE);

    }

    private void calendar() {
        calendarView.setOnDateChangeListener((view, year, month, day) -> {
            currentDay = day;
            currentMonth = month;
            currentYear = year;
            if(dayContent.getVisibility() == View.GONE){
                dayContent.setVisibility(View.VISIBLE);
            }
            for(int h = 0; h < index; h++) {
                if (years[h] == currentYear) {
                    for (int i = 0; i < index; i++) {
                        if (days[i] == currentDay) {
                            for (int j = 0; j < index; j++) {
                                if (months[j] == currentMonth && days[j] == currentDay && years[j] == currentYear) {
                                    editText.setText(calendarStrings.get(j));
                                    hideKeyboard();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public void onPause(){
        super.onPause();
        saveData();;
    }

    private void saveData(){
        File file = new File(requireContext().getFilesDir(),"saved");
        File daysFile = new File(requireContext().getFilesDir(),"days");
        File monthsFile = new File(requireContext().getFilesDir(),"months");
        File yearsFile = new File(requireContext().getFilesDir(),"years");

        try {
            FileOutputStream fOut = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fOut));

            FileOutputStream fOutDays = new FileOutputStream(daysFile);
            BufferedWriter bwDays = new BufferedWriter(new OutputStreamWriter(fOutDays));

            FileOutputStream fOutMonths = new FileOutputStream(monthsFile);
            BufferedWriter bwMonths = new BufferedWriter(new OutputStreamWriter(fOutMonths));

            FileOutputStream fOutYears = new FileOutputStream(yearsFile);
            BufferedWriter bwYears = new BufferedWriter(new OutputStreamWriter(fOutYears));

            for(int i = 0; i < index; i++){
                bw.write(calendarStrings.get(i));
                bw.newLine();;
                bwDays.write(days[i]);
                bwMonths.write(months[i]);
                bwYears.write(years[i]);
            }

            bw.close();
            fOut.close();
            bwDays.close();
            fOutDays.close();
            bwMonths.close();
            fOutMonths.close();
            bwYears.close();
            fOutYears.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void readData(){
        File file = new File(requireContext().getFilesDir(),"saved");
        File daysFile = new File(requireContext().getFilesDir(),"days");
        File monthsFile = new File(requireContext().getFilesDir(),"months");
        File yearsFile = new File(requireContext().getFilesDir(),"years");

        if(!file.exists()){
            return;
        }

        try {
            FileInputStream iS = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
            FileInputStream iSDays = new FileInputStream(daysFile);
            BufferedReader readerDays = new BufferedReader(new InputStreamReader(iSDays));
            FileInputStream iSMonths = new FileInputStream(monthsFile);
            BufferedReader readerMonths = new BufferedReader(new InputStreamReader(iSMonths));
            FileInputStream iSYears = new FileInputStream(yearsFile);
            BufferedReader readerYears = new BufferedReader(new InputStreamReader(iSYears));

            int i = 0;
            String line = reader.readLine();

            while(line!= null){
                calendarStrings.add(line);
                line = reader.readLine();
                days[i] = readerDays.read();
                months[i] = readerMonths.read();
                years[i] = readerYears.read();
                i++;
            }
            index = i;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void hideKeyboard(){
        View view = requireActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindingView(view);
        bindingAction();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}