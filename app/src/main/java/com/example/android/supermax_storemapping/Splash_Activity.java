package com.example.android.supermax_storemapping;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.bugsense.trace.BugSenseHandler;
public class Splash_Activity extends BaseActivity {

    public int syncFLAG = 0;

    public String[] xmlForWeb = new String[1];
    int serverResponseCode = 0;
    DatabaseAssistant DA = new DatabaseAssistant(this);


    String imei;
    public int chkFlgForErrorToCloseApp=0;
    public String fDate;
    public SimpleDateFormat sdf;

    private boolean backBtnPress;
    private static final int SPLASH_DURATION=3000;
    private Handler myHandler;
     DBAdapterLtFoods dbengine=new DBAdapterLtFoods(this);
    ServiceWorker newservice = new ServiceWorker();

    public boolean onKeyDown(int keyCode, KeyEvent event)    // Control the PDA Native Button Handling
    {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
           // return true;
             finish();
        }
        if(keyCode==KeyEvent.KEYCODE_HOME)
        {
            // finish();

        }
        if(keyCode==KeyEvent.KEYCODE_MENU)
        {
            return true;
        }
        if(keyCode== KeyEvent.KEYCODE_SEARCH)
        {
            return true;
        }

        return super.onKeyDown(keyCode, event);}
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        BugSenseHandler.setup(this, "cba2ccb2");

        dbengine=new DBAdapterLtFoods(Splash_Activity.this);
        if (Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
       if (Build.VERSION.SDK_INT >= 23)
       {
            if(checkingPermission()){
                onCreateFunctionality();
            }
            else
            {
                ActivityCompat.requestPermissions(Splash_Activity.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
       }
        else{
            onCreateFunctionality();
        }
    }
    public  boolean checkingPermission(){
        if ((checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) )
        {
            return false;
            //onCreateFunctionality();
        }
        else if((checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){
            return false;
        }
        else if((checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)){
            return false;
        }
        else if((checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)){
            return false;
        }
        else if((checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)){
            return false;
        }
        else if((checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)){
            return false;
        }
        else{
            return true;
        }
    }

    public void onCreateFunctionality()
    {
        // SharedPreferences.Editor editor=sharedPreferences.edit();
       /* try
        {
            TelephonyManager tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            imei = tManager.getDeviceId();
        }
        catch(SecurityException e)
        {

        }


       CommonInfo.imei=imei;*/
        imei=getIMEI();
        /*Date date1=new Date();
        sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        fDate = sdf.format(date1).toString().trim();*/
        fDate=getDateInMonthTextFormat();
        //check data is pending or not

        if(isOnline())
        {
            if(dbengine.isXMLfilesOrImageFileLeftToUpload("3",5))
            {
                dbengine.open();
                int checkdataExistOrNot=dbengine. counttblUserName();
                dbengine.close();
                if(checkdataExistOrNot==0)
                {
                    intentPassToLauncherActivity("Your device has no Data Connection.Please ensure Internet is accessible to Continue.","0","0","0");
                }
                else
                {
                    String userName=   dbengine.getUsername();
                    String storeCountDeatails=   dbengine.getTodatAndTotalStores();
                    String   TotalStores = storeCountDeatails.split(Pattern.quote("^"))[0];
                    String   TodayStores = storeCountDeatails.split(Pattern.quote("^"))[1];
                    intentPassToLauncherActivity("0",userName,TotalStores,TodayStores);
                }
            }
            else
            {
                CheckUpdateVersion cuv = new CheckUpdateVersion();
                cuv.execute();
            }

            /*
            //if data pending send it to server

            File LTFoodFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.LTFoodOrderXMLFolder);

            if (!LTFoodFolder.exists())
            {
                LTFoodFolder.mkdirs();
            }

            File del = new File(Environment.getExternalStorageDirectory(), CommonInfo.LTFoodOrderXMLFolder);

            funForCheckSaveExitData();

            // check number of files in folder
            String [] AllFilesNameNotSync= checkNumberOfFiles(del);
            if(AllFilesNameNotSync.length>0)
            {
                if(isOnline())
                {
                    FullSyncDataNow task = new FullSyncDataNow(Splash_Activity.this);
                    task.execute();
                }
                else
                {
                    showNoConnAlert();
                }

            }
            else
            {
                CheckUpdateVersion cuv = new CheckUpdateVersion();
                cuv.execute();
            }*/

        }
        else
        {
            //if data is pending send to next page else show problem to next page
           // showNoConnAlert();
            SharedPreferences sharedPreferences=getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor ed;
            boolean flagForError=false;
            if(sharedPreferences.contains("ServerDate"))
            {
                if(sharedPreferences.getString("ServerDate", "").equals("0"))
                {
                    flagForError=true;
                }
            }
            else
            {
                flagForError=true;

            }

            dbengine.open();
            int checkdataExistOrNot=  dbengine. counttblUserName();
            dbengine.close();
            if(flagForError){
                intentPassToLauncherActivity("Your device has no Data Connection.Please ensure Internet is accessible to Continue.","0","0","0");
            }
            else{
              String userName=   dbengine.getUsername();
                String storeCountDeatails=   dbengine.getTodatAndTotalStores();
                String   TotalStores = storeCountDeatails.split(Pattern.quote("^"))[0];
                String   TodayStores = storeCountDeatails.split(Pattern.quote("^"))[1];
                intentPassToLauncherActivity("0",userName,TotalStores,TodayStores);
            }


        }



    }
   public void intentPassToLauncherActivity(String errorMessageFlag,String username,String totaltarget,String todayTarget)
   {
       Intent intent=new Intent(Splash_Activity.this,LauncherActivity.class);
       intent.putExtra("FROM","SPLASH");
       intent.putExtra("errorMessageFlag",errorMessageFlag); // 0 if no error, if error, then error message passes
       intent.putExtra("username",username);//if error then it will 0
       intent.putExtra("totaltarget",totaltarget);////if error then it will 0
       intent.putExtra("todayTarget",todayTarget);//if error then it will 0
       Splash_Activity.this.startActivity(intent);
       finish();

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

    public void showNoConnAlert()
    {
        AlertDialog.Builder alertDialogNoConn = new AlertDialog.Builder(Splash_Activity.this);
        alertDialogNoConn.setTitle(R.string.genTermNoDataConnection);
        alertDialogNoConn.setMessage(R.string.genTermNoDataConnectionFullMsg);
        alertDialogNoConn.setNeutralButton(R.string.txtOk,
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                        // finish();
                    }
                });
        alertDialogNoConn.setIcon(R.drawable.error_ico);
        AlertDialog alert = alertDialogNoConn.create();
        alert.show();

    }
    private class CheckUpdateVersion extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {

                int DatabaseVersion = dbengine.DATABASE_VERSION;
                int ApplicationID = dbengine.Application_TypeID;
               newservice = newservice.getAvailableAndUpdatedVersionOfAppNew(getApplicationContext(), imei, fDate, DatabaseVersion, ApplicationID);
                if (!newservice.director.toString().trim().equals("1"))
                {
                    if (chkFlgForErrorToCloseApp == 0)
                    {
                        chkFlgForErrorToCloseApp = 1;
                    }

                }

            } catch (Exception e) {
            } finally {
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (chkFlgForErrorToCloseApp == 1)   // if Webservice showing exception or not excute complete properly
            {
                chkFlgForErrorToCloseApp = 0;
                intentPassToLauncherActivity("Internet connection is slow ,please try again.", "0", "0", "0");
              //  Toast.makeText(getApplicationContext(), "Internet connection is slow ,please try again.", Toast.LENGTH_LONG).show();

            } else {
                dbengine.open();
                int checkUserAuthenticate = dbengine.FetchflgUserAuthenticated();
                int flgAppStatus= dbengine.FetchflgAppStatus();
                int flgValidApplication= dbengine.FetchflgValidApplication();
                dbengine.close();

                if (checkUserAuthenticate == 0)   // 0 means-->New user        1 means-->Exist User
                {
                    intentPassToLauncherActivity("This phone is not registered, please contact administrator.","0","0","0");
                    return;

                } // this flgValidApplication is used when SO Try to use DSR App or DSR try to use SO App
               /* else if (flgValidApplication == 0)   // 0 means-->User not valid for this App       1 means-->User valid for this App
                {
                   String DisplayMessage=dbengine.FetchMessageForInvalid();
                    showAlert(DisplayMessage);
                    return;

                } // this flgAppStatus is used when User try to run this App before data(like 20 jan is using date but try to run it 19 jan)
                else if (flgAppStatus == 0)   // 0 means-->User can not use this App       1 means-->User can  use this App
                {
                     String DisplayMessage=dbengine.FetchDisplayMessage();
                    showAlert(DisplayMessage);
                    return;

                }*/
                dbengine.open();
                int check = dbengine.FetchVersionDownloadStatus();  // 0 means-->new version installed  1 means-->new version not install
                dbengine.close();
                if (check == 1) {
                      showNewVersionAvailableAlert();
                   // Toast.makeText(getApplicationContext(), "new version", Toast.LENGTH_LONG).show();
                } else {


                    dbengine.open();
                    int chkIfPDADaeExistOrNot = dbengine.fnCheckPdaDateExistOrNot();
                    dbengine.maintainPDADate();
                    String getPDADate = dbengine.fnGetPdaDate();
                    String getServerDate = dbengine.fnGetServerDate();

                    dbengine.close();
                    if (!getServerDate.equals(getPDADate)) {

                        // showAlertBox("Your Phone  Date is not correct.Please Correct it.");
                        intentPassToLauncherActivity("Your Phone Date is not correct.Please Correct it.", "0", "0", "0");
                       // Toast.makeText(getApplicationContext(), "Your Phone  Date is not correct.Please Correct it.", Toast.LENGTH_LONG).show();
                        return;
                    }


                    try {
                       /* funGetRegistrationID("Create Registration ID");
                        checkPlayServices();*/

                         //code here
                        SharedPreferences sharedPreferences=getSharedPreferences("MyPref", MODE_PRIVATE);
                        SharedPreferences.Editor ed;
                        if(!sharedPreferences.contains("ServerDate"))
                        {
                            ed = sharedPreferences.edit();
                            ed.putString("ServerDate", "0");
                            ed.commit();
                            //call second webservice
                            GetStoreAllData getStoreAllDataAsync= new GetStoreAllData();
                            getStoreAllDataAsync.execute();
                          //  Toast.makeText(getApplicationContext(), "first time call second webservice", Toast.LENGTH_LONG).show();
                       //goto next page
                        }
                        else{
                            if(sharedPreferences.getString("ServerDate", "").equals(getServerDate)){

                                /*String userName=   dbengine.getUsername();
                                String storeCountDeatails=   dbengine.getTodatAndTotalStores();
                                String   TotalStores = storeCountDeatails.split(Pattern.quote("^"))[0];
                                String   TodayStores = storeCountDeatails.split(Pattern.quote("^"))[1];
                                if(dbengine.fngetcountAddedstore()==0)
                                {
                                    Intent intent =new Intent(Splash_Activity.this,StorelistActivity.class);
                                    Splash_Activity.this.startActivity(intent);
                                    finish();

                                }
                                else
                                {
                                    intentPassToLauncherActivity("0", userName, TotalStores, TodayStores);
                                }*/
                                String userName=   dbengine.getUsername();
                                String storeCountDeatails=   dbengine.getTodatAndTotalStores();
                                String   TotalStores="0",TodayStores="0";
                                if(storeCountDeatails.contains("^"))
                                {
                                    TotalStores = storeCountDeatails.split(Pattern.quote("^"))[0];
                                    TodayStores = storeCountDeatails.split(Pattern.quote("^"))[1];
                                    if(dbengine.fngetcountAddedstore()==0)
                                    {
                                        Intent intent =new Intent(Splash_Activity.this,StorelistActivity.class);
                                        Splash_Activity.this.startActivity(intent);
                                        finish();

                                    }
                                    else
                                    {

                                        intentPassToLauncherActivity("0", userName, TotalStores, TodayStores);
                                    }
                                }
                                else
                                {
                                    GetStoreAllData getStoreAllDataAsync= new GetStoreAllData();
                                    getStoreAllDataAsync.execute();
                                }

                              //  Toast.makeText(getApplicationContext(), "Shared prf date server date same dont call second webservice", Toast.LENGTH_LONG).show();
                                //goto nextpage
                            }
                           else{
                               // call webservice
                               ed = sharedPreferences.edit();
                                ed.putString("ServerDate", "0");
                                ed.commit();
                                GetStoreAllData getStoreAllDataAsync= new GetStoreAllData();
                                getStoreAllDataAsync.execute();
                                //update sharedpreferences
                               // Toast.makeText(getApplicationContext(), "Shared prf date server date  not same , call second webservice", Toast.LENGTH_LONG).show();

                                //goto nextpage
                            }

                        }
                       // Toast.makeText(getApplicationContext(), "everything is fine", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }
    public void showAlert(String abcd)
    {
        AlertDialog.Builder alertDialogNoConn = new AlertDialog.Builder(Splash_Activity.this);
        alertDialogNoConn.setTitle("Info");
        alertDialogNoConn.setMessage(abcd);
        alertDialogNoConn.setNeutralButton(R.string.txtOk,
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialogNoConn.setIcon(R.drawable.error_ico);
        AlertDialog alert = alertDialogNoConn.create();
        alert.show();

    }

    private class GetStoreAllData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {

                int DatabaseVersion = dbengine.DATABASE_VERSION;
                int ApplicationID = dbengine.Application_TypeID;
                //newservice = newservice.getAvailableAndUpdatedVersionOfApp(getApplicationContext(), imei,fDate,DatabaseVersion,ApplicationID);


                for(int mm = 1; mm<5; mm++)
                {
                    System.out.println("Ohm Namah Sivay = "+mm);
                    if(mm==1)
                    {
                        newservice = newservice.getStoreAllDetails(getApplicationContext(), imei, fDate, DatabaseVersion, ApplicationID);
                        if (!newservice.director.toString().trim().equals("1")) {
                            if (chkFlgForErrorToCloseApp == 0) {
                                chkFlgForErrorToCloseApp = 1;
                                break;
                            }

                        }


                    }
                    if(mm==2)
                    {
                        newservice = newservice.callfnSingleCallAllWebService(getApplicationContext(),ApplicationID,imei);
                        if (!newservice.director.toString().trim().equals("1")) {
                            if (chkFlgForErrorToCloseApp == 0) {
                                chkFlgForErrorToCloseApp = 1;
                                break;
                            }

                        }

                    }
                    if(mm==3)
                    {
                      /*  newservice = newservice.getQuotationDataFromServer(getApplicationContext(), fDate, imei, "0");
                        if (!newservice.director.toString().trim().equals("1")) {
                            if (chkFlgForErrorToCloseApp == 0) {
                                chkFlgForErrorToCloseApp = 1;
                                break;
                            }

                        }*/

                    }

                    if(mm==4)
                    {


                       /* newservice = newservice.fnGetStateCityListMstr(Splash_Activity.this,imei, fDate,ApplicationID);
                        if(!newservice.director.toString().trim().equals("1"))
                        {
                            if(chkFlgForErrorToCloseApp==0)
                            {
                                chkFlgForErrorToCloseApp=1;
                                break;
                            }

                        }*/
                    }
                }






            } catch (Exception e) {
            } finally {
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (chkFlgForErrorToCloseApp == 1)   // if Webservice showing exception or not excute complete properly
            {
                chkFlgForErrorToCloseApp = 0;
                SharedPreferences sharedPreferences=getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor ed;
                ed = sharedPreferences.edit();
                ed.putString("ServerDate", "0");
                ed.commit();
               /* if(sharedPreferences.contains("ServerDate"))
                {

                }*/
                //clear sharedpreferences
                intentPassToLauncherActivity("Internet connection is slow ,please try again.", "0", "0", "0");
            }
            else {
                SharedPreferences sharedPreferences=getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor ed;
                ed = sharedPreferences.edit();
                dbengine.open();
                String getServerDate = dbengine.fnGetServerDate();
                dbengine.close();
                ed.putString("ServerDate", getServerDate);
                ed.commit();
                String userName=   dbengine.getUsername();
                String storeCountDeatails=   dbengine.getTodatAndTotalStores();
                String   TotalStores = storeCountDeatails.split(Pattern.quote("^"))[0];
                String   TodayStores = storeCountDeatails.split(Pattern.quote("^"))[1];


                //if
                if(dbengine.fngetcountAddedstore()==0)
                {
                    Intent intent =new Intent(Splash_Activity.this,StorelistActivity.class);
                    Splash_Activity.this.startActivity(intent);
                    finish();

                }
                else
                {
                    intentPassToLauncherActivity("0", userName, TotalStores, TodayStores);
                }
                //intentPassToLauncherActivity("0", userName, TotalStores, TodayStores);
               //else


                //send to storelist or launcher
                //next code is here
            }
        }

    }

    public void showNewVersionAvailableAlert()
    {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
        AlertDialog.Builder alertDialogNoConn = new AlertDialog.Builder(Splash_Activity.this);
        alertDialogNoConn.setTitle(R.string.genTermInformation);
        alertDialogNoConn.setCancelable(false);
        alertDialogNoConn.setMessage(getText(R.string.newVersionAlertOnLauncher));
        alertDialogNoConn.setNeutralButton(R.string.txtOk,new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                GetUpdateInfo task = new GetUpdateInfo(Splash_Activity.this);
                task.execute();
                dialog.dismiss();
            }
        });

        alertDialogNoConn.setIcon(R.drawable.info_ico);
        AlertDialog alert = alertDialogNoConn.create();
        alert.show();

    }
    private class GetUpdateInfo extends AsyncTask<Void, Void, Void>
    {

        ProgressDialog pDialogGetStores;
        public GetUpdateInfo(Splash_Activity activity)
        {
            pDialogGetStores = new ProgressDialog(activity);
        }


        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            pDialogGetStores.setTitle(getText(R.string.genTermPleaseWaitNew));
            pDialogGetStores.setMessage(getText(R.string.genTermDownloadData));
            pDialogGetStores.setIndeterminate(false);
            pDialogGetStores.setCancelable(false);
            pDialogGetStores.setCanceledOnTouchOutside(false);
            pDialogGetStores.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {

            try
            {
                downloadapk();
            }
            catch(Exception e)
            {}

            finally
            {}

            return null;
        }


        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);

            if(pDialogGetStores.isShowing())
            {
                pDialogGetStores.dismiss();
            }

            installApk();
        }
    }
    private void installApk()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed;
        ed = sharedPreferences.edit();
        ed.clear();
        ed.commit();

        this.deleteDatabase(DBAdapterLtFoods.DATABASE_NAME);
        File file = new File(Environment.getExternalStorageDirectory() + "/download/" + CommonInfo.VersionDownloadAPKName);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(getBaseContext(), getApplicationContext().getPackageName() + ".provider", file);
            //  Uri contentUri = FileProvider.getUriForFile(getApplicationContext(),"project.astix.com.rsplsosfaindirect.fileprovider" , );
            // Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + CommonInfo.VersionDownloadAPKName));

            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + CommonInfo.VersionDownloadAPKName));
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }

        startActivity(intent);
        finish();


       /* this.deleteDatabase(DBAdapterLtFoods.DATABASE_NAME);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + "ParagIndirect.apk"));
        Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + CommonInfo.VersionDownloadAPKName));

        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);
        finish();*/




    }
    private void downloadapk()
    {
        try {

            //ParagIndirectTest
            // URL url = new URL("http://115.124.126.184/downloads/ParagIndirect.apk");
            //  URL url = new URL("http://115.124.126.184/downloads/ParagIndirectTest.apk");
            URL url = new URL(CommonInfo.VersionDownloadPath.trim()+CommonInfo.VersionDownloadAPKName);
            URLConnection connection = url.openConnection();
            HttpURLConnection urlConnection = (HttpURLConnection) connection;
            //urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            //urlConnection.setDoOutput(false);
            // urlConnection.setInstanceFollowRedirects(false);
            urlConnection.connect();

            //if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            // {
            File sdcard = Environment.getExternalStorageDirectory();

            //  //System.out.println("sunil downloadapk sdcard :" +sdcard);
            //File file = new File(sdcard, "neo.apk");

            String PATH = Environment.getExternalStorageDirectory() + "/download/";
            // File file2 = new File(PATH+"ParagIndirect.apk");
            File file2 = new File(PATH+CommonInfo.VersionDownloadAPKName);
            if(file2.exists())
            {
                file2.delete();
            }

            File file1 = new File(PATH);
            file1.mkdirs();

            // File file = new File(file1, "ParagIndirect.apk");
            File file = new File(file1, CommonInfo.VersionDownloadAPKName);
            //  FileOutputStream fos = new FileOutputStream(file);


            //  //System.out.println("sunil downloadapk making directory :" +sdcard);

            int size = connection.getContentLength();
            //  //System.out.println("sunil downloadapk getting size :" +size);

            FileOutputStream fileOutput = new FileOutputStream(file);
            //  //System.out.println("two");
            InputStream inputStream = urlConnection.getInputStream();
            //  //System.out.println("sunil downloadapk sdcard called :" +sdcard);
            byte[] buffer = new byte[10240];
            int bufferLength = 0;
            int current = 0;
            while ( (bufferLength = inputStream.read(buffer)) != -1 ) {
                fileOutput.write(buffer, 0, bufferLength);
            }
            //current+=bufferLength;
            fileOutput.close();

            //  //System.out.println("");
            //   //System.out.println("sunil downloadapk completed ");
            //checkUnknownSourceEnability();
            //initiateInstallation();
            //  }


        } catch (MalformedURLException e)
        {
            //  e.printStackTrace();
            //   //System.out.println("sunil downloadapk failed ");
        } catch (IOException e) {
            //   e.printStackTrace();
            //   //System.out.println("sunil downloadapk failed ");

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if((grantResults[0]== PackageManager.PERMISSION_GRANTED) && (grantResults[1]== PackageManager.PERMISSION_GRANTED) && (grantResults[2]== PackageManager.PERMISSION_GRANTED) && (grantResults[3]== PackageManager.PERMISSION_GRANTED)&& (grantResults[4]== PackageManager.PERMISSION_GRANTED))
        {
            onCreateFunctionality();
        }
        else
        {
            finish();

        }
    }
/*
    public void funForCheckSaveExitData()
    {
        try
        {
            int checkSaveExitData = dbengine.CheckIfSavedDataExist();
            if(checkSaveExitData==1)
            {
                long  syncTIMESTAMP = System.currentTimeMillis();
                Date dateobj = new Date(syncTIMESTAMP);
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String StampEndsTime = df.format(dateobj);


                dbengine.open();
                dbengine.UpdateStoreFlagForSaveExit("1");
                dbengine.close();


                SimpleDateFormat df1 = new SimpleDateFormat(imei+"."+ "dd.MM.yyyy.HH.mm.ss",Locale.ENGLISH);

                String newfullFileName=df1.format(dateobj);




                try {


                    File LTFoodxmlFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.RSPLOrderXMLFolder);

                    if (!LTFoodxmlFolder.exists())
                    {
                        LTFoodxmlFolder.mkdirs();

                    }


                    DA.open();
                    DA.export(CommonInfo.DATABASE_NAME, newfullFileName);
                    DA.close();



                    dbengine.savetbl_XMLfiles(newfullFileName, "3");
                    dbengine.open();
                    dbengine.UpdateStoreFlagForALLSubmit("5");

                    dbengine.close();



                } catch (Exception e)
                {

                    e.printStackTrace();

                }
            }
        }
        catch(Exception e)
        {

        }
    }*/

    private class FullSyncDataNow extends AsyncTask<Void, Void, Void>
    {


        ProgressDialog pDialogGetStores;
        public FullSyncDataNow(Splash_Activity activity)
        {
            pDialogGetStores = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            File LTFoodXMLFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.RSPLOrderXMLFolder);

            if (!LTFoodXMLFolder.exists())
            {
                LTFoodXMLFolder.mkdirs();
            }


            pDialogGetStores.setTitle(getText(R.string.genTermPleaseWaitNew));
            pDialogGetStores.setMessage("Submitting Pending Data...");
            pDialogGetStores.setIndeterminate(false);
            pDialogGetStores.setCancelable(false);
            pDialogGetStores.setCanceledOnTouchOutside(false);
            pDialogGetStores.show();


        }

        @Override

        protected Void doInBackground(Void... params)
        {


            try
            {



                File del = new File(Environment.getExternalStorageDirectory(), CommonInfo.RSPLOrderXMLFolder);

                // check number of files in folder
                String [] AllFilesName= checkNumberOfFiles(del);


                if(AllFilesName.length>0)
                {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);


                    for(int vdo=0;vdo<AllFilesName.length;vdo++)
                    {
                        String fileUri=  AllFilesName[vdo];


                        //System.out.println("Sunil Again each file Name :" +fileUri);

                        if(fileUri.contains(".zip"))
                        {
                            File file = new File(fileUri);
                            file.delete();
                        }
                        else
                        {
                            String f1=Environment.getExternalStorageDirectory().getPath()+ "/" + CommonInfo.RSPLOrderXMLFolder + "/" +fileUri;
                            // System.out.println("Sunil Again each file full path"+f1);
                            try
                            {
                                upLoad2Server(f1,fileUri);
                            }
                            catch (Exception e)
                            {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                    }

                }
                else
                {

                }







            } catch (Exception e)
            {

                e.printStackTrace();
                if(pDialogGetStores.isShowing())
                {
                    pDialogGetStores.dismiss();
                }
            }
            return null;
        }

        @Override
        protected void onCancelled() {

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(pDialogGetStores.isShowing())
            {
                pDialogGetStores.dismiss();
            }

            if(syncFLAG == 0)
            {


                CheckUpdateVersion cuv = new CheckUpdateVersion();
                cuv.execute();


            }
            else
            {
                CheckUpdateVersion cuv = new CheckUpdateVersion();
                cuv.execute();
            }


        }
    }

    public void delXML(String delPath)
    {
        File file = new File(delPath);
        file.delete();
        File file1 = new File(delPath.toString().replace(".xml", ".zip"));
        file1.delete();
    }

    public static String[] checkNumberOfFiles(File dir)
    {
        int NoOfFiles=0;
        String [] Totalfiles = null;

        if (dir.isDirectory())
        {
            String[] children = dir.list();
            NoOfFiles=children.length;
            Totalfiles=new String[children.length];

            for (int i=0; i<children.length; i++)
            {
                Totalfiles[i]=children[i];
            }
        }
        return Totalfiles;
    }

    public static void zip(String[] files, String zipFile) throws IOException
    {
        BufferedInputStream origin = null;
        final int BUFFER_SIZE = 2048;

        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
        try {
            byte data[] = new byte[BUFFER_SIZE];

            for (int i = 0; i < files.length; i++) {
                FileInputStream fi = new FileInputStream(files[i]);
                origin = new BufferedInputStream(fi, BUFFER_SIZE);
                try {
                    ZipEntry entry = new ZipEntry(files[i].substring(files[i].lastIndexOf("/") + 1));
                    out.putNextEntry(entry);
                    int count;
                    while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
                        out.write(data, 0, count);
                    }
                }
                finally {
                    origin.close();
                }
            }
        }

        finally {
            out.close();
        }
    }



    public  int upLoad2Server(String sourceFileUri,String fileUri)
    {

        fileUri=fileUri.replace(".xml", "");

        String fileName = fileUri;
        String zipFileName=fileUri;

        String newzipfile = Environment.getExternalStorageDirectory() + "/"+CommonInfo.RSPLOrderXMLFolder+"/" + fileName + ".zip";

        sourceFileUri=newzipfile;

        xmlForWeb[0]=         Environment.getExternalStorageDirectory() + "/"+CommonInfo.RSPLOrderXMLFolder+"/" + fileName + ".xml";


        try
        {
            zip(xmlForWeb,newzipfile);
        }
        catch (Exception e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            //java.io.FileNotFoundException: /359648069495987.2.21.04.2016.12.44.02: open failed: EROFS (Read-only file system)
        }


        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;


        File file2send = new File(newzipfile);

        String urlString = CommonInfo.OrderSyncPath.trim()+"?CLIENTFILENAME=" + zipFileName;

        try {

            // open a URL connection to the Servlet
            FileInputStream fileInputStream = new FileInputStream(file2send);
            URL url = new URL(urlString);

            // Open a HTTP  connection to  the URL
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("zipFileName", zipFileName);

            dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                    + zipFileName + "\"" + lineEnd);

            dos.writeBytes(lineEnd);

            // create a buffer of  maximum size
            bytesAvailable = fileInputStream.available();

            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            // read file and write it into form...
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0)
            {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            // send multipart form data necesssary after file data...
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // Responses from the server (code and message)
            serverResponseCode = conn.getResponseCode();
            String serverResponseMessage = conn.getResponseMessage();

            //Log.i("uploadFile", "HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);

            if(serverResponseCode == 200)
            {
                syncFLAG = 1;

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                // editor.remove(xmlForWeb[0]);
                editor.putString(fileUri, ""+4);
                editor.commit();

                String FileSyncFlag=pref.getString(fileUri, ""+1);

                delXML(xmlForWeb[0].toString());


            }
            else
            {
                syncFLAG = 0;
            }

            //close the streams //
            fileInputStream.close();
            dos.flush();
            dos.close();

        } catch (MalformedURLException ex)
        {
            ex.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }




        return serverResponseCode;

    }
}
