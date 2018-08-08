package wpam.recognizer;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
public class SMS_Receiver extends BroadcastReceiver {
    private static final String TAG = SMS_Receiver.class.getSimpleName();
    public static final String pdu_type = "pdus";
    private Context mContext;

    public SMS_Receiver(){

    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the SMS message.
        mContext=context;
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;
        String strMessage = "";
        String format = bundle.getString("format");
        // Retrieve the SMS message received.
        String phoneNumber="";
        String mMessage="";
        Object[] pdus = (Object[]) bundle.get(pdu_type);
        if (pdus != null) {
            // Check the Android version.
            boolean isVersionM = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M);
            // Fill the msgs array.
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                // Check Android version and use appropriate createFromPdu.
                if (isVersionM) {
                    // If Android version M or newer:
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                } else {
                    // If Android version L or older:
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                // Build the message to show.
                phoneNumber=msgs[i].getOriginatingAddress();
                mMessage=msgs[i].getMessageBody();
                strMessage += "SMS from " + msgs[i].getOriginatingAddress();
                strMessage += " :" + msgs[i].getMessageBody() + "\n";
                // Log and display the SMS message.
                Log.d(TAG, "onReceive: " + strMessage);
                Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show();
                setRecievedSMS(phoneNumber,mMessage);
            }
        }

    }
    public void setRecievedSMS(String phoneNumberOfSMS,String messageOfSMS){
        String parseGetPhoneNumber=phoneNumberOfSMS.substring(5);
        String parsePhoneNumber=ChooseActivity.phone.substring(3);

        if(parsePhoneNumber.equals(parseGetPhoneNumber)){
            String sChannelNumber=ChooseActivity.numberOfChannel;
            boolean flag=false;
            int i=0;
            for(i=0;i<sChannelNumber.length();i++){
                if(sChannelNumber.charAt(i)==messageOfSMS.charAt(i)){
                    flag=true;
                }else{
                    flag=false;
                }
            }
            if(messageOfSMS.charAt(i)=='*' && flag){
                goToNextPage(messageOfSMS.substring(i+1));
            }
        }

    }
    public void goToNextPage(String channelState){
        if(ChooseActivity.state==1){
            if(channelState.equals("0")){
                setIntentToNextPage("Channeloff");
            }
            if(channelState.equals("1")){
                setIntentToNextPage("Channelon");
            }
        }
        if(ChooseActivity.state==2){
            if(channelState.equals("0")){
                setIntentToNextPage("ShowChannelStateOff");
            }
            if(channelState.equals("1")){
                setIntentToNextPage("ShowChannelStateOn");
            }
        }

    }
    public void setIntentToNextPage(String sMessage){
        Intent intent1 = new Intent(mContext, GetDetails.class);
        String message = sMessage;
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.putExtra(ChooseActivity.EXTRA_MESSAGE1, message);
        mContext.startActivity(intent1);
    }
}
