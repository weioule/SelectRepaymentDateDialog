package com.example.weioule.selectrepaymentdatedialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author weioule
 * @Create on 2018/6/1.
 * 滚动选择器
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 还款日 大于28号则为1号
     */
    private int mRepaymentDay;
    private TextView mRepaymentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRepaymentDate = (TextView) findViewById(R.id.repayment_date_txt);
        mRepaymentDate.setText(getDate(getToDay()));
        mRepaymentDay = getToDay() > 28 ? 1 : getToDay();
        findViewById(R.id.repayment_date_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.dialog_fullscreen);
        dialog.setContentView(R.layout.repayment_day_dialog_layout);
        dialog.setCanceledOnTouchOutside(true);
        TextView closeBtn = (TextView) dialog.findViewById(R.id.closeBtn);
        TextView okBtn = (TextView) dialog.findViewById(R.id.okBtn);

        PickerView minute_pv = (PickerView) dialog.findViewById(R.id.minute_pv);
        List<String> data = new ArrayList<>();
        for (int i = 1; i < 29; i++) {
            data.add(String.format(getString(R.string.day_every_month), i));
        }
        minute_pv.setData(data);
        final int[] selectedDay = new int[1];

        if (mRepaymentDay <= 0) {
            minute_pv.setSelected(getToDay() > 28 ? 0 : getToDay() - 1);
        } else {
            minute_pv.setSelected(mRepaymentDay - 1);
        }

        minute_pv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(int date) {
                selectedDay[0] = date;
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mRepaymentDate.setText(((TextView) v).getText());
                mRepaymentDay = 0;
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (selectedDay[0] == 0) {
                    if (mRepaymentDay == 0) {
                        int day = getToDay() > 28 ? 1 : getToDay();
                        mRepaymentDate.setText(getDate(day));
                        mRepaymentDay = day;
                    }
                } else {
                    mRepaymentDate.setText(getDate(selectedDay[0]));
                    mRepaymentDay = selectedDay[0];
                }
            }
        });
        dialog.show();
    }


    public String getDate(int date) {
        if (date < 29) {
            return String.format(getString(R.string.day_every_month), date);
        } else {
            return String.format(getString(R.string.day_every_month), 1);
        }
    }

    private int getToDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }
}
