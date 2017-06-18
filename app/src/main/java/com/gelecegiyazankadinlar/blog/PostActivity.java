package com.gelecegiyazankadinlar.blog;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST = 1;

    @BindView(R.id.post_title) EditText titleEditText;
    @BindView(R.id.post_description) EditText descriptionEditText;
    @BindView(R.id.post_image) ImageButton postImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ButterKnife.bind(PostActivity.this);
    }

    @OnClick(R.id.submit_post)
    public void submitPost() {

    }

    @OnClick(R.id.post_image)
    public void selectPostImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            postImageButton.setImageURI(imageUri);
        }
    }
}
