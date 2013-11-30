package lais.lightsout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
		easy.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(),GameActivity.class);
				intent.putExtra("SIZE",3);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
			}
		});
		
		Button medium = (Button) findViewById(R.id.main_medium);
		medium.setTypeface(Typeface.createFromAsset(getAssets(), "Existence-Light.ttf"),Typeface.BOLD);
		medium.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(),GameActivity.class);
				intent.putExtra("SIZE",5);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
			}
		});
		
		Button hard = (Button) findViewById(R.id.main_hard);
		hard.setTypeface(Typeface.createFromAsset(getAssets(), "Existence-Light.ttf"),Typeface.BOLD);
		hard.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(),GameActivity.class);
				intent.putExtra("SIZE",7);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
			}
		});
	}
}
