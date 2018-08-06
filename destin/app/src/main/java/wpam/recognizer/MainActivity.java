package wpam.recognizer;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends Activity {
    StartDTMF_Decode decoder;
    EditText recognizeText;

    private static final String TAG = MainActivity.class.getSimpleName();

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