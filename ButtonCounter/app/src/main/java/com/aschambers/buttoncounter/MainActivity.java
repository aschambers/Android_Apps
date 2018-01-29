package com.aschambers.buttoncounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// extends AppCompatActivity to support older devices
public class MainActivity extends AppCompatActivity {

    // add widgets that can only be called by methods within this class since they are private
    // create a name for the widget type you'd like to use
    private EditText userInput;
//    private Button button;
    private TextView textView;
//    private int numTimesClicked = 0;
    // logt shortcut = this declaration, MainActivity is the name of this class
    private static final String TAG = "MainActivity";

    private final String TEXT_CONTENTS = "TextContents";

    // activity lifecycle in order, with logging
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");

        // calls a super method and passes the bundle, the oncreate method recieved
        super.onCreate(savedInstanceState);
        // tells us which layout we are using for this activity
        setContentView(R.layout.activity_main);

        //field we defined = (type of object we want it to be treated as) objects ID from layout
        // the id is referring to the widget in our layout
        userInput = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        // replaces all text in textView with a blank string, to replace any existing text
        textView.setText("");
        // add the ability to make the textview scroll if it gets to long on app
        textView.setMovementMethod(new ScrollingMovementMethod());

        userInput.setText("");
        // created a new object of type onClickListener using the View class
        // all widgets extend views, so they will all know about this class
        View.OnClickListener ourClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                numTimesClicked = numTimesClicked + 1;
//                String result = "\nThe button got clicked " + numTimesClicked + " time";
//                if(numTimesClicked != 1) {
//                    result += "s"; // adds an s to time if numTimesClicked is greater than 1
//                }
//                result += "\n";
//                textView.append(result);

                // need to String to convert EditText value to a string
                String result = userInput.getText().toString();
                result += "\n";
                textView.append(result);
                userInput.setText("");
            }
        };
        // tells our button we want to use ourClickListener method whenever our button is clicked
        button.setOnClickListener(ourClickListener);

        Log.d(TAG, "onCreate: out");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: in");
        super.onStart();
        Log.d(TAG, "onStart: out");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onStart: in");
        super.onRestoreInstanceState(savedInstanceState);
        // retrieve value from onSaveInstanceState stored in TEXT_CONTENTS that we saved
//        String savedString = savedInstanceState.getString(TEXT_CONTENTS);
        // when we switch from landscape to portrait or from portrait to landscape, get saved values
        // set those values to the textview on screen rotation
//        textView.setText(savedString);

        // shortcut to above
        textView.setText(savedInstanceState.getString(TEXT_CONTENTS));

        Log.d(TAG, "onStart: out");
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "onStart: in");
        super.onRestart();
        Log.d(TAG, "onStart: out");
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onStart: in");
        super.onResume();
        Log.d(TAG, "onStart: out");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onStart: in");
        super.onPause();
        Log.d(TAG, "onStart: out");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onStart: in");
        // automatically passed to this state by android, where a bundle contains key value pairs
        // saving the current value of text view, into the bundle so we can access later in activity lifecycle
        outState.putString(TEXT_CONTENTS, textView.getText().toString());
        // needs to be called after the outState.putString in order to save value properly
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onStart: out");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStart: in");
        super.onStop();
        Log.d(TAG, "onStart: out");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onStart: in");
        super.onDestroy();
        Log.d(TAG, "onStart: out");
    }
}
