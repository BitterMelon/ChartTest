package com.example.charttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LineChart mLineChart;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLineChart = findViewById(R.id.chart_test);

        final ChartUtils chart = new ChartUtils();
        chart.initStateChart(mLineChart);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                while (true){
                    i++;
                    chart.addEntry("第"+i+"次",validNumber(random.nextInt(6)+random.nextFloat()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    public float validNumber(float Data)
    {
        //String转换成double，并且保留N位小数
        DecimalFormat df = new DecimalFormat("#.0000");//保留四位有效数字
        String temp = df.format(Data);
        float b = Float.valueOf(temp);
        return b;
    }
}
