package khudrosoft.com.renataapplication.API;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DvManager extends SQLiteOpenHelper {

    private static final String db_name="InfoDb.db";
    private static final String col_one="Id";
    private static final String col_two="Name";
    private static final String col_three="Phoneno";
    private static final String col_four="District";
    private static final String col_five="Thana";
    private static final String col_six="Gift";
    private static final String table_name="information";
    private static final String table_nameone="phone";
    private static final String table_query="SELECT * FROM "+table_name;

    public DvManager(@Nullable Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

String query ="create table "+table_name+"("+col_one+" integer primary key autoincrement,"+col_two+" text,"+col_three+" text,"+col_four+" text,"+col_five+" text,"+col_six+" text)";
sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+table_name);
        onCreate(sqLiteDatabase);
    }


    public  String addRecord(String name,String phoneno,String district,String thana,String gift){

        SQLiteDatabase db = this.getWritableDatabase();
//        String query ="create table "+table_name+"("+col_one+" integer primary key autoincrement,"+col_two+" text,"+col_three+" text,"+col_four+" text,"+col_five+" text,"+col_six+" text)";
//        db.execSQL(query);
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_two,name);
        contentValues.put(col_three,phoneno);
        contentValues.put(col_four,district);
        contentValues.put(col_five,thana);
        contentValues.put(col_six,gift);

  long res = db.insert(table_name,null,contentValues);

    if(res == -1){
        return  "failed";
    }else {
        return "Successfully Inserted";
    }
    }


    public  String addPhone(String name,String phoneno){

        SQLiteDatabase db = this.getWritableDatabase();
//        String query ="create table "+table_nameone+"("+col_one+" integer primary key autoincrement,"+col_two+" text,"+col_three+" text)";
//       db.execSQL(query);
       ContentValues contentValues = new ContentValues();
        contentValues.put(col_two,name);
        contentValues.put(col_three,phoneno);


        long res = db.insert(table_nameone,null,contentValues);

        if(res == -1){
            return  "failed";
        }else {
            return "Successfully Inserted";
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(table_query,null);

        return cursor;
    }

    public Cursor getAllDataPhone(String num){

        String Query = "SELECT * FROM "+table_nameone+" WHERE "+col_three+"="+num;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(Query,null);

        return cursor;
    }
}
