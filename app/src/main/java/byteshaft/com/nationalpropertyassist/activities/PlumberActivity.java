package byteshaft.com.nationalpropertyassist.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import byteshaft.com.nationalpropertyassist.R;
import byteshaft.com.nationalpropertyassist.utils.ServicesTask;

public class PlumberActivity extends Activity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {


    private RadioGroup radioGroup;
    private EditText details;
    private Button submitButton;
    private String mRadioText;

    private RadioButton repairPlumbing;
    private RadioButton renewal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_plumber_assist);
        repairPlumbing = (RadioButton) findViewById(R.id.repair_plumbing);
        renewal = (RadioButton) findViewById(R.id.renewal_of_plumbing_installation);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        details = (EditText) findViewById(R.id.plumber_assist_et);
        submitButton = (Button) findViewById(R.id.submit);
        radioGroup.setOnCheckedChangeListener(this);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.repair_plumbing:
                mRadioText = "Repair to leaking plumbing Installation";
                break;
            case R.id.renewal_of_plumbing_installation:
                mRadioText = "Renewal of plumbing Installation";
                break;
        }

        System.out.println(mRadioText);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                String description = details.getText().toString();
                new ServicesTask(PlumberActivity.this, description, mRadioText).execute();
                break;
        }
    }
}
