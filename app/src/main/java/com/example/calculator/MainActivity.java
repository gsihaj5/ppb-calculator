package com.example.calculator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.calculator.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private GridLayout mainGrid;
    private TextView mainTextView;
    private TextView resultTextView;
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private float result;
    private String lastOperator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        mainTextView = (TextView) findViewById(R.id.mainTextView);
        resultTextView = (TextView) findViewById(R.id.result);
        this.createNumberButton();

        Button button0 = (Button) findViewById(R.id.btn0);
        Button buttonDot = (Button) findViewById(R.id.dot);
        Button plus = (Button) findViewById(R.id.plus);
        Button minus = (Button) findViewById(R.id.minus);
        Button multiply = (Button) findViewById(R.id.multiply);
        Button divide = (Button) findViewById(R.id.divide);
        Button delete = (Button) findViewById(R.id.delete);
        Button reset = (Button) findViewById(R.id.reset);
        Button equal = (Button) findViewById(R.id.equal);

        button0.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        resultTextView.append("0");
                    }
                }
        );
        buttonDot.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        resultTextView.append(".");
                    }
                }
        );

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence lastText = resultTextView.getText();
                resultTextView.setText(
                        lastText.subSequence(0, lastText.length() - 1)
                );

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainTextView.setText("");
                resultTextView.setText("");
                result = 0;
            }
        });

        plus.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainTextView.append(resultTextView.getText());
                        result = Float.parseFloat(String.valueOf(mainTextView.getText()));
                        mainTextView.append("+");
                        lastOperator = "+";
                        resultTextView.setText("");
                    }
                }
        );
        minus.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainTextView.append(resultTextView.getText());
                        result = Float.parseFloat(String.valueOf(mainTextView.getText()));
                        mainTextView.append("-");
                        lastOperator = "-";
                        resultTextView.setText("");
                    }
                }
        );
        divide.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainTextView.append(resultTextView.getText());
                        result = Float.parseFloat(String.valueOf(mainTextView.getText()));
                        mainTextView.append("/");
                        lastOperator = "/";
                        resultTextView.setText("");
                    }
                }
        );
        multiply.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainTextView.append(resultTextView.getText());
                        result = Float.parseFloat(String.valueOf(mainTextView.getText()));
                        mainTextView.append("x");
                        lastOperator = "x";
                        resultTextView.setText("");
                    }
                }
        );

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float buffer = Float.parseFloat(String.valueOf(resultTextView.getText()));
                mainTextView.append(resultTextView.getText());
                mainTextView.append("=");
                switch (lastOperator) {
                    case "+":
                        result += buffer;
                        break;
                    case "-":
                        result -= buffer;
                        break;
                    case "/":
                        result /= buffer;
                        break;
                    case "x":
                        result *= buffer;
                        break;
                }
                resultTextView.setText(String.valueOf(result));
            }
        });

    }

    private void createNumberButton() {
        int ROWSIZE = 3;
        int COLSIZE = 3;

        int counter = 1;
        for (int x = 0; x < ROWSIZE; x++) {
            for (int y = 0; y < COLSIZE; y++) {
                Button newButton = new Button(this);
                newButton.setText("" + counter);
                newButton.setBackgroundColor(android.graphics.Color.rgb(5, 172, 13));

                int finalCounter = counter;
                newButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                resultTextView.append("" + finalCounter);
                            }
                        }
                );

                buttons.add(newButton);
                mainGrid.addView(newButton, new GridLayout.LayoutParams(GridLayout.spec(5 - x), GridLayout.spec(y)));
                counter++;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}