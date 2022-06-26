package com.example.romankolin3x3matrixgame;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.Arrays;

public class MatrixGameActivity extends AppCompatActivity
{
    TextView textView1result;
    EditText editText111;
    EditText editText212;
    EditText editText313;
    EditText editText421;
    EditText editText522;
    EditText editText623;
    EditText editText731;
    EditText editText832;
    EditText editText933;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrixgameactivity);

        textView1result = findViewById(R.id.textView1result);
        editText111 = findViewById(R.id.editText111);
        editText212 = findViewById(R.id.editText212);
        editText313 = findViewById(R.id.editText313);
        editText421 = findViewById(R.id.editText421);
        editText522 = findViewById(R.id.editText522);
        editText623 = findViewById(R.id.editText623);
        editText731 = findViewById(R.id.editText731);
        editText832 = findViewById(R.id.editText832);
        editText933 = findViewById(R.id.editText933);
    }

    @SuppressLint("SetTextI18n")
    public void onclickplay(View view)
    {
        textView1result.setText("");

        if (editText111.getText().toString().equals("") || editText212.getText().toString().equals("") || editText313.getText().toString().equals("") || editText421.getText().toString().equals("") || editText522.getText().toString().equals("") || editText623.getText().toString().equals("") || editText731.getText().toString().equals("") || editText832.getText().toString().equals("") || editText933.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_LONG).show();
        else
        {
            int e11, e12, e13, e21, e22, e23, e31, e32, e33, min1, min2, min3, max1, max2, max3, maximin, minimax;
            double p1, p2, p3, q1, q2, q3, pg1i, pg2i, pg1, pg2;
            String sp;

            e11 = Integer.parseInt(editText111.getText().toString());
            e12 = Integer.parseInt(editText212.getText().toString());
            e13 = Integer.parseInt(editText313.getText().toString());
            e21 = Integer.parseInt(editText421.getText().toString());
            e22 = Integer.parseInt(editText522.getText().toString());
            e23 = Integer.parseInt(editText623.getText().toString());
            e31 = Integer.parseInt(editText731.getText().toString());
            e32 = Integer.parseInt(editText832.getText().toString());
            e33 = Integer.parseInt(editText933.getText().toString());

            min1 = MinElement(e11, e12, e13);
            min2 = MinElement(e21, e22, e23);
            min3 = MinElement(e31, e32, e33);
            max1 = MaxElement(e11, e21, e31);
            max2 = MaxElement(e12, e22, e32);
            max3 = MaxElement(e13, e23, e33);
            maximin = MaxElement(min1, min2, min3);
            minimax = MinElement(max1, max2, max3);
            if (maximin == minimax)
            {
                sp = "Есть";

                textView1result.setText("Максимин = " + maximin + "\n" +
                        "Минимакс = " + minimax + "\n" +
                        "Седловая точка – " + sp + "\n" +
                        "Цена игры = " + maximin);
            }
            else
            {
                sp = "Отсутствует";

                int[] p1a = new int[20];
                int[] p1b = new int[20];
                int[] p1c = new int[20];
                int[] p2a = new int[20];
                int[] p2b = new int[20];
                int[] p2y = new int[20];
                String[] pl1 = new String[20];
                String[] pl2 = new String[20];
                p1a[0] = e11;
                p1b[0] = e21;
                p1c[0] = e31;
                p2a[0] = e11;
                p2b[0] = e12;
                p2y[0] = e13;
                pl1[0] = "A";
                pl2[0] = "a";
                pg1 = MaxElement(e11, e21, e31);
                pg2 = MinElement(e11, e12, e13);
                for (int i = 1; i < 20; i++)
                {
                    if (MaxElement(p1a[i - 1], p1b[i - 1], p1c[i - 1]) == p1a[i - 1])
                        pl1[i] = "A";
                    else if (MaxElement(p1a[i - 1], p1b[i - 1], p1c[i - 1]) == p1b[i - 1])
                        pl1[i] = "B";
                    else
                        pl1[i] = "C";
                    if (MinElement(p2a[i - 1], p2b[i - 1], p2y[i - 1]) == p2a[i - 1])
                        pl2[i] = "a";
                    else if (MinElement(p2a[i - 1], p2b[i - 1], p2y[i - 1]) == p2b[i - 1])
                        pl2[i] = "b";
                    else
                        pl2[i] = "y";

                    if (pl2[i].equals("a"))
                    {
                        p1a[i] = p1a[i - 1] + e11;
                        p1b[i] = p1b[i - 1] + e21;
                        p1c[i] = p1c[i - 1] + e31;
                    }
                    else if (pl2[i].equals("b"))
                    {
                        p1a[i] = p1a[i - 1] + e12;
                        p1b[i] = p1b[i - 1] + e22;
                        p1c[i] = p1c[i - 1] + e32;
                    }
                    else
                    {
                        p1a[i] = p1a[i - 1] + e13;
                        p1b[i] = p1b[i - 1] + e23;
                        p1c[i] = p1c[i - 1] + e33;
                    }
                    if (pl1[i].equals("A"))
                    {
                        p2a[i] = p2a[i - 1] + e11;
                        p2b[i] = p2b[i - 1] + e12;
                        p2y[i] = p2y[i - 1] + e13;
                    }
                    else if (pl1[i].equals("B"))
                    {
                        p2a[i] = p2a[i - 1] + e21;
                        p2b[i] = p2b[i - 1] + e22;
                        p2y[i] = p2y[i - 1] + e23;
                    }
                    else
                    {
                        p2a[i] = p2a[i - 1] + e31;
                        p2b[i] = p2b[i - 1] + e32;
                        p2y[i] = p2y[i - 1] + e33;
                    }

                    pg1i = (double)MaxElement(p1a[i], p1b[i], p1c[i]) / (i + 1);
                    pg2i = (double)MinElement(p2a[i], p2b[i], p2y[i]) / (i + 1);
                    if (pg1 > pg1i)
                        pg1 = pg1i;
                    if (pg2 < pg2i)
                        pg2 = pg2i;
                }
                if (pg1 > pg2)
                {
                    double temp;

                    temp = pg1;
                    pg1 = pg2;
                    pg2 = temp;
                }

                p1 = (double)Arrays.stream(pl1).filter("A"::equals).count() / 20;
                p2 = (double)Arrays.stream(pl1).filter("B"::equals).count() / 20;
                p3 = (double)Arrays.stream(pl1).filter("C"::equals).count() / 20;
                q1 = (double)Arrays.stream(pl2).filter("a"::equals).count() / 20;
                q2 = (double)Arrays.stream(pl2).filter("b"::equals).count() / 20;
                q3 = (double)Arrays.stream(pl2).filter("y"::equals).count() / 20;

                DecimalFormat df = new DecimalFormat("#.##");
                textView1result.setText("Максимин = " + maximin + "\n" +
                        "Минимакс = " + minimax + "\n" +
                        "Седловая точка – " + sp + "\n" +
                        "Вероятности первой стратегии = " + NumberWithoutZeroInTheEnd(p1) + ", " + NumberWithoutZeroInTheEnd(p2) + ", " + NumberWithoutZeroInTheEnd(p3) + "\n" +
                        "Вероятности второй стратегии = " + NumberWithoutZeroInTheEnd(q1) + ", " + NumberWithoutZeroInTheEnd(q2) + ", " + NumberWithoutZeroInTheEnd(q3) + "\n" +
                        "Цена игры = (" + df.format(pg1) + "; " + df.format(pg2) + ")");
            }
        }
    }

    public Integer MinElement(int e1, int e2, int e3)
    {
        int[] el = new int[] {e1, e2, e3};
        int elmin = e1;
        for (int i = 0; i < 3; i++)
        {
            if (elmin > el[i])
                elmin = el[i];
        }
        return elmin;
    }
    public Integer MaxElement(int e1, int e2, int e3)
    {
        int[] el = new int[] {e1, e2, e3};
        int elmax = e1;
        for (int i = 0; i < 3; i++)
        {
            if (elmax < el[i])
                elmax = el[i];
        }
        return  elmax;
    }

    public String NumberWithoutZeroInTheEnd(double numb)
    {
        String snumbwoz = String.valueOf(numb);

        if (String.valueOf(numb).indexOf('0', String.valueOf(numb).length() - 1) == String.valueOf(numb).length() - 1 && String.valueOf(numb).indexOf('.', String.valueOf(numb).length() - 2) == String.valueOf(numb).length() - 2)
            snumbwoz = String.valueOf(numb).substring(0, String.valueOf(numb).length() - 2);
        return snumbwoz;
    }
}