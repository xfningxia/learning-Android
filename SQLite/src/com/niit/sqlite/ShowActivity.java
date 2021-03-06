package com.niit.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * 显示查询的数据
 */
public class ShowActivity extends Activity {
	
	private SQLiteDatabase db;
	private ListView  listview;
	private EditText  searchtxt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		listview=(ListView)findViewById(R.id.listView1);
		searchtxt=(EditText)findViewById(R.id.search_txt);
		Button  bnt=(Button)findViewById(R.id.search_bnt);
		
		//打开数据库
		String path=getFilesDir().toString()+"/niit.db3";
		db=SQLiteDatabase.openOrCreateDatabase(path, null);
		
		
		//查询按钮
		bnt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				String txtname="%"+searchtxt.getText().toString()+"%";
				
				//执行查询
				Cursor  cursor=db.rawQuery(
						"select * from user where name like '"+txtname+"'"
						, null);
				List<Object>  itemList=new ArrayList<Object>();
				//循环取值
				while(cursor.moveToNext()){
					int id=cursor.getInt(0);
					String name=cursor.getString(1);
					int age=cursor.getInt(2);
					
					itemList.add("编号:"+id+"\t姓名:"+name+"\t年龄:"+age);
				}
				
				Object[] arr=itemList.toArray();
				//内容适配器
				ArrayAdapter<Object>  adapter=new ArrayAdapter<Object>(
						ShowActivity.this,R.layout.show_item,arr);
				//设置显示
				listview.setAdapter(adapter);
			}
		});
		
	}
}
