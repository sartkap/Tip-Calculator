package com.example.mytipcalculator;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    final String TAG1 = "Activity 1";
    @BindView(R.id.etBillAmount)
    EditText etBillAmount;
    @BindView(R.id.tipPercent)
        TextView tipPercent;
    @BindView(R.id.tipTotal)
    TextView tipTotal;
    @BindView(R.id.billTotalAmount)
    TextView billTotalAmount;

    float percentage = 0;
    float totalBill = 0;
    float totalTip = 0;
    float finalBill = totalBill;
    float BAD_SERVICE_TIP = 5;
    float REGULAR_SERVICE_TIP = 10;
    float GOOD_SERVICE_TIP = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String initialValue = "";
//        String initVal = "";
//        String initialValue2 = "";
        if (savedInstanceState != null){
            initialValue = savedInstanceState.getString("tipPercent");
            char x = initialValue.charAt(0);
            if(x=='5')percentage = 5;
            else if(x=='1')percentage=10;
            else if(x=='2')percentage=20;
//            initVal = savedInstanceState.getString("tipTotal");
//            initialValue2 = savedInstanceState.getString("tipPercent");
        }
        else{
            percentage=0;
        }
        //percentage = (Float.valueOf(initialValue)).floatValue();
        //setValues();
        Log.d(TAG1, "STATUS: ON CREATE");
        changeValues();
//        billTotalAmount.setText(initialValue);
//        tipTotal.setText(initVal);
//        tipPercent.setText(initialValue2);
    }

    private void setValues() {
        tipPercent.setText(getString(R.string.main_msg_tippercent, percentage));
        tipTotal.setText(getString(R.string.main_msg_tiptotal, totalTip));
        billTotalAmount.setText(getString(R.string.main_msg_billtotalresult, finalBill));
    }


    protected void onStart() {
        super.onStart();
        Log.d(TAG1, "STATUS: ON START");
    }

    protected void onResume() {
        super.onResume();
        Log.d(TAG1, "STATUS: ON RESUME");
    }

    protected void onPause() {
        super.onPause();
        Log.d(TAG1, "STATUS: ON PAUSE");
    }

    protected void onStop() {
        super.onStop();
        Log.d(TAG1, "STATUS: ON STOP");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG1, "STATUS: ON DESTROY");
    }

    protected void onRestart() {
        super.onRestart();
        Log.d(TAG1, "STATUS: ON RESTART");
    }

    @OnClick({R.id.service1, R.id.service2, R.id.service3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.service1:
                percentage = BAD_SERVICE_TIP;
                changeValues();
                break;
            case R.id.service2:
                percentage = REGULAR_SERVICE_TIP;
                changeValues();
                break;

            case R.id.service3:
                percentage = GOOD_SERVICE_TIP;
                changeValues();
                break;

        }
    }

    @OnTextChanged(R.id.etBillAmount)
    public void onTextChanged() {
        if(!etBillAmount.getText().toString().equals("") && !etBillAmount.getText().toString().equals(".")){
            totalBill = Float.valueOf(etBillAmount.getText().toString());
//            totalTip = (percentage * totalBill)/100;
//            finalBill = totalBill + totalTip;
            changeValues();
//            setValues();
        }
    }

    private void changeValues() {
        totalTip = (percentage * totalBill)/100;
        finalBill = totalBill + totalTip;
        setValues();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putString("tipPercent",tipPercent.getText().toString());
        super.onSaveInstanceState(outState);
    }
}
