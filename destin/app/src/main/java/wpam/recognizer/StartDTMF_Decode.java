package wpam.recognizer;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import android.widget.Toast;
public class StartDTMF_Decode extends Activity  {
    Controller controller;
    private String recognizeredText;


    public StartDTMF_Decode(){
        controller = new Controller(this);
        recognizeredText = "";
    }
    private static final int REQUEST_CODE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = intent.getData();
                String[] projection = { ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME };

                Cursor cursor = getContentResolver().query(uri, projection,
                        null, null, null);
                cursor.moveToFirst();

                int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberColumnIndex);

				/*int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
				String name = cursor.getString(nameColumnIndex);*/
                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number));
                startActivity(dialIntent);

            }
        }
    };
    public void startDecode()
    {
        recognizeredText = "";
        controller.changeState();
    }

    public void stopDecode()
    {
        controller.changeState();
    }
    public String getRecognizeredText(){
        return recognizeredText;
    }

    public void showToast(String s){
        Toast.makeText(StartDTMF_Decode.this,s,Toast.LENGTH_LONG).show();
    }
    public void clearText()
    {
        recognizeredText = "";
    }

    public void addText(Character c)
    {
        //Toast.makeText(this,"character: "+c, Toast.LENGTH_LONG).show();
        recognizeredText += c;
    }



    @Override
    protected void onDestroy()
    {

        super.onDestroy();
    }

    @Override
    protected void onPause()
    {

        super.onPause();
    }
}
