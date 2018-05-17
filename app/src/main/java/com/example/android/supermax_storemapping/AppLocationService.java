package com.example.android.supermax_storemapping;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

public class AppLocationService extends Service implements LocationListener, com.google.android.gms.location.LocationListener {

	 private final Context mContext=this;
	private static final long MIN_DISTANCE_FOR_UPDATE=0;
	private static final long MIN_TIME_FOR_UPDATE=0;
 
	public AppLocationService(){
		
		//locationManager=(LocationManager) con.getSystemService(LOCATION_SERVICE);
	}
	
	
	 @Override
	 public void onCreate() {
	 // Toast.makeText(this, "Service was Created", Toast.LENGTH_LONG).show();
		
	 }

	  @Override
	 public void onStart(Intent intent, int startId) {
		  
		  //locationManager=(LocationManager) this.getSystemService(LOCATION_SERVICE);
	  }
	public Location getLocation(LocationManager lmn,String prd,Location lok)
	{
		 //locationManager=(LocationManager)this.getSystemService(LOCATION_SERVICE);
		//if(locationManager.isProviderEnabled(provider)){
		//lmn.removeUpdates(this);
		//funCallLocaUpdate(lmn,prd,lok);
		lok=null;
		if(lmn.isProviderEnabled(prd))
				{
			lmn.requestLocationUpdates(prd, MIN_TIME_FOR_UPDATE, MIN_DISTANCE_FOR_UPDATE, this);
			if(lmn!=null){
				lok=lmn.getLastKnownLocation(prd);
				 String chekLastGPSLat="0";
				 String chekLastGPSLong="0";
				 String chekLastGpsAccuracy="0";	
				if(prd.equals(lmn.GPS_PROVIDER)){
					
					try {


						File jsonTxtFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.AppLatLngJsonFile);
						if (!jsonTxtFolder.exists()) 
						{
							jsonTxtFolder.mkdirs();
							 
						} 
						String txtFileNamenew="GPSLastLocation.txt";
						File file = new File(jsonTxtFolder,txtFileNamenew);
					String fpath = Environment.getExternalStorageDirectory()+"/"+CommonInfo.AppLatLngJsonFile+"/"+txtFileNamenew;
						 
		       
		         // If file does not exists, then create it
		         if (file.exists()) {
		             StringBuffer buffer=new StringBuffer();
					String myjson_stampiGPSLastLocation="";
					StringBuffer sb = new StringBuffer();
					BufferedReader br = null;
					
					try {
					    br = new BufferedReader(new FileReader(file));

					    String temp;
					    while ((temp = br.readLine()) != null)
					        sb.append(temp);
					} catch (IOException e) {
					    e.printStackTrace();
					} finally {
					    try {
					        br.close(); // stop reading
					    } catch (IOException e) {
					        e.printStackTrace();
					    }
					}    
					
					myjson_stampiGPSLastLocation=sb.toString();
					
					JSONObject jsonObjGPSLast = new JSONObject(myjson_stampiGPSLastLocation);
					JSONArray jsonObjGPSLastInneralues = jsonObjGPSLast.getJSONArray("GPSLastLocationDetils");
					
					String StringjsonGPSLastnew = jsonObjGPSLastInneralues.getString(0);
					JSONObject jsonObjGPSLastnewwewe = new JSONObject(StringjsonGPSLastnew);
					
					  chekLastGPSLat=jsonObjGPSLastnewwewe.getString("chekLastGPSLat");
					  chekLastGPSLong=jsonObjGPSLastnewwewe.getString("chekLastGPSLong");
					  chekLastGpsAccuracy=jsonObjGPSLastnewwewe.getString("chekLastGpsAccuracy");
					 if(lok!=null)
					 {
						 if (String.valueOf(Double.valueOf(lok.getLatitude())).equals(chekLastGPSLat) && String.valueOf(Double.valueOf(lok.getLongitude())).equals(chekLastGPSLong) && String.valueOf(Double.valueOf(lok.getAccuracy())).equals(chekLastGpsAccuracy))
						 {

							 System.out.println("NIKITA" + String.valueOf(lok.getLatitude()) + "," + chekLastGPSLat + "," + String.valueOf(lok.getLongitude()) + "," + String.valueOf(lok.getAccuracy()) + "," + chekLastGpsAccuracy);
							 lok = null;

						 }

					 }



				 }
		         

		         
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
				return lok;
			}
		}
		
		return null;
	}
	
	public void funCallLocaUpdate(LocationManager lmn,String prd,Location lok)
	{
		if(lok!=null){
			//LocationManager locationManager=(LocationManager) this.getSystemService(this.LOCATION_SERVICE);
			this.getLocation(lmn,prd,lok);
			this.getLocation(lmn,prd,lok);
			}
	}
	@Override
	public void onLocationChanged(Location args0) {
		/*if(args0!=null){
		LocationManager locationManager=(LocationManager) this.getSystemService(this.LOCATION_SERVICE);
		this.getLocation(locationManager,locationManager.GPS_PROVIDER,args0);
		this.getLocation(locationManager,locationManager.NETWORK_PROVIDER,args0);
		}*/
		/*if(location!=null){
			Toast.makeText(this, "UPDATED LOCATION(GPS)\nLATTITUDE:"+location.getLatitude() +"\nLONGITUDE:"+location.getLatitude()+"Accuracy"+location.getAccuracy() , Toast.LENGTH_LONG).show();
		}*/
		// TODO Auto-generated method stub
	//	Toast.makeText(this, "UPDATED LOCATION(GPS)\nLATTITUDE:"+location.getLatitude() +"\nLONGITUDE:"+location.getLatitude()+"Accuracy"+location.getAccuracy() , Toast.LENGTH_LONG).show();
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		 this.stopSelf();
		// System.exit(0);
	}
	public void KillServiceLoc(AppLocationService cls, LocationManager lonck)
	{
		cls.stopSelf();
		lonck.removeUpdates(cls);//(lonck);
		
		//System.exit(0);
	}
	/*@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		 this.stopSelf();
		  System.exit(0);
	}*/

}
