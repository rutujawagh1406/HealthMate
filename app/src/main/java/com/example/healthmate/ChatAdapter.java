package com.example.healthmate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<ChatMessage> chatMessages;

    public ChatAdapter(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatMessage msg = chatMessages.get(position);
        if (msg.isUser()) {
            holder.userText.setText(msg.getMessage());
            holder.userText.setVisibility(View.VISIBLE);
            holder.botText.setVisibility(View.GONE);
        } else {
            holder.botText.setText(msg.getMessage());
            holder.botText.setVisibility(View.VISIBLE);
            holder.userText.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView userText, botText;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            userText = itemView.findViewById(R.id.textUser);
            botText = itemView.findViewById(R.id.textBot);
        }
    }
}