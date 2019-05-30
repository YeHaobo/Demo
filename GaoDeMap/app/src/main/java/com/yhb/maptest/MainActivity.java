package com.yhb.maptest;


import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnMyLocationChangeListener;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

public class MainActivity extends AppCompatActivity implements OnMyLocationChangeListener,GeocodeSearch.OnGeocodeSearchListener  {
    private MapView mMapView = null;
    private AMap aMap = null;
    private LatLonPoint mLatLonPoint = null;
    private GeocodeSearch mGeocodeSearch = null;
    private Marker marker = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView = findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.getMap();
        initMap();
    }

    /**
     * 初始化地图
     */
    private void initMap(){

        MyLocationStyle myLocationStyle = new MyLocationStyle()
                .strokeColor(getResources().getColor(R.color.blue_high))//中心点范围边框色
                .radiusFillColor(getResources().getColor(R.color.blue_normal))//中心点范围填充色
                .myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//定位模式

        UiSettings mUiSettings = aMap.getUiSettings();
        mUiSettings.setCompassEnabled(true);//设置指南针
        mUiSettings.setScaleControlsEnabled(true);//设置比例尺
        mUiSettings.setMyLocationButtonEnabled(true);//定位按钮
        mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);//logo位置

        aMap.setMyLocationStyle(myLocationStyle);//设置地图样式
        aMap.setOnMyLocationChangeListener(this);//设置定位回调
        aMap.setMyLocationEnabled(true);//开启定位
//        aMap.setMyLocationEnabled(true);// 可触发定位并显示当前位置

        mGeocodeSearch = new GeocodeSearch(this);
        mGeocodeSearch.setOnGeocodeSearchListener(this);

    }
    /**
     * 定位的回调
     */
    @Override
    public void onMyLocationChange(Location location) {
        if(mLatLonPoint == null){
            //定位当前蓝点位置
            CameraUpdate mCameraUpdate = CameraUpdateFactory
                    .newCameraPosition(new CameraPosition(new LatLng(location.getLatitude(),location.getLongitude()),15,30,0));
            //移动地图至中心点(无移动动画)
            aMap.moveCamera(mCameraUpdate);
            //添加地标
            marker = addMyMark(location.getLatitude(),location.getLongitude(),"当前位置","江苏省 南京市 建邺区 紫金西城1号楼");
        }
        //逆地理编码（坐标转地址）
        mLatLonPoint = new LatLonPoint(location.getLatitude(),location.getLongitude());
        RegeocodeQuery query = new RegeocodeQuery(mLatLonPoint, 100,GeocodeSearch.AMAP);
        mGeocodeSearch.getFromLocationAsyn(query);
    }

    /**
     * 坐标转地址回调
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
        if(i != 1000){
            Toast.makeText(MainActivity.this,"回调地址失败，errCode:" + i,Toast.LENGTH_SHORT).show();
        }else{
            RegeocodeAddress mRegeocodeAddress = regeocodeResult.getRegeocodeAddress();
            String address = mRegeocodeAddress.getFormatAddress();
            marker.setSnippet(address);
        }
    }
    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) { }

    /**
     * 添加Mark
     */
    private Marker addMyMark(double latitude,double longitude,String title,String desc){
        View view = View.inflate(this,R.layout.mark_image, null);//将要显示的Mark视图
        MarkerOptions markerOption = new MarkerOptions() //自定义Mark
                .icon(BitmapDescriptorFactory.fromView(view))//不支持SVG画出的图片
                .draggable(false)
                .position(new LatLng(latitude,longitude))
                .title(title)
                .snippet(desc)
                .setFlat(false);
        Marker marker = aMap.addMarker(markerOption);//添加自定义Mark
        addMarkAnim(marker);
        addMarkListener();
        customInfoWindow();
        return marker;
    }

    /**
     * 为Mark地标添加动画
     */
    private void addMarkAnim(Marker marker){
        Animation animation = new ScaleAnimation(2,1,2,1);//自定义mark动画
        animation.setDuration(1000);
        animation.setInterpolator(new BounceInterpolator());
        marker.setAnimation(animation);
        marker.startAnimation();
    }

    /**
     * 设置Mark地标点击监听
     */
    private void addMarkListener(){
        //设置Mark点击事件
        AMap.OnMarkerClickListener markListener = new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(!marker.isInfoWindowShown()){
                    marker.showInfoWindow();
                    Toast.makeText(MainActivity.this,
                            "经度："
                            + marker.getPosition().latitude
                            + "纬度："
                            + marker.getPosition().longitude,Toast.LENGTH_SHORT).show();
                }
                else{
                    marker.hideInfoWindow();
                }
                /**
                 * 返回true则冒泡事件不向下传递，返回false则冒泡向下传递
                 */
                return true;
            }
        };
        aMap.setOnMarkerClickListener(markListener);
    }

    /**
     * 自定义infoWindow
     */
    private void customInfoWindow(){
        aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                View view = LayoutInflater.from(MainActivity.this).inflate(
                        R.layout.custom_info_window, null);
                TextView title = view.findViewById(R.id.title);
                TextView desc = view.findViewById(R.id.desc);
                title.setText(marker.getTitle());
                desc.setText(marker.getSnippet());
                return view;
            }
            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
}
