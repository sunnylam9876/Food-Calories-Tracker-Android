package com.example.nutritiontracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nutritiontracking.db.PersonalDatabaseHelper;
import com.example.nutritiontracking.db.entity.Personal;

public class Setting extends AppCompatActivity {

    RadioGroup rbGroup;
    RadioButton rbFemale, rbMale, selectedRadioButton;
    EditText etWeight, etHeight, etCalBudget;
    Button btnLink, btnSave, btnDelete;

    Personal personal;

    private PersonalDatabaseHelper db_personal;
    Boolean isDataAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        rbGroup = findViewById(R.id.rb_group);
        rbFemale = findViewById(R.id.rb_female);
        rbMale = findViewById(R.id.rb_male);
        etWeight = findViewById(R.id.et_weight);
        etHeight = findViewById(R.id.et_height);
        etCalBudget = findViewById(R.id.et_cal_budget);
        btnLink = findViewById(R.id.btn_link);
        btnSave = findViewById(R.id.btn_personal_save);
        btnDelete = findViewById(R.id.btn_personal_delete);

        db_personal = new PersonalDatabaseHelper(this);

        getPersonalData();

        btnDelete.setBackgroundColor(Color.RED);

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.calculator.net/calorie-calculator.html"));
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(Setting.this, "No application can handle this request."
                            + " Please install a web browser or check your URL.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        //save data
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rbGroup.getCheckedRadioButtonId();
                //selectedRadioButton = findViewById(selectedId);
                if ((selectedId == -1) ||
                        etWeight.getText().toString().trim().isEmpty() ||
                        etHeight.getText().toString().trim().isEmpty() ||
                        etCalBudget.getText().toString().trim().isEmpty()
                ) {
                    Toast.makeText(getApplicationContext(), "Please input the data", Toast.LENGTH_SHORT).show();
                } else {
                    savePersonalData(v);
                    recreate();
                }
            }
        });

        //Delete data
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int return_value = db_personal.deletePersonal("test");
                if (return_value > 0) {
                    Toast.makeText(v.getContext(), "Data deleted", Toast.LENGTH_LONG).show();
                    rbFemale.setChecked(false);
                    rbMale.setChecked(false);
                    etWeight.setText("");
                    etHeight.setText("");
                    etCalBudget.setText("");
                    btnSave.setText("Save");
                } else {
                    Toast.makeText(v.getContext(), "Data deletion failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void getPersonalData() {
        personal = db_personal.getPersonal("test");
        if (personal == null) { //if no data return from database
            isDataAvailable = false;
        } else {    //if has data return from database, load it the the screen
            isDataAvailable = true;
            if (personal.getGender() == "Female") {
                rbFemale.setChecked(true);
            } else {
                rbMale.setChecked(true);
            }
            etWeight.setText(Double.toString(personal.getWeight()));
            etHeight.setText(Double.toString(personal.getHeight()));
            etCalBudget.setText(Double.toString(personal.getCal_budget()));
            btnSave.setText("Update");
        }
    }

    public void savePersonalData(View v) {
        int selectedId = rbGroup.getCheckedRadioButtonId();
        selectedRadioButton = findViewById(selectedId);

        if (isDataAvailable == false) { //if no data return from database, insert the data to database
            long id = db_personal.insertPersonal("test", selectedRadioButton.getText().toString(),
                    Double.parseDouble(etWeight.getText().toString()),
                    Double.parseDouble(etHeight.getText().toString()),
                    Double.parseDouble(etCalBudget.getText().toString()));

            if (id == -1) {
                Toast.makeText(v.getContext(), "Failed to save data to database", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(v.getContext(), "Data saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        } else {    //if has data return from database, update the data to database
            Personal updatedPersonal = new Personal(1, "test",
                    selectedRadioButton.getText().toString(),
                    Double.parseDouble(etWeight.getText().toString()),
                    Double.parseDouble(etHeight.getText().toString()),
                    Double.parseDouble(etCalBudget.getText().toString()));

            int id = db_personal.updatePersonal(updatedPersonal);
            if (id == -1) {
                Toast.makeText(v.getContext(), "Failed to updated data to database", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(v.getContext(), "Data updated", Toast.LENGTH_LONG).show();
            }
        }
    }


}