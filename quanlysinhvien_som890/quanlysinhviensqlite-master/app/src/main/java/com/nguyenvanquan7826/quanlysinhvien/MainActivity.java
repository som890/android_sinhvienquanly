package com.nguyenvanquan7826.quanlysinhvien;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    private EditText editName, editBirth, editSchool;
    private RadioButton radMale, radFeMale;
    private CheckBox cbSport, cbTravel, cbReadBook;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        editName = findViewById(R.id.editName);
        editBirth = findViewById(R.id.editBirth);
        editSchool = findViewById(R.id.editSchool);
        radMale = findViewById(R.id.radMale);
        radFeMale = findViewById(R.id.radFeMale);
        cbSport = findViewById(R.id.cbSport);
        cbTravel = findViewById(R.id.cbTravel);
        cbReadBook = findViewById(R.id.cbReadBook);

        findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetView();
            }
        });
        findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudent();
            }
        });
    }

    private void initData() {
        db = openOrCreateDatabase("qlsv.db", MODE_PRIVATE, null);

        String sql = "CREATE table IF NOT exists tbsv (id integer primary key autoincrement, " +
                "name text," +
                "birth text," +
                "school text," +
                "sex integer," +
                "favorite text)";
        db.execSQL(sql);
    }

    private void saveStudent() {
        String name = editName.getText().toString();
        String birth = editBirth.getText().toString();
        String school = editSchool.getText().toString();
        int sex = 0;
        if (radFeMale.isChecked()) {
            sex = 1;
        }
        String favorite = "";
        if (cbSport.isChecked()) {
            favorite += "," + cbSport.getText().toString();
        }
        if (cbTravel.isChecked()) {
            favorite += "," + cbTravel.getText().toString();
        }
        if (cbReadBook.isChecked()) {
            favorite += "," + cbReadBook.getText().toString();
        }

        String sql = "INSERT INTO tbsv (name, birth, school, sex, favorite) VALUES(" +
                "'" + name + "'," +
                "'" + birth + "'," +
                "'" + school + "'," +
                "'" + sex + "'," +
                "'" + favorite + "'" + ")";
        db.execSQL(sql);
    }

    private void resetView() {
        editName.setText("");
        editBirth.setText("");
        editSchool.setText("");

        radMale.setChecked(true);

        cbSport.setChecked(false);
        cbTravel.setChecked(false);
        cbReadBook.setChecked(false);
    }

    private void deleteByName(String name){
        String sql = "DELETE FROM tbsv WHERE name = '" + name + "'";
        db.execSQL(sql);
    }
}