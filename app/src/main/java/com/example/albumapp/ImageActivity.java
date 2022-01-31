package com.example.albumapp;

import static com.example.albumapp.AlbumActivity.EXTRA_TITLE;
import static com.example.albumapp.AlbumActivity.EXTRA_URL;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String title = intent.getStringExtra(EXTRA_TITLE);
        ImageView imageView = findViewById(R.id.iv2);
        TextView textView = findViewById(R.id.txtTitle2);

        textView.setText(title);
        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);

    }
}