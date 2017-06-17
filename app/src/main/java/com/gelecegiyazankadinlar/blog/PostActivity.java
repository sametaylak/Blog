package com.gelecegiyazankadinlar.blog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostActivity extends AppCompatActivity {

    @BindView(R.id.post_title) EditText titleEditText;
    @BindView(R.id.post_description) EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ButterKnife.bind(PostActivity.this);
    }

    @OnClick(R.id.submit_post)
    public void submitPost() {
        String baslik = titleEditText.getText().toString();
        String aciklama = descriptionEditText.getText().toString();

        Log.d("POST", "Başlık: " + baslik + "\nAçıklama: " + aciklama);
    }
}
