package com.example.advenced_calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvResultField;
    private TextView tvInputField;
    private OperationSigns operationSign;
    private double firstOperand;
    private double secondOperand;

    private boolean mathButtonIsPressed = false;
    private int mathButtonId = -1;

    private StringBuilder currentInput = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInputField = (TextView) findViewById(R.id.tv_input_field);
        tvResultField = (TextView) findViewById(R.id.tv_result_field);
        operationSign = getLastCustomNonConfigurationInstance();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("resultField", tvResultField.getText().toString());
        outState.putString("inputField", tvInputField.getText().toString());
        outState.putDouble("firstOperand", firstOperand);
        outState.putDouble("secondOperand", secondOperand);
        outState.putBoolean("mathButtonIsPressed", mathButtonIsPressed);
        outState.putInt("mathButtonId", mathButtonId);
        outState.putString("currentInput", currentInput.toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvResultField.setText(savedInstanceState.getString("resultField"));
        tvInputField.setText(savedInstanceState.getString("inputField"));
        firstOperand = savedInstanceState.getDouble("firstOperand");
        secondOperand = savedInstanceState.getDouble("secondOperand");
        mathButtonIsPressed = savedInstanceState.getBoolean("mathButtonIsPressed");
        savedInstanceState.getInt("mathButtonId");
        savedInstanceState.getString("currentInput");

    }

    @Override
    public OperationSigns onRetainCustomNonConfigurationInstance() {
        return operationSign;
    }

    @Override
    public OperationSigns getLastCustomNonConfigurationInstance() {
        return operationSign;
    }

    public void numberButtonClicked(View view) {
        if (mathButtonIsPressed) unpressMathButton();

        int buttonId = view.getId();
        switch (buttonId) {
            case R.id.button_zero:
                currentInput.append(0);
                tvInputField.setText(currentInput.toString());
                break;
            case R.id.button_one:
                currentInput.append(1);
                tvInputField.setText(currentInput.toString());
                break;
            case R.id.button_two:
                currentInput.append(2);
                tvInputField.setText(currentInput.toString());
                break;
            case R.id.button_three:
                currentInput.append(3);
                tvInputField.setText(currentInput.toString());
                break;
            case R.id.button_four:
                currentInput.append(4);
                tvInputField.setText(currentInput.toString());
                break;
            case R.id.button_five:
                currentInput.append(5);
                tvInputField.setText(currentInput.toString());
                break;
            case R.id.button_six:
                currentInput.append(6);
                tvInputField.setText(currentInput.toString());
                break;
            case R.id.button_seven:
                currentInput.append(7);
                tvInputField.setText(currentInput.toString());
                break;
            case R.id.button_eight:
                currentInput.append(8);
                tvInputField.setText(currentInput.toString());
                break;
            case R.id.button_nine:
                currentInput.append(9);
                tvInputField.setText(currentInput.toString());
                break;
            default:
                break;
        }
    }

    public void mathButtonClicked(View view) {
        int buttonId = view.getId();

        mathButtonIsPressed = true;
        mathButtonId = buttonId;
        changeMathButtonBackground(mathButtonId);

        switch (buttonId) {
            case R.id.button_plus:
                if (currentInput.length() == 0) return;
                pressOperationButtonWithSign(OperationSigns.PLUS);
                break;
            case R.id.button_minus:
                if (currentInput.length() == 0) return;
                pressOperationButtonWithSign(OperationSigns.MINUS);
                break;
            case R.id.button_multiply:
                if (currentInput.length() == 0) return;
                pressOperationButtonWithSign(OperationSigns.MULTIPLY);
                break;
            case R.id.button_divide:
                if (currentInput.length() == 0) return;
                pressOperationButtonWithSign(OperationSigns.DIVIDE);
                break;
        }
    }

    public void equalityButtonClicked(View view) {
        if (mathButtonIsPressed) unpressMathButton();

        if (currentInput.length() == 0 || operationSign == null) return;
        secondOperand = Double.parseDouble(currentInput.toString());

        double result;

        switch (operationSign) {
            case PLUS:
                result = firstOperand + secondOperand;
                showResult(result);
                break;
            case MINUS:
                result = firstOperand - secondOperand;
                showResult(result);
                break;
            case DIVIDE:
                if (secondOperand == 0) {
                    tvResultField.setText("LOL");
                    return;
                }
                result = firstOperand / secondOperand;
                showResult(result);
                break;
            case MULTIPLY:
                result = firstOperand * secondOperand;
                showResult(result);
                break;
        }
    }

    public void actionButtonClicked(View view) {
        if (mathButtonIsPressed) unpressMathButton();
        int actionButtonId = view.getId();

        switch (actionButtonId) {
            case R.id.button_percent:
                if (currentInput.length() == 0) return;
                double a = Double.parseDouble(currentInput.toString()) / 100;
                showInput(a);
                break;
            case R.id.button_plusminus:
                if (currentInput.length() == 0) return;
                double b = -Double.parseDouble(currentInput.toString());
                showInput(b);
                break;
        }

    }

    public void clearButtonIsClicked(View view) {
        if (mathButtonIsPressed) unpressMathButton();

        int buttonId = view.getId();

        if (buttonId == R.id.button_clear) {
            if (currentInput.length() == 0) {
                currentInput.setLength(0);
                firstOperand = 0;
                secondOperand = 0;
                tvInputField.setText("");
                tvInputField.setText("");
                operationSign = null;
            } else {
                currentInput.deleteCharAt(currentInput.length() - 1);
                tvInputField.setText(currentInput);
            }
        }
    }

    public void dotIsPressed(View view) {
        unpressMathButton();
        if (currentInput.length() == 0) {
            currentInput.append(0).append(".");
        } else {
            currentInput.append(".");
        }
        tvInputField.setText(currentInput.toString());
    }

    private void changeMathButtonBackground(int buttonId) {
        Button mathButton = (Button) findViewById(buttonId);
        if (mathButtonIsPressed) {
            mathButton.setBackgroundResource(R.drawable.math_button_pressed);
            mathButton.setTextColor(getResources().getColor(R.color.math_button_background));
        } else {
            mathButton.setBackgroundResource(R.drawable.math_button);
            mathButton.setTextColor(getResources().getColor(R.color.math_button_text));
        }
    }

    private void unpressMathButton() {
        mathButtonIsPressed = false;
        if (mathButtonId != -1) {
            changeMathButtonBackground(mathButtonId);
        }
    }

    private void pressOperationButtonWithSign(OperationSigns sign) {

        firstOperand = Double.parseDouble(currentInput.toString());
        operationSign = sign;
        currentInput.setLength(0);
    }

    private void showResult(double result) {
        tvResultField.setText(Double.toString(result));
        currentInput.setLength(0);
        currentInput.append(result);
        tvInputField.setText("");
    }

    private void showInput(double result) {
        currentInput.setLength(0);
        currentInput.append(result);
        tvInputField.setText(currentInput.toString());
    }

}
