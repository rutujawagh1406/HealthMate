package com.example.healthmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages =
        {
                {"Paracetamol","Used to relieve mild to moderate pain and reduce fever","","","50"},
                {"Ibuprofen","A painkiller and anti-inflammatory for headaches, muscle pain, or fever.","","","50"},
                {"Amoxicillin","An antibiotic used to treat a wide range of bacterial infections.","","","50"},
                {"Cetirizine "," An antihistamine that helps relieve allergy symptoms like sneezing or itching.","","","50"},
                {"Metformin ","Helps control blood sugar levels in people with type 2 diabetes.","","","50"},
                {"Omeprazole ","Reduces stomach acid, commonly used for acidity or acid reflux.","","","50"},
                {"ORS (Oral Rehydration Salts)"," Replenishes fluids and electrolytes lost due to diarrhea or dehydration.","","","50"},
                {"Dolo 650 "," A popular tablet for fever, cold, and mild to moderate body aches.\n" +
                        "\n","","","50"},
        };
    private String[] package_details = {
            "Paracetamol is used to relieve mild to moderate pain and reduce fever.\n" +
                    "Commonly used for headaches, toothaches, and muscle aches.\n" +
                    "Available over-the-counter and generally well-tolerated.",

            "Ibuprofen is a nonsteroidal anti-inflammatory drug (NSAID).\n" +
                    "It helps reduce fever and relieve pain from various conditions.\n" +
                    "Often used for headaches, muscle aches, and arthritis.",

            "Amoxicillin is a penicillin-type antibiotic used to treat bacterial infections.\n" +
                    "Effective against infections like pneumonia, bronchitis, and urinary tract infections.\n" +
                    "Should be taken as prescribed to complete the full course.",

            "Cetirizine is an antihistamine that relieves allergy symptoms.\n" +
                    "Helps with sneezing, runny nose, and itchy or watery eyes.\n" +
                    "Commonly used for hay fever and hives.",

            "Metformin is used to control high blood sugar in type 2 diabetes.\n" +
                    "It works by helping to restore the body's proper response to insulin.\n" +
                    "Often prescribed as a first-line treatment for diabetes management.",

            "Omeprazole reduces stomach acid production.\n" +
                    "Used to treat conditions like acid reflux and ulcers.\n" +
                    "Provides relief from heartburn and promotes healing of the stomach lining.",

            "Oral Rehydration Salts (ORS) are used to prevent dehydration.\n" +
                    "They replenish fluids and electrolytes lost due to diarrhea or vomiting.\n" +
                    "Essential in treating mild to moderate dehydration, especially in children.",

            "Dolo 650 contains paracetamol and is used to relieve pain and reduce fever.\n" +
                    "Effective for conditions like headaches, body aches, and common colds.\n" +
                    "Should be used as directed to avoid potential liver damage."
    };


    HashMap<String, String >item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGoToCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.listViewBM);
        btnBack = findViewById(R.id.buttonBMBack);
        btnGoToCart = findViewById(R.id.buttonBMGoToCart);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList<>();
        for(int i = 0;i < packages.length;i++){
            HashMap<String, String> item = new HashMap<String, String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost : "+ packages[i][4] + "/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });
    }
}