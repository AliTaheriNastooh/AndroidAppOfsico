package wpam.recognizer;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.support.v4.app.ActivityCompat;


public class ChooseActivity  extends Activity {
    private String phoneNumber="";
    ToggleButton simpleSwitch;
    LinearLayout getNumberOfChannel;
    Calling getCall;
    int state=-1;
    public static final String EXTRA_MESSAGE1 = "wpam.recognizer.ChooseActivity.MESSAGE";

    public static String EXTRA_MESSAGE;

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

        final TextView textOfChooseAmaliat=(TextView)findViewById(R.id.textofChooseAmaliat);
        textOfChooseAmaliat.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);

        getNumberOfChannel=(LinearLayout)findViewById(R.id.layoutOfGetChannelNumber);
        getNumberOfChannel.setVisibility(View.INVISIBLE);

        getCall=new Calling(this);

        NoDefaultSpinner spinner = (NoDefaultSpinner) findViewById(R.id.spinnerOfActivity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.Activity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                switch(position){
                    case 0:
                        TextView textOfGetChannelNumber= (TextView) findViewById(R.id.textViewOfGetChannelNumber);
                        state=1;
                        textOfGetChannelNumber.setText("شماره کانال مورد نظر برای تنظیم راوارد کرده و دکمه تماس را بفشارید:");
                        getNumberOfChannel.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        TextView textOfGetChannelNumber1= (TextView) findViewById(R.id.textViewOfGetChannelNumber);
                        state=2;
                        textOfGetChannelNumber1.setText("شماره کانال مورد نظر برای دریافت گزارش راوارد کرده و دکمه تماس را بفشارید:");
                        getNumberOfChannel.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        Intent intent1 = new Intent(getApplicationContext(), GetDetails.class);
                        String message = "configDevice";
                        intent1.putExtra(EXTRA_MESSAGE1, message);
                        startActivity(intent1);
                        break;
                    default:

                }

                Log.v("item........... :", (String) parent.getItemAtPosition(position)+"  switch : "+ simpleSwitch.isChecked());
            }


            public void onNothingSelected(AdapterView<?> parent) {
                Log.v("item........... :", "nothing Selected");
                // TODO Auto-generated method stub
            }
        });
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void CallButtonForGetChannel(View view){
        if(simpleSwitch.isChecked()){

        }else{
            if(state !=-1){
                EditText getChannelNumber=findViewById(R.id.editTextGetChannel);
                getCall.setCall(phoneNumber+","+state+","+getChannelNumber.getText().toString()+" ,*");
            }
        }
    }
//
//    public void setFragement(View view){
//        Bundle bundle = new Bundle();
//        String myMessage = "Stackoverflow is cool!";
//        bundle.putString("message", myMessage );
//        Fragment fragment ;
//        fragment=new getChannelNumberAndCall();
//        fragment.setArguments(bundle);
//        FragmentManager fm =getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
//        ft.replace(R.id.fragment,fragment);
//        ft.commit();
//    }

    ////////////////////////////////////////////////////////////////////////////


}
