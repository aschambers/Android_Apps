package com.aschambers.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String STATE_OPERAND1 = "Operand1";

    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    // variables to hold the operands and type of calculations
    private Double operand1 = null;
//    private Double operand2 = null;
    private String pendingOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (EditText) findViewById(R.id.result);
        newNumber = (EditText) findViewById(R.id.newNumber);
        displayOperation = (TextView) findViewById(R.id.operation);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button buttonDot = (Button) findViewById(R.id.buttonDot);

        Button buttonEquals = (Button) findViewById(R.id.buttonEquals);
        Button buttonDivide = (Button) findViewById(R.id.buttonDivide);
        Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        Button buttonPlus = (Button) findViewById(R.id.buttonPlus);

        // the variable listener holds a reference to a new on click instance
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            // when a button is tapped, it passes a reference to the button that was passed
            public void onClick(View view) {
                Button b = (Button) view;
                newNumber.append(b.getText().toString());
            }
        };
        // assign listener to each button so we know when they are clicked
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        // opListener to listen for an operation click and act accordingly
        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
//                if(value.length() != 0) {
//                    performOperation(value, op);
//                }
                try {
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);
                } catch (NumberFormatException e) {
                    // if error, from putting in a decimal number, clear field
                    newNumber.setText("");
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };

        buttonEquals.setOnClickListener(opListener);
        buttonDivide.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);

        Button buttonNeg = (Button) findViewById(r.id.buttonNeg);

        buttonNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = newNumber.getText().toString();
                if(value.length() == 0) {
                    newNumber.setText("-");
                } else {
                    try {
                        Double doubleValue = Double.valueOf(value);
                        doubleValue += 1;
                        newNumber.setText(doubleValue.toString());
                    } catch (NumberFormatException e) {
                        // if newNumber is "-", or ".", the field will be cleared
                        newNumber.setText("");
                    }
                }
            }
        });
    }

    private void performOperation(Double value, String operation) {
        if(null == operand1) {
            operand1 = value;
        } else {
//            operand2 = value;

            if(pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if(value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
            }
        }
        result.setText(operand1.toString());
        result.setText("");
    }

    // save pending operation and operand1 in state when device is rotated
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: start");
        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
        // device will crash otherwise
        if(operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1);
        }
        // saves our state for us
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: end");
    }

    // state can also be restored in the onCreate method
    // better than onCreate since it only runs if the bundle is valid
    // retrieve values from onSaveInstanceState
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: start");
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
        displayOperation.setText(pendingOperation);
        Log.d(TAG, "onRestoreInstanceState: end");
    }
}
