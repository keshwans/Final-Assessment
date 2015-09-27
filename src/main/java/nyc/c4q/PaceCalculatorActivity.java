package nyc.c4q;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PaceCalculatorActivity extends FragmentActivity {

    EditText inputDistanceET;
    EditText inputTimeMinET;
    EditText inputTimeSecET;
    EditText inputPaceMinET;
    EditText inputPaceSecET;
    Button calcButton = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pace_calculator);
    }
}

///* SK V1 */
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pace_calculator);
//
//        inputDistanceET = (EditText) findViewById(R.id.input_distance);
//        inputTimeMinET = (EditText) findViewById(R.id.input_time_min);
//        inputTimeSecET = (EditText) findViewById(R.id.input_time_sec);
//        inputPaceMinET = (EditText) findViewById(R.id.input_pace_min);
//        inputPaceSecET = (EditText) findViewById(R.id.input_pace_sec);
//
//        calcButton = (Button) findViewById(R.id.button_calculate);
//
//        calcButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                calculate();
//            }
//        });
//    }
//
//    private void calculate() {
//        float inputDistance = 0;
//        int inputTimeMin = 0;
//        int inputTimeSec = 0;
//        int inputPaceMin = 0;
//        int inputPaceSec = 0;
//
//        try {
//            inputDistance = Float.parseFloat(inputDistanceET.getText().toString());
//        } catch (Exception e) {
//            Log.e("calc", "distance: " + e.getMessage());
//        }
//
//        try {
//            inputTimeMin = Integer.parseInt(inputTimeMinET.getText().toString());
//        } catch (Exception e) {
//            Log.e("calc", "inputTimeMin: " + e.getMessage());
//        }
//
//        try {
//            inputTimeSec = Integer.parseInt(inputTimeSecET.getText().toString());
//        } catch (Exception e) {
//            Log.e("calc", "inputTimeSec: " + e.getMessage());
//        }
//
//        try {
//            inputPaceMin = Integer.parseInt(inputPaceMinET.getText().toString());
//        } catch (Exception e) {
//            Log.e("calc", "inputPaceMin: " + e.getMessage());
//        }
//
//        try {
//            inputPaceSec = Integer.parseInt(inputPaceSecET.getText().toString());
//        } catch (Exception e) {
//            Log.e("calc", "inputPaceSec: " + e.getMessage());
//        }
//
//        float totalTimeSec = 0;
//        float totalPaceSec = 0;
//
//        if (inputTimeMin > 0 || inputTimeSec > 0) {
//            if (inputTimeMin > 0) {
//                totalTimeSec = inputTimeMin * 60;
//            }
//            if (inputTimeSec > 0) {
//                totalTimeSec += inputTimeSec;
//            }
//        }
//        if (inputPaceMin > 0 || inputPaceSec > 0) {
//            totalPaceSec = inputPaceMin * 60;
//
//            if (inputPaceMin > 0) {
//                totalPaceSec = inputPaceMin * 60;
//            }
//            if (inputPaceSec > 0) {
//                totalPaceSec += inputPaceSec;
//            }
//        }
//
//        if (inputDistance > 0 && totalTimeSec > 0) {
//
//            inputPaceSec = (int) (totalTimeSec / inputDistance);
//            inputPaceMin = inputPaceSec / 60;
//            inputPaceSec = inputPaceSec - (60 * inputPaceMin);
//
//            inputPaceMinET.setText(inputPaceMin > 0 ? inputPaceMin + "" : "");
//            inputPaceSecET.setText(inputPaceSec > 0 ? inputPaceSec + "" : "");
//
//
//        } else if (inputDistance > 0 && totalPaceSec > 0) {
//            inputTimeSec = (int) (inputDistance * totalPaceSec);
//            inputTimeMin = (int) (inputTimeSec / 60);
//            inputTimeSec = inputTimeSec - (60 * inputTimeMin);
//            inputTimeMinET.setText(inputTimeMin > 0 ? inputTimeMin + "" : "");
//            inputTimeSecET.setText(inputTimeSec > 0 ? inputTimeSec + "" : "");
//
//        } else if (totalTimeSec > 0 && totalPaceSec > 0) {
//            inputDistance = totalTimeSec / totalPaceSec;
//            inputDistanceET.setText(inputDistance > 0 ? inputDistance + "" : "");
//        }
//    }
//}
