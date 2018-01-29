package com.aschambers.buttoncounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// extends AppCompatActivity to support older devices
public class MainActivity extends AppCompatActivity {

    // add widgets that can only be called by methods within this class since they are private
    // create a name for the widget type you'd like to use
    private EditText userInput;
    private Button button;
    private TextView textView;
    private int numTimesClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // calls a super method and passes the bundle, the oncreate method recieved
        super.onCreate(savedInstanceState);
        // tells us which layout we are using for this activity
        setContentView(R.layout.activity_main);

        //field we defined = (type of object we want it to be treated as) objects ID from layout
        // the id is referring to the widget in our layout
        userInput = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        // replaces all text in textView with a blank string, to replace any existing text
        textView.setText("");
        // add the ability to make the textview scroll if it gets to long on app
        textView.setMovementMethod(new ScrollingMovementMethod());

        // created a new object of type onClickListener using the View class
        // all widgets extend views, so they will all know about this class
        View.OnClickListener ourClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numTimesClicked = numTimesClicked + 1;
                String result = "\nThe button got clicked " + numTimesClicked + " time";
                if(numTimesClicked != 1) {
                    result += "s"; // adds an s to time if numTimesClicked is greater than 1
                }
                result += "\n";
                textView.append(result);
            }
        };
        // tells our button we want to use ourClickListener method whenever our button is clicked
        button.setOnClickListener(ourClickListener);
    }
}
