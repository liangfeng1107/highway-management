package com.android.highway;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginActivity extends Activity {
    //用户名文本编辑框
    private EditText username;
    //密码文本编辑框
    private EditText password;
    //登录按钮
    private Button login;

    //注册按钮
    private Button register;
    private Button register_user;
    private EditText emailText;
    //记住密码按钮
    private CheckBox rem_pw;
    //自动登录按钮
    private CheckBox auto_login;
    //SharedPreferences是Android平台上一个轻量级的存储类，主要是保存一些常用的配置参数
    private SharedPreferences sp;
    //定义Intent对象，用来连接两个Activity
    private String name;
    private String pass;
    private String email;
    private Intent intent;
    private Toast toast;
    final static String nameSpace = "http://service.highway.com/"; //Webservice所在命名空间
    final static String endPoint = "http://www.highwayservice.site:80/UserService?wsdl";//Webservice服务地址
    final static String methodName = "login";//要使用的接口函数
    final static String registermethodName = "addUser";
    final static String getusermethodName = "getUser";
    private ArrayList<String> params;
    private ArrayList<String> vals;
    private boolean result;
    private boolean register_result;
    private HttpTransportSE ht; //该对象用于调用WebService操作
    private boolean getuserstate = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//不显示标题栏
        //设置布局
        setContentView(R.layout.activity_login);
        //获得实例对象
        sp = this.getSharedPreferences("userinfo", Context.MODE_WORLD_READABLE);
        username = (EditText) findViewById(R.id.et_Username);
        password = (EditText) findViewById(R.id.et_Password);
        //得到登录按钮对象
        login = (Button) findViewById(R.id.btn_login);

        register = (Button) findViewById(R.id.register);
        register_user = (Button) findViewById(R.id.register_user);
        emailText = (EditText) findViewById(R.id.editEmail);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString();
                pass = password.getText().toString();

            }
        });
        //给登录按钮设置监听器
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到用户名和密码的编辑框
                name = username.getText().toString();
                pass = password.getText().toString();
                params = new ArrayList<String>();
                vals = new ArrayList<String>();
                params.add("username");
                params.add("password");
                vals.add(name);
                vals.add(pass);


                //通过Runable接口和Thread类创建线程调用
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        result = getRemoteInfo(nameSpace, methodName, endPoint, params, vals);
                        //子线程运行
                        if (getLoginResult()) {
                            if (true) {
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("USERNAME", name);
                                editor.putString("PASSWORD", pass);
                                editor.commit();
                            }
                            //创建Intent对象，传入源Activity和目的Activity的类对象
                            intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("username", name);
                            //启动Activity
                            startActivity(intent);
                        } else {
                            //登录信息错误，通过Toast显示提示信息
                            Looper.prepare();
                            toast = Toast.makeText(LoginActivity.this, R.string.login_fail, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 300);
                            toast.show();
                            Looper.loop();
                        }
                    }
                });
                t.start();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailText.setVisibility(View.VISIBLE);
                register_user.setVisibility(View.VISIBLE);
            }
        });
        register_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString();
                pass = password.getText().toString();
                email = emailText.getText().toString();
                params = new ArrayList<String>();
                vals = new ArrayList<String>();
                params.add("username");
                params.add("password");
                params.add("email");
                vals.add(name);
                vals.add(pass);
                vals.add(email);
                //通过Runable接口和Thread类创建线程调用
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getuserstate = getRegisterInfo(nameSpace, getusermethodName, endPoint, params, vals);
                        if (!isGetuserstate()) {
                            getRemoteInfo(nameSpace, registermethodName, endPoint, params, vals);
                            register_result = true;
                        } else {
                            register_result = false;
                        }
                        //子线程运行
                        if (getRegisterResult()) {
                            Looper.prepare();
                            toast = Toast.makeText(LoginActivity.this, R.string.register_success, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 300);
                            toast.show();
                            Looper.loop();
                        } else {
                            //登录信息错误，通过Toast显示提示信息
                            Looper.prepare();
                            toast = Toast.makeText(LoginActivity.this, R.string.register_fail, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 300);
                            toast.show();
                            Looper.loop();
                        }
                    }
                });
                t.start();
//                emailText.setVisibility(View.GONE);
//                register_user.setVisibility(View.GONE);
            }
        });

    }


    public boolean getRemoteInfo(String nameSpace, String methodName, String endPoint, ArrayList<String> params, ArrayList<String> vals) {

        SoapObject request = new SoapObject(nameSpace, methodName);
        for (int i = 0; i < params.size();i++) {
            request.addProperty(params.get(i),vals.get(i));
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = request;
        ht = new HttpTransportSE(endPoint);
        try {
            ht.call(null, envelope);
            if (envelope.getResponse() != null) {
                //SoapSerializationEnvelope对象的bodyIn属性返回一个SoapObject对象，
                //该对象就代表了WebService的返回消息。
                //WebService在服务器端返回值是String类型的数值的时候使用Object代替SoapObject
                SoapObject result = (SoapObject) envelope.bodyIn;
                Object detail1 = (Object) result.getProperty(0);
                Integer i = Integer.valueOf(String.valueOf(detail1));
                if (i == 1) {
                    return true;
                }
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getRegisterInfo(String nameSpace, String methodName, String endPoint, ArrayList<String> params, ArrayList<String> vals) {

        SoapObject request = new SoapObject(nameSpace, methodName);
        for (int i = 0; i < params.size();i++) {
            request.addProperty(params.get(i),vals.get(i));
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = request;
        ht = new HttpTransportSE(endPoint);
        try {
            ht.call(null, envelope);
            if (envelope.getResponse() != null) {
                //SoapSerializationEnvelope对象的bodyIn属性返回一个SoapObject对象，
                //该对象就代表了WebService的返回消息。
                //WebService在服务器端返回值是String类型的数值的时候使用Object代替SoapObject
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean isGetuserstate() {
        return getuserstate;
    }

    public boolean getLoginResult() {
        return result;
    }

    public boolean getRegisterResult() {
        return register_result;
    }

}

