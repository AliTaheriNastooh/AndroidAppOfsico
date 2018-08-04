package wpam.recognizer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends Activity {
    StartDTMF_Decode decoder;
    EditText recognizeText;
    public static final String EXTRA_MESSAGE1 = "com.example.myfirstapp.MESSAGE";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView company=(TextView)findViewById(R.id.textCompany);
        company.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);

    }
    public void startTapped(View view){
        Intent intent1 = new Intent(this, ChooseActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText1);
        String message = editText.getText().toString();
        intent1.putExtra(EXTRA_MESSAGE1, message);
        startActivity(intent1);
    }


}