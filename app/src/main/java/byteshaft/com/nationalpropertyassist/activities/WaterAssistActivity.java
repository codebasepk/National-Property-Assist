package byteshaft.com.nationalpropertyassist.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import byteshaft.com.nationalpropertyassist.Helpers;
import byteshaft.com.nationalpropertyassist.R;

public class WaterAssistActivity extends Activity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {


    private RadioGroup radioGroup;
    private EditText details;
    private Button submitButton;
    private String mRadioText;
    private String mReceverEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_water_assist);
        mReceverEmail = getString(R.string.email_string);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        details = (EditText) findViewById(R.id.water_assist_et);
        submitButton = (Button) findViewById(R.id.submit);
        radioGroup.setOnCheckedChangeListener(this);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.repair_to_leaking:
                mRadioText = "Repair to leaking Supply Pipe";
                break;
            case R.id.renewal_pipe:
                mRadioText = "Renewal of Supply Pipe";
                break;
            case R.id.new_installation:
                mRadioText = "New Installation";
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                String finalMessage = Helpers.getStringFromSharedPreferences("basic") +
                        details.getText().toString();
                System.out.println(mRadioText + finalMessage);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {mReceverEmail});
                intent.putExtra(Intent.EXTRA_SUBJECT, mRadioText);
                intent.putExtra(Intent.EXTRA_TEXT, finalMessage);
                startActivity(Intent.createChooser(intent, "Send Email"));
                break;
        }
    }
}