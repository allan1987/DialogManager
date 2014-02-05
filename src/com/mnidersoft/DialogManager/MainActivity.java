package com.mnidersoft.DialogManager;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Handler handler;
	
	private Button alertDialog, confirmDialog, progressDialog, customDialog;
	
	private DialogInterface.OnClickListener yesListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			Toast.makeText(MainActivity.this, R.string.confirmed, Toast.LENGTH_LONG).show();
		}
	};
	
	private DialogInterface.OnClickListener buttonListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			Toast.makeText(MainActivity.this, R.string.do_anything, Toast.LENGTH_LONG).show();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		
		this.initComponents();
		this.setListeners();
	}

	private void initComponents() {
		this.handler = new Handler();
		
		this.alertDialog = (Button) this.findViewById(R.id.alertDialog);
		this.confirmDialog = (Button) this.findViewById(R.id.confirmDialog);
		this.progressDialog = (Button) this.findViewById(R.id.progressDialog);
		this.customDialog = (Button) this.findViewById(R.id.customDialog);
	}
	
	private void setListeners() {
		this.alertDialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				A Way (more simple):
				DialogManager.getInstance(MainActivity.this).makeAlert(R.string.hello_world);
				
//				Another Way:
//				String message = MainActivity.this.getString(R.string.hello_world);
//				DialogManager.getInstance(MainActivity.this).makeAlert(message);
				
//				Another Way:
//				DialogManager.getInstance(MainActivity.this)
//				.makeAlert(R.string.app_name, R.string.hello_world, buttonListener);
			}
		});
		
		this.confirmDialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				A Way (more simple):
				DialogManager.getInstance(MainActivity.this)
				.makeConfirm(R.string.confirm, yesListener);
				
//				Another Way:
//				String message = MainActivity.this.getString(R.string.hello_world);
//				DialogManager.getInstance(MainActivity.this).makeConfirm(message, yesListener);
				
//				Another Way:
//				DialogManager.getInstance(MainActivity.this)
//				.makeConfirm(R.string.app_name, R.string.hello_world, yesListener);
			}
		});
		
		this.progressDialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				A Way (more simple):
				DialogManager.getInstance(MainActivity.this).showWaiting();
//				To dismiss Waiting Screen after 3 seconds
				handler.postDelayed(dismissRunnable, 3000);
				
//				Another Way (press back button to dismiss):
//				DialogManager.getInstance(MainActivity.this).showWaiting(true);
				
//				Another Way:
//				DialogManager.getInstance(MainActivity.this).showWaiting(R.string.hello_world, false);
//				handler.postDelayed(dismissRunnable, 3000);
				
			}
		});
		
		this.customDialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ImageView view = new ImageView(MainActivity.this);
				view.setBackgroundResource(R.drawable.ic_launcher);
				
//				A Way (more simple):
				DialogManager.getInstance(MainActivity.this).showContent(view);
				
//				Another Way:
//				DialogManager.getInstance(MainActivity.this).showContent(view, true);
				
//				Another Way:
//				DialogManager.getInstance(MainActivity.this).showContent(R.string.app_name, view, true);
			}
		});
	}
	
	//Runnable that dismisses some open dialog. I did this only for test
	private Runnable dismissRunnable = new Runnable() {
		@Override
		public void run() {
			DialogManager.getInstance(MainActivity.this).dismiss();
		}
	};
}
