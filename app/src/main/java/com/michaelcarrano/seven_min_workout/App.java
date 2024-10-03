package com.michaelcarrano.seven_min_workout;

import android.app.Application;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.michaelcarrano.seven_min_workout.data.WorkoutContent;

import io.fabric.sdk.android.Fabric;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        loadWorkoutData();
    }

    private void loadWorkoutData() {
        Resources resources = getResources();
        final String[] workoutNames = resources.getStringArray(R.array.workout_names);
        final String[] workoutDescriptions = resources.getStringArray(R.array.workout_descriptions);
        final String[] workoutVideos = resources.getStringArray(R.array.workout_videos);
        final int[] darkColors = resources.getIntArray(R.array.darkColors);
        final int[] lightColors = resources.getIntArray(R.array.lightColors);

        if (WorkoutContent.WORKOUTS.isEmpty()) {
            try {
                for (int i = 0; i < workoutNames.length; i++) {
                    WorkoutContent.addWorkout(new WorkoutContent.Workout(
                            String.valueOf(i + 1),
                            workoutNames[i],
                            workoutDescriptions[i],
                            workoutVideos[i],
                            darkColors[i],
                            lightColors[i]
                    ));
                }
            } catch (Exception e) {
                Log.e("App", "Error loading workout data", e);
                Toast.makeText(this, "Failed to load workouts", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
