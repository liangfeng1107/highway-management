package com.android.highway;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.text.Layout;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private  TableLayout logtableLayout;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    //定位相关
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;
    private  boolean isFristIn = true;
    private Context context;
    private double mLatitude;
    private double mLongitude;
    //自定义图标
    private BitmapDescriptor mIconLocation;
    private MyOrientationListener myOrientationListener;
    private float mCurrentX;
    private LinearLayout mMarkerLy;


    //地图覆盖物
    private BitmapDescriptor mMarker;
    private BitmapDescriptor mMarker2;
    final static String nameSpace = "http://service.highway.com/"; //Webservice所在命名空间
    final static String endPoint = "http://www.highwayservice.site:80/EquipmentTableService?wsdl";//Webservice服务地址
    final static String methodName = "getAllEquipment";//要使用的接口函数
    final static String setmethodName = "setEquipmnetState";
    final static String logEndPoint = "http://www.highwayservice.site:80/NodeLogService?wsdl";
    final static String addlogmethodName = "addNodeLog";
    final static String showlog = "getNodeLog";
    private HttpTransportSE ht;
    private List<Equipment> equipments = new ArrayList<>();
    private TextView logtext;
    private String logtextstr;

    private int logstate = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        logtext = (TextView) findViewById(R.id.node_log);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        this.context = this;
        //初始化百度地图
        initView();
        //初始化定位
        initLocation();
        //初始化覆盖物
        initMarker();
        //初始化log
        flashlog();
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle extraInfo = marker.getExtraInfo();
                final Equipment equipment = (Equipment) extraInfo.getSerializable("equipment");
                TextView textView = (TextView) mMarkerLy.findViewById(R.id.setAddress);
                Switch switch_state = (Switch) mMarkerLy.findViewById(R.id.switch_button);
                if(equipment!=null) {
                    if(equipment.getAddress() != null) {
                        textView.setText(equipment.getAddress());
                    }
                    if (equipment.getState().equals("开")) {
                        switch_state.setChecked(true);
                    } else {
                        switch_state.setChecked(false);
                    }
                }
                switch_state.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = equipment.getId();
                        String state = equipment.getState();
                        if(state.equals("开")){
                            changeEquipmentState(id, "关");
                            Toast.makeText(MainActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                            addLogThread(equipment.getTSurvnodename(), "执行成功");
                        } else if(state.equals("关")){
                            changeEquipmentState(id, "开");
                            Toast.makeText(MainActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                            addLogThread(equipment.getTSurvnodename(), "执行成功");
                        } else {
                            System.out.println("Error");
                            addLogThread(equipment.getTSurvnodename(), "未知错误");
                        }
                    }
                });
                mMarkerLy.setVisibility(View.VISIBLE);
                return true;
            }
        });

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMarkerLy.setVisibility(View.GONE);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }


    private void changeEquipmentState(final int id, final String state) {
        Thread r = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean success = changeState(id, state);
            }
        });
        r.start();
    }

    //新建log的函数
    private void addlog(String nodename, String state) {
        Date date = new Date();
        String createtime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
        SoapObject request = new SoapObject(nameSpace, addlogmethodName);
        request.addProperty("arg0", createtime);
        request.addProperty("arg1", nodename);
        request.addProperty("arg2", state);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);//指明SOPA规范
        //创建SoapObject对象，创建对象时需要传入调用Web Service的命名空间、方法名。
        envelope.bodyOut = request;
        //创建HttpTransportSE对象，该对象用于调用WebService操作
        ht = new HttpTransportSE(logEndPoint);
        try {
            ht.call(null, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    private void addLogThread(final String nodename, final String state) {

        Thread r = new Thread(new Runnable() {
            @Override
            public void run() {
                addlog(nodename, state);
            }
        });
        r.start();
    }

    private boolean changeState(int id, String state) {
        SoapObject request = new SoapObject(nameSpace, setmethodName);
        request.addProperty("arg0", id);
        request.addProperty("arg1", state);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);//指明SOPA规范
        //创建SoapObject对象，创建对象时需要传入调用Web Service的命名空间、方法名。
        envelope.bodyOut = request;
        //创建HttpTransportSE对象，该对象用于调用WebService操作
        ht = new HttpTransportSE(endPoint);
        try {
            ht.call(null, envelope);
            if(envelope.getResponse() != null){
                SoapObject result = (SoapObject) envelope.bodyIn;
                Object detail1 = (Object) result.getProperty(0);
                int i = Integer.parseInt(String.valueOf(detail1));
                if (i == 1) {
                    initMarker();
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


    private void initMarker() {
        mMarker = BitmapDescriptorFactory.fromResource(R.mipmap.orange_icon);
        mMarker2 = BitmapDescriptorFactory.fromResource(R.mipmap.green_icon);
        mMarkerLy = (LinearLayout) findViewById(R.id.id_marker_layout);
        Thread r = new Thread(new Runnable() {
            @Override
            public void run() {
                equipments = getEquipments(nameSpace, methodName, endPoint);
            }
        });
        r.start();

    }

    private void initLocation() {
        mLocationClient = new LocationClient(this);
        mLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mLocationListener);

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
//        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
//        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
        //初始化地图定位图标
        mIconLocation = BitmapDescriptorFactory.fromResource(R.mipmap.gps_icon);

        myOrientationListener = new MyOrientationListener(context);
        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
            }
        });
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.id_bmapView);
        mBaiduMap = mMapView.getMap();

        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo((float) 15.0);
        mBaiduMap.setMapStatus(msu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开启定位
        mBaiduMap.setMyLocationEnabled(true);//地图开启定位
        if(!mLocationClient.isStarted()){
            mLocationClient.start();
            //开启方向传感器
        }
        myOrientationListener.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //关闭定位
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        //停止方向传感器
        myOrientationListener.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.id_map_common:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.id_map_site:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.id_map_traffic:
                if(mBaiduMap.isTrafficEnabled()) {
                    mBaiduMap.setTrafficEnabled(false);
                    item.setTitle("实时交通(off)");
                } else {
                    mBaiduMap.setTrafficEnabled(true);
                    item.setTitle("实时交通(on)");
                }
                break;
            case R.id.id_map_location:
                getMyLocation(mLatitude, mLongitude);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Equipment> getEquipments(String nameSpace, String methodName, String endPoint) {
        SoapObject request = new SoapObject(nameSpace, methodName);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);//指明SOPA规范
        //创建SoapObject对象，创建对象时需要传入调用Web Service的命名空间、方法名。
        envelope.bodyOut = request;
        //创建HttpTransportSE对象，该对象用于调用WebService操作
        ht = new HttpTransportSE(endPoint);

        envelope.dotNet = false;
        try {
            //调用远程WebService，call（）方法的参数意义 第一个参数：命名空间＋方法名，
            //第二个参数：SoapSerializationEnvelope对象
            ht.call(null, envelope);
            if (envelope.getResponse() != null) {
                //SoapSerializationEnvelope对象的bodyIn属性返回一个SoapObject对象，
                //该对象就代表了WebService的返回消息。
                //WebService在服务器端返回值是String类型的数值的时候使用Object代替SoapObject
                SoapObject result = (SoapObject)envelope.bodyIn;
                int count = result.getPropertyCount();
                List<Equipment> equipments = new ArrayList<Equipment>();
                for(int i=0;i<count;i++){
                    Equipment equipment = new Equipment();
                    SoapObject equip = (SoapObject) result.getProperty(i);
                    equipment.setAddress(equip.getProperty(0).toString());
                    equipment.setArea(equip.getProperty(1).toString());
                    equipment.setBatteryState(equip.getProperty(2).toString());
                    equipment.setBaudrate(equip.getProperty(3).toString());
                    equipment.setCommState(equip.getProperty(4).toString());
                    equipment.setId(Integer.parseInt(equip.getProperty(5).toString()));
                    equipment.setPort(equip.getProperty(6).toString());
                    equipment.setLongtitude(Double.parseDouble(equip.getProperty(7).toString()));
                    equipment.setLatitude(Double.parseDouble(equip.getProperty(8).toString()));
                    equipment.setState(equip.getProperty(9).toString());
                    equipment.setSurvnodeindex(Integer.parseInt(equip.getProperty(10).toString()));
                    equipment.setTSurvnodename(equip.getProperty(11).toString());
                    equipment.setTSurvnoderoad(equip.getProperty(12).toString());
                    equipment.setType(equip.getProperty(13).toString());
                    equipments.add(equipment);
                }
                addEquipments(equipments);
                return equipments;
            }
            return  null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;

    }
    //向地图添加设备
    private void addEquipments(List<Equipment> Equipments) {
        mBaiduMap.clear();
        LatLng latLng = null;
        OverlayOptions options;
        Marker marker;
        for(Equipment equipment: Equipments) {
            //经纬度
            latLng = new LatLng(equipment.getLatitude(), equipment.getLongtitude());
            //图标
            if(equipment.getState().equals("关")) {
                options = new MarkerOptions().position(latLng).icon(mMarker).zIndex(9).title(equipment.getAddress());
            } else {
                options = new MarkerOptions().position(latLng).icon(mMarker2).zIndex(9).title(equipment.getAddress());
            }
            Bundle bEquipment = new Bundle();
            bEquipment.putSerializable("equipment",equipment);
            marker = (Marker) mBaiduMap.addOverlay(options);
            marker.setExtraInfo(bEquipment);
        }

//        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
//        mBaiduMap.setMapStatus(msu);

    }
    private void getMyLocation(double mLatitude, double mLongitude) {
        LatLng latLng = new LatLng(mLatitude, mLongitude);//获得当前经纬度
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(msu);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        View layout = findViewById(R.id.includeLayout);
        View layout2 = findViewById(R.id.equipmentLayout);
        View layout3 = findViewById(R.id.nodelogLayout);
        if (id == R.id.nav_camera) {
            // Handle the camera action
            layout.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);

        } else if (id == R.id.nav_gallery) {
            layout.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.VISIBLE);
            showEquipment();
            layout3.setVisibility(View.INVISIBLE);
        } else if (id == R.id.nav_slideshow) {
            layout.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.VISIBLE);
            flashlog();
        } else if (id == R.id.nav_manage) {
            layout.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            //启动Activity
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showNodeLog() {
        Thread r = new Thread(new Runnable() {

            @Override
            public void run() {
                logtextstr = showlog();
                logstate = 1;
            }
        });
        r.start();
    }

    private void flashlog() {
        showNodeLog();
        if(logstate == 1) {
            logtext.setText(logtextstr);
        }
    }

    private String showlog() {
        SoapObject request = new SoapObject(nameSpace, showlog);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);//指明SOPA规范
        //创建SoapObject对象，创建对象时需要传入调用Web Service的命名空间、方法名。
        envelope.bodyOut = request;
        //创建HttpTransportSE对象，该对象用于调用WebService操作
        ht = new HttpTransportSE(logEndPoint);
        try {
            ht.call(null, envelope);
            if(envelope.getResponse() != null){
                SoapObject result = (SoapObject) envelope.bodyIn;
                String str = " 序号     栏杆机名         日志时间            操作结果   \n";
                for(int row=0;row<20;row++)
                {
                    //tv用于显示
                    SoapObject detail = (SoapObject) result.getProperty(row);
                    String createtime = detail.getProperty(3).toString();
                    String name = detail.getProperty(2).toString();
                    String res  = detail.getProperty(1).toString();
                    str = str + "   " + (row+1) + "    " + name + "    " + createtime + "     " + res + "\n";
                }
                return str;
            }
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return "Error";

    }

    private void showEquipment() {
        final TextView nodename = (TextView) findViewById(R.id.nodename);
        final TextView longitude = (TextView) findViewById(R.id.equipment_Lng);
        final TextView latitude = (TextView) findViewById(R.id.equipment_Lat);
        final TextView type = (TextView) findViewById(R.id.equipment_type);
        final TextView state = (TextView) findViewById(R.id.equipment_state);
        final TextView comm_state = (TextView) findViewById(R.id.comm_state);
        final TextView battery_state = (TextView) findViewById(R.id.battery_state);
        final TextView port = (TextView) findViewById(R.id.port);
        final TextView roadname = (TextView) findViewById(R.id.roadname);
        final TextView area = (TextView) findViewById(R.id.area);
        final TextView address = (TextView)findViewById(R.id.address);
        final Switch switch_state = (Switch) findViewById(R.id.switch_button2);
        //第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        List<Integer> list = new ArrayList<Integer>();
        for(Equipment equipment: equipments){
            list.add(equipment.getId());
        }
        Spinner mySpinner = (Spinner)findViewById(R.id.equipment_id);
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        final ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mySpinner.setAdapter(adapter);
        //第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for(final Equipment equipment: equipments){
                    if(equipment.getId() == adapter.getItem(position)){
                        nodename.setText(equipment.getTSurvnodename());
                        longitude.setText(String.valueOf(equipment.getLongtitude()));
                        latitude.setText(String.valueOf(equipment.getLatitude()));
                        type.setText(equipment.getType());
                        state.setText(equipment.getState());
                        comm_state.setText(equipment.getCommState());
                        battery_state.setText(equipment.getBatteryState());
                        port.setText(equipment.getPort());
                        roadname.setText(equipment.getTSurvnoderoad());
                        area.setText(equipment.getArea());
                        address.setText(equipment.getAddress());

                        if(equipment!=null) {
                            if (equipment.getState().equals("开")) {
                                switch_state.setChecked(true);
                            } else {
                                switch_state.setChecked(false);
                            }
                        }
                        switch_state.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int id = equipment.getId();
                                String state = equipment.getState();
                                if (state.equals("开")) {
                                    changeEquipmentState(id, "关");
                                } else if (state.equals("关")) {
                                    changeEquipmentState(id, "开");
                                } else {
                                    System.out.println("Error");
                                }
                            }
                        });
                    }
                }
            }
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    //定位Listener
    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation){// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());

            MyLocationData data = new MyLocationData.Builder()//
                    .direction(mCurrentX)
                    .accuracy(location.getRadius())//
                    .latitude(location.getLatitude())//
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(data);
            //设置自定义图标
            MyLocationConfiguration configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,true, mIconLocation);
            mBaiduMap.setMyLocationConfigeration(configuration);

            //更新经纬度
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();

            if(isFristIn) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());//获得当前经纬度
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(msu);

                isFristIn = false;

                Toast.makeText(context, location.getAddrStr(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
