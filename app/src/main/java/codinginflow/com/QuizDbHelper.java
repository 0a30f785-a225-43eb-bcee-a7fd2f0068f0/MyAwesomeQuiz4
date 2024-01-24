package codinginflow.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;

import androidx.core.database.sqlite.SQLiteDatabaseKt;

import codinginflow.com.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
        QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION_A_VALUE  + " INTEGER, " +
                QuizContract.QuestionsTable.COLUMN_OPTION_B_VALUE  + " INTEGER, " +
                QuizContract.QuestionsTable.COLUMN_OPTION_C_VALUE  + " INTEGER " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("If you had to choose a color to describe your ideal vacation, would it be:", "Calm and serene beaches", "Energetic and bustling city", "Tranquil nature retreat", 2, 3, 4);
        addQuestion(q1);
        Question q2 = new Question("When faced with a challenge, do you tend to:", "Analyze and plan", "Take decisive action", "Seek harmony and collaboration", 2, 3, 4);
        addQuestion(q2);
        Question q3 = new Question("In a group project, are you more likely to:", "Focus on details and accuracy", "Take charge and lead", "Ensure everyone's opinions are considered", 2, 3, 4);
        addQuestion(q3);
        Question q4 = new Question("How do you approach making decisions?", "With careful consideration and logic", "Quickly and decisively", "Seeking consensus and input from others", 2, 3, 4);
        addQuestion(q4);
        Question q5 = new Question("When it comes to organization and planning, are you:", "Methodical and organized", "Spontaneous and adaptable", "Flexible and easygoing", 2, 3, 4);
        addQuestion(q5);
        Question q6 = new Question("In a group setting, do you prefer:", "Meaningful one-on-one conversations", "Group discussions and debates", "Creating a harmonious and friendly atmosphere", 2, 3, 4);
        addQuestion(q6);
        Question q7 = new Question("When it comes to personal goals, are you more focused on:", "Achieving personal growth and knowledge", "Attaining success and recognition", "Building strong relationships and connections", 2, 3, 4);
        addQuestion(q7);
        Question q8 = new Question("In a work environment, do you prefer:", "A structured and organized workspace", "A dynamic and fast-paced environment", "A collaborative and team-oriented atmosphere", 2, 3, 4);
        addQuestion(q8);
        Question q9 = new Question("How would you describe your communication style?", "Thoughtful and precise", "Direct and assertive", "Diplomatic and empathetic", 2, 3, 4);
        addQuestion(q9);
        Question q10 = new Question("When faced with change, do you typically:", "Approach it cautiously and adapt slowly", "Embrace it with enthusiasm and energy", "Take charge and lead the way", 2, 3, 4);
        addQuestion(q10);
        Question q11 = new Question("How do you prefer to unwind after a long day?", "Reading or engaging in a quiet activity", "Exercising or participating in a high-energy activity", "Spending time with loved ones", 2, 3, 4);
        addQuestion(q11);
        Question q12 = new Question("In a conflict, do you tend to:", "Analyze the situation and seek a logical resolution", "Address it head-on and assertively", "Mediate and find common ground", 2, 3, 4);
        addQuestion(q12);
        Question q13 = new Question("What type of books or movies do you enjoy the most?", "Thought-provoking and informative", "Action-packed and exciting", "Heartwarming and relational", 2, 3, 4);
        addQuestion(q13);
        Question q14 = new Question("How do you approach meeting new people?", "Observing and analyzing before engaging", "Taking charge and initiating conversation", "Building connections slowly and steadily", 2, 3, 4);
        addQuestion(q14);
        Question q15 = new Question("If you were planning a special celebration, what kind of event would you prefer?", " A sophisticated dinner party with close friends", "An energetic dance party with a lively atmosphere", "A cozy gathering with family in a natural setting", 2, 3, 4);
        addQuestion(q15);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION_A_VALUE, question.getOptionAValue());
        cv.put(QuestionsTable.COLUMN_OPTION_B_VALUE, question.getOptionBValue());
        cv.put(QuestionsTable.COLUMN_OPTION_C_VALUE, question.getOptionCValue());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndexOrThrow(QuestionsTable.COLUMN_OPTION3)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }



}
