package com.example.android.supermax_storemapping;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.Menu;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;


public class LauncherActivity extends BaseActivity implements LocationListener,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

   // Distance for near Store
    LinkedHashMap<String, String> hmapOutletListForNear=new LinkedHashMap<String, String>();

    LinkedHashMap<String, String> hmapOutletListForNearUpdated=new LinkedHashMap<String, String>();
   DBAdapterLtFoods dbengine=new DBAdapterLtFoods(this);
    String errorMessageFlag,username,totaltarget,todayTarget;
    LinearLayout erreorSectionParent,TotalStoreAddedParent,TodayStoreAddedParent;
    TextView usernameTextView,errorMessageTextView ,totalStoreTextview,ToadyStoreTextview;
    Button ExitBtn,ProceedBtn,AddStoreBtn;
    public   PowerManager pm;
    public	 PowerManager.WakeLock wl;
    public LocationManager locationManager;
    public ProgressDialog pDialog2STANDBY;
    public String FusedLocationLatitudeWithFirstAttempt="0";
    public String FusedLocationLongitudeWithFirstAttempt="0";
    public String FusedLocationAccuracyWithFirstAttempt="0";
    public String AllProvidersLocation="";
    public Location location;
    public String FusedLocationLatitude="0";
    public String FusedLocationLongitude="0";
    public String FusedLocationProvider="";
    public String FusedLocationAccuracy="0";

    public String GPSLocationLatitude="0";
    public String GPSLocationLongitude="0";
    public String GPSLocationProvider="";
    public String GPSLocationAccuracy="0";

    public String NetworkLocationLatitude="0";
    public String NetworkLocationLongitude="0";
    public String NetworkLocationProvider="";
    public String NetworkLocationAccuracy="0";

    public AppLocationService appLocationService;
    public boolean isGPSEnabled = false;
    public   boolean isNetworkEnabled = false;


    public CoundownClass countDownTimer;
    private final long startTime = 15000;
    private final long interval = 200;

    private static final String TAG = "LocationActivity";
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    GoogleApiClient mGoogleApiClient;
    Location mCurrentLocation;
    String mLastUpdateTime;

    LocationRequest mLocationRequest;

    public String fnAccurateProvider="";
    public String fnLati="0";
    public String fnLongi="0";
    public Double fnAccuracy=0.0;

    String fusedData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);
        Intent intent = getIntent();
        String FROM= intent.getStringExtra("FROM");

        if(FROM.equals("SPLASH"))
        {
            errorMessageFlag = intent.getStringExtra("errorMessageFlag");
            username=intent.getStringExtra("username");
            totaltarget = intent.getStringExtra("totaltarget");
            todayTarget = intent.getStringExtra("todayTarget");
        }
        else
        {
            username= dbengine.getUsername();
            String storeCountDeatails=dbengine.getTodatAndTotalStores();
            totaltarget = storeCountDeatails.split(Pattern.quote("^"))[0];
            todayTarget = storeCountDeatails.split(Pattern.quote("^"))[1];
            errorMessageFlag ="0";




        }



        erreorSectionParent= (LinearLayout) findViewById(R.id.erreorSectionParent);
        TotalStoreAddedParent= (LinearLayout) findViewById(R.id.TotalStoreAddedParent);
        TodayStoreAddedParent= (LinearLayout) findViewById(R.id.TodayStoreAddedParent);

        errorMessageTextView= (TextView) findViewById(R.id.errorMessageTextView);
        usernameTextView= (TextView) findViewById(R.id.usernameTextView);
        totalStoreTextview= (TextView) findViewById(R.id.totalStoreTextview);
        ToadyStoreTextview= (TextView) findViewById(R.id.ToadyStoreTextview);

        ExitBtn= (Button) findViewById(R.id.ExitBtn);
        ProceedBtn= (Button) findViewById(R.id.ProceedBtn);
        AddStoreBtn=(Button) findViewById(R.id.AddStoreBtn);
        buttonClickFucntion();

        if(errorMessageFlag.equals("0") ){
            erreorSectionParent.setVisibility(View.GONE);
            ExitBtn.setVisibility(View.VISIBLE);
           // ProceedBtn.setVisibility(View.VISIBLE);//proceed button is not using in this project ,when needed  comment below line and uncomment it
            ProceedBtn.setVisibility(View.GONE);
            AddStoreBtn.setVisibility(View.VISIBLE);
            TotalStoreAddedParent.setVisibility(View.VISIBLE);
            TodayStoreAddedParent.setVisibility(View.VISIBLE);
            usernameTextView.setVisibility(View.VISIBLE);
            usernameTextView.setText("Welcome " + username);
            totalStoreTextview.setText(totaltarget);
            ToadyStoreTextview.setText(todayTarget);




        }
        else{
            erreorSectionParent.setVisibility(View.VISIBLE);
            ExitBtn.setVisibility(View.VISIBLE);
            ProceedBtn.setVisibility(View.GONE);
            AddStoreBtn.setVisibility(View.GONE);
            TotalStoreAddedParent.setVisibility(View.GONE);
            TodayStoreAddedParent.setVisibility(View.GONE);
            usernameTextView.setVisibility(View.GONE);
            errorMessageTextView.setText(errorMessageFlag);

        }
        locationManager=(LocationManager) this.getSystemService(LOCATION_SERVICE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launcher, menu);
        return true;
    }
  public void   buttonClickFucntion(){
      AddStoreBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              //send to storelistpage
              Intent intent =new Intent(LauncherActivity.this,StorelistActivity.class);
              LauncherActivity.this.startActivity(intent);
              finish();
          }
      });

      ExitBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              AlertDialog.Builder alertDialog = new AlertDialog.Builder(LauncherActivity.this);
              alertDialog.setTitle("Information");

              alertDialog.setCancelable(false);
              alertDialog.setMessage("Do you really want to close app ");
              alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog,int which) {
                      dialog.dismiss();


                      finishAffinity();

                  }
              });
              alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog,int which) {
                      dialog.dismiss();

                  }
              });

              // Showing Alert Message
              alertDialog.show();
          }
      });
      //PoceedBtn is now not working for this project-----------------------------------------------
      ProceedBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              boolean isGPSok = false;
              boolean isNWok = false;
              isGPSok = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
              isNWok = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

              if (!isGPSok && !isNWok) {
                  try {
                      showSettingsAlert();
                  } catch (Exception e) {

                  }
                  isGPSok = false;
                  isNWok = false;
              } else {
                  appLocationService = new AppLocationService();

                  pm = (PowerManager) getSystemService(POWER_SERVICE);
                  wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
                          | PowerManager.ACQUIRE_CAUSES_WAKEUP
                          | PowerManager.ON_AFTER_RELEASE, "INFO");
                  wl.acquire();


                  pDialog2STANDBY = ProgressDialog.show(LauncherActivity.this, getText(R.string.genTermPleaseWaitNew), getText(R.string.genTermRetrivingLocation), true);
                  pDialog2STANDBY.setIndeterminate(true);

                  pDialog2STANDBY.setCancelable(false);
                  pDialog2STANDBY.show();

                  if (isGooglePlayServicesAvailable()) {
                      createLocationRequest();

                      mGoogleApiClient = new GoogleApiClient.Builder(LauncherActivity.this)
                              .addApi(LocationServices.API)
                              .addConnectionCallbacks(LauncherActivity.this)
                              .addOnConnectionFailedListener(LauncherActivity.this)
                              .build();
                      mGoogleApiClient.connect();
                  }
                  //startService(new Intent(DynamicActivity.this, AppLocationService.class));
                  startService(new Intent(LauncherActivity.this, AppLocationService.class));
                  Location nwLocation = appLocationService.getLocation(locationManager, LocationManager.GPS_PROVIDER, location);
                  Location gpsLocation = appLocationService.getLocation(locationManager, LocationManager.NETWORK_PROVIDER, location);
                  countDownTimer = new CoundownClass(startTime, interval);
                  countDownTimer.start();

              }


          }
      });

  }

    @Override
    public void onConnected(Bundle bundle) {
        startLocationUpdates();

    }
    protected void startLocationUpdates()
    {
        try
        {
            PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        }
        catch(SecurityException e)
        {

        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, appLocationService);
        locationManager.removeUpdates(appLocationService);

    }

    @Override
    public void onLocationChanged(Location args0) {
        mCurrentLocation = args0;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateUI();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {


        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, appLocationService);
        locationManager.removeUpdates(appLocationService);

    }
    private void updateUI() {
        Location loc =mCurrentLocation;
        if (null != mCurrentLocation) {
            String lat = String.valueOf(mCurrentLocation.getLatitude());
            String lng = String.valueOf(mCurrentLocation.getLongitude());

            FusedLocationLatitude=lat;
            FusedLocationLongitude=lng;
            FusedLocationProvider=mCurrentLocation.getProvider();
            FusedLocationAccuracy=""+mCurrentLocation.getAccuracy();
            fusedData="At Time: " + mLastUpdateTime  +
                    "Latitude: " + lat  +
                    "Longitude: " + lng  +
                    "Accuracy: " + mCurrentLocation.getAccuracy() +
                    "Provider: " + mCurrentLocation.getProvider();

        } else {

        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        boolean isGPSok = false;
        boolean isNWok=false;
        isGPSok = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNWok = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(!isGPSok && !isNWok)
        {
            try
            {
                showSettingsAlert();
            }
            catch(Exception e)
            {

            }
            isGPSok = false;
            isNWok=false;
        }

    }
    public void showSettingsAlert()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Title
        alertDialog.setTitle("Information");
        alertDialog.setIcon(R.drawable.error_info_ico);
        alertDialog.setCancelable(false);
        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. \nPlease select all settings on the next page!");

        // On pressing Settings button
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    public class CoundownClass extends CountDownTimer {

        public CoundownClass(long startTime, long interval) {
            super(startTime, interval);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onFinish()
        {
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            String GpsLat="0";
            String GpsLong="0";
            String GpsAccuracy="0";
            String GpsAddress="0";
            if(isGPSEnabled)
            {

                Location nwLocation=appLocationService.getLocation(locationManager,LocationManager.GPS_PROVIDER,location);

                if(nwLocation!=null){
                    double lattitude=nwLocation.getLatitude();
                    double longitude=nwLocation.getLongitude();
                    double accuracy= nwLocation.getAccuracy();
                    GpsLat=""+lattitude;
                    GpsLong=""+longitude;
                    GpsAccuracy=""+accuracy;
                    if(isOnline())
                    {
                        GpsAddress=getAddressOfProviders(GpsLat, GpsLong);
                    }
                    else
                    {
                        GpsAddress="NA";
                    }

                    GPSLocationLatitude=""+lattitude;
                    GPSLocationLongitude=""+longitude;
                    GPSLocationProvider="GPS";
                    GPSLocationAccuracy=""+accuracy;
                    AllProvidersLocation="GPS=Lat:"+lattitude+"^Long:"+longitude+"^Acc:"+accuracy;

                }
            }

            Location gpsLocation=appLocationService.getLocation(locationManager,LocationManager.NETWORK_PROVIDER,location);
            String NetwLat="0";
            String NetwLong="0";
            String NetwAccuracy="0";
            String NetwAddress="0";
            if(gpsLocation!=null){
                double lattitude1=gpsLocation.getLatitude();
                double longitude1=gpsLocation.getLongitude();
                double accuracy1= gpsLocation.getAccuracy();

                NetwLat=""+lattitude1;
                NetwLong=""+longitude1;
                NetwAccuracy=""+accuracy1;
                if(isOnline())
                {
                    NetwAddress=getAddressOfProviders(NetwLat, NetwLong);
                }
                else
                {
                    NetwAddress="NA";
                }

                NetworkLocationLatitude=""+lattitude1;
                NetworkLocationLongitude=""+longitude1;
                NetworkLocationProvider="Network";
                NetworkLocationAccuracy=""+accuracy1;
                if(!AllProvidersLocation.equals(""))
                {
                    AllProvidersLocation=AllProvidersLocation+"$Network=Lat:"+lattitude1+"^Long:"+longitude1+"^Acc:"+accuracy1;
                }
                else
                {
                    AllProvidersLocation="Network=Lat:"+lattitude1+"^Long:"+longitude1+"^Acc:"+accuracy1;
                }
                System.out.println("LOCATION(N/W)  LATTITUDE: " +lattitude1 + "LONGITUDE:" + longitude1+ "accuracy:" + accuracy1);

            }
									 /* TextView accurcy=(TextView) findViewById(R.id.Acuracy);
									  accurcy.setText("GPS:"+GPSLocationAccuracy+"\n"+"NETWORK"+NetworkLocationAccuracy+"\n"+"FUSED"+fusedData);*/

           // System.out.println("LOCATION Fused"+fusedData);

            String FusedLat="0";
            String FusedLong="0";
            String FusedAccuracy="0";
            String FusedAddress="0";

            if(!FusedLocationProvider.equals(""))
            {
                fnAccurateProvider="Fused";
                fnLati=FusedLocationLatitude;
                fnLongi=FusedLocationLongitude;
                fnAccuracy= Double.parseDouble(FusedLocationAccuracy);

                FusedLat=FusedLocationLatitude;
                FusedLong=FusedLocationLongitude;
                FusedAccuracy=FusedLocationAccuracy;
                FusedLocationLatitudeWithFirstAttempt=FusedLocationLatitude;
                FusedLocationLongitudeWithFirstAttempt=FusedLocationLongitude;
                FusedLocationAccuracyWithFirstAttempt=FusedLocationAccuracy;


                if(isOnline())
                {
                    FusedAddress=getAddressOfProviders(FusedLat, FusedLong);
                }
                else
                {
                    FusedAddress="NA";
                }

                if(!AllProvidersLocation.equals(""))
                {
                    AllProvidersLocation=AllProvidersLocation+"$Fused=Lat:"+FusedLocationLatitude+"^Long:"+FusedLocationLongitude+"^Acc:"+fnAccuracy;
                }
                else
                {
                    AllProvidersLocation="Fused=Lat:"+FusedLocationLatitude+"^Long:"+FusedLocationLongitude+"^Acc:"+fnAccuracy;
                }
            }


            appLocationService.KillServiceLoc(appLocationService, locationManager);
            stopLocationUpdates();
            mGoogleApiClient.disconnect();



            fnAccurateProvider="";
            fnLati="0";
            fnLongi="0";
            fnAccuracy=0.0;

            if(!FusedLocationProvider.equals(""))
            {
                fnAccurateProvider="Fused";
                fnLati=FusedLocationLatitude;
                fnLongi=FusedLocationLongitude;
                fnAccuracy= Double.parseDouble(FusedLocationAccuracy);
            }

            if(!fnAccurateProvider.equals(""))
            {
                if(!GPSLocationProvider.equals(""))
                {
                    if(Double.parseDouble(GPSLocationAccuracy)<fnAccuracy)
                    {
                        fnAccurateProvider="Gps";
                        fnLati=GPSLocationLatitude;
                        fnLongi=GPSLocationLongitude;
                        fnAccuracy= Double.parseDouble(GPSLocationAccuracy);
                    }
                }
            }
            else
            {
                if(!GPSLocationProvider.equals(""))
                {
                    fnAccurateProvider="Gps";
                    fnLati=GPSLocationLatitude;
                    fnLongi=GPSLocationLongitude;
                    fnAccuracy= Double.parseDouble(GPSLocationAccuracy);
                }
            }

            if(!fnAccurateProvider.equals(""))
            {
                if(!NetworkLocationProvider.equals(""))
                {
                    if(Double.parseDouble(NetworkLocationAccuracy)<fnAccuracy)
                    {
                        fnAccurateProvider="Network";
                        fnLati=NetworkLocationLatitude;
                        fnLongi=NetworkLocationLongitude;
                        fnAccuracy= Double.parseDouble(NetworkLocationAccuracy);
                    }
                }
            }
            else
            {
                if(!NetworkLocationProvider.equals(""))
                {
                    fnAccurateProvider="Network";
                    fnLati=NetworkLocationLatitude;
                    fnLongi=NetworkLocationLongitude;
                    fnAccuracy= Double.parseDouble(NetworkLocationAccuracy);
                }
            }
            // fnAccurateProvider="";
            if(fnAccurateProvider.equals(""))
            {
                //because no location found so updating table with NA
                dbengine.open();
                dbengine.deleteLocationTable();
                dbengine.saveTblLocationDetails("NA", "NA", "NA","NA","NA","NA","NA","NA", "NA", "NA","NA","NA","NA","NA","NA","NA","NA","NA","NA","NA","NA","NA","NA","NA");
                dbengine.close();
                if(pDialog2STANDBY.isShowing())
                {
                    pDialog2STANDBY.dismiss();
                }
                //send direct to dynamic page-------------------------
                Intent intent=new Intent(LauncherActivity.this,AddNewStore_DynamicSectionWise.class);
                intent.putExtra("FLAG_NEW_UPDATE","NEW");
                LauncherActivity.this.startActivity(intent);
                finish();


                //commenting below error message
               // showAlertForEveryOne("Please try again, No Fused,GPS or Network found.");
            }
            else
            {
                String FullAddress="0";
                if(isOnline())
                {
                    FullAddress=   getAddressForDynamic(fnLati, fnLongi);
                }
                else
                {
                    FullAddress="NA";
                }

                if(!GpsLat.equals("0") )
                {
                    fnCreateLastKnownGPSLoction(GpsLat,GpsLong,GpsAccuracy);
                }
                //now Passing intent to other activity
                String addr="NA";
                String zipcode="NA";
                String city="NA";
                String state="NA";


                if(!FullAddress.equals("NA"))
                {
                    addr = FullAddress.split(Pattern.quote("^"))[0];
                    zipcode = FullAddress.split(Pattern.quote("^"))[1];
                    city = FullAddress.split(Pattern.quote("^"))[2];
                    state = FullAddress.split(Pattern.quote("^"))[3];
                }

                if(fnAccuracy>100)
                {
                    dbengine.open();
                    dbengine.deleteLocationTable();
                    dbengine.saveTblLocationDetails(fnLati, fnLongi, String.valueOf(fnAccuracy), addr, city, zipcode, state,fnAccurateProvider,GpsLat,GpsLong,GpsAccuracy,NetwLat,NetwLong,NetwAccuracy,FusedLat,FusedLong,FusedAccuracy,AllProvidersLocation,GpsAddress,NetwAddress,FusedAddress,FusedLocationLatitudeWithFirstAttempt,FusedLocationLongitudeWithFirstAttempt,FusedLocationAccuracyWithFirstAttempt);
                    dbengine.close();
                    if(pDialog2STANDBY.isShowing())
                    {
                        pDialog2STANDBY.dismiss();
                    }

                    //send to addstore Dynamic page direct-----------------------------
                    Intent intent=new Intent(LauncherActivity.this,AddNewStore_DynamicSectionWise.class);
                    intent.putExtra("FLAG_NEW_UPDATE","NEW");
                    LauncherActivity.this.startActivity(intent);
                    finish();


                   //From, addr,zipcode,city,state,errorMessageFlag,username,totaltarget,todayTarget


                }
                else
                {
                    dbengine.open();
                    dbengine.deleteLocationTable();
                    dbengine.saveTblLocationDetails(fnLati, fnLongi, String.valueOf(fnAccuracy), addr, city, zipcode, state,fnAccurateProvider,GpsLat,GpsLong,GpsAccuracy,NetwLat,NetwLong,NetwAccuracy,FusedLat,FusedLong,FusedAccuracy,AllProvidersLocation,GpsAddress,NetwAddress,FusedAddress,FusedLocationLatitudeWithFirstAttempt,FusedLocationLongitudeWithFirstAttempt,FusedLocationAccuracyWithFirstAttempt);
                    dbengine.close();


                    hmapOutletListForNear=dbengine.fnGetALLOutletMstr();
                    System.out.println("SHIVAM"+hmapOutletListForNear);
                    if(hmapOutletListForNear!=null)
                    {

                        for(Map.Entry<String, String> entry:hmapOutletListForNear.entrySet())
                        {
                            int DistanceBWPoint=1000;
                            String outID=entry.getKey().toString().trim();
                          //  String PrevAccuracy = entry.getValue().split(Pattern.quote("^"))[0];
                            String PrevLatitude = entry.getValue().split(Pattern.quote("^"))[0];
                            String PrevLongitude = entry.getValue().split(Pattern.quote("^"))[1];

                           // if (!PrevAccuracy.equals("0"))
                           // {
                                if (!PrevLatitude.equals("0"))
                                {
                                    try
                                    {
                                        Location locationA = new Location("point A");
                                        locationA.setLatitude(Double.parseDouble(fnLati));
                                        locationA.setLongitude(Double.parseDouble(fnLongi));

                                        Location locationB = new Location("point B");
                                        locationB.setLatitude(Double.parseDouble(PrevLatitude));
                                        locationB.setLongitude(Double.parseDouble(PrevLongitude));

                                        float distance = locationA.distanceTo(locationB) ;
                                        DistanceBWPoint=(int)distance;

                                        hmapOutletListForNearUpdated.put(outID, ""+DistanceBWPoint);
                                    }
                                    catch(Exception e)
                                    {

                                    }
                                }
                           // }
                        }
                    }

                    if(hmapOutletListForNearUpdated!=null)
                    {
                        dbengine.open();
                        for(Map.Entry<String, String> entry:hmapOutletListForNearUpdated.entrySet())
                        {
                            String outID=entry.getKey().toString().trim();
                            String DistanceNear = entry.getValue().trim();
                            if(!DistanceNear.equals(""))
                            {
                                if(outID.equals("853399-71761ee2f30d-NA"))
                                {
                                    //System.out.println("Shvam Distance = "+DistanceNear);
                                }
                                dbengine.UpdateStoreDistanceNear(outID,Integer.parseInt(DistanceNear));
                            }
                        }
                        dbengine.close();
                    }
                    //send to storeListpage page
                    //From, addr,zipcode,city,state,errorMessageFlag,username,totaltarget,todayTarget
              int flagtoShowStorelistOrAddnewStore=      dbengine.fncheckCountNearByStoreExistsOrNot(CommonInfo.DistanceRange);

                    if(pDialog2STANDBY.isShowing())
                    {
                        pDialog2STANDBY.dismiss();
                    }
                    if(flagtoShowStorelistOrAddnewStore==1)
                    {
                        Intent intent =new Intent(LauncherActivity.this,StorelistActivity.class);
                        LauncherActivity.this.startActivity(intent);
                        finish();

                    }
                    else
                    {
                        //send to AddnewStore directly
                        Intent intent=new Intent(LauncherActivity.this,AddNewStore_DynamicSectionWise.class);
                        intent.putExtra("FLAG_NEW_UPDATE","NEW");
                        LauncherActivity.this.startActivity(intent);
                        finish();
                    }


                }
               /* Intent intent =new Intent(LauncherActivity.this,StorelistActivity.class);
               *//* intent.putExtra("FROM","SPLASH");
                intent.putExtra("errorMessageFlag",errorMessageFlag); // 0 if no error, if error, then error message passes
                intent.putExtra("username",username);//if error then it will 0
                intent.putExtra("totaltarget",totaltarget);////if error then it will 0
                intent.putExtra("todayTarget",todayTarget);//if error then it will 0*//*
                LauncherActivity.this.startActivity(intent);
                finish();
*/
                GpsLat="0";
                GpsLong="0";
                GpsAccuracy="0";
                GpsAddress="0";
                NetwLat="0";
                NetwLong="0";
                NetwAccuracy="0";
                NetwAddress="0";
                FusedLat="0";
                FusedLong="0";
                FusedAccuracy="0";
                FusedAddress="0";

                //code here
            }




        }

        @Override
        public void onTick(long arg0) {
            // TODO Auto-generated method stub

        }}

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);

    }
    public void showAlertForEveryOne(String msg)
    {
        AlertDialog.Builder alertDialogNoConn = new AlertDialog.Builder(LauncherActivity.this);
        alertDialogNoConn.setTitle("Information");
        alertDialogNoConn.setMessage(msg);
        alertDialogNoConn.setCancelable(false);
        alertDialogNoConn.setNeutralButton(R.string.txtOk,new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                // finish();
            }
        });
        alertDialogNoConn.setIcon(R.drawable.info_ico);
        AlertDialog alert = alertDialogNoConn.create();
        alert.show();
    }
    public boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
        {
            return true;
        }
        return false;
    }

    public String getAddressForDynamic(String latti,String longi){



        Address address=null;
        String addr="NA";
        String zipcode="NA";
        String city="NA";
        String state="NA";
        String fullAddress="";
        StringBuilder FULLADDRESS3 =new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(latti), Double.parseDouble(longi), 1);
            if (addresses != null && addresses.size() > 0){
                if(addresses.get(0).getAddressLine(1)!=null){
                    addr=addresses.get(0).getAddressLine(1);
                }



                if(addresses.get(0).getLocality()!=null){
                    city=addresses.get(0).getLocality();
                }

                if(addresses.get(0).getAdminArea()!=null){
                    state=addresses.get(0).getAdminArea();
                }


                for(int i=0 ;i<addresses.size();i++){
                    address = addresses.get(i);
                    if(address.getPostalCode()!=null){
                        zipcode=address.getPostalCode();
                        break;
                    }




                }
                if(addresses.get(0).getAddressLine(0)!=null && addr.equals("NA")){
                    String countryname="NA";
                    if(addresses.get(0).getCountryName()!=null){
                        countryname=addresses.get(0).getCountryName();
                    }

                    addr=  getAddressNewWay(addresses.get(0).getAddressLine(0),city,state,zipcode,countryname);
                }
            }
            else{FULLADDRESS3.append("NA");}
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            return fullAddress=addr+"^"+zipcode+"^"+city+"^"+state;
        }
    }

    public String getAddressNewWay(String ZeroIndexAddress,String city,String State,String pincode,String country){
        String editedAddress=ZeroIndexAddress;
        if(editedAddress.contains(city)){
            editedAddress= editedAddress.replace(city,"");

        }
        if(editedAddress.contains(State)){
            editedAddress=editedAddress.replace(State,"");

        }
        if(editedAddress.contains(pincode)){
            editedAddress= editedAddress.replace(pincode,"");

        }
        if(editedAddress.contains(country)){
            editedAddress=editedAddress.replace(country,"");

        }
        if(editedAddress.contains(",")){
            editedAddress=editedAddress.replace(","," ");

        }
        return editedAddress;
    }

    public void fnCreateLastKnownGPSLoction(String chekLastGPSLat,String chekLastGPSLong,String chekLastGpsAccuracy)
    {

        try {

            JSONArray jArray=new JSONArray();
            JSONObject jsonObjMain=new JSONObject();


            JSONObject jOnew = new JSONObject();
            jOnew.put( "chekLastGPSLat",chekLastGPSLat);
            jOnew.put( "chekLastGPSLong",chekLastGPSLong);
            jOnew.put( "chekLastGpsAccuracy", chekLastGpsAccuracy);


            jArray.put(jOnew);
            jsonObjMain.put("GPSLastLocationDetils", jArray);

            File jsonTxtFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.AppLatLngJsonFile);
            if (!jsonTxtFolder.exists())
            {
                jsonTxtFolder.mkdirs();

            }
            String txtFileNamenew="GPSLastLocation.txt";
            File file = new File(jsonTxtFolder,txtFileNamenew);
            String fpath = Environment.getExternalStorageDirectory()+"/"+CommonInfo.AppLatLngJsonFile+"/"+txtFileNamenew;


            // If file does not exists, then create it
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


            FileWriter fw;
            try {
                fw = new FileWriter(file.getAbsoluteFile());

                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(jsonObjMain.toString());

                bw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{

        }
    }
   /* public String getAddressOfProviders(String latti,String longi){

        StringBuilder FULLADDRESS2 =new StringBuilder();
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());



        try {
            addresses = geocoder.getFromLocation(Double.parseDouble(latti), Double.parseDouble(longi), 1);

            if (addresses == null || addresses.size()  == 0)
            {
                FULLADDRESS2=  FULLADDRESS2.append("NA");
            }
            else
            {
                for(Address address : addresses) {
                    //  String outputAddress = "";
                    for(int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                        if(i==1)
                        {
                            FULLADDRESS2.append(address.getAddressLine(i));
                        }
                        else if(i==2)
                        {
                            FULLADDRESS2.append(",").append(address.getAddressLine(i));
                        }
                    }
                }
		      *//* //String address = addresses.get(0).getAddressLine(0);
		       String address = addresses.get(0).getAddressLine(1); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
		       String city = addresses.get(0).getLocality();
		       String state = addresses.get(0).getAdminArea();
		       String country = addresses.get(0).getCountryName();
		       String postalCode = addresses.get(0).getPostalCode();
		       String knownName = addresses.get(0).getFeatureName();
		       FULLADDRESS=address+","+city+","+state+","+country+","+postalCode;
		      Toast.makeText(contextcopy, "ADDRESS"+address+"city:"+city+"state:"+state+"country:"+country+"postalCode:"+postalCode, Toast.LENGTH_LONG).show();*//*

            }

        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // Here 1 represent max location result to returned, by documents it recommended 1 to 5


        return FULLADDRESS2.toString();

    }*/
   public String getAddressOfProviders(String latti, String longi){

       StringBuilder FULLADDRESS2 =new StringBuilder();
       Geocoder geocoder;
       List<Address> addresses;
       geocoder = new Geocoder(LauncherActivity.this, Locale.ENGLISH);



       try {
           addresses = geocoder.getFromLocation(Double.parseDouble(latti), Double.parseDouble(longi), 1);

           if (addresses == null || addresses.size()  == 0 || addresses.get(0).getAddressLine(0)==null)
           {
               FULLADDRESS2=  FULLADDRESS2.append("NA");
           }
           else
           {
               FULLADDRESS2 =FULLADDRESS2.append(addresses.get(0).getAddressLine(0));
           }

       } catch (NumberFormatException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } // Here 1 represent max location result to returned, by documents it recommended 1 to 5


       return FULLADDRESS2.toString();

   }
}
