package wpam.recognizer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class channelOn extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_channel_on, container, false);
        TextView textOfshowChannelOn=(TextView) view.findViewById(R.id.TextOfShowChannelOn);
        textOfshowChannelOn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
        return view;
    }



}
