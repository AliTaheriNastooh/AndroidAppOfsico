package wpam.recognizer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ChooseActivity extends Activity {
    private String phoneNumber="";
    ToggleButton simpleSwitch;
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

               Intent intent = getIntent();
        phoneNumber = intent.getStringExtra(MainActivity.EXTRA_MESSAGE1);
        TextView showNumber=(TextView)findViewById(R.id.textView2);
        showNumber.setText(" شماره تماس سیستم شما: "+phoneNumber);
        showNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);

        simpleSwitch = (ToggleButton) findViewById(R.id.simpleSwitch);

        TextView textOfToggleButton=(TextView)findViewById(R.id.textViewOfToogleButton);
        textOfToggleButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f);

        TextView textOfChooseAmaliat=(TextView)findViewById(R.id.textofChooseAmaliat);
        textOfChooseAmaliat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);

        NoDefaultSpinner spinner = (NoDefaultSpinner) findViewById(R.id.spinnerOfActivity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.Activity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {



// check current state of a Switch (true or false).
               // Boolean switchState = simpleSwitch.isChecked();
                Log.v("item........... :", (String) parent.getItemAtPosition(position)+"  switch : "+ simpleSwitch.isChecked());
            }


            public void onNothingSelected(AdapterView<?> parent) {
                Log.v("item........... :", "nothing Selected");
                // TODO Auto-generated method stub
            }
        });
    }

}
