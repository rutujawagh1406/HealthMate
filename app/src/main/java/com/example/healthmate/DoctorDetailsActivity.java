package com.example.healthmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Dr. Abhishek", "Hospital Address : Chembur", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Agarwal", "Hospital Address : Dadar", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Deshmukh", "Hospital Address : Matunga", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Ajinkya Shinde", "Hospital Address : Kurla", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Kaivalya Kharkhanis", "Hospital Address : Sion", "Experience :  10yrs","Mobile No : 1234567890","600"}
            };

    private String [][] doctor_details2 =
            {
                    {"Doctor Name : Dr. Abhishek", "Hospital Address : Chembur", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Agarwal", "Hospital Address : Dadar", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Deshmukh", "Hospital Address : Matunga", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Ajinkya Shinde", "Hospital Address : Kurla", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Kaivalya Kharkhanis", "Hospital Address : Sion", "Experience :  10yrs","Mobile No : 1234567890","600"}
            };

    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Dr. Abhishek", "Hospital Address : Chembur", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Agarwal", "Hospital Address : Dadar", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Deshmukh", "Hospital Address : Matunga", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Ajinkya Shinde", "Hospital Address : Kurla", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Kaivalya Kharkhanis", "Hospital Address : Sion", "Experience :  10yrs","Mobile No : 1234567890","600"}
            };

    private String [][] doctor_details4 =
            {
                    {"Doctor Name : Dr. Abhishek", "Hospital Address : Chembur", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Agarwal", "Hospital Address : Dadar", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Deshmukh", "Hospital Address : Matunga", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Ajinkya Shinde", "Hospital Address : Kurla", "Experience :  10yrs","Mobile No : 1234567890","600"},
                    {"Doctor Name : Dr. Kaivalya Kharkhanis", "Hospital Address : Sion", "Experience :  10yrs","Mobile No : 1234567890","600"}
            };

    private String [][] doctor_details5 =
    {
            {"Doctor Name : Dr. Abhishek", "Hospital Address : Chembur", "Experience :  10yrs","Mobile No : 1234567890","600"},
            {"Doctor Name : Dr. Agarwal", "Hospital Address : Dadar", "Experience :  10yrs","Mobile No : 1234567890","600"},
            {"Doctor Name : Dr. Deshmukh", "Hospital Address : Matunga", "Experience :  10yrs","Mobile No : 1234567890","600"},
            {"Doctor Name : Dr. Ajinkya Shinde", "Hospital Address : Kurla", "Experience :  10yrs","Mobile No : 1234567890","600"},
            {"Doctor Name : Dr. Kaivalya Kharkhanis", "Hospital Address : Sion", "Experience :  10yrs","Mobile No : 1234567890","600"}
    };

    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Family Physicians") == 0)
            doctor_details = doctor_details1;
        else if (title.compareTo("Dietician") == 0)
            doctor_details = doctor_details2;
        else if (title.compareTo("Dentist") == 0)
            doctor_details = doctor_details3;
        else if (title.compareTo("Surgeon") == 0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });
        list = new ArrayList();
        for (int i = 0; i< doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fees : "+doctor_details[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView lst = findViewById(R.id.ListViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2" , doctor_details[i][0]);
                it.putExtra("text3" , doctor_details[i][1]);
                it.putExtra("text4" , doctor_details[i][3]);
                it.putExtra("text5" , doctor_details[i][4]);
                startActivity(it);
      ;      }
        });
    }
}