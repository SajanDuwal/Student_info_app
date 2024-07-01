package com.sajiman.jasonapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sajiman.jasonapp.Dto.StudentDto;
import com.sajiman.jasonapp.Utils.AppLogUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private DbOpenHelper dbOpenHelper;
    private SQLiteDatabase database;
    private String[] studentID = new String[]{
            DbOpenHelper.COLUMN_STUDENT_ID
    };
    private String[] allColumns = new String[]{
            DbOpenHelper.COLUMN_ID,
            DbOpenHelper.COLUMN_STUDENT_ID,
            DbOpenHelper.COLUMN_NAME,
            DbOpenHelper.COLUMN_IMAGE_PATH,
            DbOpenHelper.COLUMN_ORGANIZATION,
            DbOpenHelper.COLUMN_FACULTY,
            DbOpenHelper.COLUMN_ROLL
    };


    public StudentDao(Context context) {
        dbOpenHelper = new DbOpenHelper(context);
    }

    public long insert(StudentDto studentDto) {
        database = dbOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbOpenHelper.COLUMN_STUDENT_ID, studentDto.getStudentId());
        values.put(DbOpenHelper.COLUMN_IMAGE_PATH, studentDto.getImagePath());
        values.put(DbOpenHelper.COLUMN_NAME, studentDto.getName());
        values.put(DbOpenHelper.COLUMN_ORGANIZATION, studentDto.getOrganization());
        values.put(DbOpenHelper.COLUMN_FACULTY, studentDto.getFaculty());
        values.put(DbOpenHelper.COLUMN_ROLL, studentDto.getRoll());

        long insertTest;
        try {
            insertTest = database.insert(DbOpenHelper.TABLE_NAME, null, values);
        } finally {
            database.close();
            dbOpenHelper.close();
        }
        return insertTest;
    }

    public boolean uniqueIdCheck(StudentDto studentDto) {
        database = dbOpenHelper.getReadableDatabase();

        Cursor cursor = database.query(DbOpenHelper.TABLE_NAME, studentID, DbOpenHelper.COLUMN_STUDENT_ID + "=?", new String[]{studentDto.getStudentId()}, null, null, null);
        boolean isUniqueId = true;
        if (cursor != null) {
            isUniqueId = cursor.getCount() <= 0;
        }
        cursor.close();
        database.close();
        dbOpenHelper.close();
        return isUniqueId;
    }

    public List<StudentDto> getFacultyStudents(String faculty) {
        database = dbOpenHelper.getReadableDatabase();
        List<StudentDto> studentDtoList = new ArrayList<>();
        Cursor cursor = database.query(DbOpenHelper.TABLE_NAME, allColumns, DbOpenHelper.COLUMN_FACULTY + "=?",
                new String[]{faculty}, null, null, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_ID));
                    String studentID = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_STUDENT_ID));
                    String imagePath = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_IMAGE_PATH));
                    String name = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_NAME));
                    String organization = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_ORGANIZATION));
                    int roll = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_ROLL));

                    StudentDto studentDto = new StudentDto(id, studentID, imagePath, name, organization, faculty, roll);
                    studentDtoList.add(studentDto);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        database.close();
        dbOpenHelper.close();
        return studentDtoList;
    }

    public List<StudentDto> getAllStudent() {
        database = dbOpenHelper.getReadableDatabase();
        List<StudentDto> allStudentList = new ArrayList<>();

        Cursor cursor = database.query(DbOpenHelper.TABLE_NAME, allColumns, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_ID));
                    String studentID = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_STUDENT_ID));
                    String imagePath = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_IMAGE_PATH));
                    String name = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_NAME));
                    String faculty = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_FACULTY));
                    String organization = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_ORGANIZATION));
                    int roll = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_ROLL));

                    StudentDto studentDto = new StudentDto(id, studentID, imagePath, name, organization, faculty, roll);
                    allStudentList.add(studentDto);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        database.close();
        dbOpenHelper.close();
        return allStudentList;
    }

    public List<StudentDto> getStudentByName(String name) {
        database = dbOpenHelper.getReadableDatabase();
        List<StudentDto> studentDtoList = new ArrayList<>();
        Cursor cursor = database.query(DbOpenHelper.TABLE_NAME, allColumns, DbOpenHelper.COLUMN_NAME + " LIKE ?",
                new String[]{name+"%"}, null, null, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_ID));
                    String studentID = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_STUDENT_ID));
                    String imagePath = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_IMAGE_PATH));
                    String faculty = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_FACULTY));
                    String nameFromDb = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_NAME));
                    String organization = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_ORGANIZATION));
                    int roll = cursor.getInt(cursor.getColumnIndex(DbOpenHelper.COLUMN_ROLL));

                    StudentDto studentDto = new StudentDto(id, studentID, imagePath, nameFromDb, organization, faculty, roll);
                    studentDtoList.add(studentDto);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        database.close();
        dbOpenHelper.close();
        return studentDtoList;
    }

    public int delete(StudentDto studentDto2) {
        database = dbOpenHelper.getWritableDatabase();

        int affectedRow;
        try {
            affectedRow = database.delete(DbOpenHelper.TABLE_NAME, DbOpenHelper.COLUMN_STUDENT_ID + "=?", new String[]{studentDto2.getStudentId()});
        } finally {
            database.close();
            dbOpenHelper.close();
        }
        return affectedRow;
    }

    public List<StudentDto> getAllStudentId() {
        database = dbOpenHelper.getReadableDatabase();

        List<StudentDto> studentIdList = new ArrayList<>();

        Cursor cursor = database.query(DbOpenHelper.TABLE_NAME, studentID, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String studentID = cursor.getString(cursor.getColumnIndex(DbOpenHelper.COLUMN_STUDENT_ID));
                    StudentDto studentDto = new StudentDto();
                    studentDto.setStudentId(studentID);
                    studentIdList.add(studentDto);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        database.close();
        dbOpenHelper.close();
        return studentIdList;
    }

    public int update(StudentDto studentDto) {
        int affectedRow = 0;
        try {
            database = dbOpenHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DbOpenHelper.COLUMN_IMAGE_PATH, studentDto.getImagePath());
            values.put(DbOpenHelper.COLUMN_STUDENT_ID, studentDto.getStudentId());
            values.put(DbOpenHelper.COLUMN_NAME, studentDto.getName());
            values.put(DbOpenHelper.COLUMN_ORGANIZATION, studentDto.getOrganization());
            values.put(DbOpenHelper.COLUMN_FACULTY, studentDto.getFaculty());
            values.put(DbOpenHelper.COLUMN_ROLL, studentDto.getRoll());

            affectedRow = database.update(DbOpenHelper.TABLE_NAME, values, DbOpenHelper.COLUMN_ID + "=?", new String[]{String.valueOf(studentDto.getId())});
        } catch (Exception e) {
            AppLogUtils.showLog("StudentDao   class", "Exception " + e.getLocalizedMessage());
        } finally {
            database.close();
            dbOpenHelper.close();
        }
        return affectedRow;
    }
}
