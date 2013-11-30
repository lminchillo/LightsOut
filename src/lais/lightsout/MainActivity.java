package lais.lightsout;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initialize();
	}
	
	private void initialize()
	{
		TextView title = (TextView) findViewById(R.id.main_title);
		title.setTypeface(Typeface.createFromAsset(getAssets(), "Existence-Light.ttf"),Typeface.BOLD);
		
		Button easy = (Button) findViewById(R.id.main_easy);
		easy.setTypeface(Typeface.createFromAsset(getAssets(), "Existence-Light.ttf"),Typeface.BOLD);
		
		Button medium = (Button) findViewById(R.id.main_medium);
		medium.setTypeface(Typeface.createFromAsset(getAssets(), "Existence-Light.ttf"),Typeface.BOLD);
		
		Button hard = (Button) findViewById(R.id.main_hard);
		hard.setTypeface(Typeface.createFromAsset(getAssets(), "Existence-Light.ttf"),Typeface.BOLD);
		
	}
}
