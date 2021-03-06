package com.niit.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText txt=(EditText)findViewById(R.id.et_dbname);//输入文本框
		Button bnt=(Button)findViewById(R.id.bt_createdb);//创建数据库按钮
		Button bnt2=(Button)findViewById(R.id.bt_createtable);//创建表并插入数据
		Button bnt3=(Button)findViewById(R.id.bt_search);//显示数据
		
		
		//创建数据库
		bnt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String path=MainActivity.this.getFilesDir().toString();
				
				//.this.getFilesDir().toString()
				//得到路径  data/data/org.niit.sqlitedemo/files
				path+="/"+txt.getText().toString()+".db3";
				
				//打开或者创建数据库
				SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(path, null);
				
				if(db.isDatabaseIntegrityOk()){
					Toast.makeText(MainActivity.this, 
							"创建成功!"
							+ "\n"+path, Toast.LENGTH_LONG).show();
				}else{
					Toast.makeText(MainActivity.this, 
							"创建失败!"
							+ "\n"+path, Toast.LENGTH_LONG).show();
				}
				
//				Toast.makeText(MainActivity.this, 
//						"路径:"+path, Toast.LENGTH_LONG).show();
				
				//System.out.println("路径:"+path);
			}
		});
		
		//创建表并插入数据
		bnt2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String path=MainActivity.this.getFilesDir().toString();
				path+="/"+txt.getText().toString()+".db3";
				//打开或者创建数据库
				SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(path, null);
				
				
				//执行语句
				String sql="CREATE TABLE user("
						+"id  integer  primary key  autoincrement,"
						+"name  varchar(20),"
						+"age   int  default 0"
						+")";
				//执行创建表
				db.execSQL(sql);
				
				//插入测试数据
				db.execSQL("insert  into  user(name,age) values('张三',18)");
				db.execSQL("insert  into  user(name,age) values('niit',30)");
				db.execSQL("insert  into  user(name,age) values('李四',25)");
				db.execSQL("insert  into  user(name,age) values('王五',5)");
				
				Toast.makeText(MainActivity.this, 
						"创建表并插入测试语句成功！", Toast.LENGTH_LONG).show();
			}
		});
		
		
		
		//跳转到显示 数据的页面
		bnt3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent  intent=new Intent(MainActivity.this,ShowActivity.class);
				
				EditText txt = (EditText)findViewById(R.id.textView1);
				String dbname = txt.getText().toString();
				
				intent.putExtra("dbname", dbname);
				
				startActivity(intent);
			}
		});
		
	}

}
