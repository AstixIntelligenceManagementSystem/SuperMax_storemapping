package com.example.android.supermax_storemapping;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by ALOK on 9/7/2017.
 */

public class BroadcastReceiverOnBootComplete extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            DBAdapterLtFoods helperDb=new DBAdapterLtFoods(context);
           String prvsStoreId=helperDb.getPreviousShownPopUpStoreId();

            if(!TextUtils.isEmpty(prvsStoreId))
            {

                helperDb.updateisRestartDoneByDSR(prvsStoreId);
               /* Intent intentTemp=new Intent(context,Splash_Activity.class);
                intentTemp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentTemp);*/
            }
        }

    }
}
