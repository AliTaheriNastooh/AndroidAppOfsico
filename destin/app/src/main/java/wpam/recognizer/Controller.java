package wpam.recognizer;

import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Controller 
{
	private boolean started=false;
	
	private RecordTask recordTask;	
	private RecognizerTask recognizerTask;	
	private StartDTMF_Decode mainActivity;
	BlockingQueue<DataBlock> blockingQueue;

	private Character lastValue;
		
	public Controller(StartDTMF_Decode mainActivity)
	{
		this.mainActivity = mainActivity;
	}

	public void changeState() 
	{
		if (started == false)
		{
			
			lastValue = ' ';
			
			blockingQueue = new LinkedBlockingQueue<DataBlock>();
			
			//mainActivity.start();
			Log.i("changeState",":in started==false1");
			recordTask = new RecordTask(this,blockingQueue);
			Log.i("changeState",":in started==false2");
			recognizerTask = new RecognizerTask(this,blockingQueue);
			Log.i("changeState",":in started==false3");
			recordTask.execute();
			Log.i("changeState",":in started==false5");
			recognizerTask.execute();
			Log.i("changeState",":in started==false6");
			started = true;
		} else {
			
			//mainActivity.stop();
			
			recognizerTask.cancel(true);
			recordTask.cancel(true);
			
			started = false;
		}
	}

	public void clear() {
		mainActivity.clearText();
	}

	public boolean isStarted() {
		return started;
	}


	


	public void keyReady(char key) 
	{

		
		if(key != ' ')
			if(lastValue != key)
				mainActivity.addText(key);
		
		lastValue = key;
	}
	public void showToast(String s){
		mainActivity.showToast(s);
	}

}
