package wpam.recognizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.TypedValue;
import android.view.View;
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



    }

    public void changeFragment(String desFragment){
        Bundle bundle = new Bundle();
        String myMessage = "Stackoverflow is cool!";
        bundle.putString("message", myMessage );
        Fragment fragment ;
        switch (desFragment){
            case "channel_on":
                break;
            case "channel_off":
                break;
            case "channel_state":
                break;
            default:
        }
        fragment.setArguments(bundle);
        FragmentManager fm =getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragment,fragment);
        ft.commit();
    }
}
