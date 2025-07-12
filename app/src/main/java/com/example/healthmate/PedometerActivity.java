package com.example.healthmate;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PedometerActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepSensor;
    private TextView stepCountView;
    private boolean isSensorPresent;
    private int stepCount = 0;
    private int initialStepCount = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);

        stepCountView = findViewById(R.id.stepCountView);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorPresent = true;
        } else {
            Toast.makeText(this, "No Step Counter Sensor!", Toast.LENGTH_SHORT).show();
            isSensorPresent = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (isSensorPresent && event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            if (initialStepCount == -1) {
                initialStepCount = (int) event.values[0];
            }
            stepCount = (int) event.values[0] - initialStepCount;
            runOnUiThread(() -> stepCountView.setText("Steps: " + stepCount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }
}