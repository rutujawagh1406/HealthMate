package com.example.healthmate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ChatbotActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText userInput;
    private Button sendBtn;
    private ChatAdapter adapter;
    private List<ChatMessage> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        recyclerView = findViewById(R.id.recyclerView);
        userInput = findViewById(R.id.editTextMessage);
        sendBtn = findViewById(R.id.buttonSend);
        messages = new ArrayList<>();

        adapter = new ChatAdapter(messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = userInput.getText().toString().trim();
                if (!input.isEmpty()) {
                    messages.add(new ChatMessage(input, true));
                    adapter.notifyItemInserted(messages.size() - 1);
                    recyclerView.scrollToPosition(messages.size() - 1);
                    userInput.setText("");
                    String response = getBotResponse(input);
                    messages.add(new ChatMessage(response, false));
                    adapter.notifyItemInserted(messages.size() - 1);
                    recyclerView.scrollToPosition(messages.size() - 1);
                }
            }
        });
    }

    private String getBotResponse(String input) {
        switch (input.toLowerCase()) {
            case "hello": return "Hi there! How can I assist you today?";
            case "who are you": return "I’m your HealthMate assistant.";
            case "book lab test": return "You can go to Home > Lab Tests to book a test.";
            default: return "Sorry, I didn't get that. Please try again.";
        }
    }
}