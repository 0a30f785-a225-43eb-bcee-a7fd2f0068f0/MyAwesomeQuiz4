package codinginflow.com;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private int totalScore = 0;
    public static final String EXTRA_SCORE = "extraScore";
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private int score;
    private boolean answered;


    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        accumulateScores();
                        showNextQuestion();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");
        } else {
            finishQuiz();
        }
    }

//    private void accumulateScores() {
//        if (rb1.isChecked()) {
//            totalScore += getScoreForRadioButton(rb1);
//        } else if (rb2.isChecked()) {
//            totalScore += getScoreForRadioButton(rb2);
//        } else if (rb3.isChecked()) {
//            totalScore += getScoreForRadioButton(rb3);
//        }
//    }

    private int getScoreForRadioButton(RadioButton radioButton) {
        // Implement logic to map radio buttons to scores based on trait
        // Example: If trait is A, return currentQuestion.getOptionAValue(), and so on
        int traitValue = 0;
        // Extract the trait associated with the selected radio button
        String trait = extractTraitFromRadioButton(radioButton);
        switch (trait) {
            case "A":
                traitValue = 2; // Replace with actual values from your currentQuestion object
                break;
            case "B":
                traitValue = 3; // Replace with actual values from your currentQuestion object
                break;
            case "C":
                traitValue = 4; // Replace with actual values from your currentQuestion object
                break;
        }
        return traitValue;
    }

    private String extractTraitFromRadioButton(RadioButton radioButton) {
        // Assuming you have identifiers for your radio buttons (e.g., R.id.rb1, R.id.rb2, R.id.rb3)

        // Determine trait based on the radio button's identifier
        if (radioButton == rb1) {
            return "A";
        } else if (radioButton == rb2) {
            return "B";
        } else if (radioButton == rb3) {
            return "C";
        } else {
            return ""; // Default or handle other cases
        }
    }

    private String determinePersonality(int totalScore) {
        // Implement your logic to map the total score to a personality trait
        // Adjust this logic based on your specific requirements
        if (totalScore <= 30) {
            return "Blue";
        } else if (totalScore <= 45) {
            return "Red";
        } else {
            return "Green";
        }
    }

    private void accumulateScores() {
        // Debug output
        Log.d("DEBUG", "Total Score Before: " + totalScore);

        if (rb1.isChecked()) {
            totalScore += getScoreForRadioButton(rb1);
        } else if (rb2.isChecked()) {
            totalScore += getScoreForRadioButton(rb2);
        } else if (rb3.isChecked()) {
            totalScore += getScoreForRadioButton(rb3);
        }

        // Debug output
        Log.d("DEBUG", "Total Score After: " + totalScore);
    }


//    private void showSolution() {
//        rb1.setTextColor(Color.RED);
//        rb2.setTextColor(Color.RED);
//        rb3.setTextColor(Color.RED);
//
//        switch (currentQuestion.getAnswerNr()) {
//            case 1:
//                rb1.setTextColor(Color.GREEN);
//                textViewQuestion.setText("Answer 1 is correct");
//                break;
//            case 2:
//                rb2.setTextColor(Color.GREEN);
//                textViewQuestion.setText("Answer 2 is correct");
//                break;
//            case 3:
//                rb3.setTextColor(Color.GREEN);
//                textViewQuestion.setText("Answer 3 is correct");
//                break;
//        }
//
//        if (questionCounter < questionCountTotal) {
//            buttonConfirmNext.setText("Next");
//        } else {
//            buttonConfirmNext.setText("Finish");
//        }
//    }
private void finishQuiz() {
    // Accumulate scores based on selected radio button
    accumulateScores();

    // Compute personality based on the total score
    String personality = determinePersonality(totalScore);

    // Display the result or use it as needed
    //Toast.makeText(getApplicationContext(), "Your personality is influenced by " + personality, Toast.LENGTH_SHORT).show();

    // Assuming you are starting ResultActivity from ActivityA
    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
    intent.putExtra("personality", personality);
    startActivity(intent);


    // Optionally, you can pass the personality result to another activity or use it in any way you see fit
//    Intent resultIntent = new Intent();
//    resultIntent.putExtra(EXTRA_PERSONALITY, personality);
//    setResult(RESULT_OK, resultIntent);

    // Return to the home screen or perform other actions
    // Disable ko muna kasi gumamit na ng intent sa taas
    //goToResultActivity();
}

//    private void finishQuiz() {
//        Intent resultIntent = new Intent();
//        resultIntent.putExtra(EXTRA_SCORE, score);
//        setResult(RESULT_OK, resultIntent);
//        returnHome();
//    }

    private void goToResultActivity() {
        Intent intentNew = new Intent(QuizActivity.this, ResultActivity.class);
        startActivity(intentNew);
    }
}