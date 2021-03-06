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
import android.text.Editable;
import android.text.TextWatcher;
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
    private ToggleButton simpleSwitch;
    private LinearLayout getNumberOfChannel;
    private Calling getCall;
    private SendSMS mySender_sms;
    private static final int REQUEST_CODE = 1;
    private StartDTMF_Decode dtmf_decode;

    public  static int state=-1;
    public static final String EXTRA_MESSAGE1 = "wpam.recognizer.ChooseActivity.MESSAGE";
    public static String EXTRA_MESSAGE;
    public static String phone;
    public static String numberOfChannel;
    //////////////////////////////////////////////

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

       //SMS_Receiver sms_receiver=new SMS_Receiver(this);
        mySender_sms=new SendSMS(this);

        dtmf_decode=new StartDTMF_Decode(this);
        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra(MainActivity.EXTRA_MESSAGE1);
        TextView showNumber=(TextView)findViewById(R.id.textView2);
        showNumber.setText(" شماره تماس سیستم شما: "+phoneNumber);
        showNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
        phone=phoneNumber;
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
                        state=3;
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
        EditText ChannelNumber=(EditText)findViewById(R.id.editTextGetChannel);
        ChannelNumber.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void afterTextChanged(Editable editable) {
                numberOfChannel=editable.toString();
                Log.i("me-------:",numberOfChannel);

            }
        });
    }
    //////////////////////////////////////////////
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void CallButtonForGetChannel(View view){

        String setMessage="";
        EditText getChannelNumber=(EditText)findViewById(R.id.editTextGetChannel);
        if(simpleSwitch.isChecked()){
            Button button=(Button)findViewById(R.id.buttonofCallDevice);
            button.setEnabled(false);
            button.setText("لطفامنتظر بمانید");
            setMessage=state+"*"+getChannelNumber.getText().toString()+"*";
        }else{
            setMessage=","+state+","+getChannelNumber.getText().toString()+" ,*";
        }


        connectWithDevice(simpleSwitch.isChecked(),setMessage);
    }
    //////////////////////////////////////////////
    public void connectWithDevice(boolean call_SMS,String sendMessage){
        if(call_SMS){

            mySender_sms.smsSendMessage(phoneNumber,sendMessage);
        }else{
            if(state !=-1){
                dtmf_decode.startDecode();
                getCall.setCall(phoneNumber+sendMessage);

            }
        }
    }
    //////////////////////////////////////////////
    public void setIntentToNextPage(String sMessage){
        Intent intent1 = new Intent(this, GetDetails.class);
        String message = sMessage;
        intent1.putExtra(EXTRA_MESSAGE1, message);
        startActivity(intent1);
    }

    //////////////////////////////////////////////
    public void getRecognizeDTMF(){
        Toast.makeText(this,"text"+ dtmf_decode.getRecognizeredText(),Toast.LENGTH_SHORT).show();
        Log.i("recognize Text----:", dtmf_decode.getRecognizeredText());
        dtmf_decode.stopDecode();
        }

    //////////////////////////////////////////////

    //////////////////////////////////////////////
    public void goToNextPage(String channelState){
     if(state==1){
        if(channelState.equals("0")){
            setIntentToNextPage("Channeloff");
        }
         if(channelState.equals("1")){
             setIntentToNextPage("Channelon");
         }
     }
     if(state==2){
         if(channelState.equals("0")){
             setIntentToNextPage("ShowChannelStateOff");
         }
         if(channelState.equals("1")){
             setIntentToNextPage("ShowChannelStateOn");
         }
     }

    }
    //////////////////////////////////////////////

    //////////////////////////////////////////////

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
