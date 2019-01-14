package com.example.android.myunittesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView{

    private EditText edtWidth, edtHeight, edtLength;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = (EditText) findViewById(R.id.edt_width);
        edtHeight = (EditText) findViewById(R.id.edt_height);
        edtLength = (EditText) findViewById(R.id.edt_length);
        Button btnCalculate = (Button) findViewById(R.id.btn_calculate);
        tvResult = (TextView) findViewById(R.id.tv_result);

        final MainPresenter presenter = new MainPresenter(this);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String length = edtLength.getText().toString();
                String width = edtWidth.getText().toString();
                String height = edtHeight.getText().toString();

                boolean isEmptyFields = false;

                if(TextUtils.isEmpty(length)){
                    isEmptyFields = true;
                    edtLength.setError("Field ini tidak boleh kosong");
                }

                if(TextUtils.isEmpty(width)){
                    isEmptyFields = true;
                    edtWidth.setError("Field ini tidak boleh kosong");
                }

                if(TextUtils.isEmpty(height)){
                    isEmptyFields = true;
                    edtHeight.setError("Field ini tidak boleh kosong");
                }

                if(!isEmptyFields){
                    double l = Double.parseDouble(length);
                    double w = Double.parseDouble(width);
                    double h = Double.parseDouble(height);

                    presenter.hitungVolume(l, w, h);
                }


            }
        });
    }

    @Override
    public void tampilVolume(MainModel model) {
        tvResult.setText(String.valueOf(model.getVolume()));
    }
}
