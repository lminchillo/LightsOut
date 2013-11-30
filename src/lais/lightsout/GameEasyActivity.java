package lais.lightsout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GameEasyActivity extends Activity
{
	private int size = 5;
	private GridView gridView;
	private boolean[][] lights;
 
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_easy);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null)
		{
			size = extras.getInt("SIZE",5);
		}
		
		initialize();
	}
	
	private void initialize()
	{
		lights = new boolean[size][size];
		for (int i=0; i<size; i++)
		{
			for (int j=0; j<size; j++)
			{
				lights[i][j] = Math.random()>0.5;
			}
		}
		
		gridView = (GridView) findViewById(R.id.game_easy_gridview);
		gridView.setNumColumns(size);
		final LightAdapter adapter = new LightAdapter(getApplicationContext(), R.layout.gridview_item);
		
		gridView.setAdapter(adapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Log.d("Game","onItemClick");
				
				int j = position%size, i = (position-j)/size;
				
				lights[i][j] = !lights[i][j];
				if (i>0)		lights[i-1][j] = !lights[i-1][j];
				if (i<size-1)	lights[i+1][j] = !lights[i+1][j];
				if (j>0)		lights[i][j-1] = !lights[i][j-1];
				if (j<size-1)	lights[i][j+1] = !lights[i][j+1];
				
				adapter.notifyDataSetChanged();
				checkWin();
			}
		});
	}
	
	private void checkWin()
	{
		boolean win = true;
		for (int i=0; i<5; i++)
		{
			for (int j=0; j<5; j++)
			{
				if (lights[i][j])
				{
					win = false;
					break;
				}
			}
		}
		
		if (win)
		{
			Log.d("Game","win");
		}
	}
	
	class LightAdapter extends ArrayAdapter<Boolean>
	{
		Context ctx;
		int resourceId;
		
		public LightAdapter(Context context, int resource)
		{
			super(context, resource);
			ctx = context;
			resourceId = resource;
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
				convertView = LayoutInflater.from(ctx).inflate(resourceId,null);
			}
			ImageView img = (ImageView) convertView.findViewById(R.id.gridview_item_img);
			
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
