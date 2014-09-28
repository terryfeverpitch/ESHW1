package com.ESHW1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class ESHW1Activity extends Activity {
    /** Called when the activity is first created. */
	private String[] colors    = {"Red",     "Yellow",  "Green",   "Cyan",    "Blue",    "Magenta", "White",   "Black"};
	private String[] hexColors = {"#FF0000", "#FFFF00", "#00FF00", "#00FFFF", "#0000FF", "#FF00FF", "#FFFFFF", "#000000"};
	private String ui_text = "Hello World, Hello 410006128.";
	private int    ui_size = 40;
	private int    ui_foreground = 0; // red
	private int    ui_background = 6; // white
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // get textView component
        TextView textView = (TextView) findViewById(R.id.textView_msg); 
        LinearLayout view = (LinearLayout) findViewById(R.id.view);
        // set default text
        textView.setText(ui_text);
        // set font size   
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, ui_size);
        // set foreground color
        textView.setTextColor(Color.parseColor(hexColors[ui_foreground]));
        // set background color
        view.setBackgroundColor(Color.parseColor(hexColors[ui_background]));
        
        Button btn_style = (Button) findViewById(R.id.btn_style);
        btn_style.setOnClickListener(btnHandler);
    }
    
    View.OnClickListener btnHandler = new View.OnClickListener() {
        public void onClick(View v) {
        	LayoutInflater inflater = getLayoutInflater();
        	View layout = inflater.inflate(R.layout.dialog, (ViewGroup) findViewById(R.layout.dialog));
        	AlertDialog.Builder dialog = new AlertDialog.Builder(ESHW1Activity.this);
        	
        	ArrayAdapter<String> colorList = new ArrayAdapter<String>(ESHW1Activity.this, android.R.layout.simple_spinner_item, colors);
        	colorList.setDropDownViewResource(android.R.layout.select_dialog_item);
        	
        	final Spinner spinner_foreground = (Spinner)layout.findViewById(R.id.spinner_foreground);    
            spinner_foreground.setAdapter(colorList);
            spinner_foreground.setSelection(ui_foreground);
            
            final Spinner spinner_background = (Spinner)layout.findViewById(R.id.spinner_background);    
            spinner_background.setAdapter(colorList);
            spinner_background.setSelection(ui_background);
            
            final EditText editText_text     = (EditText) layout.findViewById(R.id.editText_text);
            final EditText editText_fontSize = (EditText) layout.findViewById(R.id.editText_fontSize);
            editText_text.setText(ui_text);
            editText_fontSize.setText("" + ui_size);
            
        	dialog.setTitle("Style")
        	      .setView(layout)
        		  .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        			  public void onClick(DialogInterface d, int arg1) {
        				  TextView textView = (TextView) findViewById(R.id.textView_msg);
        				  ui_text = editText_text.getText().toString();
        				  textView.setText(ui_text);
        				  ui_size = Integer.parseInt(editText_fontSize.getText().toString());
        				  textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, ui_size);
        				  
        				  ui_foreground = spinner_foreground.getSelectedItemPosition();
        				  textView.setTextColor(Color.parseColor(hexColors[ui_foreground]));
        				  ui_background = spinner_background.getSelectedItemPosition();
        				  LinearLayout view = (LinearLayout) findViewById(R.id.view);
        				  view.setBackgroundColor(Color.parseColor(hexColors[ui_background]));
        			  }})
        		  .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        			  public void onClick(DialogInterface d, int arg1) {
        				  // do nothing.
					  }})
        		  .show(); 
        }
    };
}