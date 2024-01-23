package codinginflow.com;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    private EditText quizNameEditText;
    private EditText questionEditText;
    private Button addQuestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        quizNameEditText = findViewById(R.id.quiz_name_edit_text);
        questionEditText = findViewById(R.id.question_edit_text);
        addQuestionButton = findViewById(R.id.add_question_button);

        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quizName = quizNameEditText.getText().toString();
                String question = questionEditText.getText().toString();

                // Save the quiz name and question to the database
//                saveQuizAndQuestion(quizName, question);

                Toast.makeText(AdminActivity.this, "Question added successfully", Toast.LENGTH_SHORT).show();

                // Clear the input fields
                quizNameEditText.setText("");
                questionEditText.setText("");
            }
        });
    }

//    private void saveQuizAndQuestion(String quizName, String question) {
//        QuizDbHelper databaseHelper = new QuizDbHelper(this);
//        SQLiteDatabase database = databaseHelper.getWritableDatabase();
//
//        // Save the quiz name to the quiz table
//        ContentValues quizValues = new ContentValues();
//        quizValues.put(QuizDbHelper.COLUMN_QUIZ_NAME, quizName);
//        long quizId = database.insert(QuizDbHelper.TABLE_QUIZ, null, quizValues);
//
//        // Save the question to the question table
//        ContentValues questionValues = new ContentValues();
//        questionValues.put(QuizDbHelper.COLUMN_QUESTION_TEXT, question);
//        questionValues.put(QuizDbHelper.COLUMN_QUIZ_ID, quizId);
//        database.insert(QuizDbHelper.TABLE_QUESTION, null, questionValues);
//
//        database.close();
//        databaseHelper.close();
//    }
}
