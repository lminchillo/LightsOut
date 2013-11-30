package lais.lightsout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GameEasyActivity extends Activity
{
	private GridView gridView;
	private boolean[][] lights = new boolean[5][5];
 
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_easy);
		
		initialize();
	}
	
	private void initialize()
	{
		for (int i=0; i<5; i++)
		{
			for (int j=0; j<5; j++)
			{
				lights[i][j] = Math.random()>0.5;
			}
		}
		
		gridView = (GridView) findViewById(R.id.gridView1);
		
		LightAdapter adapter = new LightAdapter(getApplicationContext(), 0);
		
		gridView.setAdapter(adapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Log.d("Game","onItemClick");
				
				int j = position%5, i = (position-j)/5;
				
				ImageView b = (ImageView) view;
				
				if (lights[i][j])
				{
					b.setImageDrawable(getResources().getDrawable(R.drawable.button_off));
				}
				else
				{
					b.setImageDrawable(getResources().getDrawable(R.drawable.button_on));
				}
				lights[i][j] = !lights[i][j];
			}
		});
	}
	
	class LightAdapter extends ArrayAdapter<Boolean>
	{
		Context ctx;
		
		public LightAdapter(Context context, int resource)
		{
			super(context, resource);
			ctx = context;
		}
		
		@Override
		public int getCount()
		{
			return 25;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			if (convertView == null)
			{
				convertView = new ImageView(ctx);
				//convertView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			}
			ImageView img = (ImageView) convertView;
			
			int j = position%5, i = (position-j)/5;
			if (lights[i][j])
			{
				img.setImageDrawable(getResources().getDrawable(R.drawable.button_on));
			}
			else
			{
				img.setImageDrawable(getResources().getDrawable(R.drawable.button_off));
			}
			
			return convertView;
		}
	}
}
