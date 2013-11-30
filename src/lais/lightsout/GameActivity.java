package lais.lightsout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends Activity
{
	private int size = 5;
	private GridView gridView;
	private boolean[][] lights;
 
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		overridePendingTransition(0, 0);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null)
		{
			size = extras.getInt("SIZE",5);
		}
		
		initialize();
	}
	
	private void initialize()
	{
		TextView title = (TextView) findViewById(R.id.game_title);
		title.setTypeface(Typeface.createFromAsset(getAssets(), "Existence-Light.ttf"),Typeface.BOLD);
		
		Button newGame = (Button) findViewById(R.id.game_new);
		newGame.setTypeface(Typeface.createFromAsset(getAssets(), "Existence-Light.ttf"),Typeface.BOLD);
		newGame.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(),GameActivity.class);
				intent.putExtra("SIZE",size);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
				finish();
				overridePendingTransition(0, 0);
			}
		});
		
		Button exit = (Button) findViewById(R.id.game_exit);
		exit.setTypeface(Typeface.createFromAsset(getAssets(), "Existence-Light.ttf"),Typeface.BOLD);
		exit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
				overridePendingTransition(0, 0);
			}
		});
		
		lights = new boolean[size][size];
		for (int i=0; i<size; i++)
		{
			for (int j=0; j<size; j++)
			{
				//lights[i][j] = Math.random()>0.5;
				if(Math.random()>0.5)
				{
					lights[i][j] = !lights[i][j] ;
					if (i>0)	 	lights[i-1][j] = !lights[i-1][j];
					if (i<size-1)	lights[i+1][j] = !lights[i+1][j];
					if (j>0)	 	lights[i][j-1] = !lights[i][j-1];
					if (j<size-1)	lights[i][j+1] = !lights[i][j+1];
				}
			}
		}
		
		gridView = (GridView) findViewById(R.id.game_gridview);
		
		// Adjust the gridview size
		LayoutParams lp = gridView.getLayoutParams();
		final float scale = getResources().getDisplayMetrics().density;
		int layoutSize = (int) (50*size * scale + 0.5f);
		lp.width = layoutSize;
		lp.height = layoutSize;
		gridView.setLayoutParams(lp);
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
		for (int i=0; i<size; i++)
		{
			for (int j=0; j<size; j++)
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
			return size*size;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			if (convertView == null)
			{
				convertView = LayoutInflater.from(ctx).inflate(resourceId,null);
			}
			ImageView img = (ImageView) convertView.findViewById(R.id.gridview_item_img);
			
			int j = position%size, i = (position-j)/size;
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
