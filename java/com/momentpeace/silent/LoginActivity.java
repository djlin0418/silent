package com.momentpeace.silent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends Activity {

    private static final int EVENT_VERIFY= 0;
    private static final int EVENT_LOGIN = 1;
    private EditText mUserName;
    private EditText mPassword;
    private Button mRegister;
    private Button mLogin;
    private InternalHandler mInternalHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUserName = (EditText)findViewById(R.id.user_name_input);
        mPassword = (EditText)findViewById(R.id.password_input);
        mRegister = (Button)findViewById(R.id.button_register);
        mLogin = (Button)findViewById(R.id.button_login);
        mInternalHandler = new InternalHandler();


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                //String user_name = (String)mUserName.getText();
                startActivity(intent);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                String user_name = mUserName.getText().toString();
                String password = mPassword.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("user.name",user_name);
                bundle.putString("password", password);
//                intent.putExtras(bundle);
                Message message = mInternalHandler.obtainMessage(EVENT_VERIFY, bundle);
                mInternalHandler.sendMessage(message);
//                startActivity(intent);
            }
        });
    }

    private class InternalHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EVENT_VERIFY:
                    Bundle bundle = (Bundle)msg.obj;
                    String user_name = bundle.getString("user.name");
                    String password = bundle.getString("password");
                case EVENT_LOGIN:
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                default:
                    super.handleMessage(msg);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
