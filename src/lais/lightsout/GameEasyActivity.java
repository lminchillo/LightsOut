package lais.lightsout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class GameEasyActivity extends Activity
{
	private boolean light = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_easy);
		
		initialize();
	}
	
	private void initialize()
	{
		final ImageButton button = (ImageButton) findViewById(R.id.game_easy_button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				if (light)
				{
					button.setImageDrawable(getResources().getDrawable(R.drawable.button_off));
				}
				else
				{
					button.setImageDrawable(getResources().getDrawable(R.drawable.button_on));
				}
				light = !light;
			}
		});
	}
}
