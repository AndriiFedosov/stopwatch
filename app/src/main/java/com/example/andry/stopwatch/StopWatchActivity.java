package com.example.andry.stopwatch;

import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StopWatchActivity extends AppCompatActivity {
    //создаем переменную секунды котороая будет вести отсчет
    private int second =0;
    //создаем переменную истинности которая будет показывать запущен ли таймер
    private boolean running;
    //создаем переменную истинности которая будет показывать был ли запущен таймер
    private boolean wasRunning;


    //при старте приложения запускаем активность
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        //проверяем не отсутсвуют ли данные в приложеннии если так принимаем данные по ключу
        if (savedInstanceState!=null){
            second =savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
        //запускаем метод который выводит нам время прошедшее в часах минутах секундах
        runTimer();
    }

    //используем метод для сохрнения данных при повороте экрана или скрытии приложения
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds",second);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }

    //при отсановке изменяем переменные
    @Override
    protected void onStop(){
        super.onStop();
        wasRunning=running;
        running=false;
    }
    //при старте активности после и  вывода на передний план
    @Override
    protected void onStart() {
        super.onStart();
        if (wasRunning){
            running=true;
        }
    }

    //присваиваем кнопке старт метод и выполняем его
    public void OnClickStart(View view) {
        running=true;
    }
    //присваиваем кнопке стоп/пауза метод и выполняем его
    public void OnClickStop(View view) {
        running=false;
    }
    //присваиваем кнопке сброс метод и выполняем его
    public void OnClickReset(View view) {
        running=false;
        second=0;
    }

    //создаем метод секундомера
    public void runTimer(){
        //создаем переменую текстового поля в которую будет приходить наше время и связываем ее с
        //xml разметкой по id
         final TextView textView = (TextView)findViewById(R.id.time_view);

         //создаем хендлер который будет указывать поведения запуска нашего метода
         final Handler handler = new Handler();
         handler.post(new Runnable() {
             @Override
             public void run() {
                 //создаем переменные часов минут и секунд и прописываем формулы по которым они
                 // будут отрабатывать
                 int hours = second/3600;
                 int minutes = (second%3600)/60;
                 int seconds = second%60;
                 //создаем строковую переменную формата чч:мм:сс
                 String time = String.format("%d:%02d:%02d",hours,minutes,seconds);
                 //передаем в текстовое поле строковую переменную с переданім занчением
                 textView.setText(time);

                 //проверяме запущен ли таймер если да то прибавляем одну секунду
                 if (running){
                     second++;
                 }

                 //указываем какой ресурс будет изпользываен и задержку выполнения метода в нашем
                 // случаее 1000 милисекунд
                 handler.postDelayed(this,1000);
             }
         });


    }

}
