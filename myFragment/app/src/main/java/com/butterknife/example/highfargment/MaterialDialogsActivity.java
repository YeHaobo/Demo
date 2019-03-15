package com.butterknife.example.highfargment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * dialog库
 */
public class MaterialDialogsActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.dialog_one)
    TextView dialogOne;
    @BindView(R.id.dialog_two)
    TextView dialogTwo;
    @BindView(R.id.dialog_three)
    TextView dialogThree;
    @BindView(R.id.dialog_four)
    TextView dialogFour;
    @BindView(R.id.dialog_five)
    TextView dialogFive;
    @BindView(R.id.dialog_six)
    TextView dialogSix;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_materialdialog);
        ButterKnife.bind(this);
        dialogOne.setOnClickListener(this);
        dialogTwo.setOnClickListener(this);
        dialogThree.setOnClickListener(this);
        dialogFour.setOnClickListener(this);
        dialogFive.setOnClickListener(this);
        dialogSix.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_one:
                dialog1();
                break;
            case R.id.dialog_two:
                dialog2();
                break;
            case R.id.dialog_three:
                dialog3();
                break;
            case R.id.dialog_four:
                dialog4();
                break;
            case R.id.dialog_five:
                dialog5();
                break;
            case R.id.dialog_six:
                startActivity(new Intent(MaterialDialogsActivity.this,SwipeLayoutActivity.class));
                break;
            default:
                break;
        }
    }

    //普通对话框
    private void dialog1() {
        //采用链式编程颜色等参数都可修改包括选择器
        new MaterialDialog.Builder(this)
                .theme(Theme.LIGHT)
                .icon(getResources().getDrawable(R.mipmap.ic_launcher_round))
                .title("标题")
                .content("内容")
                .positiveText("确认")
                .negativeText("取消")
                //确认按钮的回调
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(MaterialDialogsActivity.this, "点击了确认", Toast.LENGTH_SHORT).show();
                    }
                })
                //取消按钮的回调
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(MaterialDialogsActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    //CheckBox的对话框
    private void dialog2() {
        new MaterialDialog.Builder(this)
                .titleColor(getResources().getColor(R.color.text_black))
                .backgroundColor(getResources().getColor(R.color.white))
                .contentColor(getResources().getColor(R.color.text_black))
                .title("标题")
                .content("内容")
                .positiveText("确认")
                .checkBoxPromptRes(R.string.dont_ask_again, false, null)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        Toast.makeText(MaterialDialogsActivity.this, dialog.isPromptCheckBoxChecked() + "", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    //自定义对话框
    private void dialog3() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        final MaterialDialog dialog = new MaterialDialog.Builder(this)
                .titleColor(getResources().getColor(R.color.text_black))
                .backgroundColor(getResources().getColor(R.color.white))
                .title("Title")
                .customView(contentView, true)
                .positiveText("CHOOSE")
                .build();
        TextView textView = contentView.findViewById(R.id.clickMe);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MaterialDialogsActivity.this, "点击了clickMe", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //进度条普通对话框
    private void dialog4() {
        new MaterialDialog.Builder(this)
                .theme(Theme.LIGHT)
                .title("Progress")
                .content("Please Wait...")
                .progress(true, 0)
                .show();
    }

    //确定的进度条对话框
    private void dialog5() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .theme(Theme.LIGHT)
                .title("Progress")
                .content("please wait...")
                .contentGravity(GravityEnum.CENTER)
                .progress(false, 100, true)
                .show();
        dialog.incrementProgress(50);//表示加上50
    }

}
