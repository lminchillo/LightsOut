package lais.lightsout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.AdapterView;

public class GameEasyActivity extends Activity
{
	
	GridView gridView;
	
	static final String[] numbers = new String[] { 
		"1", "1", "0",
		"0", "1", "0",
		"0", "0", "1"};
	 
	/*static final bool[][] numbers = new bool[][]{
	{false, false, true},
	{true, true, true},
	{false, true, false} 
	};*/
	
	/*static final String[] numbers = new String[] { 
			"A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z"};*/
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_easy);
		
		initialize();
	}

	private boolean light = false;
	
	private void initialize()
	{
		gridView = (GridView) findViewById(R.id.gridView1);
		 
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, numbers);
 
		gridView.setAdapter(adapter);
 
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
				int position, long id) {
			   Toast.makeText(getApplicationContext(),
				((TextView) v).getText(), Toast.LENGTH_SHORT).show();
			}
		});
		
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
