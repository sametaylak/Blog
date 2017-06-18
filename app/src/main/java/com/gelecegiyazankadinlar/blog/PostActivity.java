package com.gelecegiyazankadinlar.blog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST = 1;
    private Uri imageUri;

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
        final ProgressDialog progressDialog = new ProgressDialog(PostActivity.this);
        progressDialog.setMessage("Lütfen bekleyin...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StorageReference postImageRef = FirebaseStorage.getInstance().getReference("blog_images");
        postImageRef.child(imageUri.getLastPathSegment()).putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                String postImageUrl = taskSnapshot.getDownloadUrl().toString();
                String postTitle = titleEditText.getText().toString();
                String postDescription = descriptionEditText.getText().toString();
                Post post = new Post(postTitle, postDescription, postImageUrl);
                DatabaseReference blogPostRef = FirebaseDatabase.getInstance().getReference("blog_posts");
                blogPostRef.push().setValue(post);
            }
        });
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
            imageUri = data.getData();
            postImageButton.setImageURI(imageUri);
        }
    }
}
