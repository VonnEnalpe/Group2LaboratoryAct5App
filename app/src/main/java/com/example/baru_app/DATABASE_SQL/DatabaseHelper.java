package com.example.baru_app.DATABASE_SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.baru_app.DATABASE_SQL.BarangayUserModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String ID = "ID";
    public static final String TABLE_NAME = "USERS";
    public static final String USER_FIRBASE_ID = "USER_ID";
    public static final String BARANGAY = "BARANGAY";
    public DatabaseHelper(Context context) {
        super(context, "TABLE_NAME", null, 1);

    }
    //    DatabaseHelper (Conte)
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_FIRBASE_ID + " TEXT, " + BARANGAY + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addOneUser(BarangayUserModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_FIRBASE_ID,model.getUser_id());
        cv.put(BARANGAY,model.getBarangay());


        long insert = db.insert(TABLE_NAME, null, cv);
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }

    }
    public String getBarangayCurrentUser(String userId){
        String resultBarangay = null;
//        boolean checker = true;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()) {
            while (true){
                if(cursor.getString(1).equals(userId)){
                    resultBarangay= cursor.getString(2);
                    break;
                }
            }
        }
        String returnString = resultBarangay;
        return returnString;
    }
//    public boolean update(String id,String barangay){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(RATE_NAME,name);
//        cv.put(DATE,date);
//        cv.put(DEPOSIT,tran1);
//        cv.put(WITHDRAWAL,tran2);
//        cv.put(BILLS,tran3);
//        cv.put(ACC_OPENING,tran4);
//        cv.put(INVESTMENT,tran5);
//        cv.put(INQUIRY,tran6);
//        cv.put(OTHERS,tran7);
//        long insert = db.update(TABLE_NAME, cv,"ID = ?", new String[] { id });
//            return true;
//    }
//    public String lastSelected(Integer rating){
//        StringBuilder sb = new StringBuilder();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String queryString = "SELECT  *  FROM " + TABLE_NAME + " ORDER BY " + ID + " DESC LIMIT 1";
//        Cursor cursor = db.rawQuery(queryString,null);
//        if(cursor.moveToFirst()) {
//            do {
//                sb.append(cursor.getString(1) + "`" + cursor.getString(2) + "`" + cursor.getString(3) + "`" + cursor.getString(4) +
//                        "`" + cursor.getString(5) + "`" + cursor.getString(6) + "`" + cursor.getString(7) + "`" + cursor.getString(8) + "`" + cursor.getString(9));
//            } while (cursor.getPosition() == rating);
//        }
//        String returnString = sb.toString();
//        cursor.close();
//        db.close();
//        return returnString;
//    }

//    public boolean updateSelected(){
//
//
//    }
//    public String showSelected(Integer rating){
//        StringBuilder sb = new StringBuilder();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String queryString = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + rating;
//        Cursor cursor = db.rawQuery(queryString,null);
//        if(cursor.moveToFirst()) {
//            do {
//                sb.append(cursor.getString(1) + "`" + cursor.getString(2) + "`" + cursor.getString(3) + "`" + cursor.getString(4) +
//                        "`" + cursor.getString(5) + "`" + cursor.getString(6) + "`" + cursor.getString(7) + "`" + cursor.getString(8) + "`" + cursor.getString(9));
//            } while (cursor.getPosition() == rating);
//        }
//        String returnString = sb.toString();
//        return returnString;
//    }

//
//    public Integer lastPos() {
//        Integer rateID;
//        SQLiteDatabase db = this.getReadableDatabase();
//        String queryString = "SELECT  *  FROM " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(queryString, null);
//        do {
//            rateID = cursor.getPosition();
//        } while (cursor.moveToNext());
//        return rateID;
//    }
//
//    public Integer lastPos() {
//        Integer rateID;
//        SQLiteDatabase db = this.getReadableDatabase();
//        String queryString = "SELECT  *  FROM " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(queryString, null);
//        do {
//            rateID = cursor.getPosition();
//        } while (cursor.moveToNext());
//        return rateID;
//    }
//    public boolean  deleteSelected(BarangayUserModel model) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String queryString = "DELETE  FROM " + TABLE_NAME + " WHERE " + ID + " = " + model.getId();
//       Cursor cursor =  db.rawQuery(queryString,null);
//       if(cursor.moveToFirst()){
//           return true;
//       }else{
//           return  false;
//       }
//
//    }
//    public Integer clickPosition(BarangayUserModel model) {
//        Integer getId = 0;
//        SQLiteDatabase db = this.getReadableDatabase();
//        String queryString = "SELECT " + ID + " FROM " + TABLE_NAME + " WHERE " + ID + " = " + model.getId();
//        Cursor cursor =  db.rawQuery(queryString,null);
//        if(cursor.moveToFirst()){
//            do{
//                getId = cursor.getInt(0);
//            }while(cursor.moveToNext());
//
//        }else {
//            ///
//        }
//        cursor.close();
//        db.close();
//        return getId;
//    }
//    }
//    public boolean showSelected_View(RatingModel model){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String queryString = "SELECT  *  FROM " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(queryString,null);
//        do{
//            rateID = cursor.getPosition();
//        }while(cursor.moveToNext());
//        return  rateID;
//    }
//    public List<BarangayUserModel> getListRating(){
//        List<BarangayUserModel> returnList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String queryString = "SELECT * FROM " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(queryString,null);
//        if(cursor.moveToFirst()){
//            do{
//                int rateID = cursor.getInt(0);
//                String rateName = cursor.getString(1);
//                String rateDate = cursor.getString(2);
//                String deposit = cursor.getString(3);
//                String withdraw = cursor.getString(4);
//                String bill = cursor.getString(5);
//                String acc_open = cursor.getString(6);
//                String invest = cursor.getString(7);
//                String inquiry = cursor.getString(8);
//                String others = cursor.getString(9);
//                BarangayUserModel rates = new BarangayUserModel(rateID,rateName,rateDate,deposit,withdraw,bill,acc_open,invest,inquiry,others);
//                returnList.add(rates);
//            }while(cursor.moveToNext());
//
//        }else {
//            ///
//        }
//        return returnList;
//    }




//    public ArrayList<BarangayUserModel> getRating(){
//        ArrayList<BarangayUserModel> returnList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String queryString = "SELECT * FROM " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(queryString,null);
//
//        if(cursor.moveToFirst()){
//            do{
//                int rateID = cursor.getInt(0);
//                String rateName = cursor.getString(1);
//                String rateDate = cursor.getString(2);
//                String deposit = cursor.getString(3);
//                String withdraw = cursor.getString(4);
//                String bill = cursor.getString(5);
//                String acc_open = cursor.getString(6);
//                String invest = cursor.getString(7);
//                String inquiry = cursor.getString(8);
//                String others = cursor.getString(9);
//                BarangayUserModel rates = new BarangayUserModel(rateID,rateName,rateDate,deposit,withdraw,bill,acc_open,invest,inquiry,others);
//                returnList.add(rates);
//            }while(cursor.moveToNext());
//
//        }else {
//            ///
//        }
//        cursor.close();
//        db.close();
//        return returnList;
//    }
}
