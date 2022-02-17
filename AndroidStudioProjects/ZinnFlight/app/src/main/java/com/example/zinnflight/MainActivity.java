package com.example.zinnflight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView contactsRecView;
    private Button sackButton;
    private TextView txtSuck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sackButton = findViewById(R.id.buttonSuck);
        txtSuck = findViewById(R.id.txtSuck);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.avenir_next_bold);
        sackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSuck.setTypeface(typeface);
                Toast.makeText(MainActivity.this, "You are a super d duper sack", Toast.LENGTH_SHORT).show();
            }
        });

        contactsRecView = findViewById(R.id.contactsRecView);

        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Jacob Zinn", "Suck@gmail.com", "https://avatarfiles.alphacoders.com/651/thumb-65159.jpg"));
        contacts.add(new Contact("Levi Harper", "Sucksuck@gmail.com", "https://avatarfiles.alphacoders.com/651/thumb-65159.jpg"));
        contacts.add(new Contact("Tagg Barton", "Tagg@gmail.com","https://avatarfiles.alphacoders.com/101/thumb-101498.jpg"));
        contacts.add(new Contact("Jesse Zinn", "Jesse@gmail.com", "https://avatarfiles.alphacoders.com/108/thumb-108304.jpg"));
        contacts.add(new Contact("Carter Barton", "cartsbarts@gmail.com", "https://avatarfiles.alphacoders.com/125/thumb-125098.png" ));

        ContactsRecViewAdapter adapter = new ContactsRecViewAdapter(this);
        adapter.setContacts(contacts);

        contactsRecView.setAdapter(adapter);
        contactsRecView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}