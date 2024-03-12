package com.huyismeee.dailyemote;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.huyismeee.dailyemote.database.TaskTimer;
import com.huyismeee.dailyemote.database.TaskTimerDao;

import java.util.ArrayList;
import java.util.Locale;


public class TimerFragment extends Fragment {

    private EditText editTextTime, editTextTaskName, editTextTaskTime;
    private Button buttonStart, buttonPause, buttonReset, buttonAddTask;
    private Spinner taskSpinner;
    private TextView textViewTimer;
    private ArrayList<TaskTimer> taskList;
    private TaskSpinnerAdapter taskAdapter;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private boolean timerRunning;

    private void bindingView(View v){
        editTextTime = v.findViewById(R.id.editTextTime);
        buttonStart = v.findViewById(R.id.buttonStart);
        buttonPause = v.findViewById(R.id.buttonPause);
        buttonReset = v.findViewById(R.id.buttonReset);
        editTextTaskName = v.findViewById(R.id.editTextTaskName);
        editTextTaskTime = v.findViewById(R.id.editTextTaskTime);
        buttonAddTask = v.findViewById(R.id.buttonAddTask);
        taskSpinner = v.findViewById(R.id.taskSpinner);
        textViewTimer = v.findViewById(R.id.textViewTimer);
        taskList = new ArrayList<>();
        taskAdapter = new TaskSpinnerAdapter(getContext(), taskList);
        taskSpinner.setAdapter(taskAdapter);
    }

    private void bindingAction(View v){
        taskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TaskTimer selectedTask = (TaskTimer) parent.getItemAtPosition(position);
                editTextTime.setText(String.valueOf(selectedTask.getDuration()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindingView(view);
        bindingAction(view);

    }
    private void startTimer() {
        String input = editTextTime.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(getContext(), "Please enter time", Toast.LENGTH_SHORT).show();
            return;
        }

        if (taskSpinner.getSelectedItem() == null) {
            Toast.makeText(getContext(), "Please add a task", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lấy task được chọn từ Spinner
        TaskTimer selectedTask = (TaskTimer) taskSpinner.getSelectedItem();

        long millisInput = selectedTask.getDuration() * 60000; // Chuyển đổi phút thành mili giây
        if (millisInput == 0) {
            Toast.makeText(getContext(), "Please enter a positive number", Toast.LENGTH_SHORT).show();
            return;
        }

        timeLeftInMillis = millisInput;
        startCountDown();
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                buttonStart.setText("Start");
                buttonPause.setVisibility(View.GONE);
                buttonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        timerRunning = true;
        buttonStart.setText("Pause");
        buttonReset.setVisibility(View.GONE);
        buttonPause.setVisibility(View.VISIBLE);
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        buttonStart.setText("Start");
        buttonPause.setVisibility(View.GONE);
        buttonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        timeLeftInMillis = 0;
        updateCountDownText();
        timerRunning = false;
        buttonStart.setText("Start");
        buttonReset.setVisibility(View.GONE);
        buttonPause.setVisibility(View.GONE);
    }

    private void updateCountDownText() {
        int hours = (int) (timeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((timeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
        textViewTimer.setText(timeLeftFormatted);
    }

    private void addTask() {
        String taskName = editTextTaskName.getText().toString().trim();
        String taskTime = editTextTaskTime.getText().toString().trim();

        if (taskName.isEmpty() || taskTime.isEmpty()) {
            Toast.makeText(getContext(), "Please enter task details", Toast.LENGTH_SHORT).show();
            return;
        }

        TaskTimer task = new TaskTimer(taskName, Long.parseLong(taskTime), 2);
        taskList.add(task);
        taskAdapter.notifyDataSetChanged();

        editTextTaskName.getText().clear();
        editTextTaskTime.getText().clear();
    }
}