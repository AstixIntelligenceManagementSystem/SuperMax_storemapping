package com.example.android.supermax_storemapping;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;



import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
public class DBAdapterLtFoods {
    public static String DATABASE_NAME = CommonInfo.DATABASE_NAME;
    public static int DATABASE_VERSION = CommonInfo.DATABASE_VERSIONID;
    public static String AppVersionID = CommonInfo.AppVersionID.trim();

    public static int Application_TypeID = CommonInfo.Application_TypeID;

    private static final String TABLE_tblStateCityMaster="tblStateCityMaster";
    private static final String DATABASE_CREATE_TABLE_tblStateCityMaster="create table tblStateCityMaster(" +
            "StateID int null, State text null,CityID int null,City text null,CityDefault integer null);";

    private static final String DATABASE_TABLE_MAIN1User = "tblUserAuthenticationMstr";
    private static final String DATABASE_CREATE_TABLE_1User = "create table tblUserAuthenticationMstr (flgUserAuthenticated text null,flgAppStatus text null,DisplayMessage text null,flgValidApplication text null,MessageForInvalid text null);";
    private static final String DATABASE_TABLE_MAIN1 = "tblAvailableVersionMstr";
    private static final String DATABASE_CREATE_TABLE_1 = "create table tblAvailableVersionMstr (VersionID text null,VersionSerialNo text null,VersionDownloadStatus text null,ServerDate text null);";//, AutoIdOutlet int null
    private static final String DATABASE_TABLE_MAIN11 = "tblPdaDate";
    private static final String DATABASE_CREATE_TABLE_11 = "create table tblPdaDate(PdaDate text null);";
    private static final String DATABASE_TABLE_NewStoreSalesQuotePaymentDetails="tblNewStoreSalesQuotePaymentDetails";
    private static final String DATABASE_NewStoreSalesQuotePaymentDetails = "create table tblNewStoreSalesQuotePaymentDetails (StoreId text null,PymtStageId text null,Sstat text null);";
    //store details tables
    private static final String TABLE_QSTOUTCHANNEL = "tblQuestIDForOutChannel";
    private static final String TABLE_QST_NAME = "tblQuestIDForName";

    private static final String DATABASE_CREATE_TABLE_QSTOUTCHANNEL = "create table tblQuestIDForOutChannel(GrpQstId int null,QuestID int null,OptID text null,SectionCount int null);";
    private static final String DATABASE_CREATE_TABLE_QST_NAME = "create table tblQuestIDForName(GrpQstId int null,QuestID int null);";

    private static final String DATABASE_TABLE_tblUserName = "tblUserName";
    private static final String DATABASE_TABLE_tblStoreCountDetails = "tblStoreCountDetails";
    private static final String DATABASE_TABLE_tblPreAddedStores = "tblPreAddedStores";
    private static final String DATABASE_TABLE_tblPreAddedStoresDataDetails = "tblPreAddedStoresDataDetails";

    private static final String DATABASE_TABLE_tblLocationDetails = "tblLocationDetails";

    private static final String DATABASE_TABLE_tblSameLocationForStoreRestartDone = "tblsameLocationForStoreRestartDone";


    //account census
    private static final String DATABASE_TABLE_tblDAGetAddedOutletSummaryReport = "tblDAGetAddedOutletSummaryReport";
    private static final String DATABASE_CREATE_TABLE_tblDAGetAddedOutletSummaryReport = "create table tblDAGetAddedOutletSummaryReport (Header text null,Child text null,TotalStores text null,Validated text null,Pending text null,FlgNormalOverall int null);";

    private static final String DATABASE_CREATE_TABLE_tblUserName = "create table tblUserName (UserName text null);";
    private static final String DATABASE_CREATE_TABLE_tblStoreCountDetails = "create table tblStoreCountDetails (TotStoreAdded int null,TodayStoreAdded int null);";
    private static final String DATABASE_CREATE_TABLE_tblPreAddedStores = "create table tblPreAddedStores (StoreID text null,StoreName text null,LatCode text null,LongCode text null,DateAdded text null,DistanceNear int null,flgOldNewStore int null,flgReMap int null,Sstat int null);";
    private static final String DATABASE_CREATE_TABLE_tblPreAddedStoresDataDetails = "create table tblPreAddedStoresDataDetails (StoreIDDB text null,GrpQuestID text null,QstId text null,AnsControlTypeID text null,AnsTextVal text null,flgPrvVal text null);";




    private static final String DATABASE_CREATE_TABLE_tblLocationDetails = "create table tblLocationDetails (Lattitude text null,Longitude text null,Accuracy text null,Address text null,City text null,Pincode text null,State text null,fnAccurateProvider  text null,GpsLat  text null,GpsLong  text null,GpsAccuracy  text null,NetwLat  text null,NetwLong  text null,NetwAccuracy  text null,FusedLat  text null,FusedLong  text null,FusedAccuracy  text null,AllProvidersLocation  text null,GpsAddress  text null,NetwAddress  text null,FusedAddress  text null,FusedLocationLatitudeWithFirstAttempt  text null,FusedLocationLongitudeWithFirstAttempt  text null,FusedLocationAccuracyWithFirstAttempt  text null);";
    private static final String DATABASE_TABLE_MAINtblStoreDeatils = "tblStoreDetails";

    private static final String DATABASE_CREATE_TABLE_tblStoreDeatils = "create table tblStoreDetails(StoreID text not null,StoreName text null," +
            "ActualLatitude text null,ActualLongitude text null, VisitStartTS text null,VisitEndTS text null," +
            "LocProvider text null, Accuracy text null, BateryLeftStatus text null," +
            "IsStoreDataCompleteSaved int null,PaymentStage text null," +
            "flgLocationTrackEnabled integer null,StoreAddress text null,StoreCity text null,StorePinCode text null,StoreState text null,Sstat integer not null,flgLocationServicesOnOff int null,flgGPSOnOff,flgNetworkOnOff int null,flgFusedOnOff int null,flgInternetOnOffWhileLocationTracking int null,flgRestart int null,flgStoreOrder int null,MapAddress text null,MapCity text null,MapPinCode text null,MapState text null,CityId text null,StateId text null);";

   //dynamic tables
    private static final String TABLE_QuestionMstr = "tblQuestionMstr";
    private static final String TABLE_QuestGrpMappingMstr = "tblPDAQuestGrpMappingMstr";
    private static final String TABLE_OptionMstr = "tblOptionMstr";
    private static final String TABLE_QuestionDependentMstr = "tblQuestionDependentMstr";
    private static final String TABLE_QuestOptionDependentMstr = "tblPDAQuestOptionDependentMstr";
    private static final String TABLE_QuestOptionValuesDependentMstr = "tblPDAQuestOptionValuesDependentMstr";
    private static final String TABLE_IMAGE = "tableImage";

    private static final String TABLE_XMLFILES = "tbl_XMLfiles";
    private static final String DATABASE_CREATE_TABLE_XMLfiles = "create table tbl_XMLfiles(XmlFileName text null,Sstat text null);";

    private static final String DATABASE_CREATE_TABLE_tblSameLocationForStoreRestartDone="create table tblsameLocationForStoreRestartDone(UniqueID INTEGER PRIMARY KEY AUTOINCREMENT,prvsStoreID text null,CrntStoreID text null,isSavedOrSubmittedStore text null,isMsgToRestartPopUpShown text null,isRestartDoneByDSR text null ,Sstat integer null,ActionTime text null);";
    private static final String DATABASE_CREATE_TABLE_QUESTIONMstr = "create table tblQuestionMstr(QuestID int null,QuestCode int null,QuestDesc text null,QuestType int null,AnsControlType int null,AnsControlInputTypeID int null,AnsControlInputTypeMinLength int null,AnsControlInputTypeMaxLength int null,AnsMustRequiredFlg int null,QuestBundleFlg int null,ApplicationTypeID int null,Sequence int null,AnsHint text null,flgQuestIDForOutChannel int null);";
    private static final String DATABASE_CREATE_TABLE_QuestGrpMappingMstr = "create table tblPDAQuestGrpMappingMstr(GrpQuestID int null,QuestID int null,GrpID int null,GrpNodeID int null,GrpDesc text null,SectionNo int null,GrpCopyID int null,QuestCopyID int null,Sequence int null);";
    private static final String DATABASE_CREATE_TABLE_OPTIONMstr = "create table tblOptionMstr(OptID text null,QuestID int null,OptionNo int null,OptionDescr text null,Sequence int null );";
    private static final String DATABASE_CREATE_TABLE_QUESTION_DEPENDENTMstr = "create table tblQuestionDependentMstr(QuestionID int null,OptionID text null,DependentQuestionID int null,GrpID int null,GrpDepQuestID int null);";
    private static final String DATABASE_CREATE_TABLE_QUESTION_OPTION_DEPENDENTMstr = "create table tblPDAQuestOptionDependentMstr(QstID int null,DepQstId int null,GrpQuestID int null,GrpDepQuestID int null);";
    private static final String DATABASE_CREATE_TABLE_QUESTION_OPTION_VAL_DEPENDENTMstr = "create table tblPDAQuestOptionValuesDependentMstr(DepQstId int null,DepAnswValId text null,QstId int null,AnswValId text null,OptDescr text null,Sequence int null,GrpQuestID int null,GrpDepQuestID int null);";
    private static final String TABLE_OutletQuestAnsMstr = "tblOutletQuestAnsMstr";
    //private static final String DATABASE_CREATE_TABLE_tblOutletQuestAnsMstr = "create table tblOutletQuestAnsMstr (OutletID text not null,QuestID text not null,AnswerType text null, AnswerValue text null,QuestionGroupID integer null,sectionID integer null,Sstat integer not null,optionValue text null);";
    private static final String DATABASE_CREATE_TABLE_tblOutletQuestAnsMstr = "create table tblOutletQuestAnsMstr (OutletID text not null,QuestID text not null,AnswerType text null, AnswerValue text null,QuestionGroupID integer null,sectionID integer null,Sstat integer not null);";
    private static final String DATABASE_CREATE_TABLE_Image = "create table tableImage(StoreID text null,QstIdAnsCntrlTyp text null,imageName text null,imagePath text null,ImageClicktime text null,Sstat integer null);";

    private static final String DATABASE_TABLE_UOMMstr="tblUOMMstr";
    private static final String DATABASE_TABLE_SalesQuotePrcsMstr="tblSalesQuotePrcsMstr";
    private static final String DATABASE_TABLE_SalesQuotePersonMeetMstr="tblSalesQuotePersonMeetMstr";
    private static final String DATABASE_TABLE_SalesQuoteProductsMstr="tblSalesQuoteProductsMstr";

    private static final String DATABASE_TABLE_tblSalesQuotePaymentModeMstr="tblSalesQuotePaymentModeMstr";

    private static final String DATABASE_TABLE_tblSalesQuotePaymentStageMstr="tblSalesQuotePaymentStageMstr";

    private static final String DATABASE_TABLE_tblSalesQuoteTypeMstr="tblSalesQuoteTypeMstr";

    private static final String DATABASE_TABLE_tblSalesQuotePaymentStageModeMapMstr="tblSalesQuotePaymentStageModeMapMstr";
    private static final String TABLE_tblSalesQuoteSponsorMstr = "tblSalesQuoteSponsorMstr";

    private static final String TABLE_tblManufacturerMstrMain = "tblManufacturerMstrMain";

    private static final String TABLE_tblRateDistribution = "tblRateDistribution";



    private static final String DATABASE_CREATE_TABLE_UOMMstr = "create table tblUOMMstr (UOMID text null,UOM text null);";
    private static final String DATABASE_CREATE_TABLE_SalesQuotePrcsMstr = "create table tblSalesQuotePrcsMstr (SalesQuotePrcsId text null, SalesQuotePrcs text null);";
    private static final String DATABASE_SalesQuotePersonMeetMstr = "create table tblSalesQuotePersonMeetMstr (SalesQuoteId text null,SalesQuoteCode text null,SalesQuotePrcsId text null,SalesQuotePrcs text null,StoreName text null,Remarks text null,StoreId text null,CreditLimit text null,CreditDays text null,ExpectedBusinessValue text null,SalesQuoteValidFrom text null,SalesQuoteValidTo text null,SalesQuoteDate text null,SalesQuoteType text null,ContactPerson text null,ContactPersonEmail text null,ContactPersonPhone text null,PaymentModeId text null,Sstat text null,PymtStageId text null );";
    private static final String DATABASE_CREATE_SalesQuoteProductsMstr = "create table tblSalesQuoteProductsMstr (SalesQuoteId text null,Row_No text null,PrdId text null,StandardRate text null,StandardRateBeforeTax text null,RateOffer text null,InclusiveTax text null,ValidFrom text null,ValidTo text null,MinDlvryQty text null,UOMID text null,Remarks text null,LastTranscRate text null,Sstat text null,TaxRate text null);";
    private static final String DATABASE_CREATE_TABLE_tblSalesQuotePaymentModeMstr="create table tblSalesQuotePaymentModeMstr (PymtModeId text null,PymtMode text null);";

    private static final String DATABASE_CREATE_TABLE_tblSalesQuotePaymentStageMstr="create table tblSalesQuotePaymentStageMstr (PymtStageId text null,PymtStage text null,PymtModeId text null);";

    private static final String DATABASE_CREATE_TABLE_tblSalesQuoteTypeMstr="create table tblSalesQuoteTypeMstr (SalesQuotetypeId	text null, SalesQuoteType text null);";

    private static final String DATABASE_CREATE_TABLE_tblSalesQuotePaymentStageModeMapMstr="create table tblSalesQuotePaymentStageModeMapMstr (PymtStageId text null, PymtModeId text null);";

    private static final String DATABASE_CREATE_TABLE_tblSalesQuoteSponsorMstr = "create table tblSalesQuoteSponsorMstr(SalesQuoteSponsorID text null,SponsorDescr text null,Ordr text null);";
    private static final String DATABASE_CREATE_TABLE_tblManufacturerMstrMain = "create table tblManufacturerMstrMain(ManufacturerID text null,ManufacturerName text null,NodeType text null);";
    private static final String DATABASE_CREATE_TABLE_tblRateDistribution = "create table tblRateDistribution(SalesQuoteId text null,StoreId text null,SalesQuoteSponsorID  text null,ManufacturerID  text null,Percentage  text null,SponsorDescr  text null,ManufacturerName  text null,Sstat text null);";



    private static final String DATABASE_TABLE_Main271 = "tblLatLongDetails";
    private static final String DATABASE_CREATE_TABLE_271 = "create table tblLatLongDetails (StoreID text null,fnLati text null,fnLongi text null,fnAccuracy text null,flgLocNotFound text null,fnAccurateProvider text null,AllProvidersLocation text null,fnAddress text null,GpsLat text null, GpsLong text null, GpsAccuracy text null, GpsAddress text null, NetwLat text null, NetwLong text null, NetwAccuracy text null, NetwAddress text null, FusedLat text null, FusedLong text null, FusedAccuracy text null, FusedAddress text null,FusedLocationLatitudeWithFirstAttempt text null,FusedLocationLongitudeWithFirstAttempt text null,FusedLocationAccuracyWithFirstAttempt text null,Sstat integer null);";






    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    Locale locale  = new Locale("en", "UK");
    String pattern = "###.##";
    DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(locale);

    public DBAdapterLtFoods(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try
            {

                db.execSQL(DATABASE_CREATE_TABLE_tblStateCityMaster);
                db.execSQL(DATABASE_CREATE_TABLE_tblDAGetAddedOutletSummaryReport);
                db.execSQL(DATABASE_CREATE_TABLE_tblSameLocationForStoreRestartDone);
                db.execSQL(DATABASE_CREATE_TABLE_271);
                db.execSQL(DATABASE_CREATE_TABLE_XMLfiles);
                db.execSQL(DATABASE_CREATE_TABLE_Image);
                db.execSQL(DATABASE_NewStoreSalesQuotePaymentDetails);
                db.execSQL(DATABASE_CREATE_TABLE_QSTOUTCHANNEL);
                db.execSQL(DATABASE_CREATE_TABLE_QST_NAME);

                db.execSQL(DATABASE_CREATE_TABLE_1User);
                db.execSQL(DATABASE_CREATE_TABLE_1);
                db.execSQL(DATABASE_CREATE_TABLE_11);
                db.execSQL(DATABASE_CREATE_TABLE_tblUserName);
                db.execSQL(DATABASE_CREATE_TABLE_tblStoreCountDetails);
                db.execSQL(DATABASE_CREATE_TABLE_tblPreAddedStores);
                db.execSQL(DATABASE_CREATE_TABLE_tblPreAddedStoresDataDetails);
                db.execSQL(DATABASE_CREATE_TABLE_tblLocationDetails);

                db.execSQL(DATABASE_CREATE_TABLE_QUESTIONMstr);
                db.execSQL(DATABASE_CREATE_TABLE_QuestGrpMappingMstr);
                db.execSQL(DATABASE_CREATE_TABLE_OPTIONMstr);
                db.execSQL(DATABASE_CREATE_TABLE_QUESTION_DEPENDENTMstr);
                db.execSQL(DATABASE_CREATE_TABLE_QUESTION_OPTION_DEPENDENTMstr);
                db.execSQL(DATABASE_CREATE_TABLE_QUESTION_OPTION_VAL_DEPENDENTMstr);
                db.execSQL(DATABASE_CREATE_TABLE_tblOutletQuestAnsMstr);

                db.execSQL(DATABASE_CREATE_TABLE_UOMMstr);
                db.execSQL(DATABASE_CREATE_TABLE_SalesQuotePrcsMstr);
                db.execSQL(DATABASE_SalesQuotePersonMeetMstr);
                db.execSQL(DATABASE_CREATE_SalesQuoteProductsMstr);
                db.execSQL(DATABASE_CREATE_TABLE_tblSalesQuotePaymentModeMstr);
                db.execSQL(DATABASE_CREATE_TABLE_tblSalesQuotePaymentStageMstr);
                db.execSQL(DATABASE_CREATE_TABLE_tblSalesQuoteTypeMstr);
                db.execSQL(DATABASE_CREATE_TABLE_tblSalesQuotePaymentStageModeMapMstr);
                db.execSQL(DATABASE_CREATE_TABLE_tblSalesQuoteSponsorMstr);
                db.execSQL(DATABASE_CREATE_TABLE_tblManufacturerMstrMain);
                db.execSQL(DATABASE_CREATE_TABLE_tblRateDistribution);
                db.execSQL(DATABASE_CREATE_TABLE_tblStoreDeatils);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try
            {


                db.execSQL("DROP TABLE IF EXISTS tblStateCityMaster");
                db.execSQL("DROP TABLE IF EXISTS tblDAGetAddedOutletSummaryReport");
                db.execSQL("DROP TABLE IF EXISTS tblsameLocationForStoreRestartDone");
                db.execSQL("DROP TABLE IF EXISTS tblLatLongDetails");
                db.execSQL("DROP TABLE IF EXISTS tbl_XMLfiles");
                db.execSQL("DROP TABLE IF EXISTS tableImage");
                db.execSQL("DROP TABLE IF EXISTS tblQuestIDForOutChannel");
                db.execSQL("DROP TABLE IF EXISTS tblQuestIDForName");
                db.execSQL("DROP TABLE IF EXISTS tblStoreDetails");
                db.execSQL("DROP TABLE IF EXISTS tblNewStoreSalesQuotePaymentDetails");
                db.execSQL("DROP TABLE IF EXISTS tblUserAuthenticationMstr");
                db.execSQL("DROP TABLE IF EXISTS tblAvailableVersionMstr");
                db.execSQL("DROP TABLE IF EXISTS tblPdaDate");
                db.execSQL("DROP TABLE IF EXISTS tblUserName");
                db.execSQL("DROP TABLE IF EXISTS tblStoreCountDetails");
                db.execSQL("DROP TABLE IF EXISTS tblPreAddedStores");
                db.execSQL("DROP TABLE IF EXISTS tblPreAddedStoresDataDetails");
                db.execSQL("DROP TABLE IF EXISTS tblLocationDetails");


                db.execSQL("DROP TABLE IF EXISTS tblQuestionMstr");
                db.execSQL("DROP TABLE IF EXISTS tblPDAQuestGrpMappingMstr");
                db.execSQL("DROP TABLE IF EXISTS tblOptionMstr");
                db.execSQL("DROP TABLE IF EXISTS tblQuestionDependentMstr");
                db.execSQL("DROP TABLE IF EXISTS tblPDAQuestOptionDependentMstr");
                db.execSQL("DROP TABLE IF EXISTS tblPDAQuestOptionValuesDependentMstr");

                db.execSQL("DROP TABLE IF EXISTS tblUOMMstr");
                db.execSQL("DROP TABLE IF EXISTS tblSalesQuotePrcsMstr");
                db.execSQL("DROP TABLE IF EXISTS tblSalesQuotePersonMeetMstr");
                db.execSQL("DROP TABLE IF EXISTS tblSalesQuoteProductsMstr");
                db.execSQL("DROP TABLE IF EXISTS tblSalesQuotePaymentModeMstr");
                db.execSQL("DROP TABLE IF EXISTS tblSalesQuotePaymentStageMstr");
                db.execSQL("DROP TABLE IF EXISTS tblSalesQuoteTypeMstr");
                db.execSQL("DROP TABLE IF EXISTS tblSalesQuotePaymentStageModeMapMstr");
                db.execSQL("DROP TABLE IF EXISTS tblSalesQuoteSponsorMstr");
                db.execSQL("DROP TABLE IF EXISTS tblManufacturerMstrMain");
                db.execSQL("DROP TABLE IF EXISTS tblRateDistribution");
                db.execSQL("DROP TABLE IF EXISTS tblOutletQuestAnsMstr");



                onCreate(db);

            } catch (Exception e) {

            }
        }
    }
    // ---opens the database---
    public DBAdapterLtFoods open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // ---closes the database---
    public void close() {
        DBHelper.close();
    }
    public long savetblUserAuthenticationMstr(String flgUserAuthenticated,String flgAppStatus,
                                              String DisplayMessage,String flgValidApplication,String MessageForInvalid)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("flgUserAuthenticated", flgUserAuthenticated.trim());
        initialValues.put("flgAppStatus", flgAppStatus.trim());
        initialValues.put("DisplayMessage", DisplayMessage.trim());
        initialValues.put("flgValidApplication", flgValidApplication.trim());
        initialValues.put("MessageForInvalid", MessageForInvalid.trim());


