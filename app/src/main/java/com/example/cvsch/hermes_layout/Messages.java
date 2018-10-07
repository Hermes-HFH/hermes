package com.example.cvsch.hermes_layout;

import android.app.Activity;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.view.*;

import java.util.ArrayList;
import java.util.List;



public class Messages extends AppCompatActivity {

    ArrayList<String> MessageList;


    public void UpdateMessageList()
    {
        final TextView messageLog = (TextView) findViewById(R.id.messageLog);
        messageLog.setText("");
        if(MessageList.size() > 11){
            for(int i = MessageList.size() - 11; i <= MessageList.size() - 1; i++)
            {
                messageLog.append(MessageList.get(i) + "\n");
            }
        }else {
            for(String thismessage : MessageList)
            {
                messageLog.append(thismessage + "\n");
            }
        }
    }
    //private RecyclerView mRecyclerView;
   //private RecyclerView.Adapter mAdapter;
    //private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        MessageList = new ArrayList<String>();
        //mRecyclerView = (RecyclerView) findViewById(R.id.messageLog);
        //mRecyclerView.setHasFixedSize(true);
        //mLayoutManager = new LinearLayoutManager(this);
        //mRecyclerView.setLayoutManager(mLayoutManager);
        final Button sendbutton = (Button)findViewById(R.id.sendMessages);

        //final TextView messageLog = (TextView) findViewById(R.id.messageLog);
        sendbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final TextView newMessage = (TextView) findViewById(R.id.newMessage);
                MessageList.add(newMessage.getText().toString());
                Log.e("Test", MessageList.toString());
                UpdateMessageList();
            }


        });

    }
}