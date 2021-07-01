package com.example.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.androidapp.QuizContract.*;


import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static QuizDbHelper instance;


    private static final String DATABASE_NAME="FinkiQuiz.db";

    private static final int DATABASE_VERSION = 5;

    private SQLiteDatabase db; // reference to the actual databse



    private QuizDbHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER + " INTEGER, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
    private void fillCategoriesTable() {
        Category c1 = new Category("Android");
        addCategory(c1);
        Category c2 = new Category("Programming");
        addCategory(c2);
        Category c3 = new Category("Database");
        addCategory(c3);
    }
    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }
    private void fillQuestionsTable() {
//        Question q1 = new Question("Programming, Easy: A is correct",
//                "A", "B", "C", 1,
//                 Category.PROGRAMMING);
//        addQuestion(q1);
        Question question1 = new Question("Is it possible to have an activity without UI to perform actions?",
                "A: Not possible", "B: Wrong question", "C: Yes it is possible" , "D: None of the above", 3, Category.ANDROID);
        addQuestion(question1);

        Question question2 = new Question("What is Manifest.xml in android?",
                "A: It has information about layout in application", "B: It has information about activities in an application", "C: It has all the information about an application" , "D: None of the above", 3, Category.ANDROID);
        addQuestion(question2);

        Question question3 = new Question("On which thread broadcast receivers will work in android?",
                "A: Worker thread", "B: Main thread", "C: Activity thread" , "D: None of the above", 2, Category.ANDROID);
        addQuestion(question3);

        Question question4 = new Question("How many broadcast receivers are available in android?",
                "A: sendIntent()", "B: onReceive()", "C: implicitBroadcast()" , "D: sendBroadcast(), sendOrderBroadcast(), and sendStickyBroadcast()", 4, Category.ANDROID);
        addQuestion(question4);

        Question question5 = new Question("Which permission are required to get a location in android?",
                "A: ACCESS_FINE and ACCESS_COARSE", "B: GPRS permission", "C: Internet Permission", "D: WIFI permission", 1, Category.ANDROID);
        addQuestion(question5);

        Question question6 = new Question("What java librabry must be imported to get user input for the Scanner class?",
                "A: DecimalFormat()", "B: java.util.Scanner", "C: javax.swing.JOptionPane", "D: java.util.Random", 2, Category.PROGRAMMING);
        addQuestion(question6);

        Question question7 = new Question("What is constructor?",
                "A: another name for an instance variable", "B: the return type of a method", "C: the method that creates an object,or instance of the class", "D: the instantiation of an object", 3, Category.PROGRAMMING);
        addQuestion(question7);

        Question question8 = new Question("What does it mean when a method is static?",
                "A: it modifies or mutates an object", "B: it applies to the entire class, not just one object or instance", "C: it is private", "D: it is overloaded", 2, Category.PROGRAMMING);
        addQuestion(question8);

       Question question9 = new Question("A precise step-by-step procedure that solves a problem or achieve a goal is?",
               "A: method", "a class", "an algorithm", "D: an interface", 3, Category.PROGRAMMING);
       addQuestion(question9);

        Question question10 = new Question("___ means the method has no return value?",
                "A: void", "B: concatenation", "C: static", "D: string", 1, Category.PROGRAMMING);
        addQuestion(question10);

        Question question11 = new Question("The DBMS acts as an interface between what two components of an enterprise-class database system?",
                "A: Database application and the database", "B: Data and the database", "C: The user and the database application", "D: Database application and SQL", 1, Category.DATABASE);
        addQuestion(question11);

        Question question12 = new Question("Which of the following products was an early implementation of the relational model developed by E.F. Codd of IBM?",
               "A: IDMS", "B: DB2", "C: dBase-II", "D: R:base", 2, Category.DATABASE );
        addQuestion(question12);

        Question question13 = new Question("The following are components of a database except __ ?",
                "A: user data", "B: metadata", "C: reports", "D: indexes", 3, Category.DATABASE);
        addQuestion(question13);

        Question question14 = new Question("An application where only one user accesses the database at a given time is an example of a(n)?",
                "A: single-use database application", "B: multiuser database application", "C: e-commerce database application", "D: data mining database application", 1, Category.DATABASE);
        addQuestion(question14);

        Question question15 = new Question("An online commercial site suach as Amazon.com is an example of a(n)?",
                "A: single-user database application", "B: multiuser database application", "C: e-commerce database application", "D: data mining database application", 3, Category.DATABASE );
        addQuestion(question15);




    }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER, question.getAnswerNumber());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME,null);
        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }


    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }


    public ArrayList<Question> getQuestions(int categoryID) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String[] selectionArgs = new String[]{String.valueOf(categoryID)};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME +
                " WHERE " + QuestionsTable.COLUMN_CATEGORY_ID + " = ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNumber(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
