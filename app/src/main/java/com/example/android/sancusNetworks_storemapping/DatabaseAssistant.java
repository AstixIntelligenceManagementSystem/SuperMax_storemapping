package com.example.android.sancusNetworks_storemapping;

/**
 * Created by sunil.kumar on 7/6/2017.
 */



        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.nio.ByteBuffer;
        import java.nio.channels.FileChannel;


        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import android.os.Environment;

        import android.util.Log;

public class DatabaseAssistant
{

    private static final String DATASUBDIRECTORY = CommonInfo.RSPLOrderXMLFolder;
    public static final String LOG_TAG = "NMPsync";
    private DatabaseHelper2 DBHelper2;
    private SQLiteDatabase db; // removed final
    private XmlBuilder xmlBuilder;
    static String DATABASE_NAME_2SYNC = CommonInfo.DATABASE_NAME;
    static  int DATABASE_VERSION = CommonInfo.DATABASE_VERSIONID;
    private final Context context;


    public DatabaseAssistant(Context ctx)
    {
        this.context = ctx;
        DBHelper2 = new DatabaseHelper2(context);
    }

    private static class DatabaseHelper2 extends SQLiteOpenHelper
    {
        DatabaseHelper2(Context context)
        {
            super(context, DATABASE_NAME_2SYNC, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    // ---opens the database---
    public DatabaseAssistant open() throws SQLException
    {
        db = DBHelper2.getWritableDatabase();
        return this;
    }

    // ---closes the database---
    public void close()
    {
        DBHelper2.close();
    }

    public void export(final String dbName, final String exportFileNamePrefix)
            throws IOException
    {

        xmlBuilder = new XmlBuilder();
        xmlBuilder.start(dbName);

        if (1==1)
        {

            exportTable("tblStoreDetails");
            exportTable("tblOutletQuestAnsMstr");
            exportTable("tableImage");
            exportTable("tblLatLongDetails");
            exportTable("tblsameLocationForStoreRestartDone");
           // exporttblLatLongDetailsTable("tblLatLongDetails");


        }
        String xmlString = xmlBuilder.end();
        writeToFile(xmlString, exportFileNamePrefix + ".xml");
        Log.i(LOG_TAG, "exporting database complete");
    }

    private void exportTableNotification(final String tableName) throws IOException
    {
        xmlBuilder.openTable(tableName);
        String sql = "select * from " + tableName + " where Sstat = 3";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }


    private void exportTableForRouteType(final String tableName,String ActiveRouteID) throws IOException {
        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        String sql = "select ID,RouteType from " + tableName + " WHERE ID='"+ ActiveRouteID +"' Limit 1";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }


    private void exportTableStoreMaterialDetail(final String tableName) throws IOException {
        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        String sql = "select * from " + tableName + " where Sstat = 3 and  (ExistStock<>0 OR ReturntoDistributor<>0 OR FreshOrder<>0 OR DiscardDamage<>0)";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }

    private void exportTablStoreProductPhotoDetail(final String tableName) throws IOException {
        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        String sql = "select StoreID,ProductID,ClickedDateTime,PhotoName,PhotoValidation,PDAPhotoPath,OrderIDPDA from " + tableName + " where Sstat = 3";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }


    private void exportTableStoreMain(final String tableName) throws IOException {
        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        String sql = "select * from " + tableName + " where Sstat = 3";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }

    private void exportTableTemp(final String tableName) throws IOException {
        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        String sql = "select * from " + tableName + " where Sstat = 3";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }


	/*private static final String DATABASE_CREATE_TABLE_32 = "create table tblInvoice (IMEIno text not null, " +
			"StoreID text not null, InvoiceDate string not null, " +
			"TotalBeforeTaxDis real not null, TaxAmt real not null, " +
			"TotalDis real not null, InvoiceVal real not null, FreeTotal integer not null," +
			" Sstat integer not null, InvAfterDis real not null, AddDis real not null, AmtPrevDue real null, " +
			"AmtColl real null, AmtOut real null, NoCoupon int null, TotalCoupunAmount real null,RouteID int null);";*/

    private void exportTabletblInvoice(final String tableName) throws IOException {
        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        String sql = "select StoreID,InvoiceDate,TotalBeforeTaxDis,TaxAmt,TotalDis,InvoiceVal,FreeTotal,InvAfterDis,AddDis,AmtPrevDue,AmtColl,AmtOut,NoCoupon,TotalCoupunAmount,OrderIDPDA from " + tableName + " where Sstat = 3";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }



    private void exportTabletblTransac(final String tableName) throws IOException {
        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        String sql = "select IMEIno,RouteID,StoreID,CatID,ProdID,TransDate,Stock,OrderQty,OrderVal,FreeQty,DisVal,SampleQuantity,ProductShortName,ProductPrice,TaxRate,TaxValue,OrderIDPDA,flgIsQuoteRateApplied from " + tableName + " where Sstat = 3 and  (OrderQty<>0 OR SampleQuantity<>0 OR Stock<>0 OR FreeQty<>0)";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }


    private void exportTableDayStartEndDetails(final String tableName) throws IOException {
        xmlBuilder.openTable(tableName);
        String sql = "select * from " + tableName + " where (DayEndFlag=1 OR ChangeRouteFlg=1)";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }
    private void exportSalesQuotePersonMeetTable(final String tableName) throws IOException {
        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        String sql = "select SalesQuoteId,SalesQuoteCode,SalesQuotePrcsId,SalesQuotePrcs,StoreName,Remarks,StoreId,SalesQuoteValidFrom,SalesQuoteValidTo,SalesQuoteDate,SalesQuoteType,ContactPerson,ContactPersonEmail,ContactPersonPhone,PymtStageId,Sstat,ManufacturerID,ManufacturerName from " + tableName + " where Sstat = 3";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }
    private void exportTable(final String tableName) throws IOException {

        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        String sql = "select * from " + tableName + " where Sstat=3";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }

    private void exporttblLatLongDetailsTable(final String tableName) throws IOException {

        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        String sql = "select StoreID,fnLati,fnLongi,fnAccuracy,fnAccurateProvider,GpsLat,GpsLong,GpsAccuracy,NetwLat,NetwLong,NetwAccuracy,FusedLat,FusedLong,FusedAccuracy,Sstat from " + tableName + " where Sstat = 3";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }
    private void exportTableStoreReturnDetail(final String tableName) throws IOException {
        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        String sql = "select * from " + tableName + " where Sstat = 3";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }
    private void exportTableStoreProductAppliedSchemesBenifitsRecords(final String tableName) throws IOException {
        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        String sql = "select * from " + tableName + " where Sstat = 3 and WhatFinallyApplied=1";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }



    private void exportTableStoreList(final String tableName) throws IOException {
        xmlBuilder.openTable(tableName);
         String sql = "select StoreID, StoreLatitude, StoreLongitude,ForDate, ActualLatitude, ActualLongitude, VisitStartTS, VisitEndTS, ISNewStore, LocProvider, Accuracy, BateryLeftStatus, StoreClose, StoreNextDay,flgFromWhereSubmitStatus,StoreAddress,flgSubmitFromQuotation,flgGSTCapture,flgGSTCompliance,GSTNumber,StoreRouteID,RouteNodeType from " + tableName + " where Sstat = 3";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }

    private void exportTableProductMappedWithSchemeSlabApplied(final String tableName) throws IOException {
        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        // text null,  text null,
        //StoreID text not null, StoreType string not null, StoreName string not null, StoreLatitude real not null, StoreLongitude real not null, LastVisitDate string not null, LastTransactionDate string not null, Sstat integer not null, ForDate string not null, ActualLatitude real null, ActualLongitude real null, VisitStartTS text null, VisitEndTS text null);";
        String sql = "select * from " + tableName + " where Sstat= 3";		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }

    private void exportTableNewStoreListEntries(final String tableName) throws IOException {
        xmlBuilder.openTable(tableName);
        //String sql = "select * from " + tableName;
        //StoreID text not null, StoreType string not null, StoreName string not null, StoreLatitude real not null, StoreLongitude real not null, LastVisitDate string not null, LastTransactionDate string not null, Sstat integer not null, ForDate string not null, ActualLatitude real null, ActualLongitude real null, VisitStartTS text null, VisitEndTS text null);";
        String sql = "select * from " + tableName + " where Sstat = 3"; ;		// chk for flag - DB adapter
        Cursor c = db.rawQuery(sql, new String[0]);
        if (c.moveToFirst()) {
            int cols = c.getColumnCount();
            do {
                xmlBuilder.openRow();
                for (int i = 0; i < cols; i++) {
                    xmlBuilder.addColumn(c.getColumnName(i), c.getString(i));
                }
                xmlBuilder.closeRow();
            } while (c.moveToNext());
        }
        c.close();
        xmlBuilder.closeTable();
    }

    private void writeToFile(final String xmlString, final String exportFileName)
            throws IOException {
        File dir = new File(Environment.getExternalStorageDirectory(),
                DatabaseAssistant.DATASUBDIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, exportFileName);
        file.createNewFile();

        ByteBuffer buff = ByteBuffer.wrap(xmlString.getBytes());
        FileChannel channel = new FileOutputStream(file).getChannel();
        try {
            channel.write(buff);
        } finally {
            if (channel != null) {
                channel.close();
            }
        }
    }

    /**
     * XmlBuilder is used to write XML tags (open and close, and a few
     * attributes) to a StringBuilder. Here we have nothing to do with IO or
     * SQL, just a StringBuilder.
     *
     *
     *
     */
    static class XmlBuilder {
        private static final String OPEN_XML_STANZA = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        private static final String CLOSE_WITH_TICK = "'>";
        private static final String DB_OPEN = "<database name='";
        private static final String DB_CLOSE = "</database>";
        private static final String TABLE_OPEN = "<table name='";
        private static final String TABLE_CLOSE = "</table>";
        private static final String ROW_OPEN = "<row>";
        private static final String ROW_CLOSE = "</row>";
        private static final String COL_OPEN = "<col name='";
        private static final String COL_CLOSE = "</col>";

        private final StringBuilder sb;

        public XmlBuilder() throws IOException {
            sb = new StringBuilder();
        }

        void start(final String dbName) {
            //sb.append(XmlBuilder.OPEN_XML_STANZA);
            sb.append(XmlBuilder.DB_OPEN + dbName + XmlBuilder.CLOSE_WITH_TICK);
        }

        String end() throws IOException {
            sb.append(XmlBuilder.DB_CLOSE);
            return sb.toString();
        }

        void openTable(final String tableName) {
            sb.append(XmlBuilder.TABLE_OPEN + tableName
                    + XmlBuilder.CLOSE_WITH_TICK);
        }

        void closeTable() {
            sb.append(XmlBuilder.TABLE_CLOSE);
        }

        void openRow() {
            sb.append(XmlBuilder.ROW_OPEN);
        }

        void closeRow() {
            sb.append(XmlBuilder.ROW_CLOSE);
        }

        void addColumn(final String name, final String val) throws IOException {
            sb.append(XmlBuilder.COL_OPEN + name + XmlBuilder.CLOSE_WITH_TICK
                    + val + XmlBuilder.COL_CLOSE);
        }
    }

	/*
	 * public String filNameFullPath; public long filNameTS; public String
	 * EXPORT_FILE_NAME; //private static final String EXPORT_FILE_NAME =
	 * "/sdcard/datanaexport.xml";
	 *
	 * private Context _ctx; private SQLiteDatabase _db; private Exporter
	 * _exporter;
	 *
	 * public String newfilename(){ ////System.out.println("inside newfilename()");
	 *
	 * filNameTS = System.currentTimeMillis(); filNameFullPath =
	 * Environment.getExternalStorageDirectory().getPath(); EXPORT_FILE_NAME =
	 * filNameFullPath + "/" + filNameTS +".xml";
	 * ////System.out.println("new file name: " + EXPORT_FILE_NAME); return
	 * EXPORT_FILE_NAME; }
	 *
	 * public DatabaseAssistant( Context ctx, SQLiteDatabase db ) { _ctx = ctx;
	 * _db = db;
	 *
	 * newfilename(); try {
	 * ////System.out.println("inside try databaseAssitant() -- file name: " +
	 * EXPORT_FILE_NAME); // create a file on the sdcard to export the //
	 * database contents to File myFile = new File( EXPORT_FILE_NAME );
	 * myFile.createNewFile();
	 *
	 * FileOutputStream fOut = new FileOutputStream(myFile);
	 * BufferedOutputStream bos = new BufferedOutputStream( fOut );
	 *
	 * _exporter = new Exporter( bos ); } catch (FileNotFoundException e) {
	 * e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } }
	 *
	 * public void exportData( ) { log( "Exporting Data" );
	 *
	 * try { ////System.out.println("_exporter: " + _exporter);
	 * ////System.out.println("_db: " + _db);
	 * ////System.out.println("inside try exportData()"); _exporter.startDbExport(
	 * _db.getPath() );
	 *
	 * // get the tables out of the given sqlite database String sql =
	 * "SELECT * FROM sqlite_master";
	 *
	 * Cursor cur = _db.rawQuery( sql, new String[0] ); Log.d("db",
	 * "show tables, cur size " + cur.getCount() ); cur.moveToFirst();
	 *
	 * String tableName; while ( cur.getPosition() < cur.getCount() ) {
	 * tableName = cur.getString( cur.getColumnIndex( "name" ) ); log(
	 * "table name " + tableName );
	 *
	 * // don't process these two tables since they are used // for metadata if
	 * ( ! tableName.equals( "android_metadata" ) && ! tableName.equals(
	 * "sqlite_sequence" ) ) { exportTable( tableName ); }
	 *
	 * cur.moveToNext(); } _exporter.endDbExport(); _exporter.close(); } catch
	 * (IOException e) { e.printStackTrace(); } }
	 *
	 * private void exportTable( String tableName ) throws IOException {
	 * _exporter.startTable(tableName);
	 *
	 * // get everything from the table String sql = "select * from " +
	 * tableName; Cursor cur = _db.rawQuery( sql, new String[0] ); int numcols =
	 * cur.getColumnCount();
	 *
	 * log( "Start exporting table " + tableName );
	 *
	 * // // logging // for( int idx = 0; idx < numcols; idx++ ) // { // log(
	 * "column " + cur.getColumnName(idx) ); // }
	 *
	 * cur.moveToFirst();
	 *
	 * // move through the table, creating rows // and adding each column with
	 * name and value // to the row while( cur.getPosition() < cur.getCount() )
	 * { _exporter.startRow(); String name; String val; for( int idx = 0; idx <
	 * numcols; idx++ ) { name = cur.getColumnName(idx); val = cur.getString(
	 * idx ); log( "col '" + name + "' -- val '" + val + "'" );
	 *
	 * _exporter.addColumn( name, val ); }
	 *
	 * _exporter.endRow(); cur.moveToNext(); }
	 *
	 * cur.close();
	 *
	 * _exporter.endTable(); }
	 *
	 * private void log( String msg ) { Log.d( "DatabaseAssistant", msg ); }
	 *
	 * class Exporter { private static final String CLOSING_WITH_TICK = "'>";
	 * private static final String START_DB = "<export-database name='"; private
	 * static final String END_DB = "</export-database>"; private static final
	 * String START_TABLE = "<table name='"; private static final String
	 * END_TABLE = "</table>"; private static final String START_ROW = "<row>";
	 * private static final String END_ROW = "</row>"; private static final
	 * String START_COL = "<col name='"; private static final String END_COL =
	 * "</col>";
	 *
	 * private BufferedOutputStream _bos;
	 *
	 * public Exporter() throws FileNotFoundException { this( new
	 * BufferedOutputStream( _ctx.openFileOutput( EXPORT_FILE_NAME,
	 * Context.MODE_WORLD_READABLE ) ) ); }
	 *
	 * public Exporter( BufferedOutputStream bos ) { _bos = bos; }
	 *
	 * public void close() throws IOException { if ( _bos != null ) {
	 * _bos.close(); } }
	 *
	 * public void startDbExport( String dbName ) throws IOException { String
	 * stg = START_DB + dbName + CLOSING_WITH_TICK; _bos.write( stg.getBytes()
	 * ); }
	 *
	 * public void endDbExport() throws IOException { _bos.write(
	 * END_DB.getBytes() ); }
	 *
	 * public void startTable( String tableName ) throws IOException { String
	 * stg = START_TABLE + tableName + CLOSING_WITH_TICK; _bos.write(
	 * stg.getBytes() ); }
	 *
	 * public void endTable() throws IOException { _bos.write(
	 * END_TABLE.getBytes() ); }
	 *
	 * public void startRow() throws IOException { _bos.write(
	 * START_ROW.getBytes() ); }
	 *
	 * public void endRow() throws IOException { _bos.write( END_ROW.getBytes()
	 * ); }
	 *
	 * public void addColumn( String name, String val ) throws IOException {
	 * String stg = START_COL + name + CLOSING_WITH_TICK + val + END_COL;
	 * _bos.write( stg.getBytes() ); } }
	 *
	 * class Importer {
	 *
	 * }
	 */

}
