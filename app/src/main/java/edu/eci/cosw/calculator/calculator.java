package edu.eci.cosw.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Stack;


public class calculator extends AppCompatActivity {
    private EditText input;
    private EditText operations;
    private Stack<String> num;
    private Boolean sigpos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        input = (EditText) findViewById(R.id.input) ;
        operations = (EditText) findViewById(R.id.operations) ;
        num=new Stack<>();
        sigpos=false;
        this.eventOnClick();
    }

    public void eventOnClick(){
        findViewById(R.id.btequal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(num.pop());
            }
        });
        findViewById(R.id.btdel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num.clear();
                operations.setText("");
                input.setText("0");
            }
        });
        findViewById(R.id.btdellast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(input.getText().toString().substring(0,input.getText().toString().length()-1));
            }
        });
        findViewById(R.id.btenter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num.push(input.getText().toString());
                if (input.getText().toString().contains("-")) {
                    operations.setText(operations.getText().toString()+" ("+input.getText().toString()+")");
                }else{
                    operations.setText(operations.getText().toString()+" "+input.getText().toString());
                }
                input.setText("");
            }
        });
        findViewById(R.id.btsin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(String.valueOf(Math.sin(Double.valueOf(input.getText().toString()))));
            }
        });
        findViewById(R.id.btcos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(String.valueOf(Math.cos(Double.valueOf(input.getText().toString()))));
            }
        });
        findViewById(R.id.bttan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText(String.valueOf(Math.tan(Double.valueOf(input.getText().toString()))));
            }
        });
        findViewById(R.id.btsig).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sigpos){
                    input.setText("-"+input.getText().toString());
                    sigpos=true;
                }else{
                    sigpos=false;
                }
            }
        });
    }
    public void ButtonNumber(View v){
        input.setText(input.getText().toString()+ v.getTag().toString());

    }
    public void operationsBasic(View v){
        try {
            String operation=v.getTag().toString();
            double num1=Double.valueOf(num.pop());
            double num2=Double.valueOf(num.pop());
            if(operation.contains("/")){
                num.push(String.valueOf(num2/num1));
            }else if(operation.contains("-")){
                num.push(String.valueOf(num2-num1));
            }else if(operation.contains("+")){
                num.push(String.valueOf(num2+num1));
            }
            else if(operation.contains("*")){
                num.push(String.valueOf(num2*num1));
            }
            operations.setText(operations.getText().toString()+" "+ operation);
            input.setText("");
        }catch (NumberFormatException ex){
        }catch (ArithmeticException ex){
            new Throwable("No se puede dividir por 0");
        }

    }
}
