package minatest.example.com.floatwindow;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * 适用所有提示框
 */
public class TipsDialog {
    private String tipStr;
    private String btn_text;
    private String title_text;
    private Activity activity;
    private AlertDialog dialog;
    public TipsDialog(String tipStr, String btn_text, String title_text, Activity activity){
        this.title_text=title_text;
        this.btn_text=btn_text;
        this.tipStr=tipStr;
        this.activity=activity;
    }
    public void getDialog(View.OnClickListener onClickListener){
        View dialogView = LayoutInflater.from(activity) .inflate(R.layout.dialog_tips,null);
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(activity).setView(dialogView);
        TextView title=dialogView.findViewById(R.id.dialog_title);
        TextView text=dialogView.findViewById(R.id.dialog_text);
        TextView ok=dialogView.findViewById(R.id.dialog_yes);
        title.setText(title_text);
        text.setText(tipStr);
        ok.setText(btn_text);
        this.dialog = customizeDialog.show();
        dialog.setCanceledOnTouchOutside(true);
        ok.setOnClickListener(onClickListener);
    }
    public void finishThis(){
        dialog.dismiss();
    }
}
