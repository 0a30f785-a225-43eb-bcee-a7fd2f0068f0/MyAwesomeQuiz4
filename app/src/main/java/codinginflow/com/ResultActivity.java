package codinginflow.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;


public class ResultActivity extends AppCompatActivity {
// Those with Blue color personality strengths tend to be enthusiastic, sympathetic, communicative, compassionate, idealistic, sincere and imaginative. They care and want to contribute to everything they are a part of. Relationships are important to blues.\n Blue personality temperaments can be good listeners, talk about the possibilities and like to do new things. Conflict or disharmony can upset a Blue. They show concern for feelings in others and have tactful ways of communicating.\n When working with Blues, it is important to reassure them of their self-worth and recognize their accomplishments. It is also important to be empathetic with them as well as believe and trust them. Give Blues opportunities to demonstrate creativity, work with and mentor others and communicate."
public static String blueDescription = "Those with Blue color personality strengths tend to be enthusiastic, sympathetic, communicative, compassionate, idealistic, sincere and imaginative. They care and want to contribute to everything they are a part of. Relationships are important to blues." + System.getProperty("line.separator") +
        "Blue personality temperaments can be good listeners, talk about the possibilities and like to do new things. Conflict or disharmony can upset a Blue. They show concern for feelings in others and have tactful ways of communicating." + System.getProperty("line.separator") +
        "When working with Blues, it is important to reassure them of their self-worth and recognize their accomplishments. It is also important to be empathetic with them as well as believe and trust them. Give Blues opportunities to demonstrate creativity, work with and mentor others and communicate.";

    public static String redDescription = "As a personality color red, you are stimulating to be with and you radiate a great deal of energy." + System.getProperty("line.separator") +
            "You are ambitious and competitive and like to be the winner - you are achievement orientated and second place is not good enough for you. With you it is all or nothing.";

    public static String greenDescription = "Those with Green color personality strengths tend to be perfectionistic, analytical, conceptual, cool, calm, inventive and logical. They seek knowledge and understanding as well as always looking for explanations and answers." + System.getProperty("line.separator") +
            "Greens can be good researchers – looking for facts and asking lots of questions. Greens have a large vocabulary and can argue both sides of an issue. Often, Greens take the time to concentrate and give correct answers. Greens like mind games that challenge their thinking.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Retrieve the value of 'personality' from the Intent extras
        String personality = getIntent().getStringExtra("personality");

        // Find the TextView by its id
        TextView textViewPersonality = findViewById(R.id.text_view_personality);
        TextView textViewDescription = findViewById(R.id.text_view_description);

        // Set the text of the TextView to the value of 'personality'
        textViewPersonality.setText("Personality: " + personality);

        if ("Blue".equals(personality)) {
            textViewDescription.setText(blueDescription);
        } else if ("Red".equals(personality)) {
            textViewDescription.setText(redDescription);
        } else if ("Green".equals(personality)) {
            textViewDescription.setText(greenDescription);
        }

        // Display the personality value using a Toast or any other UI component
        Toast.makeText(getApplicationContext(), "Quiz is now finished!", Toast.LENGTH_SHORT).show();
    }

}