        return db.insert(DATABASE_TABLE_MAIN1User, null, initialValues);
    }
    public long savetblAvailbUpdatedVersion(String VersionID, String VersionSerialNo,String VersionDownloadStatus,String ServerDate)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("VersionID", VersionID.trim());
        initialValues.put("VersionSerialNo", VersionSerialNo.trim());
        initialValues.put("VersionDownloadStatus", VersionDownloadStatus.trim());
        initialValues.put("ServerDate", ServerDate.trim());
        return db.insert(DATABASE_TABLE_MAIN1, null, initialValues);
    }
    public void droptblUserAuthenticationMstrTBL()
    {
        db.execSQL("DROP TABLE IF EXISTS tblUserAuthenticationMstr");

    }
    public void createtblUserAuthenticationMstrTBL()
    {
        try
        {
            db.execSQL(DATABASE_CREATE_TABLE_1User);
        }
        catch (Exception e)
        {

        }

    }
    public void dropAvailbUpdatedVersionTBL()
    {
        db.execSQL("DROP TABLE IF EXISTS tblAvailableVersionMstr");

    }
    public void createAvailbUpdatedVersionTBL()
    {
        try
        {
            db.execSQL(DATABASE_CREATE_TABLE_1);
        }
        catch (Exception e)
        {

        }

    }
    public int FetchflgUserAuthenticated()
    {
        int SnamecolumnIndex1 = 0;
        int CatId=0;

        Cursor cursor = db.rawQuery("SELECT flgUserAuthenticated from tblUserAuthenticationMstr", null);
        try {

            if (cursor.moveToFirst()) {

                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {

                    String abc =(String) cursor.getString(SnamecolumnIndex1).toString();
                    CatId=Integer.parseInt(abc);
                    cursor.moveToNext();
                }

            }
            return CatId;
        } finally {
            cursor.close();
        }

    }
    public int FetchVersionDownloadStatus()
    {
        int SnamecolumnIndex1 = 0;
        int CatId=0;

        Cursor cursor = db.rawQuery("SELECT VersionDownloadStatus from tblAvailableVersionMstr", null);
        try {
            //String OldDateInfo[] = new String[cursor.getCount() ];
            if (cursor.moveToFirst())
            {

                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {
                    //CatId = cursor.getString(SnamecolumnIndex1).toString();
                    String abc =(String) cursor.getString(SnamecolumnIndex1).toString();
                    CatId=Integer.parseInt(abc);
                    cursor.moveToNext();
                }

            }
            return CatId;
        } finally {
            cursor.close();
        }

    }
    public long maintainPDADate()
    {
        db.execSQL("DELETE FROM tblPdaDate");
        Date pdaDate=new Date();
        SimpleDateFormat	sdfPDaDate = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
        String fDatePda = sdfPDaDate.format(pdaDate).toString().trim();
        ContentValues initialValues = new ContentValues();
        initialValues.put("PdaDate",fDatePda);

        return db.insert("tblPdaDate", null, initialValues);
    }
    public String fnGetPdaDate()
    {

        int LoncolumnIndex = 0;
        String strPDADate="";
        Cursor cursor2 = db.rawQuery("SELECT PdaDate FROM tblPdaDate", null);

        try
        {
            if(cursor2.getCount()>0)
            {
                if (cursor2.moveToFirst())
                {
                    strPDADate =cursor2.getString(LoncolumnIndex).toString();
                }
            }

            return strPDADate;
        } finally {
            cursor2.close();
        }
    }
    public int fnCheckPdaDateExistOrNot()
    {

        int strReturnPDADateExistOrNot = 0;

        Cursor cursor2 = db.rawQuery("SELECT PdaDate FROM  tblPdaDate", null);
        try {
            if (cursor2.moveToFirst()) {

                for (int i = 0; i < cursor2.getCount(); i++) {
                    strReturnPDADateExistOrNot = 1;
                    cursor2.moveToNext();
                    // cursor.close();
                }

            }

            return strReturnPDADateExistOrNot;
        } finally {
            cursor2.close();
        }
    }
    public String fnGetServerDate()
    {

        int LoncolumnIndex = 0;
        String strServerDate="";
        Cursor cursor2 = db.rawQuery("SELECT ServerDate FROM tblAvailableVersionMstr", null);

        try
        {
            if(cursor2.getCount()>0)
            {
                if (cursor2.moveToFirst())
                {
                    strServerDate =cursor2.getString(LoncolumnIndex).toString();
                }
            }

            return strServerDate;
        } finally {
            cursor2.close();
        }
    }
    public void delete_all_storeDetailTables()
    {
        db.execSQL("DELETE FROM tblUserName");
        db.execSQL("DELETE FROM tblStoreCountDetails");
        db.execSQL("DELETE FROM tblPreAddedStores");
        db.execSQL("DELETE FROM tblPreAddedStoresDataDetails");
        db.execSQL("DELETE FROM tblLocationDetails");
    }
    public  void deleteLocationTable()
    {
        db.execSQL("DELETE FROM tblLocationDetails");

    }

    public long saveTblUserName(String UserName) {

        ContentValues initialValues = new ContentValues();

        initialValues.put("UserName", UserName);

        return db.insert(DATABASE_TABLE_tblUserName, null, initialValues);
    }
    public long saveTblStoreCountDetails(String TotStoreAdded,String TodayStoreAdded)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("TotStoreAdded", Integer.parseInt(TotStoreAdded.toString().trim()));
        initialValues.put("TodayStoreAdded", Integer.parseInt(TodayStoreAdded.toString().trim()));

        return db.insert(DATABASE_TABLE_tblStoreCountDetails, null, initialValues);
    }
    public long saveTblPreAddedStores(String StoreID,String StoreName,String LatCode,String LongCode,String DateAdded,int flgOldNewStore,int flgReMap,int Sstat)
    {
        ContentValues initialValues = new ContentValues();

        initialValues.put("StoreID", StoreID);
        initialValues.put("StoreName", StoreName);
        initialValues.put("LatCode", LatCode);
        initialValues.put("LongCode", LongCode);
        initialValues.put("DateAdded", DateAdded);
        initialValues.put("DistanceNear", 1000);
        initialValues.put("flgOldNewStore", flgOldNewStore);
        initialValues.put("flgReMap", flgReMap);
        initialValues.put("Sstat", Sstat);


        return db.insert(DATABASE_TABLE_tblPreAddedStores, null, initialValues);
    }

    public long saveTblLocationDetails(String Lattitude,String Longitude,String Accuracy,String Address,String City,String Pincode,String State,String fnAccurateProvider,String GpsLat,String GpsLong,String GpsAccuracy,String NetwLat,String NetwLong,String NetwAccuracy,String FusedLat,String FusedLong,String FusedAccuracy,String AllProvidersLocation,String GpsAddress,String NetwAddress,String FusedAddress,String FusedLocationLatitudeWithFirstAttempt,String FusedLocationLongitudeWithFirstAttempt,String FusedLocationAccuracyWithFirstAttempt)
    {
        ContentValues initialValues = new ContentValues();

        initialValues.put("Lattitude", Lattitude);
        initialValues.put("Longitude", Longitude);
        initialValues.put("Accuracy", Accuracy);
        initialValues.put("Address", Address);
        initialValues.put("City", City);
        initialValues.put("Pincode", Pincode);
        initialValues.put("State", State);

        initialValues.put("fnAccurateProvider", fnAccurateProvider);
        initialValues.put("GpsLat", GpsLat);
        initialValues.put("GpsLong", GpsLong);
        initialValues.put("GpsAccuracy", GpsAccuracy);
        initialValues.put("NetwLat", NetwLat);
        initialValues.put("NetwLong", NetwLong);
        initialValues.put("NetwAccuracy", NetwAccuracy);
        initialValues.put("FusedLat", FusedLat);
        initialValues.put("FusedLong", FusedLong);
        initialValues.put("FusedAccuracy", FusedAccuracy);

        initialValues.put("AllProvidersLocation", AllProvidersLocation);
        initialValues.put("GpsAddress", GpsAddress);
        initialValues.put("NetwAddress", NetwAddress);
        initialValues.put("FusedAddress", FusedAddress);
        initialValues.put("FusedLocationLatitudeWithFirstAttempt", FusedLocationLatitudeWithFirstAttempt);
        initialValues.put("FusedLocationLongitudeWithFirstAttempt", FusedLocationLongitudeWithFirstAttempt);
        initialValues.put("FusedLocationAccuracyWithFirstAttempt", FusedLocationAccuracyWithFirstAttempt);



        return db.insert(DATABASE_TABLE_tblLocationDetails, null, initialValues);
    }
    public long saveTblPreAddedStoresDataDetails(String StoreIDDB,String GrpQuestID,String QstId,String AnsControlTypeID,String AnsTextVal,String flgPrvVal)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("StoreIDDB", StoreIDDB);
        initialValues.put("GrpQuestID", GrpQuestID);
        initialValues.put("QstId", QstId);
        initialValues.put("AnsControlTypeID", AnsControlTypeID);

        initialValues.put("AnsTextVal", AnsTextVal);

        initialValues.put("flgPrvVal", flgPrvVal);

        return db.insert(DATABASE_TABLE_tblPreAddedStoresDataDetails, null, initialValues);
    }

    public String getUsername()
    {String userName="0"+"^"+"0";

        open();
        try {
            Cursor cur=db.rawQuery("Select UserName from tblUserName", null);

            if(cur.getCount()>0)
            {
                StringBuilder sBuilder=new StringBuilder();
                if(cur.moveToFirst())
                {
                    for(int i=0;i<cur.getCount();i++)
                    {
                        userName=cur.getString(0);
                        cur.moveToNext();
                    }
                }

            }

        } catch (Exception e) {
            // TODO: handle exception
        } finally
        {
            close();
            return userName;
        }
    }

    public String getTodatAndTotalStores()
    {
        String StoresData="0";
        open();
        try {
            Cursor cur=db.rawQuery("Select TotStoreAdded , TodayStoreAdded from tblStoreCountDetails", null);

            if(cur.getCount()>0)
            {
                StringBuilder sBuilder=new StringBuilder();
                if(cur.moveToFirst())
                {
                    for(int i=0;i<cur.getCount();i++)
                    {
                        StoresData=cur.getString(0)+"^"+cur.getString(1);
                        cur.moveToNext();
                    }
                }

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {
            close();
            return StoresData;
        }
    }


    public int counttblUserName()
    {
        Cursor cursorE2 = db.rawQuery("SELECT COUNT(*) FROM tblUserName", null);
        int chkI = 0;
        try {

            if (cursorE2.moveToFirst()) {

                if (cursorE2.getInt(0) > 0) {
                    chkI = 1;
                } else {
                    chkI = 0;
                }
            }

        } finally {
            cursorE2.close();
        }
        return chkI;
    }


    public int fncheckCountNearByStoreExistsOrNot(int DistanceRange)
    {
        int flgCheck=0;
        open();

        try {
           // Cursor cursor = db.rawQuery("SELECT Count(*) from tblPreAddedStores Where (LatCode<>'0' and LatCode<>'NA') and DistanceNear<"+ DistanceRange+ " ORDER BY DistanceNear", null);

            Cursor cursor = db.rawQuery("SELECT Count(*) from tblPreAddedStores WHERE (LatCode<>'0' AND LatCode<>'NA')    ORDER BY DistanceNear ASC", null);//
            //StoreID,StoreName,LatCode,LongCode,DateAdded
            if(cursor.getCount()>0) {
                if (cursor.moveToFirst())
                {
                  if(cursor.getInt(0)>0)
                  {
                      flgCheck=1;
                  }
                }
            }

        }
        catch (Exception e) {
            System.out.println("Error fnGettblUOMMstr= "+e.toString());
        }
        finally
        {

            close();
            if(flgCheck==0)
            {
                flgCheck=fncheckCountNewAddedNearByStoreExistsOrNot(flgCheck);
            }
            return flgCheck;
        }
    }

    public int fncheckCountNewAddedNearByStoreExistsOrNot(int flgCheck)
    {

        open();

        try {
            //Cursor cursor = db.rawQuery("SELECT StoreID,StoreName,LatCode,LongCode,DateAdded,DistanceNear from tblPreAddedStores WHERE (LatCode<>'0' AND LatCode<>'NA') AND DistanceNear<"+ DistanceRange +" ORDER BY DistanceNear  ASC Limit 30", null);//
            Cursor cursor = db.rawQuery("SELECT Count(*) from tblPreAddedStores WHERE (LatCode='NA') and flgOldNewStore=1", null);//
            if(cursor.getCount()>0) {
                if (cursor.moveToFirst())
                {
                    if(cursor.getInt(0)>0)
                    {
                        flgCheck=1;
                    }
                }
            }

        }
        catch (Exception e) {
            System.out.println("Error fnGettblUOMMstr= "+e.toString());
        }
        finally
        {

            close();

        }
        return flgCheck;
    }
    public int fngetcountAddedstore()
    {
        int flgCheck=0;
        open();

        try {
            Cursor cursor = db.rawQuery("SELECT Count(*) from tblPreAddedStores Where  flgOldNewStore=1 ", null);
            //StoreID,StoreName,LatCode,LongCode,DateAdded
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    if(cursor.getInt(0)>0)
                    {
                        flgCheck=1;
                    }
                }
            }

        }
        catch (Exception e) {
            System.out.println("Error fnGettblUOMMstr= "+e.toString());
        }
        finally
        {

            close();
            return flgCheck;
        }
    }


    public LinkedHashMap<String, String> fnGeStoreList(int DistanceRange)
    {
        LinkedHashMap<String, String> hmapQuestionMstr=new LinkedHashMap<String, String>();
        open();

        try {
            //Cursor cursor = db.rawQuery("SELECT StoreID,StoreName,LatCode,LongCode,DateAdded,DistanceNear from tblPreAddedStores WHERE (LatCode<>'0' AND LatCode<>'NA') AND DistanceNear<"+ DistanceRange +" ORDER BY DistanceNear  ASC Limit 30", null);//
           // Cursor cursor = db.rawQuery("SELECT StoreID,StoreName,LatCode,LongCode,DateAdded,DistanceNear from tblPreAddedStores WHERE (LatCode<>'0' AND LatCode<>'NA') AND DistanceNear<"+ DistanceRange +"  ORDER BY DistanceNear", null);//
//           /
           // tblPreAddedStores (StoreID text null,StoreName text null,LatCode text null,LongCode text null,DateAdded text null,DistanceNear int null,flgOldNewStore int null,flgReMap int null,Sstat int null);";
            Cursor cursor = db.rawQuery("SELECT StoreID,StoreName,LatCode,LongCode,DateAdded,DistanceNear,flgReMap from tblPreAddedStores WHERE (LatCode<>'0' AND LatCode<>'NA') ORDER BY DistanceNear", null);//
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++) {
                        String StoreLat="0.00";
                        String StoreLon="0.00";
                        if(!cursor.getString(2).toString().equals("NA"))
                        {
                            StoreLat=(String) cursor.getString(2).toString();
                            StoreLon=(String) cursor.getString(3).toString();
                        }

                        hmapQuestionMstr.put((String) cursor.getString(0).toString(),(String) cursor.getString(1).toString()+"^"+StoreLat+"^"+StoreLon+"^"+(String) cursor.getString(4).toString()+"^"+(String) cursor.getString(6).toString());

                        cursor.moveToNext();
                    }
                }
            }

        }
        catch (Exception e) {
            System.out.println("Error fnGettblUOMMstr= "+e.toString());
        }
        finally
        {

            close();
            fnGeNewlyAddedStoreList(hmapQuestionMstr);
            return hmapQuestionMstr;
        }
    }


    public void fnGeNewlyAddedStoreList(LinkedHashMap<String, String> hmapQuestionMstr)
    {

        open();

        try {
            //Cursor cursor = db.rawQuery("SELECT StoreID,StoreName,LatCode,LongCode,DateAdded,DistanceNear from tblPreAddedStores WHERE (LatCode<>'0' AND LatCode<>'NA') AND DistanceNear<"+ DistanceRange +" ORDER BY DistanceNear  ASC Limit 30", null);//

           //StoreID,StoreName,LatCode,LongCode,DateAdded,DistanceNear,flgReMap
           // Cursor cursor = db.rawQuery("SELECT StoreID,StoreName,LatCode,LongCode,DateAdded,DistanceNear,flgReMap from tblPreAddedStores WHERE (LatCode<>'0' AND LatCode<>'NA') ORDER BY DistanceNear", null);//

            Cursor cursor = db.rawQuery("SELECT StoreID,StoreName,LatCode,LongCode,DateAdded,DistanceNear,flgReMap from tblPreAddedStores WHERE ( LatCode='NA') and flgOldNewStore=1", null);//
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++) {
                        String StoreLat="0.00";
                        String StoreLon="0.00";
                        if(!cursor.getString(2).toString().equals("NA"))
                        {
                            StoreLat=(String) cursor.getString(2).toString();
                            StoreLon=(String) cursor.getString(3).toString();
                        }
                        //hmapQuestionMstr.put((String) cursor.getString(0).toString(),(String) cursor.getString(1).toString()+"^"+StoreLat+"^"+StoreLon+"^"+(String) cursor.getString(4).toString()+"^"+(String) cursor.getString(6).toString());
                        hmapQuestionMstr.put((String) cursor.getString(0).toString(),(String) cursor.getString(1).toString()+"^"+StoreLat+"^"+StoreLon+"^"+(String) cursor.getString(4).toString()+"^"+(String) cursor.getString(6).toString());

                        cursor.moveToNext();
                    }
                }
            }

        }
        catch (Exception e) {
            System.out.println("Error fnGettblUOMMstr= "+e.toString());
        }
        finally
        {

            close();

        }
    }

    public LinkedHashMap<String, String> fnGeNewAddedStoreList()
    {
        LinkedHashMap<String, String> hmapQuestionMstr=new LinkedHashMap<String, String>();
        open();

        try {
            Cursor cursor = db.rawQuery("SELECT StoreID,StoreName,LatCode,LongCode,DateAdded from tblPreAddedStores Where  flgOldNewStore=1  ", null);
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++) {
                        hmapQuestionMstr.put((String) cursor.getString(0).toString(),(String) cursor.getString(1).toString()+"^"+(String) cursor.getString(2).toString()+"^"+(String) cursor.getString(3).toString()+"^"+(String) cursor.getString(4).toString());
                        cursor.moveToNext();
                    }
                }
            }

        }
        catch (Exception e) {
            System.out.println("Error fnGettblUOMMstr= "+e.toString());
        }
        finally
        {

            close();
            return hmapQuestionMstr;
        }
    }

    public LinkedHashMap<String, String> fnGetALLOutletMstr()
    {
        Cursor cursor=null;

        LinkedHashMap<String, String> hmapOutletMstr=new LinkedHashMap<String, String>();
        open();

        cursor = db.rawQuery("SELECT StoreID,ifnull(LatCode,0),ifnull(LongCode,0) from tblPreAddedStores", null);


        try
        {
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    String phoneNum;
                    for (int i = 0; i <= (cursor.getCount() - 1); i++)
                    {
                        hmapOutletMstr.put((String) cursor.getString(0).toString(), (String) cursor.getString(1).toString()+"^"+(String) cursor.getString(2).toString());
                        cursor.moveToNext();
                    }
                }
            }
            return hmapOutletMstr;
        }
        finally
        {
            cursor.close();
            close();
        }
    }

    public void UpdateStoreDistanceNear(String OutletID, int DistanceNear)
    {
        try
        {

            final ContentValues values = new ContentValues();
            values.put("DistanceNear", DistanceNear);
            int affected = db.update("tblPreAddedStores", values, "StoreID=?", new String[]{OutletID});
        }
        catch(Exception e)
        {

        }
        finally
        {

        }

    }
    public void deleteAllSingleCallWebServiceTable()
    {
        db.execSQL("DELETE FROM tblQuestionMstr");
        db.execSQL("DELETE FROM tblPDAQuestGrpMappingMstr");
        db.execSQL("DELETE FROM tblOptionMstr");
        db.execSQL("DELETE FROM tblQuestionDependentMstr");
        db.execSQL("DELETE FROM tblPDAQuestOptionDependentMstr");
        db.execSQL("DELETE FROM tblPDAQuestOptionValuesDependentMstr");
        db.execSQL("DELETE FROM tblStoreDetails");
        db.execSQL("DELETE FROM tblLatLongDetails");
       // db.execSQL("DELETE FROM tableImage");
        db.execSQL("DELETE FROM tblQuestIDForOutChannel");
        db.execSQL("DELETE FROM tblQuestIDForName");
        db.execSQL("DELETE FROM tblsameLocationForStoreRestartDone");




    }

    //saving functions of dynamic saving
    public long savetblQuestionMstr(String QuestID,String QuestCode,String QuestDesc,String QuestType,String AnsControlType,String AnsControlInputTypeID,String AnsControlInputTypeMaxLength,String AnsMustRequiredFlg,String QuestBundleFlg,String ApplicationTypeID,String Sequence,String AnsControlInputTypeMinLength,String answerHint,int flgQuestIDForOutChannel)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("QuestCode", Integer.parseInt(QuestCode));
        initialValues.put("QuestID", Integer.parseInt(QuestID));

        initialValues.put("QuestDesc", QuestDesc.trim());
        initialValues.put("QuestType", Integer.parseInt(QuestType));
        initialValues.put("AnsControlType", Integer.parseInt(AnsControlType));
        initialValues.put("AnsControlInputTypeID", Integer.parseInt(AnsControlInputTypeID));
        initialValues.put("AnsControlInputTypeMaxLength", Integer.parseInt(AnsControlInputTypeMaxLength));
        initialValues.put("AnsMustRequiredFlg", Integer.parseInt(AnsMustRequiredFlg));
        initialValues.put("QuestBundleFlg", Integer.parseInt(QuestBundleFlg));
        initialValues.put("ApplicationTypeID", Integer.parseInt(ApplicationTypeID));
        initialValues.put("Sequence", Integer.parseInt(Sequence));//AnsControlInputTypeMinLength
        initialValues.put("AnsControlInputTypeMinLength", Integer.parseInt(AnsControlInputTypeMinLength));
        initialValues.put("AnsHint", answerHint.trim());
        initialValues.put("flgQuestIDForOutChannel", flgQuestIDForOutChannel);
        //
        return db.insert(TABLE_QuestionMstr, null, initialValues);
    }


    public long savetblPDAQuestGrpMappingMstr(String GrpQuestID,String QuestID,String GrpID,String GrpNodeID,String GrpDesc,String SectionNo,String GrpCopyID,String QuestCopyID,String sequence) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("GrpQuestID", Integer.parseInt(GrpQuestID));
        initialValues.put("QuestID", Integer.parseInt(QuestID));
        initialValues.put("GrpID", Integer.parseInt(GrpID));
        initialValues.put("GrpNodeID", Integer.parseInt(GrpNodeID));
        initialValues.put("GrpDesc", GrpDesc.trim());
        initialValues.put("SectionNo", Integer.parseInt(SectionNo));
        initialValues.put("GrpCopyID", Integer.parseInt(GrpCopyID));
        initialValues.put("QuestCopyID", Integer.parseInt(QuestCopyID));
        initialValues.put("Sequence", Integer.parseInt(sequence));
        return db.insert(TABLE_QuestGrpMappingMstr, null, initialValues);
    }


    public long savetblOptionMstr(String OptID,String QuestID,String OptionNo,String OptionDescr,String Sequence)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("OptID", OptID);
        initialValues.put("QuestID", Integer.parseInt(QuestID));
        initialValues.put("OptionNo", Integer.parseInt(OptionNo));


        initialValues.put("OptionDescr", OptionDescr.trim());
        initialValues.put("Sequence", Integer.parseInt(Sequence));



        return db.insert(TABLE_OptionMstr, null, initialValues);
    }


    public long savetblQuestionDependentMstr(String QuestionID,String OptionID,String DependentQuestionID,String GrpID,String GrpDepQuestID)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("QuestionID", Integer.parseInt(QuestionID));
        initialValues.put("OptionID", OptionID);
        initialValues.put("DependentQuestionID", Integer.parseInt(DependentQuestionID));
        initialValues.put("GrpID", Integer.parseInt(GrpID));
        initialValues.put("GrpDepQuestID", Integer.parseInt(GrpDepQuestID));

        System.out.println("QuestionID2:" + QuestionID + "OptionID2:" + OptionID + "DependentQuestionID2:" + DependentQuestionID);
        return db.insert(TABLE_QuestionDependentMstr, null, initialValues);
    }

    public void savetblPDAQuestOptionDependentMstr(int QstID,int DepQstId,int GrpQuestID,int GrpDepQuestID)
    {

        ContentValues values=new ContentValues();
        values.put("QstID", QstID);
        values.put("DepQstId", DepQstId);
        values.put("GrpQuestID", GrpQuestID);
        values.put("GrpDepQuestID", GrpDepQuestID);
        db.insert(TABLE_QuestOptionDependentMstr, null, values);

    }

    public void savetblPDAQuestOptionValuesDependentMstr(int DepQstId,String DepAnswValId ,int QstId,String AnswValId,String OptDescr,int Sequence,int GrpQuestID,int GrpDepQuestID)
    {

        ContentValues values=new ContentValues();
        values.put("DepQstId", DepQstId);
        values.put("DepAnswValId", DepAnswValId);
        values.put("QstId", QstId);
        values.put("AnswValId", AnswValId);
        values.put("OptDescr", OptDescr);
        values.put("Sequence", Sequence);
        values.put("GrpQuestID", GrpQuestID);
        values.put("GrpDepQuestID", GrpDepQuestID);
        db.insert(TABLE_QuestOptionValuesDependentMstr, null, values);

    }
    //quotation all saving function
    public long savetbltblUOMMstr(String UOMID,String UOM)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("UOMID", UOMID.toString().trim());
        initialValues.put("UOM", UOM.toString().trim());

        return db.insert(DATABASE_TABLE_UOMMstr, null, initialValues);
    }

    public long saveSalesQuotePrcsMstr(String SalesQuotePrcsId,String SalesQuotePrcs)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("SalesQuotePrcsId", SalesQuotePrcsId.toString().trim());
        initialValues.put("SalesQuotePrcs", SalesQuotePrcs.toString().trim());

        return db.insert(DATABASE_TABLE_SalesQuotePrcsMstr, null, initialValues);
    }

    public long saveSalesQuotePersonMeetMstr(String SalesQuoteId,String SalesQuoteCode,String SalesQuotePrcsId,String SalesQuotePrcs,String StoreName,String Remarks,String StoreId,String CreditLimit,String CreditDays,String ExpectedBusinessValue,String SalesQuoteValidFrom ,String SalesQuoteValidTo,String SalesQuoteDate,String SalesQuoteType,String ContactPerson,String ContactPersonEmail,String ContactPersonPhone,String PaymentModeId,String PaymentStageId)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("SalesQuoteId", SalesQuoteId.toString().trim());
        initialValues.put("SalesQuoteCode", SalesQuoteCode.toString().trim());
        initialValues.put("SalesQuotePrcsId", SalesQuotePrcsId.toString().trim());
        initialValues.put("SalesQuotePrcs", SalesQuotePrcs.toString().trim());
        initialValues.put("StoreName", StoreName.toString().trim());
        initialValues.put("Remarks", Remarks.toString().trim());
        initialValues.put("StoreId", StoreId.toString().trim());
        initialValues.put("CreditLimit", CreditLimit.toString().trim());
        initialValues.put("CreditDays", CreditDays.toString().trim());
        initialValues.put("ExpectedBusinessValue", ExpectedBusinessValue.toString().trim());
        initialValues.put("SalesQuoteValidFrom", SalesQuoteValidFrom.toString().trim());
        initialValues.put("SalesQuoteValidTo", SalesQuoteValidTo.toString().trim());
        initialValues.put("SalesQuoteDate", SalesQuoteDate.toString().trim());
        initialValues.put("SalesQuoteType", SalesQuoteType.toString().trim());
        initialValues.put("ContactPerson", ContactPerson.toString().trim());
        initialValues.put("ContactPersonEmail", ContactPersonEmail.toString().trim());
        initialValues.put("ContactPersonPhone", ContactPersonPhone.toString().trim());
        initialValues.put("PaymentModeId", PaymentModeId.toString().trim());
        initialValues.put("PymtStageId", PaymentStageId.toString().trim());
        initialValues.put("Sstat", "1");

        return db.insert(DATABASE_TABLE_SalesQuotePersonMeetMstr, null, initialValues);
    }

    public long SalesQuoteProductsMstr(String SalesQuoteId,String Row_No,String PrdId,String StandardRate,String StandardRateBeforeTax,String RateOffer,String InclusiveTax,String ValidFrom,String ValidTo,String MinDlvryQty,String UOMID ,String Remarks,String LastTranscRate,String ProductTaxRateBK)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("SalesQuoteId", SalesQuoteId.toString().trim());
        initialValues.put("Row_No", Row_No.toString().trim());
        initialValues.put("PrdId", PrdId.toString().trim());
        initialValues.put("StandardRate", StandardRate.toString().trim());
        initialValues.put("StandardRateBeforeTax", StandardRateBeforeTax.toString().trim());
        initialValues.put("RateOffer", RateOffer.toString().trim());
        initialValues.put("InclusiveTax", InclusiveTax.toString().trim());
        initialValues.put("ValidFrom", ValidFrom.toString().trim());
        initialValues.put("ValidTo", ValidTo.toString().trim());
        initialValues.put("MinDlvryQty", MinDlvryQty.toString().trim());
        initialValues.put("UOMID", UOMID.toString().trim());
        initialValues.put("Remarks", Remarks.toString().trim());
        initialValues.put("LastTranscRate", LastTranscRate.toString().trim());
        initialValues.put("TaxRate", ProductTaxRateBK.toString().trim());
        initialValues.put("Sstat", "1");

        //LastTranscRate

        return db.insert(DATABASE_TABLE_SalesQuoteProductsMstr, null, initialValues);
    }

    public long SavetblSalesQuotePaymentModeMstr(String PymtModeId,String PymtMode)
    {
        ContentValues initialValues= new ContentValues();

        initialValues.put("PymtModeId", PymtModeId.toString().trim());
        initialValues.put("PymtMode", PymtMode.toString().trim());

        return db.insert(DATABASE_TABLE_tblSalesQuotePaymentModeMstr, null, initialValues);
    }

    public long SavetblSalesQuotePaymentStageMstr(String PymtStageId,String PymtStage, String PymtModeId)
    {
        ContentValues initialValues= new ContentValues();

        initialValues.put("PymtStageId", PymtStageId.toString().trim());
        initialValues.put("PymtStage", PymtStage.toString().trim());
        initialValues.put("PymtModeId", PymtModeId.toString().trim());

        return db.insert(DATABASE_TABLE_tblSalesQuotePaymentStageMstr, null, initialValues);
    }

    public long SavetblSalesQuoteTypeMstr(String SalesQuotetypeId	, String SalesQuoteType)
    {
        ContentValues initialValues=new ContentValues();

        initialValues.put("SalesQuotetypeId", SalesQuotetypeId);
        initialValues.put("SalesQuoteType", SalesQuoteType);

        return db.insert(DATABASE_TABLE_tblSalesQuoteTypeMstr, null, initialValues);
    }

    public long SavetblSalesQuotePaymentStageModeMapMstr(String PymtStageId, String PymtModeId )
    {
        ContentValues initialValues=new ContentValues();

        initialValues.put("PymtStageId", PymtStageId);
        initialValues.put("PymtModeId", PymtModeId);

        return db.insert(DATABASE_TABLE_tblSalesQuotePaymentStageModeMapMstr, null, initialValues);
    }
    public long saveTblSalesQuoteSponsorMstr(String SalesQuoteSponsorID,String SponsorDescr,String Ordr)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("SalesQuoteSponsorID", SalesQuoteSponsorID.toString().trim());
        initialValues.put("SponsorDescr", SponsorDescr.toString().trim());
        initialValues.put("Ordr", Ordr.toString().trim());

        return db.insert(TABLE_tblSalesQuoteSponsorMstr, null, initialValues);
    }

    public long saveTblManufacturerMstrMain(String ManufacturerID,String ManufacturerName,String NodeType)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("ManufacturerID", ManufacturerID.toString().trim());
        initialValues.put("ManufacturerName", ManufacturerName.toString().trim());
        initialValues.put("NodeType", NodeType.toString().trim());

        return db.insert(TABLE_tblManufacturerMstrMain, null, initialValues);
    }

    public long saveTblRateDistribution(String SalesQuoteId,String StoreId,String SalesQuoteSponsorID,String ManufacturerID,String Percentage,String SponsorDescr,String ManufacturerName,String Sstat)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put("SalesQuoteId", SalesQuoteId.toString().trim());
        initialValues.put("StoreId", StoreId.toString().trim());
        initialValues.put("SalesQuoteSponsorID", SalesQuoteSponsorID.toString().trim());
        initialValues.put("ManufacturerID", ManufacturerID.toString().trim());
        initialValues.put("Percentage", Percentage.toString().trim());
        initialValues.put("SponsorDescr", SponsorDescr.toString().trim());
        initialValues.put("ManufacturerName", ManufacturerName.toString().trim());
        initialValues.put("Sstat", Sstat.toString().trim());

        return db.insert(TABLE_tblRateDistribution, null, initialValues);
    }

    public void deleteAllQuotationTables() {

        db.execSQL("DELETE FROM tblUOMMstr");
        db.execSQL("DELETE FROM tblSalesQuotePrcsMstr");
        db.execSQL("DELETE FROM tblSalesQuotePersonMeetMstr");
        db.execSQL("DELETE FROM tblSalesQuoteProductsMstr");
        db.execSQL("DELETE FROM tblSalesQuotePaymentModeMstr");
        db.execSQL("DELETE FROM tblSalesQuotePaymentStageMstr");
        db.execSQL("DELETE FROM tblSalesQuoteTypeMstr");
        db.execSQL("DELETE FROM tblSalesQuotePaymentStageModeMapMstr");
        db.execSQL("DELETE FROM tblSalesQuoteSponsorMstr");
        db.execSQL("DELETE FROM tblManufacturerMstrMain");

    }

    public void fnDeletesaveNewOutletFromOutletMstr(String OutletID)
    {
        open();
        db.execSQL("DELETE FROM tblPreAddedStores WHERE StoreID ='"+ OutletID + "'");// and sectionID="+sectionID
        close();
    }
    public String fngettblNewStoreSalesQuotePaymentDetails(String StoreID)

    {



        String searchString="";



        try {
            open();

            Cursor cur=db.rawQuery("Select IFNULL(PymtStageId,0) from tblNewStoreSalesQuotePaymentDetails Where StoreId ='"+ StoreID + "'", null);

            if(cur.getCount()>0)

            {

                StringBuilder sBuilder=new StringBuilder();

                if(cur.moveToFirst())

                {

                    for(int i=0;i<cur.getCount();i++)

                    {

                        searchString= cur.getString(0);

                        cur.moveToNext();

                    }

                }



            }



        } catch (Exception e) {

            // TODO: handle exception

        }

        finally

        {

            close();

            return searchString;

        }

    }
    public LinkedHashMap<String, String> fnGetQuestionMstr()
    {
        LinkedHashMap<String, String> hmapQuestionMstr=new LinkedHashMap<String, String>();
        open();
        int sectionCount=getsectionCount();
        //tblPDAQuestGrpMappingMstr(GrpQuestID int null,QuestID int null,GrpID int null,GrpNodeID int null,GrpDesc text null,SectionNo int null);";
        //tblQuestionMstr(QuestID int null,QuestCode int null,QuestDesc text null,QuestType int null,AnsControlType int null,AnsControlInputTypeID int null,AnsControlInputTypeMinLength int null,AnsControlInputTypeMaxLength int null,AnsMustRequiredFlg int null,QuestBundleFlg int null,ApplicationTypeID int null,Sequence int null,AnsHint text null);";
        Cursor cursor;
        if(sectionCount!=0)
        {

            cursor = db.rawQuery("SELECT tblQuestionMstr.QuestID,tblQuestionMstr.QuestCode,tblQuestionMstr.QuestDesc,tblQuestionMstr.QuestType,tblQuestionMstr.AnsControlType,tblQuestionMstr.AnsControlInputTypeID,tblQuestionMstr.AnsControlInputTypeMaxLength,tblQuestionMstr.AnsMustRequiredFlg,tblQuestionMstr.QuestBundleFlg,tblQuestionMstr.ApplicationTypeID,tblQuestionMstr.Sequence,tblQuestionMstr.AnsControlInputTypeMinLength,tblQuestionMstr.AnsHint,tblPDAQuestGrpMappingMstr.GrpQuestID,tblPDAQuestGrpMappingMstr.Sequence from tblQuestionMstr inner join tblPDAQuestGrpMappingMstr On tblQuestionMstr.QuestID=tblPDAQuestGrpMappingMstr.QuestID  where tblPDAQuestGrpMappingMstr.SectionNo<="+sectionCount+" Order By tblPDAQuestGrpMappingMstr.Sequence ASC  ", null);// Where PNodeID='"+TSIID+"'
        }
        else
        {
            cursor = db.rawQuery("SELECT tblQuestionMstr.QuestID,tblQuestionMstr.QuestCode,tblQuestionMstr.QuestDesc,tblQuestionMstr.QuestType,tblQuestionMstr.AnsControlType,tblQuestionMstr.AnsControlInputTypeID,tblQuestionMstr.AnsControlInputTypeMaxLength,tblQuestionMstr.AnsMustRequiredFlg,tblQuestionMstr.QuestBundleFlg,tblQuestionMstr.ApplicationTypeID,tblQuestionMstr.Sequence,tblQuestionMstr.AnsControlInputTypeMinLength,tblQuestionMstr.AnsHint,tblPDAQuestGrpMappingMstr.GrpQuestID,tblPDAQuestGrpMappingMstr.Sequence from tblQuestionMstr inner join tblPDAQuestGrpMappingMstr On tblQuestionMstr.QuestID=tblPDAQuestGrpMappingMstr.QuestID  Order By tblPDAQuestGrpMappingMstr.Sequence ASC  ", null);// Where PNodeID='"+TSIID+"'
        }


        try
        {
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++)
                    {
                        System.out.println("Varun Quest Id = "+(String) cursor.getString(0).toString()+"^"+(String) cursor.getString(4).toString()+"^"+(String) cursor.getString(13).toString()+"~"+ (String) cursor.getString(0).toString()+"^"+(String) cursor.getString(1).toString()+"^"+(String) cursor.getString(2).toString()+"^"+(String) cursor.getString(3).toString()+"^"+(String) cursor.getString(4).toString()+"^"+(String) cursor.getString(5).toString()+"^"+(String) cursor.getString(6).toString()+"^"+(String) cursor.getString(7).toString()+"^"+(String) cursor.getString(8).toString()+"^"+(String) cursor.getString(13).toString()+"^"+(String) cursor.getString(14).toString());
                        hmapQuestionMstr.put((String) cursor.getString(0).toString()+"^"+(String) cursor.getString(4).toString()+"^"+(String) cursor.getString(13).toString(), (String) cursor.getString(0).toString()+"^"+(String) cursor.getString(1).toString()+"^"+(String) cursor.getString(2).toString()+"^"+(String) cursor.getString(3).toString()+"^"+(String) cursor.getString(4).toString()+"^"+(String) cursor.getString(5).toString()+"^"+(String) cursor.getString(6).toString()+"^"+(String) cursor.getString(7).toString()+"^"+(String) cursor.getString(8).toString()+"^"+(String) cursor.getString(9).toString()+"^"+(String) cursor.getString(10).toString()+"^"+(String) cursor.getString(11).toString()+"^"+(String) cursor.getString(12).toString()+"^"+(String) cursor.getString(13).toString());
                        cursor.moveToNext();
                    }
                }
            }
            return hmapQuestionMstr;
        }
        finally
        {
            cursor.close();
            close();
        }
    }

    public LinkedHashMap<String, ArrayList<String>> fnGetQuestionMstrKey()
    {
        LinkedHashMap<String, ArrayList<String>> hmapQuestionMstr=new LinkedHashMap<String, ArrayList<String>>();
        ArrayList<String> listKeyQuesVal=new ArrayList<String>();
        open();
        //tblPDAQuestGrpMappingMstr(GrpQuestID int null,QuestID int null,GrpID int null,GrpNodeID int null,GrpDesc text null,SectionNo int null,GrpCopyID int null,QuestCopyID int null,Sequence int null);";
        //tblQuestionMstr(QuestID int null,QuestCode int null,QuestDesc text null,QuestType int null,AnsControlType int null,AnsControlInputTypeID int null,AnsControlInputTypeMinLength int null,AnsControlInputTypeMaxLength int null,AnsMustRequiredFlg int null,QuestBundleFlg int null,ApplicationTypeID int null,Sequence int null,AnsHint text null);";
        Cursor cursor = db.rawQuery("SELECT tblQuestionMstr.QuestID,tblQuestionMstr.AnsControlType,tblPDAQuestGrpMappingMstr.GrpQuestID,tblPDAQuestGrpMappingMstr.GrpID,tblPDAQuestGrpMappingMstr.Sequence from tblQuestionMstr inner join tblPDAQuestGrpMappingMstr where tblQuestionMstr.QuestID=tblPDAQuestGrpMappingMstr.QuestID  Order By tblPDAQuestGrpMappingMstr.GrpID ASC,tblPDAQuestGrpMappingMstr.Sequence", null);// Where PNodeID='"+TSIID+"'

        try
        {
            if(cursor.getCount()>0)
            {

                String sectionNo="0",preVisousSectionNum="0";
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++)
                    {
                        sectionNo= cursor.getString(3);
                        System.out.println("Squence Added = "+cursor.getString(3)+" : "+cursor.getString(4));
                        if(i==0)
                        {
                            preVisousSectionNum= sectionNo;
                            listKeyQuesVal.add(cursor.getString(0)+"^"+cursor.getString(1)+"^"+cursor.getString(2));
                        }
                        else if(preVisousSectionNum.equals(sectionNo))
                        {
                            listKeyQuesVal.add(cursor.getString(0)+"^"+cursor.getString(1)+"^"+cursor.getString(2));
                        }
                        else
                        {
                            hmapQuestionMstr.put(preVisousSectionNum, listKeyQuesVal);
                            listKeyQuesVal=new ArrayList<String>();
                            preVisousSectionNum=sectionNo;
                            listKeyQuesVal.add(cursor.getString(0)+"^"+cursor.getString(1)+"^"+cursor.getString(2));

                        }
                        if(i==(cursor.getCount()-1))
                        {
                            hmapQuestionMstr.put(preVisousSectionNum, listKeyQuesVal);
                        }

                        cursor.moveToNext();
                    }
                }
            }
            return hmapQuestionMstr;
        }
        finally
        {
            cursor.close();
            close();
        }
    }


    public LinkedHashMap<String, String> getGroupDescription()
    {
        open();
        LinkedHashMap<String, String> hmapGroupDescrptn=new LinkedHashMap<String, String>();
        //tblPDAQuestGrpMappingMstr(GrpQuestID int null,QuestID int null,GrpID int null,GrpNodeID int null,GrpDesc text null,SectionNo int null);";
        Cursor cur=db.rawQuery("Select Distinct GrpID,GrpDesc from tblPDAQuestGrpMappingMstr", null);
        if(cur.getCount()>0)
        {
            if(cur.moveToFirst())
            {
                for(int i=0;i<cur.getCount();i++)
                {
                    hmapGroupDescrptn.put(cur.getString(0), cur.getString(1));
                    cur.moveToNext();
                }

            }
        }
        close();
        return hmapGroupDescrptn;
    }

    public LinkedHashMap<String, ArrayList<String>> fnGetGroupIdMpdWdSectionId()
    {
        LinkedHashMap<String, ArrayList<String>> hmapQuestionMstr=new LinkedHashMap<String, ArrayList<String>>();
        ArrayList<String> listKeyQuesVal=new ArrayList<String>();

        open();
        int sectionCount=getsectionCount();
    //    int sectionCount=0;
        //tblPDAQuestGrpMappingMstr(GrpQuestID int null,QuestID int null,GrpID int null,GrpNodeID int null,GrpDesc text null,SectionNo int null);";
        //tblQuestionMstr(QuestID int null,QuestCode int null,QuestDesc text null,QuestType int null,AnsControlType int null,AnsControlInputTypeID int null,AnsControlInputTypeMinLength int null,AnsControlInputTypeMaxLength int null,AnsMustRequiredFlg int null,QuestBundleFlg int null,ApplicationTypeID int null,Sequence int null,AnsHint text null);";
        Cursor cursor;
       if(sectionCount!=0)
       {
           cursor = db.rawQuery("SELECT Distinct GrpID,SectionNo from tblPDAQuestGrpMappingMstr where SectionNo<="+sectionCount+" order by SectionNo", null);// Where PNodeID='"+TSIID+"'
       }
       else
       {
            cursor = db.rawQuery("SELECT Distinct GrpID,SectionNo from tblPDAQuestGrpMappingMstr order by SectionNo", null);// Where PNodeID='"+TSIID+"'
       }


        try
        {
            if(cursor.getCount()>0)
            {
                String sectionNo="0",preVisousSectionNum="0";
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++)
                    {
                        sectionNo= cursor.getString(1);
                        if(i==0)
                        {
                            preVisousSectionNum= sectionNo;
                            listKeyQuesVal.add(cursor.getString(0));
                        }
                        else if(preVisousSectionNum.equals(sectionNo))
                        {
                            listKeyQuesVal.add(cursor.getString(0));
                        }
                        else
                        {
                            hmapQuestionMstr.put(preVisousSectionNum, listKeyQuesVal);
                            listKeyQuesVal=new ArrayList<String>();
                            preVisousSectionNum=sectionNo;
                            listKeyQuesVal.add(cursor.getString(0));

                        }
                        if(i==cursor.getCount() - 1)
                        {
                            hmapQuestionMstr.put(preVisousSectionNum, listKeyQuesVal);
                        }


                        cursor.moveToNext();
                    }
                }
            }
            return hmapQuestionMstr;
        }
        finally
        {
            cursor.close();
            close();
        }
    }

    public LinkedHashMap<String, String> fnGetDependentQuestionMstr()
    {

        LinkedHashMap<String, String> hmapDpndntQuestionMstr=new LinkedHashMap<String, String>();
        open();
        //tblQuestionDependentMstr(QuestionID int null,OptionID int null,DependentQuestionID int null,GrpID int null,GrpDepQuestID int null);";
        Cursor cursor = db.rawQuery("SELECT DISTINCT GrpDepQuestID,OptionID from tblQuestionDependentMstr", null);// Where PNodeID='"+TSIID+"'
        String []arrbhi=new String[cursor.getCount()];
        try {
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++) {
                        // hmapDpndntQuestionMstr.put((String) cursor.getString(3).toString()+"^"+(String) cursor.getString(1).toString(), (String) cursor.getString(2).toString());
                        arrbhi[i]=(String) cursor.getString(0).toString()+"^"+(String) cursor.getString(1).toString();

                        cursor.moveToNext();
                    }

                }
            }
            for(int cntLoop=0;cntLoop<arrbhi.length;cntLoop++)
            {
                String dpndIdAndOptId=arrbhi[cntLoop].toString();
                String dpndntId=dpndIdAndOptId.split(Pattern.quote("^"))[0];
                String optId=dpndIdAndOptId.split(Pattern.quote("^"))[1];
                StringBuilder quesIdToBeVisOrinVis= fnQuestionIdOnBasisOfDependentQuestionIDdpndntId(dpndntId,optId);
                hmapDpndntQuestionMstr.put(arrbhi[cntLoop].toString(), quesIdToBeVisOrinVis.toString());
            }

            return hmapDpndntQuestionMstr;
        }
        finally
        {
            cursor.close();
            close();
        }

    }

    public LinkedHashMap<String, ArrayList<String>> fnGetSection_Key()
    {
        LinkedHashMap<String, ArrayList<String>> hmapQuestionMstr=new LinkedHashMap<String, ArrayList<String>>();
        ArrayList<String> listKeyQuesVal=new ArrayList<String>();
        open();
        //tblPDAQuestGrpMappingMstr(GrpQuestID int null,QuestID int null,GrpID int null,GrpNodeID int null,GrpDesc text null,SectionNo int null);";
        //tblQuestionMstr(QuestID int null,QuestCode int null,QuestDesc text null,QuestType int null,AnsControlType int null,AnsControlInputTypeID int null,AnsControlInputTypeMinLength int null,AnsControlInputTypeMaxLength int null,AnsMustRequiredFlg int null,QuestBundleFlg int null,ApplicationTypeID int null,Sequence int null,AnsHint text null);";
        Cursor cursor = db.rawQuery("SELECT tblQuestionMstr.QuestID,tblQuestionMstr.AnsControlType,tblPDAQuestGrpMappingMstr.GrpQuestID,tblPDAQuestGrpMappingMstr.SectionNo from tblQuestionMstr inner join tblPDAQuestGrpMappingMstr where tblQuestionMstr.QuestID=tblPDAQuestGrpMappingMstr.QuestID  Order By tblPDAQuestGrpMappingMstr.SectionNo ASC  ", null);// Where PNodeID='"+TSIID+"'

        try
        {
            if(cursor.getCount()>0)
            {
                String sectionNo="0",preVisousSectionNum="0";
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++)
                    {
                        sectionNo= cursor.getString(3);
                        if(i==0)
                        {
                            preVisousSectionNum= sectionNo;
                            listKeyQuesVal.add(cursor.getString(0)+"^"+cursor.getString(1)+"^"+cursor.getString(2));
                        }
                        else if(preVisousSectionNum.equals(sectionNo))
                        {
                            listKeyQuesVal.add(cursor.getString(0)+"^"+cursor.getString(1)+"^"+cursor.getString(2));
                        }
                        else
                        {
                            hmapQuestionMstr.put(preVisousSectionNum, listKeyQuesVal);
                            listKeyQuesVal=new ArrayList<String>();
                            preVisousSectionNum=sectionNo;
                            listKeyQuesVal.add(cursor.getString(0)+"^"+cursor.getString(1)+"^"+cursor.getString(2));

                        }
                        if(i==(cursor.getCount()-1))
                        {
                            hmapQuestionMstr.put(preVisousSectionNum, listKeyQuesVal);
                        }

                        cursor.moveToNext();
                    }
                }
            }
            return hmapQuestionMstr;
        }
        finally
        {
            cursor.close();
            close();
        }
    }

    public LinkedHashMap<String, String> fnGetOptionId_OptionValue()
    {
        LinkedHashMap<String, String> hmapOptionId_OptionValue=new LinkedHashMap<String, String>();
        open();

        Cursor cursor = db.rawQuery("SELECT QuestID,OptID,OptionNo from tblOptionMstr Order By Sequence ASC ", null);// Where PNodeID='"+TSIID+"'

        try
        {
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++)
                    {
                        hmapOptionId_OptionValue.put((String) cursor.getString(0).toString()+"_"+(String) cursor.getString(1).toString(), (String) cursor.getString(2).toString());
                        cursor.moveToNext();
                    }
                }
            }
            return hmapOptionId_OptionValue;
        }
        finally
        {
            cursor.close();
            close();
        }
    }

    public String fnGetQuestIDForOutChannelFromQuestionMstr()
    {//tblPDAQuestGrpMappingMstr(GrpQuestID int null,QuestID int null,GrpID int null,GrpNodeID int null,GrpDesc text null,SectionNo int null,GrpCopyID int null,QuestCopyID int null);";

        int ScodecolumnIndex = 0;
        int ansCntrlTypeIndex = 1;
        int grpQuestIdIndex = 2;
        String QuestID="0";
        open();
        Cursor cursor = db.rawQuery("SELECT tblQuestionMstr.QuestID,tblQuestionMstr.AnsControlType,tblPDAQuestGrpMappingMstr.GrpQuestID FROM tblQuestionMstr inner join tblPDAQuestGrpMappingMstr ON tblQuestionMstr.QuestID=tblPDAQuestGrpMappingMstr.QuestID where tblQuestionMstr.flgQuestIDForOutChannel=1", null);
        try
        {


            if (cursor.moveToFirst())
            {

                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {
                    QuestID = cursor.getString(ScodecolumnIndex)+"^"+cursor.getString(ansCntrlTypeIndex)+"^"+cursor.getString(grpQuestIdIndex);
                    cursor.moveToNext();
                }
            }
            return QuestID;
        }
        finally
        {
            cursor.close();
            close();
        }
    }
    public StringBuilder fnQuestionIdOnBasisOfDependentQuestionIDdpndntId(String dpndntQuesId,String optionId)
    {

        String value;
        open();
        //tblQuestionDependentMstr(QuestionID int null,OptionID int null,DependentQuestionID int null,GrpID int null,GrpDepQuestID int null);";
        Cursor cursor = db.rawQuery("SELECT GrpID from tblQuestionDependentMstr where GrpDepQuestID = '"+dpndntQuesId +"' And OptionID = '"+optionId+"'", null);// Where PNodeID='"+TSIID+"'
        StringBuilder arrbhi=new StringBuilder();
        try {
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++) {
                        // hmapDpndntQuestionMstr.put((String) cursor.getString(3).toString()+"^"+(String) cursor.getString(1).toString(), (String) cursor.getString(2).toString());
                        if(i==0)
                        {
                            arrbhi.append(cursor.getString(0).toString());
                        }
                        else
                        {
                            arrbhi.append("^").append(cursor.getString(0).toString());
                        }

                        cursor.moveToNext();
                    }
                }
            }

            return arrbhi;
        }
        finally
        {
            cursor.close();
            close();
        }
    }

    public int fnGetAnsValID(String optionId)
    {
//tblOptionMstr(OptID int null,QuestID int null,OptionNo int null,OptionDescr text null,Sequence int null );";
        int ScodecolumnIndex = 0;
        int ansValId=0;
        open();
        Cursor cursor = db.rawQuery("SELECT OptionNo FROM tblOptionMstr where OptID='"+optionId+"'", null);
        try
        {


            if (cursor.moveToFirst())
            {

                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {
                    ansValId = Integer.parseInt(cursor.getString(ScodecolumnIndex).toString());
                    cursor.moveToNext();
                }
            }
            return ansValId;
        }
        finally
        {
            cursor.close();
            close();
        }
    }

    public int fnGetBusinessSegmentIDAgainstStoreType(int OutChannelID)
    {

        int ScodecolumnIndex = 0;
        int BusinessSegmentID=0;
        open();
        Cursor cursor = db.rawQuery("SELECT BusinessSegmentID FROM tblOutletChannelBusinessSegmentMaster where OutChannelID="+OutChannelID, null);
        try
        {


            if (cursor.moveToFirst())
            {

                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {
                    BusinessSegmentID = Integer.parseInt(cursor.getString(ScodecolumnIndex).toString());
                    cursor.moveToNext();
                }
            }
            return BusinessSegmentID;
        }
        finally
        {
            cursor.close();
            close();
        }
    }

    public void fnsaveOutletQuestAnsMstrSectionWise(LinkedHashMap<String, String> hmapQuesAnsVal,int CurrentsectionID,String CurrentOutletID)
    {

        open();
        fnDeletesaveOutletQuestAnsMstrSctionWise(CurrentOutletID,CurrentsectionID);
        String channelOptId=getChannelGroupIdOptId();
        String channelkey =getChannelGroupIdKey();
        if(!channelOptId.equals("0-0-0"))
        {
            hmapQuesAnsVal.put(channelkey,channelOptId);
        }
       // hmapQuesAnsVal.put("1^6^1","0-1-80");
        String OutletID="0",QuestID = "0",AnswerType,AnswerValue = "";
        int sectionID = 0;
        int QuestionGroupID=0;

        for(Map.Entry<String, String> entry:hmapQuesAnsVal.entrySet())
        {
            String questId=entry.getKey().split(Pattern.quote("^"))[0].toString();
            AnswerType=entry.getKey().split(Pattern.quote("^"))[1].toString();
            QuestionGroupID=Integer.valueOf(entry.getKey().split(Pattern.quote("^"))[2].toString());
            AnswerValue=entry.getValue();

            ContentValues content=new ContentValues();

            content.put("AnswerType", Integer.parseInt(AnswerType));
            content.put("AnswerValue", AnswerValue.trim());

            content.put("Sstat", 1);

            content.put("sectionID", CurrentsectionID);
            content.put("QuestionGroupID", QuestionGroupID);
            content.put("QuestID", questId);
            content.put("OutletID", CurrentOutletID.toString().trim());

            db.insert(TABLE_OutletQuestAnsMstr, null, content);


        }


        close();

    }



    public void fndeleteNewStoreSalesQuotePaymentDetails(String StoreID)
    {

        db.execSQL("DELETE FROM tblNewStoreSalesQuotePaymentDetails WHERE StoreId ='" + StoreID + "'");
    }

    public void fnDeletesaveOutletQuestAnsMstrSctionWise(String OutletID,int sectionID)
    {
        db.execSQL("DELETE FROM tblOutletQuestAnsMstr WHERE OutletID ='"+ OutletID + "'");// and sectionID="+sectionID
    }

    public void UpdateStoreReturnphotoFlag(String StoreID, String StoreName,int flgReMap)
    {
        try
        {
            //
            final ContentValues values = new ContentValues();
            values.put("StoreName", StoreName);
            values.put("flgReMap", flgReMap);



            int affected16 = db.update("tblPreAddedStores", values,"StoreID=?", new String[] { StoreID });
        }
        catch (Exception ex) {

        }
    }

    public String getLocationDetails()
    {
        String StoresData="0";
        open();
        try {                           //  0           1        2         3       4     5       6     7                 8          9   1 0              11     12    13           14          15         16          17                        18        19    20                21                                   22                                   23
            Cursor cur=db.rawQuery("Select Lattitude , Longitude,Accuracy,Address,City,Pincode,State,fnAccurateProvider,GpsLat,GpsLong,GpsAccuracy,NetwLat,NetwLong,NetwAccuracy,FusedLat,FusedLong,FusedAccuracy,AllProvidersLocation,GpsAddress,NetwAddress,FusedAddress,FusedLocationLatitudeWithFirstAttempt,FusedLocationLongitudeWithFirstAttempt,FusedLocationAccuracyWithFirstAttempt from tblLocationDetails", null);

            if(cur.getCount()>0)
            {
                StringBuilder sBuilder=new StringBuilder();
                if(cur.moveToFirst())
                {
                    for(int i=0;i<cur.getCount();i++)
                    {
                        StoresData=cur.getString(0)+"^"+cur.getString(1)+"^"+cur.getString(2)+"^"+cur.getString(3)+"^"+cur.getString(4)+"^"+cur.getString(5)+"^"+cur.getString(6)+"^"+cur.getString(7)+"^"+cur.getString(8)+"^"+cur.getString(9)+"^"+cur.getString(10)+"^"+cur.getString(11)+"^"+cur.getString(12)+"^"+cur.getString(13)+"^"+cur.getString(14)+"^"+cur.getString(15)+"^"+cur.getString(16)+"^"+cur.getString(17)+"^"+cur.getString(18)+"^"+cur.getString(19)+"^"+cur.getString(20)+"^"+cur.getString(21)+"^"+cur.getString(22)+"^"+cur.getString(23);
                        cur.moveToNext();
                    }
                }

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {
            close();
            return StoresData;
        }
    }
    /*public void fnSave_tblStoreDeatils(String StoreID,String StoreName,String ActualLatitude,String ActualLongitude,String VisitStartTS,String VisitEndTS,String LocProvider,String Accuracy,String BateryLeftStatus,int IsStoreDataCompleteSaved,String PaymentStage,int flgLocationTrackEnabled,String StoreAddress,String StoreCity,String StorePinCode,String StoreState,int Sstat)
    {
        open();
        try {
            fndelete_tblStoreDeatils(StoreID);
            ContentValues initialValues = new ContentValues();

            initialValues.put("StoreID", StoreID.trim());
            initialValues.put("StoreName", StoreName.trim());
            initialValues.put("ActualLatitude", ActualLatitude);
            initialValues.put("ActualLongitude", ActualLongitude);
            initialValues.put("VisitStartTS", VisitStartTS);
            initialValues.put("VisitEndTS", VisitEndTS);
            initialValues.put("LocProvider", LocProvider);
            initialValues.put("Accuracy", Accuracy);
            initialValues.put("BateryLeftStatus", BateryLeftStatus);
            initialValues.put("IsStoreDataCompleteSaved", IsStoreDataCompleteSaved);
            initialValues.put("PaymentStage", PaymentStage);
            initialValues.put("flgLocationTrackEnabled", flgLocationTrackEnabled);
            initialValues.put("StoreAddress", StoreAddress);
            initialValues.put("StoreCity", StoreCity);
            initialValues.put("StorePinCode", StorePinCode);
            initialValues.put("StoreState", StoreState);
            initialValues.put("Sstat", Sstat);

            db.insert(DATABASE_TABLE_MAINtblStoreDeatils, null, initialValues);
        } finally {
            close();
        }
    }*/





    public void fnInsertOrUpdate_tblStoreDeatils(String StoreID,String StoreName,String ActualLatitude,String ActualLongitude,String VisitStartTS,String VisitEndTS,String LocProvider,String Accuracy,String BateryLeftStatus,int IsStoreDataCompleteSaved,String PaymentStage,int flgLocationTrackEnabled,String StoreAddress,String StoreCity,String StorePinCode,String StoreState,int Sstat,int flgLocationServicesOnOff,int flgGPSOnOff,int flgNetworkOnOff,int flgFusedOnOff,int flgInternetOnOffWhileLocationTracking,int flgRestart,int flgStoreOrder,String CityId,String StateId,String MapAddress,String MapCity,String MapPinCode,String MapState)
    {
        int flgIfStoreHasRecords=0;
try {
     flgIfStoreHasRecords=CheckTotalStoreCount();
}
catch (Exception ex)
{

}

        open();
        try {
            Cursor cursor = db.rawQuery("SELECT StoreID FROM "+ DATABASE_TABLE_MAINtblStoreDeatils +" where StoreID='"+StoreID +"'" , null);


            ContentValues initialValues = new ContentValues();


            initialValues.put("StoreName", StoreName.trim());
            initialValues.put("VisitEndTS", VisitEndTS);

            initialValues.put("IsStoreDataCompleteSaved", IsStoreDataCompleteSaved);
            initialValues.put("PaymentStage", PaymentStage);
            initialValues.put("flgLocationTrackEnabled", flgLocationTrackEnabled);
            initialValues.put("StoreAddress", StoreAddress);
            initialValues.put("StoreCity", StoreCity);
            initialValues.put("StorePinCode", StorePinCode);
            initialValues.put("StoreState", StoreState);


            initialValues.put("CityId", CityId);
            initialValues.put("StateId", StateId);
            initialValues.put("Sstat", Sstat);



            if(cursor.getCount()>0)
            {

                int affected = db.update(DATABASE_TABLE_MAINtblStoreDeatils, initialValues, "StoreID=?",new String[] {StoreID});
            }
            else
            {
                initialValues.put("StoreID", StoreID.trim());
                initialValues.put("ActualLatitude", ActualLatitude);
                initialValues.put("ActualLongitude", ActualLongitude);
                initialValues.put("VisitStartTS", VisitStartTS);
                initialValues.put("LocProvider", LocProvider);
                initialValues.put("Accuracy", Accuracy);
                initialValues.put("BateryLeftStatus", BateryLeftStatus);


                initialValues.put("flgLocationServicesOnOff", flgLocationServicesOnOff);
                initialValues.put("flgGPSOnOff", flgGPSOnOff);
                initialValues.put("flgNetworkOnOff", flgNetworkOnOff);
                initialValues.put("flgFusedOnOff", flgFusedOnOff);
                initialValues.put("flgInternetOnOffWhileLocationTracking", flgInternetOnOffWhileLocationTracking);
                initialValues.put("flgRestart", flgRestart);

                initialValues.put("flgStoreOrder", (flgIfStoreHasRecords+1));
//,MapAddress text null,MapCity text null,MapPinCode text null,MapState
                initialValues.put("MapAddress", MapAddress);
                initialValues.put("MapCity", MapCity);
                initialValues.put("MapPinCode", MapPinCode);
                initialValues.put("MapState", MapState);

                db.insert(DATABASE_TABLE_MAINtblStoreDeatils, null, initialValues);
            }


        } finally {
            close();
        }

    }



    public int fnCheckIfStoreIDExistsIn_tblStoreDeatils(String StoreID)
    {
        open();
        int flgCheckIfStoreExists=0;
        //tblStoreDetails(StoreID
        Cursor cursor2 = db.rawQuery("SELECT Count(*) FROM tblStoreDetails where StoreID='"+StoreID+"'", null);
        try {
            if(cursor2.getCount()>0)
            {
                if(cursor2.moveToFirst())
                {
                    if (cursor2.getInt(0)>0)
                    {

                        flgCheckIfStoreExists=1;

                    }
                }
            }


        }
        catch(Exception e)
        {

        }
        finally {
            cursor2.close();
            close();
        }
        return flgCheckIfStoreExists;
    }

    public ArrayList<String> fnGetDetails_tblStoreDeatils(String StoreID)
    {
        ArrayList<String> arrBasisDetailsAgainstStore=new ArrayList<String>();

        open();
        try
        {
            Cursor cursor = db.rawQuery("SELECT StoreName,IFNULL(PaymentStage,'0') AS PaymentStage,IFNULL(StoreAddress,'NA') AS StoreAddress,IFNULL(StoreCity,'NA') AS StoreCity,IFNULL(StorePinCode,'NA') AS StorePinCode,IFNULL(StoreState,'NA') AS StoreState,ActualLatitude,ActualLongitude,Accuracy FROM tblStoreDetails where StoreID='" + StoreID+"'", null);
            if (cursor.moveToFirst())
            {

                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {
                    arrBasisDetailsAgainstStore.add((String) cursor.getString(0).toString());
                    arrBasisDetailsAgainstStore.add((String) cursor.getString(1).toString());
                    arrBasisDetailsAgainstStore.add((String) cursor.getString(2).toString());
                    arrBasisDetailsAgainstStore.add((String) cursor.getString(3).toString());
                    arrBasisDetailsAgainstStore.add((String) cursor.getString(4).toString());
                    arrBasisDetailsAgainstStore.add((String) cursor.getString(5).toString());
                    arrBasisDetailsAgainstStore.add((String) cursor.getString(6).toString());
                    arrBasisDetailsAgainstStore.add((String) cursor.getString(7).toString());
                    arrBasisDetailsAgainstStore.add((String) cursor.getString(8).toString());
                    cursor.moveToNext();
                }
            }


        }
        catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {
            close();
        }

        return arrBasisDetailsAgainstStore;
    }
    public void saveOutletChammetQstnIdGrpId(int grpQstId,int qstId,String optId,int section_count)
    {
  /*private static final String TABLE_QSTOUTCHANNEL = "tblQuestIDForOutChannel";
   private static final String TABLE_QST_NAME = "tblQuestIDForName";
    private static final String DATABASE_CREATE_TABLE_QSTOUTCHANNEL = "create table tblOptionMstr(GrpQstId int null,QuestID int null);";
    private static final String DATABASE_CREATE_TABLE_QST_NAME = "create table tblOptionMstr(GrpQstId int null,QuestID int null);";*/
      //tblQuestIDForOutChannel(GrpQstId int null,QuestID int null,OptID text null,SectionCount int null);";
        ContentValues values=new ContentValues();
        values.put("GrpQstId", grpQstId);
        values.put("QuestID", qstId);
        values.put("OptID", optId);
        values.put("SectionCount", section_count);

        db.insert(TABLE_QSTOUTCHANNEL, null, values);

    }
    public void savetblQuestIDForName(int grpQstId,int qstId)
    {
  /*private static final String TABLE_QSTOUTCHANNEL = "tblQuestIDForOutChannel";
   private static final String TABLE_QST_NAME = "tblQuestIDForName";
    private static final String DATABASE_CREATE_TABLE_QSTOUTCHANNEL = "create table tblOptionMstr(GrpQstId int null,QuestID int null);";
    private static final String DATABASE_CREATE_TABLE_QST_NAME = "create table tblOptionMstr(GrpQstId int null,QuestID int null);";*/
        ContentValues values=new ContentValues();
        values.put("GrpQstId", grpQstId);
        values.put("QuestID", qstId);
        db.insert(TABLE_QST_NAME, null, values);

    }



    public String getNameQstGrpId_QstId()
    {
        String grpQstId_qstIdForName="";

        open();
        try {


            Cursor cur=db.rawQuery("Select * from tblQuestIDForName", null);
            if(cur.getCount()>0)
            {
                if(cur.moveToFirst())
                {
                    grpQstId_qstIdForName=cur.getString(0)+"^"+cur.getString(1);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {
            close();
            return grpQstId_qstIdForName;
        }
    }
    public LinkedHashMap<String, String> fnGettblSalesQuotePaymentModeMstrAllValues()
    {
        LinkedHashMap<String, String> hmapQuestionMstr=new LinkedHashMap<String, String>();
        open();
        Cursor cursor= db.rawQuery("SELECT PymtModeId,PymtMode from tblSalesQuotePaymentModeMstr", null);// Where PNodeID='"+TSIID+"'
        //(String) cursor.getString(0).toString()+"^"+(String) cursor.getString(1).toString()+"^"+(String) cursor.getString(2).toString()+"^"+(String) cursor.getString(3).toString()+"^"+(String) cursor.getString(4).toString()+"^"+(String) cursor.getString(5).toString()+"^"+(String) cursor.getString(6).toString()+"^"+(String) cursor.getString(7).toString()+"^"+(String) cursor.getString(8).toString()+"^"+(String) cursor.getString(9).toString()+"^"+(String) cursor.getString(10).toString()
        // close();
        try {
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++) {
                        hmapQuestionMstr.put((String) cursor.getString(0).toString(),(String) cursor.getString(1).toString());
                        //    System.out.println("QuestID:"+(String)cursor.getString(0).toString()+"QuestCode:"+(String) cursor.getString(1).toString()+"QuestDesc:"+(String) cursor.getString(2).toString()+"QuestType:"+(String) cursor.getString(3).toString()+"AnsControlType:"+(String) cursor.getString(4).toString()+"AnsControlInputTypeID:"+(String) cursor.getString(5).toString()+"AnsControlInputTypeMaxLength:"+(String) cursor.getString(6).toString()+"AnsMustRequiredFlg:"+(String) cursor.getString(7).toString()+"QuestBundleFlg:"+(String) cursor.getString(8).toString()+"ApplicationTypeID:"+(String) cursor.getString(9).toString()+"Sequence:"+(String) cursor.getString(10).toString());
                        cursor.moveToNext();
                    }
                }
            }
            return hmapQuestionMstr;
        }
        finally
        {
            cursor.close();
            close();
        }
    }

    public String[] getAllOptValueDpndntQuest()
    {
        String[] allQuestValesDpndnt=null;
        open();
        try {

            //tblQuestionDependentMstr(QuestionID int null,OptionID int null,DependentQuestionID int null,GrpID int null,GrpDepQuestID int null)
            Cursor cur=db.rawQuery("Select Distinct GrpID from tblQuestionDependentMstr", null);
            if(cur.getCount()>0)
            {
                allQuestValesDpndnt=new String[cur.getCount()];
                if(cur.moveToFirst())
                {
                    for(int i=0;i<cur.getCount();i++)
                    {
                        allQuestValesDpndnt[i]=String.valueOf(cur.getInt(0));
                        cur.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {
            close();
            return allQuestValesDpndnt;
        }

    }

    public String[] fnGetDependentParentQuesIdr()
    {


        open();
//tblQuestionDependentMstr(QuestionID int null,OptionID int null,DependentQuestionID int null,GrpID int null,GrpDepQuestID int null);";
        Cursor cursor = db.rawQuery("SELECT DISTINCT GrpDepQuestID from tblQuestionDependentMstr", null);// Where PNodeID='"+TSIID+"'
        String []arrbhi=new String[cursor.getCount()];
        try {
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++) {
                        // hmapDpndntQuestionMstr.put((String) cursor.getString(3).toString()+"^"+(String) cursor.getString(1).toString(), (String) cursor.getString(2).toString());
                        arrbhi[i]=(String) cursor.getString(0).toString();
                        cursor.moveToNext();
                    }
                }
            }

            return arrbhi;
        }
        finally
        {
            cursor.close();
            close();
        }
    }

    public LinkedHashMap<String, String> fnGetQuestionID_AnsCntrlType()
    {
        LinkedHashMap<String, String> hmapQuestionId_AnsCntrlTyper=new LinkedHashMap<String, String>();
        open();
        int lastIndex=0;
        //tblQuestionMstr(QuestID int null,QuestCode int null,QuestDesc text null,QuestType int null,AnsControlType int null,AnsControlInputTypeID int null,AnsControlInputTypeMinLength int null,AnsControlInputTypeMaxLength int null,AnsMustRequiredFlg int null,QuestBundleFlg int null,ApplicationTypeID int null,Sequence int null,AnsHint text null);";
        Cursor cursor = db.rawQuery("SELECT QuestID,AnsControlType from tblQuestionMstr", null);

        try {
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++) {
                        hmapQuestionId_AnsCntrlTyper.put(cursor.getString(0).trim(),(String) cursor.getString(1).toString().trim());
                        //    System.out.println("QuestID:"+(String)cursor.getString(0).toString()+"QuestCode:"+(String) cursor.getString(1).toString()+"QuestDesc:"+(String) cursor.getString(2).toString()+"QuestType:"+(String) cursor.getString(3).toString()+"AnsControlType:"+(String) cursor.getString(4).toString()+"AnsControlInputTypeID:"+(String) cursor.getString(5).toString()+"AnsControlInputTypeMaxLength:"+(String) cursor.getString(6).toString()+"AnsMustRequiredFlg:"+(String) cursor.getString(7).toString()+"QuestBundleFlg:"+(String) cursor.getString(8).toString()+"ApplicationTypeID:"+(String) cursor.getString(9).toString()+"Sequence:"+(String) cursor.getString(10).toString());
                        cursor.moveToNext();
                    }

                }
            }
            return hmapQuestionId_AnsCntrlTyper;
        }
        finally
        {
            cursor.close();
            close();
        }
    }

    public LinkedHashMap<String, ArrayList<String>> getAllQuestIdDpndnt()
    {// //tblQuestionDependentMstr(QuestionID int null,OptionID int null,DependentQuestionID int null,GrpID int null,GrpDepQuestID int null);";
        LinkedHashMap<String, ArrayList<String>> hmapQuestDependVisible=new LinkedHashMap<String, ArrayList<String>>();
        ArrayList<String> listQestDpndnt=new ArrayList<String>();

        try {


            open();

            Cursor cur=db.rawQuery("Select GrpID,GrpDepQuestID from tblQuestionDependentMstr Order By GrpDepQuestID", null);
            if(cur.getCount()>0)
            {
                if(cur.moveToFirst())
                {
                    String questId,dpndntQuestId,prvsdpndntQuestId = null;
                    for(int i=0;i<cur.getCount();i++)
                    {
                        questId=String.valueOf(cur.getInt(0));
                        dpndntQuestId =String.valueOf(cur.getInt(1));
                        if(i==0)
                        {
                            listQestDpndnt.add(questId);
                            prvsdpndntQuestId=dpndntQuestId;
                        }
                        else
                        {
                            if( prvsdpndntQuestId==dpndntQuestId)
                            {
                                listQestDpndnt.add(questId);
                            }
                            else
                            {
                                hmapQuestDependVisible.put(prvsdpndntQuestId, listQestDpndnt);
                                listQestDpndnt=new ArrayList<String>();

                                listQestDpndnt.add(questId);
                                prvsdpndntQuestId=dpndntQuestId;
                            }


                        }
                        if(i==cur.getCount()-1)
                        {
                            hmapQuestDependVisible.put(prvsdpndntQuestId, listQestDpndnt);
                        }
                        cur.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ErrorDatabase getAllQuestIdWhoseValuesDpndnt ="+ e.toString());
        } finally
        {
            close();
            return hmapQuestDependVisible;
        }
    }

    public LinkedHashMap<String, String> getAllQstGrpIdAgainstGrp()
    {
        //tblPDAQuestGrpMappingMstr(GrpQuestID int null,QuestID int null,GrpID int null,GrpNodeID int null,GrpDesc text null,SectionNo int null);";
        open();
        LinkedHashMap<String, String> hmapData=new LinkedHashMap<String, String>();
        Cursor cur=db.rawQuery("Select GrpID,GrpQuestID from tblPDAQuestGrpMappingMstr", null);
        if(cur.getCount()>0)
        {
            if(cur.moveToFirst())
            {
                for(int i=0;i<cur.getCount();i++)
                {
                    hmapData.put(cur.getString(1), cur.getString(0));
                    cur.moveToNext();
                }
            }
        }
        close();
        return hmapData;
    }


    public LinkedHashMap<String, String> getQuestGrpIdLnkWdQstId()
    {
        //tblPDAQuestGrpMappingMstr(GrpQuestID int null,QuestID int null,GrpID int null,GrpNodeID int null,GrpDesc text null,SectionNo int null);";
        open();
        LinkedHashMap<String, String> hmapData=new LinkedHashMap<String, String>();
        Cursor cur=db.rawQuery("Select QuestID,GrpQuestID from tblPDAQuestGrpMappingMstr", null);
        if(cur.getCount()>0)
        {
            if(cur.moveToFirst())
            {
                for(int i=0;i<cur.getCount();i++)
                {
                    hmapData.put(cur.getString(1), cur.getString(0));
                    cur.moveToNext();
                }
            }
        }
        close();
        return hmapData;
    }


    public LinkedHashMap isMstrQuestToHide()
    {
        LinkedHashMap<String,String> hmapMstrQstOptId=new LinkedHashMap<String,String>();
        open();

        String channelOptId=getChannelGroupIdOptId();
        String channelkey =getChannelGroupIdKey();
        if(!channelOptId.equals("0-0-0"))
        {
            hmapMstrQstOptId.put(channelkey,channelOptId);
        }
        else
        {
            hmapMstrQstOptId.put(channelkey,"0");
        }
        close();
        return hmapMstrQstOptId;
    }
    public LinkedHashMap<String, String> getQuestAnswer(String tempId)
    {
        open();
        LinkedHashMap<String, String> hmapRtrvQuestAns=new LinkedHashMap<String, String>();
        try {
            String channelOptId=getChannelGroupIdOptId();
            String channelkey =getChannelGroupIdKey();
            if(!channelOptId.equals("0-0-0"))
            {
                hmapRtrvQuestAns.put(channelkey,channelOptId);
            }

          //  hmapRtrvQuestAns.put("1^6^1","0-1-80");
            //tblOutletQuestAnsMstr (OutletID text not null,QuestID text not null,AnswerType text null, AnswerValue text null,QuestionGroupID integer null,sectionID integer null,Sstat integer not null);";
            Cursor cursor=db.rawQuery("Select * from tblOutletQuestAnsMstr where OutletID='"+tempId+"'", null);

            if(cursor.getCount()>0)
            {
                if(cursor.moveToFirst())
                {
                    for(int i=0;i<cursor.getCount();i++)
                    {

                        hmapRtrvQuestAns.put(cursor.getString(1)+"^"+cursor.getString(2)+"^"+cursor.getString(4), cursor.getString(3));
                        cursor.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {
            close();
            return hmapRtrvQuestAns;
        }
    }

    public LinkedHashMap<String, String> getGroupIdCopyAsAbove()
    {
        LinkedHashMap<String, String> hmapGroupIdCopyAsAbove=new LinkedHashMap<String, String>();
        open();
        Cursor cur=db.rawQuery("Select DISTINCT GrpID,GrpCopyID from tblPDAQuestGrpMappingMstr where GrpCopyID<>0",null);
        if(cur.getCount()>0)
        {
            if(cur.moveToFirst())
            {
                for(int i=0;i<cur.getCount();i++)
                {
                    hmapGroupIdCopyAsAbove.put(cur.getString(0), cur.getString(1)) ;
                    cur.moveToNext();
                }
            }
        }
        close();
        return hmapGroupIdCopyAsAbove;
    }

    public String getChannelGroupId()
    {
        String grpQstIdForChannel="";

        open();
        try {

//tblQuestIDForOutChannel(GrpQstId int null,QuestID int null,OptID text null,SectionCount int null);";
            Cursor cur=db.rawQuery("Select GrpQstId,QuestID from tblQuestIDForOutChannel", null);
            if(cur.getCount()>0)
            {
                if(cur.moveToFirst())
                {
                    grpQstIdForChannel=cur.getString(0);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {
            close();
            return grpQstIdForChannel;
        }
    }

    public String getChannelGroupIdKey()
    {
        String keyForChannel="";


        try {
//tblQuestionMstr(QuestID int null,QuestCode int null,QuestDesc text null,QuestType int null,AnsControlType int null,
//tblQuestIDForOutChannel(GrpQstId int null,QuestID int null,OptID text null,SectionCount int null);";
            Cursor cur=db.rawQuery("Select tblQuestIDForOutChannel.GrpQstId,tblQuestIDForOutChannel.QuestID,tblQuestionMstr.AnsControlType from tblQuestIDForOutChannel inner join tblQuestionMstr On tblQuestIDForOutChannel.QuestID=tblQuestionMstr.QuestID", null);
            if(cur.getCount()>0)
            {
                if(cur.moveToFirst())
                {
                    keyForChannel=cur.getString(1)+"^"+cur.getString(2)+"^"+cur.getString(0);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {

            return keyForChannel;
        }
    }

    public String getChannelGroupIdOptId()
    {
        String grpQstIdOptIdForChannel="0-0-0";


        try {

//tblQuestIDForOutChannel(GrpQstId int null,QuestID int null,OptID text null,SectionCount int null);";
            Cursor cur=db.rawQuery("Select OptID from tblQuestIDForOutChannel", null);
            if(cur.getCount()>0)
            {
                if(cur.moveToFirst())
                {

                    grpQstIdOptIdForChannel=cur.getString(0);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {

            return grpQstIdOptIdForChannel;
        }
    }

    public int getsectionCount()
    {
        int sectionCount=0;


        try {

//tblQuestIDForOutChannel(GrpQstId int null,QuestID int null,OptID text null,SectionCount int null);";
            Cursor cur=db.rawQuery("Select SectionCount from tblQuestIDForOutChannel", null);
            if(cur.getCount()>0)
            {
                if(cur.moveToFirst())
                {

                    sectionCount=cur.getInt(0);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {

            return sectionCount;
        }
    }
    public  LinkedHashMap<String, String> getDepOptMstr()
    {


        LinkedHashMap<String, String> hmapgetOptDepMstr=new LinkedHashMap<String, String>();

        open();
        try {


            Cursor cur=db.rawQuery("Select GrpQuestID,GrpDepQuestID from tblPDAQuestOptionDependentMstr", null);
            if(cur.getCount()>0)
            {
                String grpDepQstId,grpQstd;
                if(cur.moveToFirst())
                    for(int i=0;i<cur.getCount();i++)
                    {
                        grpQstd=cur.getString(0);
                        grpDepQstId=cur.getString(1);
                        hmapgetOptDepMstr.put(grpQstd, grpDepQstId);
                        cur.moveToNext();


                    }

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {
            close();
            return hmapgetOptDepMstr;
        }
    }
    public int checkCountIntblNewStoreSalesQuotePaymentDetails(String  StoreId)
    {
        open();
        Cursor cursor =null;
        int check=0;
        try {

            //tblNewStoreSalesQuotePaymentDetails (StoreId text null,PymtStageId text null,Sstat text null);";
            cursor= db.rawQuery("SELECT Count(*) FROM tblNewStoreSalesQuotePaymentDetails WHERE  StoreId ='"+ StoreId + "'", null);
            if (cursor.getCount() > 0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++)
                    {
                        check=1;
                        cursor.moveToNext();
                    }
                }

            }


        } catch (Exception e) {
            System.out.println("shivam query = "+e.toString());
        }
        finally {
            cursor.close();
            close();
        }
        return check;
    }
    public long fnsaveNewStoreSalesQuotePaymentDetails(String StoreId,String PaymentStageId) {

        ContentValues initialValues = new ContentValues();


        initialValues.put("StoreId", StoreId.toString().trim());

        initialValues.put("PymtStageId", PaymentStageId.toString().trim());
        initialValues.put("Sstat", "1");

        return db.insert(DATABASE_TABLE_NewStoreSalesQuotePaymentDetails, null, initialValues);
    }
    public LinkedHashMap<String, String> fnGettblSalesQuotePaymentModeMstr(String PaymentStageID)
    {


        String strPaymentModeID="";
        LinkedHashMap<String, String> hmapQuestionMstr=new LinkedHashMap<String, String>();
        open();
        strPaymentModeID=fngetPaymentModeIDsBasedOnStageID(PaymentStageID);

        String searchString="0";

        if(strPaymentModeID.indexOf(",")!=-1)
        {
            String[] arrsearchString=strPaymentModeID.split(Pattern.quote(","));
            for(int i=0;i<arrsearchString.length;i++)
            {
                if(i==0)
                {
                    searchString="'"+arrsearchString[i].trim().toLowerCase()+"'";
                }
                else
                {
                    searchString+= ",'"+arrsearchString[i].trim().toLowerCase()+"'";
                }
            }
        }
        else
        {
            searchString="'"+strPaymentModeID.trim().toLowerCase()+"'";
        }

        // Cursor cursor = db.rawQuery("SELECT SST_NameId,SST_Name_des from tbl_SST_NameMstr Order By Sequence ASC  ", null);// Where PNodeID='"+TSIID+"'
        Cursor cursor=null;
//tblSalesQuotePaymentModeMstr (PymtModeId text null,PymtMode text null);";
     //   cursor= db.rawQuery("SELECT PymtModeId,PymtMode from tblSalesQuotePaymentModeMstr Where  PymtModeId in ("+searchString+")  ", null);// Where PNodeID='"+TSIID+"'

  if(PaymentStageID.equals("1"))
  {
  cursor= db.rawQuery("SELECT PymtModeId,PymtMode from tblSalesQuotePaymentModeMstr limit 3  ", null);// Where PNodeID='"+TSIID+"'
  }
  if(PaymentStageID.equals("2"))
  {
  cursor= db.rawQuery("SELECT PymtModeId,PymtMode from tblSalesQuotePaymentModeMstr limit 4  ", null);// Where PNodeID='"+TSIID+"'
  }
  if(PaymentStageID.equals("3"))
  {
  cursor= db.rawQuery("SELECT PymtModeId,PymtMode from tblSalesQuotePaymentModeMstr limit 5  ", null);// Where PNodeID='"+TSIID+"'
  }
        if(PaymentStageID.equals("1")||PaymentStageID.equals("2")||PaymentStageID.equals("3"))
        {
            try {
                if(cursor.getCount()>0)
                {
                    if (cursor.moveToFirst())
                    {
                        for (int i = 0; i <= (cursor.getCount() - 1); i++) {
                            hmapQuestionMstr.put((String) cursor.getString(0).toString(),(String) cursor.getString(1).toString());
                            //    System.out.println("QuestID:"+(String)cursor.getString(0).toString()+"QuestCode:"+(String) cursor.getString(1).toString()+"QuestDesc:"+(String) cursor.getString(2).toString()+"QuestType:"+(String) cursor.getString(3).toString()+"AnsControlType:"+(String) cursor.getString(4).toString()+"AnsControlInputTypeID:"+(String) cursor.getString(5).toString()+"AnsControlInputTypeMaxLength:"+(String) cursor.getString(6).toString()+"AnsMustRequiredFlg:"+(String) cursor.getString(7).toString()+"QuestBundleFlg:"+(String) cursor.getString(8).toString()+"ApplicationTypeID:"+(String) cursor.getString(9).toString()+"Sequence:"+(String) cursor.getString(10).toString());
                            cursor.moveToNext();
                        }
                    }
                }

            }
            finally
            {
                cursor.close();
                close();
            }
        }
        return hmapQuestionMstr;

    }
    public String[] fnGetGroupIdQuestionMstr(String questGroupID,int section)
    {
        String[] questonWdSameGroupId=new String[2];
        open();
        int lastIndex=0;
        //tblPDAQuestGrpMappingMstr(GrpQuestID int null,QuestID int null,GrpID int null,GrpNodeID int null,GrpDesc text null,SectionNo int null);";
        Cursor cursor = db.rawQuery("SELECT tblQuestionMstr.QuestID,tblQuestionMstr.AnsControlType, tblPDAQuestGrpMappingMstr.GrpQuestID from tblQuestionMstr inner join tblPDAQuestGrpMappingMstr on tblQuestionMstr.QuestID=tblPDAQuestGrpMappingMstr.QuestID where tblQuestionMstr.QuestBundleGroupId = '"+questGroupID+"' AND tblPDAQuestGrpMappingMstr.SectionNo="+section, null);
        try {
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++) {
                        questonWdSameGroupId[i]=(String) cursor.getString(0).toString()+"^"+(String) cursor.getString(1).toString()+"^"+(String) cursor.getString(2).toString();

                        cursor.moveToNext();
                    }

                }
            }
            return questonWdSameGroupId;
        }
        finally
        {
            cursor.close();
            close();
        }
    }
    public String fngetPaymentModeIDsBasedOnStageID(String PaymentStageID)
    {

        String flag="0";
        try {
            //open();
            Cursor cursor = db.rawQuery("SELECT PymtModeId from tblSalesQuotePaymentStageMstr where PymtStageId= '"+PaymentStageID +"'", null);

            if(cursor.getCount()>0){
                if (cursor.moveToFirst()){
                    flag= cursor.getString(0).toString();
                }

            }
            return flag;
        }catch(Exception e)
        {

        }

        finally
        {

            //close();
            return flag;
        }

    }
    public ArrayList<String> fnGetOptionMstr(int questionId)
    {
        //  LinkedHashMap<String, String> hmapOptionMstr=new LinkedHashMap<String, String>();
        ArrayList<String> listOptionMstr = new ArrayList<String>();
        // ArrayList<String> al1 = new ArrayList<String>();
        String idd="0";

        open();
        Cursor cursor = db.rawQuery("SELECT  tblQuestionMstr.AnsControlType ,tblOptionMstr.OptID,tblOptionMstr.QuestID,tblOptionMstr.OptionDescr,tblOptionMstr.Sequence from tblOptionMstr inner join tblQuestionMstr on tblOptionMstr.QuestID =tblQuestionMstr.QuestID  Where tblOptionMstr.QuestID='"+questionId+"' Order By tblOptionMstr.Sequence ASC ", null);// Where PNodeID='"+TSIID+"'
        // close();
        try {
            if(cursor.getCount()>0)

            {
                if (cursor.moveToFirst())
                {

                    for (int i = 0; i <= (cursor.getCount() - 1); i++) {

                        listOptionMstr.add(i,(String) cursor.getString(1).toString()+"^"+(String) cursor.getString(3).toString());
                        cursor.moveToNext();
                    }
                    //  hmapOptionMstr.put(idd, al1);
                }
            }
            return listOptionMstr;
        }
        finally
        {
            cursor.close();
            close();
        }
    }
    public String[] getQuestIdGroupIdCopyAsAbove(int GrpCopyID,int groupId)
    {
        String[] questGroupIdCopyAsAbove = null;
        open();
        Cursor cur=db.rawQuery("Select QuestCopyID from tblPDAQuestGrpMappingMstr where GrpID="+groupId+" AND GrpCopyID="+GrpCopyID,null);
        if(cur.getCount()>0)
        {
            questGroupIdCopyAsAbove=new String[cur.getCount()];
            if(cur.moveToFirst())
            {
                for(int i=0;i<cur.getCount();i++)
                {
                    questGroupIdCopyAsAbove[i]= cur.getString(0);
                    cur.moveToNext();
                }
            }
        }
        close();
        return questGroupIdCopyAsAbove;
    }
    public ArrayList<String> getDepOtnVal(String depAnsValid,int grpId,int grpQstDepId)
    {
        ////  private static final String DATABASE_CREATE_TABLE_QUESTION_OPTION_VAL_DEPENDENTMstr = "create table tblPDAQuestOptionValuesDependentMstr(DepQstId int null,DepAnswValId int null,QstId int null,AnswValId text null,OptDescr text null,Sequence int null,GrpQuestID int null,GrpDepQuestID int null);";
        open();

        ArrayList<String> listOptId_OptName=new ArrayList<String>();
        try {


            Cursor cur=db.rawQuery("Select AnswValId,OptDescr from tblPDAQuestOptionValuesDependentMstr where GrpQuestID="+grpId+" AND GrpDepQuestID="+grpQstDepId+" AND DepAnswValId='"+depAnsValid+"'", null);

            if(cur.getCount()>0)
            {
                String optId_optDescr;
                if(cur.moveToFirst())
                {
                    for(int i=0;i<cur.getCount();i++)
                    {
                        optId_optDescr=cur.getString(0)+"^"+cur.getString(1);
                        listOptId_OptName.add(optId_optDescr);
                        cur.moveToNext();
                    }

                }


            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {
            close();
            return listOptId_OptName;
        }

    }
    public int checkCountIntblNewStoreMainTable(String  StoreId)
    {
        open();
        Cursor cursor =null;
        int check=0;
        try {

            cursor= db.rawQuery("SELECT Count(*) FROM tblOutletQuestAnsMstr WHERE  OutletID ='"+ StoreId + "'", null);
            if (cursor.getCount() > 0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++)
                    {
                        check=1;
                        cursor.moveToNext();
                    }
                }

            }


        } catch (Exception e) {
            System.out.println("shivam query = "+e.toString());
        }
        finally {
            cursor.close();
            close();
        }
        return check;
    }


    public void deletetblStoreCountDetails()
    {
        db.execSQL("DELETE FROM tblStoreCountDetails");

    }


    public void insertImageInfo(String tempId,LinkedHashMap<String, String> hmapAllValues)
    {
        //tableImage(tempId text null,QstIdAnsCntrlTyp text null,imageName text null,imagePath text null,Sstat integer null);";
        open();
        Cursor cur=db.rawQuery("Select imageName from tableImage where StoreID='"+tempId+"'", null);
        if(cur.getCount()>0)
        {
            db.delete(TABLE_IMAGE, "StoreID=?", new String[]{tempId});
        }
        String StoreID = null, qstIdAndCntrlTyp = null, _imageName = null, imagePath = null,clkdTime;
        int sStat = 0;
        for(Map.Entry<String, String> hmaEntry:hmapAllValues.entrySet())
        {
            StoreID=hmaEntry.getValue().split(Pattern.quote("~"))[1].toString();
            qstIdAndCntrlTyp=hmaEntry.getValue().split(Pattern.quote("~"))[0].toString();
            _imageName=hmaEntry.getKey();
            imagePath=hmaEntry.getValue().split(Pattern.quote("~"))[2].toString();
            clkdTime=hmaEntry.getValue().split(Pattern.quote("~"))[3].toString();
            sStat=Integer.valueOf(hmaEntry.getValue().split(Pattern.quote("~"))[4].toString());


            // tableImage(tempId
            ContentValues content=new ContentValues();
            content.put("StoreID", StoreID);
            content.put("QstIdAnsCntrlTyp", qstIdAndCntrlTyp);

            content.put("imageName", _imageName);
            content.put("imagePath", imagePath);
            content.put("ImageClicktime", clkdTime);
            content.put("Sstat", sStat);

            db.insert(TABLE_IMAGE, null, content);


        }

        close();
    }



    public void updateSSttImage(String imageName,int sStat)
    {
        open();
        Cursor cursorImage=db.rawQuery("Select StoreID from tableImage where imageName='"+imageName+"'", null);
        if(cursorImage.getCount()>0)
        {
            ContentValues value=new ContentValues();
            value.put("Sstat", sStat);
            db.update(TABLE_IMAGE, value, "imageName=?", new String[]{imageName});
        }

        close();
    }


    public ArrayList<String> getImagePath(String StoreID)
    {
        open();
        ArrayList<String> listImagePath=new ArrayList<String>();
        try {

            //tableImage(tempId text null,QstIdAnsCntrlTyp text null,imageName text null,imagePath text null,ImageClicktime text null,Sstat integer null);";
            //imagButtonTag+"~"+tempId+"~"+uriSavedImage.toString()+"~"+clkdTime+"~"+"2";
            Cursor cursor=db.rawQuery("Select QstIdAnsCntrlTyp,StoreID,imagePath,ImageClicktime,Sstat from tableImage where StoreID='"+StoreID+"'", null);

            if(cursor.getCount()>0)
            {
                if(cursor.moveToFirst())
                {
                    for(int i=0;i<cursor.getCount();i++)
                    {
                        listImagePath.add(cursor.getString(0)+"~"+cursor.getString(1)+"~"+cursor.getString(2)+"~"+cursor.getString(3)+"~"+cursor.getString(4));
                        cursor.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {
            close();
            return listImagePath;
        }
    }


    public ArrayList<String> getImageDetails(int sStat)
    {
        open();
        ArrayList<String> listImageDetails=new ArrayList<String>();
        try {

            //tableImage(StoreID text null,QstIdAnsCntrlTyp text null,imageName text null,imagePath text null,ImageClicktime text null,Sstat integer null);";
            Cursor cursor=db.rawQuery("Select StoreID,imagePath,imageName from tableImage where Sstat="+sStat, null);

            if(cursor.getCount()>0)
            {
                if(cursor.moveToFirst())
                {
                    for(int i=0;i<cursor.getCount();i++)
                    {
                        listImageDetails.add(cursor.getString(0)+"^"+cursor.getString(1)+"^"+cursor.getString(2));
                        cursor.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {
            close();
            return listImageDetails;
        }
    }


    // Changes by Sunil

    public String[] getAllStoreIDForPhotoTakenDetail()
    {
        Cursor cursor=null;
        try
        {
            cursor = db.rawQuery("SELECT DISTINCT(StoreID) FROM tableImage where Sstat=5", null);

            String StoreName[] = new String[cursor.getCount()];

            if (cursor.moveToFirst())
            {
                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {
                    StoreName[i] = (String) cursor.getString(0).toString();
                    cursor.moveToNext();
                }
            }

            return StoreName;
        }
        finally
        {
            cursor.close();
        }

    }

    public int getExistingPicNos(String StoreID)
    {

        Cursor cursor=null;
        try
        {
            cursor = db.rawQuery("SELECT Count(StoreID) FROM tableImage where StoreID='" + StoreID + "'", null);

            int strProdStockQty = 0;
            if (cursor.moveToFirst())
            {

                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {
                    if (!cursor.isNull(0))
                    {
                        strProdStockQty = Integer.parseInt(cursor.getString(0).toString());
                        cursor.moveToNext();
                    }

                }
            }
            return strProdStockQty;
        } finally {
            cursor.close();
        }
    }

    public String[] getImgsPath(String StoreID)
    {
        Cursor cursor=null;
        try
        {
            cursor = db.rawQuery("SELECT imageName FROM tableImage WHERE StoreID ='"+ StoreID + "'", null);

            String StoreName[] = new String[cursor.getCount()];

            if (cursor.moveToFirst())
            {

                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {

                    StoreName[i] = (String) cursor.getString(0).toString();
                    cursor.moveToNext();
                }
            }
            return StoreName;
        } finally {
            cursor.close();
        }

    }

    public void updateImageRecordsSyncdforPOSMaterial(String PhotoName)
    {

        try
        {
            open();
            final ContentValues values = new ContentValues();
            values.put("Sstat", 4);

            int affected3 = db.update("tableImage", values, "imageName=?",new String[] { PhotoName });
        }
        catch (Exception ex)
        {
            // Log.e(TAG, ex.toString());
        }
        finally
        {
            close();
        }


    }



    public String fnGetXMLFile(String Sstat)
    {
        String optionList="";
        open();
        Cursor cursor = db.rawQuery("SELECT XmlFileName from tbl_XMLfiles Where Sstat='"+Sstat+"'  ", null);// Where PNodeID='"+TSIID+"'

        try
        {
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++)
                    {
                        if(optionList.equals(""))
                        {
                            optionList =(String) cursor.getString(0).toString();
                        }
                        else
                        {
                            optionList =optionList+"^"+(String) cursor.getString(0).toString();
                        }
                        cursor.moveToNext();
                    }
                }
            }
            return optionList;
        }
        finally
        {
            close();
            cursor.close();

        }
    }

    public void upDateTblXmlFile(String XmlFileName)
    {
        open();
        db.execSQL("UPDATE tbl_XMLfiles SET Sstat='4' WHERE XmlFileName='"+XmlFileName+"'");
        close();

    }

    public void deleteXmlTable(String Sstat)
    {
        open();
        db.execSQL("DELETE FROM tbl_XMLfiles WHERE Sstat ="+ Sstat);
        close();
    }

    public String[] getAllStoreIDWithStat5()
    {

        Cursor cursor =null;

        try
        {
            cursor = db.rawQuery("SELECT DISTINCT(StoreID) FROM tblStoreDetails where Sstat=5", null);
            String StoreName[] = new String[cursor.getCount()];

            if (cursor.moveToFirst())
            {
                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {
                    StoreName[i] = (String) cursor.getString(0).toString();
                    cursor.moveToNext();
                }
            }

            return StoreName;
        }
        finally
        {
            cursor.close();
        }

    }


    public void UpdateStoreFlagForSaveExit(String flag)
    {

        try
        {

            final ContentValues values = new ContentValues();
            values.put("Sstat", "3");
            int affected1 = db.update("tblStoreDetails", values, "Sstat=?",new String[] { flag });
            int affected4 = db.update("tblLatLongDetails", values, "Sstat=?",new String[] { flag });
            int affected2 = db.update("tblOutletQuestAnsMstr", values, "Sstat=?",new String[] { flag });
            int affected3 = db.update("tableImage", values, "Sstat=?",new String[] { flag });



        } catch (Exception ex)
        {

        }

    }



    public void UpdateStoreFlagForFinalSubmit(String flag)
    {

        try
        {

            final ContentValues values = new ContentValues();
            values.put("Sstat", "5");
            int affected1 = db.update("tblStoreDetails", values, "Sstat=?",new String[] { flag });
            int affected2 = db.update("tblOutletQuestAnsMstr", values, "Sstat=?",new String[] { flag });
             int affected3 = db.update("tableImage", values, "Sstat=?",new String[] { flag });
            int affected4 = db.update("tblLatLongDetails", values, "Sstat=?",new String[] { flag });
        } catch (Exception ex)
        {

        }

    }
    public void UpdateStoreFlagForALLSubmit(String flag)
    {

        try
        {

            final ContentValues values = new ContentValues();
            values.put("Sstat", "4");
            int affected1 = db.update("tblStoreDetails", values, "Sstat=?",new String[] { flag });
            int affected2 = db.update("tblOutletQuestAnsMstr", values, "Sstat=?",new String[] { flag });
            int affected3 = db.update("tblLatLongDetails", values, "Sstat=?",new String[] { flag });
           // int affected3 = db.update("tableImage", values, "Sstat=?",new String[] { flag });



        } catch (Exception ex)
        {

        }

    }

    public void UpdateStoreFlagWithOutImage(String sID, int flag2set)
    {

        try
        {

            final ContentValues values = new ContentValues();
            values.put("Sstat", flag2set);
            int affected = db.update("tblStoreDetails", values, "StoreID=?",new String[] { sID });

            int affected1 = db.update("tblOutletQuestAnsMstr", values,"OutletID=?", new String[] { sID });

            //int affected2 = db.update("tableImage", values,"StoreID=?", new String[] { sID });
            int affected3 = db.update("tblLatLongDetails", values,"StoreID=?", new String[] { sID });
            if(flag2set==3)
            {
                int affected4 = db.update("tblsameLocationForStoreRestartDone", values,"CrntStoreID=?", new String[] { sID });
            }






        }
        catch (Exception ex)
        {

        }

    }


    public void UpdateStoreFlag(String sID, int flag2set)
    {

        try
        {

            final ContentValues values = new ContentValues();
            values.put("Sstat", flag2set);
            int affected = db.update("tblStoreDetails", values, "StoreID=?",new String[] { sID });

            int affected1 = db.update("tblOutletQuestAnsMstr", values,"OutletID=?", new String[] { sID });

            int affected2 = db.update("tableImage", values,"StoreID=?", new String[] { sID });
            int affected3 = db.update("tblLatLongDetails", values,"StoreID=?", new String[] { sID });
            if(flag2set==3)
            {
                int affected4 = db.update("tblsameLocationForStoreRestartDone", values,"CrntStoreID=?", new String[] { sID });
            }






        }
        catch (Exception ex)
        {

        }

    }

    public String[] deletFromSDcCardPhotoValidationBasedSstat(String Sstat) {

        String[] imageNameToBeDeleted = null;
        open();

        Cursor cursor = db.rawQuery("SELECT  imageName from tableImage where Sstat='"+Sstat+"'", null);
        try{
            if(cursor.getCount()>0)
            {
                imageNameToBeDeleted=new String[cursor.getCount()];
                if(cursor.moveToFirst())
                {
                    for(int i=0;i<cursor.getCount();i++)
                    {
                        imageNameToBeDeleted[i]=cursor.getString(0);
                        cursor.moveToNext();
                    }
                }
            }
            else
            {
                imageNameToBeDeleted=new String[1];
                imageNameToBeDeleted[0]="No Data";
            }
        }finally
        {
            cursor.close();
            close();
        }


        return imageNameToBeDeleted;
    }









    public void UpdateStoreEndVisit(String StoreID, String strVisitEndTS) {

        final ContentValues values = new ContentValues();

        values.put("VisitEndTS", strVisitEndTS);

        int affected = db.update("tblStoreDetails", values,"StoreID=? and VisitStartTS is not null",
                new String[] { StoreID });

    }

    public void UpdateStoreStartVisit(String StoreID, String strVisitStartTS) {

        final ContentValues values = new ContentValues();
        values.put("VisitStartTS", strVisitStartTS);

        int affected = db.update("tblStoreDetails", values, "StoreID=?",new String[] { StoreID });

    }

    public void savetbl_XMLfiles(String XmlFileName,String Sstat)
    {
        open();
        ContentValues initialValues = new ContentValues();


        initialValues.put("XmlFileName", XmlFileName.trim());
        initialValues.put("Sstat", Sstat.trim());



        db.insert(TABLE_XMLFILES, null, initialValues);
        close();
    }



    public boolean isXMLfilesOrImageFileLeftToUpload(String SstatXml,int SstatImage)
    {

        open();
        boolean isDataPendingForUpload=false;
        //tbl_XMLfiles(XmlFileName text null,Sstat text null);";
        Cursor curImage=db.rawQuery("Select imageName from tableImage where Sstat="+SstatImage,null);
        Cursor curXml=db.rawQuery("Select XmlFileName from tbl_XMLfiles where Sstat='"+SstatXml+"'",null);
        try {

          // tableImage(StoreID text null,QstIdAnsCntrlTyp text null,imageName text null,imagePath text null,ImageClicktime text null,Sstat integer null);";

        if(curXml.getCount()>0 || curImage.getCount()>0)
        {
            isDataPendingForUpload=true;
        }
        } catch(Exception e)
        {
            isDataPendingForUpload=true;
        }
        finally {
            curImage.close();
            curXml.close();
            close();
            return isDataPendingForUpload;
        }



    }

   /* public int CheckIfSavedDataExist(int Sstat)
    {
        open();
        int ScodecolumnIndex = 0;

        Cursor cursor = null;

        try
        {
            cursor = db.rawQuery("SELECT ProductID,Date,EnteredValue  FROM tblDistributorSavedData where  Sstat='"+Sstat+"'", null);

            if (cursor.moveToFirst()) {

                for (int i = 0; i < cursor.getCount(); i++)
                {
                    ScodecolumnIndex = 1;
                    cursor.moveToNext();

                }
            }
            return ScodecolumnIndex;
        } finally {
            cursor.close();
            close();
        }
    }*/

    public int CheckIfSavedDataExist()  throws IOException
    {

        int chkI = 0;
        Cursor cursorE2=null;
        open();
        try
        {
            cursorE2 = db.rawQuery("SELECT Count(*) from tblStoreDetails where Sstat=1", null);
            if (cursorE2.moveToFirst())
            {

                if (cursorE2.getInt(0) > 0)
                {
                    chkI = 1;
                } else
                {
                    chkI = 0;
                }
            }

        } finally
        {
            cursorE2.close();
            close();
        }
        return chkI;
    }

    public int CheckTotalStoreCount() throws IOException
    {

        int chkI = 0;
        Cursor cursorE2=null;
        open();
        try
        {
            cursorE2 = db.rawQuery("SELECT flgStoreOrder from tblStoreDetails order by flgStoreOrder descr limit 1", null);
           if(cursorE2.getCount()>0) {
               if (cursorE2.moveToFirst()) {

                   if (cursorE2.getInt(0) > 0) {
                       chkI = cursorE2.getInt(0);
                   } else {
                       chkI = 0;
                   }
               }
           }

        } finally
        {
            if(cursorE2!=null) {
                cursorE2.close();
            }
            close();
        }
        return chkI;
    }




    public LinkedHashMap<String, String> getPDAUserPreviousQuestionAnswerMasterServer(String tempId)
    {
        open();
        LinkedHashMap<String, String> hmapPreviousVisitServerQuestionSavedAns=new LinkedHashMap<String, String>();
        try {
//tblPreAddedStoresDataDetails (StoreIDDB text null,GrpQuestID text null,QstId text null,AnsControlTypeID text null,AnsTextVal text null,flgPrvVal text null);";
            //tblPDAUserPreviousQuestionAnswerMaster(NodeID text null, NodeType text null,QstID text null,AnsControlTypeID text null,Answers text null,temID text null,flgPrvValue text null);";
            Cursor cursor=db.rawQuery("Select * from tblPreAddedStoresDataDetails where StoreIDDB='"+tempId+"'", null);

            if(cursor.getCount()>0)
            {
                if(cursor.moveToFirst())
                {
                    for(int i=0;i<cursor.getCount();i++)
                    {
                        hmapPreviousVisitServerQuestionSavedAns.put(cursor.getString(2)+"^"+cursor.getString(3)+"^"+cursor.getString(1), cursor.getString(4));
                        cursor.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {
            close();
            return hmapPreviousVisitServerQuestionSavedAns;
        }
    }


    public LinkedHashMap<String, String> fnGetQuestionIdFlgPrvValue(String TempID)
    {
        LinkedHashMap<String, String> hmapQuestionflgPrvValue=new LinkedHashMap<String, String>();
        open();
        //tblQuestionTaskIdMapd(QstId int null,TaskId int null);";
        int lastIndex=0;
        Cursor cursor;
//tblPreAddedStoresDataDetails (StoreIDDB text null,GrpQuestID text null,QstId text null,AnsControlTypeID text null,AnsTextVal text null,flgPrvVal text null);";
        cursor = db.rawQuery("SELECT QstId,AnsControlTypeID,flgPrvVal,GrpQuestID from tblPreAddedStoresDataDetails Where tblPreAddedStoresDataDetails.StoreIDDB='"+ TempID +"'", null);


        try {
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst())
                {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++) {
                        hmapQuestionflgPrvValue.put((String) cursor.getString(0).toString()+"^"+(String) cursor.getString(1).toString()+"^"+(String) cursor.getString(3).toString(), (String) cursor.getString(2).toString());

                        cursor.moveToNext();
                    }

                }
            }
            return hmapQuestionflgPrvValue;
        }
        finally
        {
            cursor.close();
            close();
        }
    }


    public void deleteImageData(String imageValidName,String storeId)
    {
        //tableImage(tempId text null,QstIdAnsCntrlTyp text null,imageName text null,imagePath text null,Sstat integer null);";
        open();
        Cursor cur=db.rawQuery("Select imageName from tableImage where StoreID='"+storeId+"' AND imageName='"+imageValidName+"'", null);
        if(cur.getCount()>0)
        {
            db.delete(TABLE_IMAGE, "StoreID=? AND imageName=?", new String[]{storeId,imageValidName});
        }
        close();
    }





    public void UpdateStoreIintblLatLongDetails(String ServerStoreID,String CreatedStoreID)
    {
        open();
        try
        {
            db.execSQL("UPDATE tblLatLongDetails SET StoreID='"+ServerStoreID+"' where StoreID='"+CreatedStoreID+"'");

        }
        catch (Exception ex)
        {
           // Log.e(TAG, ex.toString());
        }
        finally
        {
            close();
        }
    }





    public String[] fetchLatLonStoresForOnlineStoreEntry(String StoreID)
    {
        open();
        Cursor cursor = db.rawQuery("SELECT * FROM tblLatLongDetails WHERE StoreID ='"+ StoreID + "'", null);

        try {

            String CompleteResult[] = new String[cursor.getColumnCount()];
            if (cursor.getCount() > 0) {

                if (cursor.moveToFirst()) {

                    for (int i = 0; i <= (cursor.getColumnCount()-1); i++) {
                        CompleteResult[i] = (String) cursor.getString(i).toString();
                    }
                }

            }
            return CompleteResult;

        } finally {
            //cursor.close();
            close();
        }

    }




    public void Delete_tblLatLongDetails(String StoreID)
    {
        open();
        db.execSQL("DELETE FROM tblLatLongDetails WHERE StoreID ='"+ StoreID + "'");
        close();
    }

    public long  saveLatLngToTxtFile(String StoreID,String fnLati, String fnLongi,
                                     String fnAccuracy, String fnAccurateProvider,
                                      String GpsLat, String GpsLong, String GpsAccuracy,
                                     String NetwLat,String NetwLong,String NetwAccuracy, String FusedLat,
                                     String FusedLong, String FusedAccuracy,
                                     int Sstat,String flgLocNotFound,String fnAddress,String AllProvidersLocation,String GpsAddress,String NetwAddress
    ,String FusedAddress,String FusedLocationLatitudeWithFirstAttempt,String FusedLocationLongitudeWithFirstAttempt,String FusedLocationAccuracyWithFirstAttempt)
    {
        open();
        try
        {
            ContentValues initialValues = new ContentValues();

            Cursor cursor=db.rawQuery("Select StoreID from tblLatLongDetails where StoreID='"+StoreID+"'",null);

            if(cursor.getCount()>0)
            {

            }
            else
            {
                initialValues.put("StoreID", StoreID);
                initialValues.put("fnLati", fnLati);
                initialValues.put("fnLongi", fnLongi.trim());
                initialValues.put("fnAccuracy", fnAccuracy.trim());
                initialValues.put("flgLocNotFound", flgLocNotFound);
                initialValues.put("fnAccurateProvider", fnAccurateProvider.trim());
                initialValues.put("fnAddress", fnAddress);
                initialValues.put("AllProvidersLocation", AllProvidersLocation);


                initialValues.put("GpsLat", GpsLat.trim());
                initialValues.put("GpsLong", GpsLong.trim());
                initialValues.put("GpsAccuracy", GpsAccuracy.trim());
                initialValues.put("GpsAddress", GpsAddress);

                initialValues.put("NetwLat", NetwLat.trim());
                initialValues.put("NetwLong", NetwLong.trim());
                initialValues.put("NetwAccuracy", NetwAccuracy.trim());
                initialValues.put("NetwAddress", NetwAddress);

                initialValues.put("FusedLat", FusedLat.trim());
                initialValues.put("FusedLong", FusedLong.trim());
                initialValues.put("FusedAccuracy", FusedAccuracy.trim());
                initialValues.put("FusedAddress", FusedAddress);


                initialValues.put("FusedLocationLatitudeWithFirstAttempt", FusedLocationLatitudeWithFirstAttempt);
                initialValues.put("FusedLocationLongitudeWithFirstAttempt", FusedLocationLongitudeWithFirstAttempt);
                initialValues.put("FusedLocationAccuracyWithFirstAttempt", FusedLocationAccuracyWithFirstAttempt);
                initialValues.put("Sstat", Sstat);
                return db.insert(DATABASE_TABLE_Main271, null, initialValues);
            }





        }
        catch(Exception e)
        {

        }
        finally
        {
            close();
        }
        return 0;
    }


    public void insertRestartStoreInfo(String prvsStoreID,String CrntStoreID,String isSavedOrSubmittedStore,String MsgToRestartPopUpShown,String isRestartDoneByDSR,int Sstat,String ActionTime)
    {
       //tblsameLocationForStoreRestartDone(UniqueID INTEGER PRIMARY KEY AUTOINCREMENT,prvsStoreID text null,CrntStoreID text null,isSavedOrSubmittedStore text null,is MsgToRestartPopUpShown text null,isRestartDoneByDSR text null ,prvsStoreFlag text null,Sstat text null);";
            open();

        Cursor cursor=db.rawQuery("Select prvsStoreID from tblsameLocationForStoreRestartDone where prvsStoreID='"+CrntStoreID+"'",null);

            ContentValues values = new ContentValues();





        values.put("Sstat",Sstat);

        if(cursor.getCount()>0)
        {


           db.update(DATABASE_TABLE_tblSameLocationForStoreRestartDone,values,"prvsStoreID=?",new String[]{prvsStoreID});
        }
        else
        {
            fnDeleteFlgStoreUnusedPrvs("0");

            values.put("ActionTime",ActionTime);
            values.put("CrntStoreID",CrntStoreID);
            values.put("prvsStoreID",prvsStoreID);
            values.put("isMsgToRestartPopUpShown",MsgToRestartPopUpShown);
            values.put("isSavedOrSubmittedStore",isSavedOrSubmittedStore);
            values.put("isRestartDoneByDSR",isRestartDoneByDSR);
            db.insert(DATABASE_TABLE_tblSameLocationForStoreRestartDone,null,values);
        }


        close();


    }

    public void updateMsgToRestartPopUpShown(String prvsStoreId,String visitSTime)
    {
        open();
        ContentValues values=new ContentValues();
        values.put("isMsgToRestartPopUpShown","1");
        values.put("ActionTime",visitSTime);
        db.update(DATABASE_TABLE_tblSameLocationForStoreRestartDone,values,"prvsStoreID=?",new String[]{prvsStoreId});
        close();
    }
    public boolean isPrvsStoreMsgShownAndRestrtDone(String prvsStoreId)
    {
        open();
        boolean isToShowPopUpForResart=true;
        try {
            Cursor cur=db.rawQuery("Select prvsStoreID from tblsameLocationForStoreRestartDone where isMsgToRestartPopUpShown='1' AND isRestartDoneByDSR='1' AND prvsStoreID='"+prvsStoreId+"'",null);
            if(cur.getCount()>0)
            {
                isToShowPopUpForResart=false;
            }
        }
        catch(Exception e)
        {

        }
        finally {
            close();
            return isToShowPopUpForResart;
        }

    }

    public String PrvsStoreMsgShownAndRestrtDone()
    {
        open();
        String storeShowPopUpForResartAndDone="";
        try {
            Cursor cur=db.rawQuery("Select prvsStoreID from tblsameLocationForStoreRestartDone where isMsgToRestartPopUpShown='1' AND isRestartDoneByDSR='1' AND UniqueID=(SELECT MAX(UniqueID) FROM tblsameLocationForStoreRestartDone)",null);
            if(cur.getCount()>0)
            {
                if(cur.moveToFirst())
                {
                    storeShowPopUpForResartAndDone=cur.getString(0);
                }

            }
            else
            {
                cur.close();
                Cursor cursor=db.rawQuery("Select prvsStoreID from tblsameLocationForStoreRestartDone where isMsgToRestartPopUpShown='1' AND isRestartDoneByDSR='0' AND UniqueID=(SELECT MAX(UniqueID) FROM tblsameLocationForStoreRestartDone)",null);
                if(cursor.getCount()>0)
                {
                    if(cursor.moveToFirst())
                    {
                        storeShowPopUpForResartAndDone=cursor.getString(0);
                    }

                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            close();
            return storeShowPopUpForResartAndDone;
        }

    }
    public void updateisRestartDoneByDSR(String prvsStoreId)
    {
        open();
       ContentValues values=new ContentValues();
        values.put("isRestartDoneByDSR","1");
        db.update(DATABASE_TABLE_tblSameLocationForStoreRestartDone,values,"prvsStoreID=?",new String[]{prvsStoreId});
        close();
    }
    public void updateCurrentStoreId(String crntStoreID,String prvsStoreId)
    {
        open();
        ContentValues values=new ContentValues();
        values.put("CrntStoreID",crntStoreID);
        db.update(DATABASE_TABLE_tblSameLocationForStoreRestartDone,values,"prvsStoreID=?",new String[]{prvsStoreId});
        close();
    }
    public String getPreviousStoreId()
    {
        String prvsStoreId="";
        open();
        try {
            Cursor cursor=db.rawQuery("Select prvsStoreID from tblsameLocationForStoreRestartDone where UniqueID=(SELECT MAX(UniqueID) FROM tblsameLocationForStoreRestartDone)",null);
            if(cursor.getCount()>0)
            {
                if(cursor.moveToFirst())
                {
                    prvsStoreId=cursor.getString(0);
                }

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            close();
            return prvsStoreId;
        }

    }

    public String getPreviousShownPopUpStoreId()
    {
        String prvsStoreId="";
        open();
        try {
            Cursor cursor=db.rawQuery("Select prvsStoreID from tblsameLocationForStoreRestartDone where UniqueID=(SELECT MAX(UniqueID) FROM tblsameLocationForStoreRestartDone) AND isMsgToRestartPopUpShown='1'",null);
            if(cursor.getCount()>0)
            {
                if(cursor.moveToFirst())
                {
                    prvsStoreId=cursor.getString(0);
                }

            }
        }catch (Exception e)
        {

        }
        finally {
            close();
            return prvsStoreId;
        }

    }



    public void fnDeleteFlgStoreUnusedPrvs(String MsgToRestartPopUpShown) {
        try {
            db.execSQL("DELETE FROM tblsameLocationForStoreRestartDone WHERE isMsgToRestartPopUpShown='"+ MsgToRestartPopUpShown + "'");// and sectionID="+sectionID
        }
        catch (Exception e)
        {

        }


    }
   /* public void updatePrvsStoreFlag(String prvsStoreID)
    {
        ContentValues values=new ContentValues();
        values.put("prvsStoreFlag","1");
        db.update(DATABASE_TABLE_tblSameLocationForStoreRestartDone,values,"prvsStoreID=?",new String[]{prvsStoreID});
    }*/


    //account census
    public void droptblDAGetAddedOutletSummaryReport()
    {
        db.execSQL("DROP TABLE IF EXISTS tblDAGetAddedOutletSummaryReport");

    }

    public void createtblDAGetAddedOutletSummaryReport()
    {
        try
        {
            db.execSQL(DATABASE_CREATE_TABLE_tblDAGetAddedOutletSummaryReport);
        }
        catch (Exception e)
        {

        }
    }

    public LinkedHashMap<String,ArrayList<String>> fetchtblDAGetAddedOutletSummaryReport()
    {
        LinkedHashMap<String,ArrayList<String>> hmap=new LinkedHashMap<>();
        Cursor cursor=null;
        try {
            cursor = db.rawQuery("SELECT Distinct Header from tblDAGetAddedOutletSummaryReport where FlgNormalOverall='"+0+"'", null);
            if(cursor.getCount()>0) {
                if (cursor.moveToFirst()) {

                    for (int i = 0; i <= (cursor.getCount() - 1); i++) {
                        String abc = cursor.getString(0);
                        hmap.put(abc, fetchOutletSummaryByHeader(abc));
                        cursor.moveToNext();
                    }
                }
            }
        } finally {
            if(cursor !=null)
                cursor.close();
            return hmap;
        }

    }

    public String fetchtblDAGetAddedOutletOverAllData()
    {
        String hmap="NA";
        Cursor cursor=null;
        try {
            cursor = db.rawQuery("SELECT TotalStores,Validated,Pending from tblDAGetAddedOutletSummaryReport where FlgNormalOverall='"+1+"'", null);
            if(cursor.getCount()>0) {
                if (cursor.moveToFirst()) {
                    for (int i = 0; i <= (cursor.getCount() - 1); i++) {
                        hmap = cursor.getString(0)+"^"+cursor.getString(1)+"^"+cursor.getString(2);
                        cursor.moveToNext();
                    }
                }
            }
        } finally {
            if(cursor !=null)
                cursor.close();
            return hmap;
        }
    }

    //outlet summary
    public long savetblDAGetAddedOutletSummaryReport(String Header,String Child,String TotalStores,String Validated,String Pending,int FlgNormalOverall)
    {

       /*create table tblDAGetAddedOutletSummaryReport (Header text null,Child text null,TotalStores text null,
       Validated text null,Pending text null);";*/
        ContentValues initialValues = new ContentValues();

        initialValues.put("Header", Header.trim());
        initialValues.put("Child", Child.trim());
        initialValues.put("TotalStores", TotalStores.trim());
        initialValues.put("Validated", Validated.trim());
        initialValues.put("Pending", Pending.trim());
        initialValues.put("FlgNormalOverall", FlgNormalOverall);

        return db.insert(DATABASE_TABLE_tblDAGetAddedOutletSummaryReport, null, initialValues);
    }

    public ArrayList<String> fetchOutletSummaryByHeader(String Header)
    {
        ArrayList<String> arrData=new ArrayList<>();
        Cursor cursor=null;

        try {
            cursor = db.rawQuery("SELECT Child,TotalStores,Validated,Pending from tblDAGetAddedOutletSummaryReport where Header='"+Header+"'", null);
            if(cursor.getCount()>0)
            {
                if (cursor.moveToFirst()) {

                    for (int i = 0; i <= (cursor.getCount() - 1); i++)
                    {
                        arrData.add(cursor.getString(0)+"^"+cursor.getString(1)+"^"+cursor.getString(2)+"^"+cursor.getString(3));
                        cursor.moveToNext();
                    }
                }
            }
        } finally {
            if(cursor !=null)
                cursor.close();
            return arrData;
        }
    }

    public int FetchflgAppStatus()
    {
        int CatId=0;

        Cursor cursor = db.rawQuery("SELECT flgAppStatus from tblUserAuthenticationMstr", null);
        try {

            if (cursor.moveToFirst()) {

                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {

                    String abc =(String) cursor.getString(0).toString();
                    CatId= Integer.parseInt(abc);
                    cursor.moveToNext();
                }

            }
            return CatId;
        } finally {
            cursor.close();
        }

    }

    public int FetchflgValidApplication()
    {
        int CatId=0;

        Cursor cursor = db.rawQuery("SELECT flgValidApplication from tblUserAuthenticationMstr", null);
        try {

            if (cursor.moveToFirst()) {

                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {

                    String abc =(String) cursor.getString(0).toString();
                    CatId= Integer.parseInt(abc);
                    cursor.moveToNext();
                }

            }
            return CatId;
        } finally {
            cursor.close();
        }

    }

    public String  FetchDisplayMessage()
    {
        open();
        String abc="No Message";
        Cursor cursor = db.rawQuery("SELECT DisplayMessage from tblUserAuthenticationMstr", null);
        try {

            if (cursor.moveToFirst()) {

                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {

                    abc =(String) cursor.getString(0).toString();
                    cursor.moveToNext();
                }

            }
            return abc;
        } finally {
            cursor.close();
            close();
        }

    }
    public String  FetchMessageForInvalid()
    {
        open();
        String abc="No Message";
        Cursor cursor = db.rawQuery("SELECT MessageForInvalid from tblUserAuthenticationMstr", null);
        try {

            if (cursor.moveToFirst()) {

                for (int i = 0; i <= (cursor.getCount() - 1); i++)
                {

                    abc =(String) cursor.getString(0).toString();
                    cursor.moveToNext();
                }

            }
            return abc;
        } finally {
            cursor.close();
            close();
        }

    }

    public void deletetblStateCityMaster()
    {
        open();
        db.execSQL("DELETE FROM tblStateCityMaster");
        close();
    }

    public void fnsavetblStateCityMaster(String StateID, String State, String CityID, String City,int cityDefault)
    {

        ContentValues values=new ContentValues();
        values.put("StateID", Integer.parseInt(StateID));
        values.put("State", State);
        values.put("CityID", Integer.parseInt(CityID));
        values.put("City", City);
        values.put("CityDefault", cityDefault);

        db.insert(TABLE_tblStateCityMaster , null, values);

    }

    public LinkedHashMap<String, String> fngetDistinctState()
    {

        open();
        Cursor cur=null;
        LinkedHashMap<String, String> hmapDistinctStates=new LinkedHashMap<String, String>();
        try {
            cur=db.rawQuery("Select Distinct StateID,State from tblStateCityMaster", null);
            if(cur.getCount()>0)
            {
                if(cur.moveToFirst())
                {
                    for(int i=0;i<cur.getCount();i++)
                    {
                        hmapDistinctStates.put(cur.getString(1), cur.getString(0));
                        cur.moveToNext();
                    }
                }

            }

        } catch (SQLiteException e) {
            // TODO: handle exception
        }
        finally
        {
            if(cur!=null)
            {
                cur.close();
            }
            close();
            return hmapDistinctStates;
        }
    }



    public LinkedHashMap<String,String> getCityAgainstState()
    {
        open();
        LinkedHashMap<String,String> hmapCityAgainstState=new LinkedHashMap<String,String>();
        Cursor cur=null;
        try {
            cur=db.rawQuery("Select City,State from tblStateCityMaster",null);
            if(cur.getCount()>0)
            {
                if(cur.moveToFirst())
                {
                    for(int i=0;i<cur.getCount();i++)
                    {
                        hmapCityAgainstState.put(cur.getString(0),cur.getString(1));
                        cur.moveToNext();
                    }
                }
            }
        }catch(SQLiteException exception)
        {

        }
        finally
        {
            if(cur!=null)
            {
                cur.close();
            }
            close();
            return hmapCityAgainstState;

        }
    }
    public LinkedHashMap<String, String> fngetCityList()
    {

        open();
        Cursor cur=null;
        LinkedHashMap<String, String> hmapCityList=new LinkedHashMap<String, String>();
        try {
            cur=db.rawQuery("Select CityID,City from tblStateCityMaster", null);
            if(cur.getCount()>0)
            {
                if(cur.moveToFirst())
                {
                    for(int i=0;i<cur.getCount();i++)
                    {
                        hmapCityList.put(cur.getString(1).trim(), cur.getString(0));
                        cur.moveToNext();
                    }
                }

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        finally
        {
            if(cur!=null)
            {
                cur.close();
            }
            close();
            return hmapCityList;
        }
    }

    public String getDefaultCity()
    {
        open();
        String defaultCity="";
        Cursor cur=null;
        try {
            cur=db.rawQuery("Select City from tblStateCityMaster where CityDefault=1",null);
            if(cur.getCount()>0)
            {
                if(cur.moveToFirst())
                {
                    defaultCity=cur.getString(0);
                }
            }
        }catch(SQLiteException exception)
        {

        }
        finally
        {
            if(cur!=null)
            {
                cur.close();
            }
            close();
            return defaultCity;
        }
    }
    public void updateAllDefaultCity(String cityId)
    {
        open();
        try {


            db.execSQL("UPDATE tblStateCityMaster SET CityDefault=0" );
            ContentValues values=new ContentValues();
            values.put("CityDefault",1);
            db.update(TABLE_tblStateCityMaster,values,"CityID=?",new String[]{cityId});
        }catch(SQLiteException exception)
        {

        }finally
        {
            close();
        }

    }

}
