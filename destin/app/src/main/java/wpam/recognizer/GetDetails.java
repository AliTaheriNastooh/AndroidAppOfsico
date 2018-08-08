package wpam.recognizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GetDetails extends FragmentActivity {
    String whichFragment="";
    int a=0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_details);
        TextView textOfToggleButton=(TextView)findViewById(R.id.textViewOfToogleButton2);
        textOfToggleButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f);

        Intent intent = getIntent();
        whichFragment= intent.getStringExtra(ChooseActivity.EXTRA_MESSAGE1);
        changeFragment(whichFragment);


    }

    public void changeFragment(String desFragment){
        Bundle bundle = new Bundle();
        String myMessage = "";

        Fragment fragment=new backgroundFragment() ;
        if(desFragment.equals("Channeloff")){
            fragment=new Channel_Off();
        }
        if(desFragment.equals("Channelon")){
            fragment=new channelOn();
        }
        if(desFragment.equals("ShowChannelStateOff")){
            fragment=new showChannelState();
            myMessage = "کانال خاموش است";
            LinearLayout l1=(LinearLayout) findViewById(R.id.LinearLayoutOfSetChange);
            l1.setVisibility(View.INVISIBLE);
            LinearLayout l2=(LinearLayout) findViewById(R.id.linearLayoutOfHowConnectWithDevice);
            l2.setVisibility(View.INVISIBLE);
        }
        if(desFragment.equals("ShowChannelStateOn")){
            fragment=new showChannelState();
            myMessage = "کانال روشن است";
            LinearLayout l1=(LinearLayout) findViewById(R.id.LinearLayoutOfSetChange);
            l1.setVisibility(View.INVISIBLE);
            LinearLayout l2=(LinearLayout) findViewById(R.id.linearLayoutOfHowConnectWithDevice);
            l2.setVisibility(View.INVISIBLE);
        }

        bundle.putString("message", myMessage );
        fragment.setArguments(bundle);
        FragmentManager fm =getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragment,fragment);
        ft.commit();
    }
}
