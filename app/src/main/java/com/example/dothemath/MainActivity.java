package com.example.dothemath;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //поля
    private TextView equationOne, equationTwo, equationThree; // поля вывода примеров
    private EditText solvingOne, solvingTwo, solvingThree; // поля ввода ответов
    private int[] equationValue; // массив 6 чисел (для трёх примеров)
    private boolean right = false; // флаг правильности решения примеров
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Привязка к полям
        equationOne = findViewById(R.id.equationOne);
        equationTwo = findViewById(R.id.equationTwo);
        equationThree = findViewById(R.id.equationThree);
        solvingOne = findViewById(R.id.solvingOne);
        solvingTwo = findViewById(R.id.solvingTwo);
        solvingThree = findViewById(R.id.solvingThree);

        button = findViewById(R.id.button);

        //формирование массива случ. чисел
        equationValue = valueArrayRandom();

        //заполнение строк примерами
        equationOne.setText(equationValue[0] + " + " + equationValue[1] + " = ");
        equationTwo.setText(equationValue[2] + " - " + equationValue[3] + " = ");
        equationThree.setText(equationValue[4] + " * " + equationValue[5] + " = ");

        // обработка фокусировки/снятие фокусировки с EditText
        solvingOne.setOnFocusChangeListener(focusListener);
        solvingTwo.setOnFocusChangeListener(focusListener);
        solvingThree.setOnFocusChangeListener(focusListener);

        button.setOnClickListener(listener);
    }
    //создание слушателя для кнопки
    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (right){
                equationValue = valueArrayRandom(); //формирование нового массива случ. чисел
                //заполнение строк примерами для решения
                equationOne.setText(equationValue[0] + " + " + equationValue[1] + " = ");
                equationTwo.setText(equationValue[2] + " - " + equationValue[3] + " = ");
                equationThree.setText(equationValue[4] + " * " + equationValue[5] + " = ");
                //задание исходного цвета
                solvingOne.setBackgroundColor(Color.rgb(0xE7,0xE4,0xEC));//исходный цвет
                solvingTwo.setBackgroundColor(Color.rgb(0xE7,0xE4,0xEC));
                solvingThree.setBackgroundColor(Color.rgb(0xE7,0xE4,0xEC));
                //очистка примеров
                solvingOne.setText("");
                solvingTwo.setText("");
                solvingThree.setText("");

        }
    }
    };


    //создание слушателя фокусировки/снятие фокусировки с EditText
    private View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            //с помощью view.getId() определения с каким виджетом происходит действие
            switch (view.getId()){
                case R.id.solvingOne:
                    if(!b) {
                        try {
                            //при потери фокуса производим проверку введенного числа
                            // если посчитано верно
                            if(Integer.parseInt(solvingOne.getText().toString()) == (equationValue[0] + equationValue[1])) {
                                solvingOne.setBackgroundColor(Color.GREEN); // закрашиваем в зелённый цвет
                                right = true;
                            } else { // иначе
                                solvingOne.setBackgroundColor(Color.RED); // закрашиваем в красный цвет
                                right = false;
                            }
                        } catch(NumberFormatException exception) {
                            //очистка edittext
                            solvingOne.setText("");
                            Toast.makeText(MainActivity.this,"Вы не дали ответа",Toast.LENGTH_SHORT).show();
                        }
                        }
                    break;
                case R.id.solvingTwo:
                    if(!b) {
                        try {
                            //при потери фокуса производим проверку введенного числа
                            // если посчитано верно
                            if(Integer.parseInt(solvingTwo.getText().toString()) == (equationValue[0] + equationValue[1])) {
                                solvingTwo.setBackgroundColor(Color.GREEN); // закрашиваем в зелённый цвет
                                right = true;
                            } else { // иначе
                                solvingTwo.setBackgroundColor(Color.RED); // закрашиваем в красный цвет
                                right = false;
                            }
                        } catch(NumberFormatException exception) {
                            //очистка edittext
                            solvingTwo.setText("");
                            Toast.makeText(MainActivity.this,"Вы не дали ответа",Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.solvingThree:
                    if(!b) { try {
                        //при потери фокуса производим проверку введенного числа
                        // если посчитано верно
                        if(Integer.parseInt(solvingThree.getText().toString()) == (equationValue[0] + equationValue[1])) {
                            solvingThree.setBackgroundColor(Color.GREEN); // закрашиваем в зелённый цвет
                            right = true;
                        } else { // иначе
                            solvingThree.setBackgroundColor(Color.RED); // закрашиваем в красный цвет
                            right = false;
                        }
                    } catch(NumberFormatException exception) {
                        //очистка edittext
                        solvingThree.setText("");
                        Toast.makeText(MainActivity.this,"Вы не дали ответа",Toast.LENGTH_SHORT).show();
                    }
                    }
                    break;
    }}
    };

    // метод генерации случ. примеров (3 примера)
    private int[] valueArrayRandom() {
        Random random = new Random(); //создание  объекста класса Random
        int[] arrayValue = new int[6]; //создание массива для заполнения
        for (int i = 0; i < arrayValue.length; i++) { //цикл заполнения случ. числами
            arrayValue[i] = random.nextInt(99) + 1;
        }
        return arrayValue;
    }

}
