package wpam.recognizer;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class getChannelNumberAndCall extends Fragment {


    public getChannelNumberAndCall() {
        // Required empty public constructor
    }
    public static getChannelNumberAndCall newInstance(String index) {
        getChannelNumberAndCall f = new getChannelNumberAndCall();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("index", index);
        f.setArguments(args);
        return f;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_get_channel_number_and_call, container, false);
        TextView me=(TextView)view.findViewById(R.id.textViewOfGetChannelNumber);
        Bundle args = getArguments();
        String index = args.getString("message", "");
        me.setText(index);
        return view;
    }




}
