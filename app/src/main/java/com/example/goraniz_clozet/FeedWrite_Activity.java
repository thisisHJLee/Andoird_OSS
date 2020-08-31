package com.example.goraniz_clozet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;

public class FeedWrite_Activity extends AppCompatActivity {

    int PICK_PHOTO_FROM_ALBUM = 0;
    private ImageView imageView;
    private static int REQUEST_CODE = 777;
    Bitmap bitmap;
    String uri;
    String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_write);

        // userID 불러오기
        Intent send_intent = getIntent();
        final String userID = send_intent.getStringExtra("userID");

        // 이미지 추가하기
        imageView = (ImageView) findViewById(R.id.feedWrite_Image);
        imageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PICK_PHOTO_FROM_ALBUM);
            }
        });

        // 옷 정보 태그하기
        Button TagClothing = (Button) findViewById(R.id.btn_TagClothing);
        TagClothing.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ImageTag_Activity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        // 업로드 버튼 클릭 시 서버에 업로드 하기
        Button btn_upload = (Button)findViewById(R.id.feedWrite_btn_upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = (EditText)findViewById(R.id.feedWrite_editExplain);
                final String description = editText.getText().toString();

                // 이미지 전송 준비, 이미지를 String 으로 변환
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // 통신상태의 성공 여부 반환함
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = (boolean) jsonObject.get("success");

                            // 게시글 올리기 성공 경우
                            if (success) {
                                Toast.makeText(getApplicationContext(), "게시글이 업로드 되었습니다!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FeedWrite_Activity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            // 게시글 올리기 실패 경우
                            else {
                                Toast.makeText(getApplicationContext(), "게시글 업로드에 실패했습니다. 나중에 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // 서버로 volley를 이용해 요청을 함
                FeedWrite_Request feedWrite_request = new FeedWrite_Request(userID, description, tag, imageString, responseListener);
                RequestQueue queue = Volley.newRequestQueue(FeedWrite_Activity.this);
                queue.add(feedWrite_request);
            }
        });

        // 취소 버튼 클릭 시 이전 화면으로 돌아가기
        Button btnCancel = (Button)findViewById(R.id.cancel_button);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onActivityResult(int request_code, int result_code, Intent data){
        super.onActivityResult(request_code, result_code, data);

        // 이미지 선택 후, 이미지 선택 창의 기본 이미지 대신 선택한 이미지의 사진으로 변경
        if (request_code == PICK_PHOTO_FROM_ALBUM && result_code == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            imageView.setImageURI(selectedImageUri);
            uri = String.valueOf(selectedImageUri);

            try{
                //bitmap에 선택한 이미지를 저장
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        // 태그 가져오기
        if (result_code == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
        }
        if (request_code == REQUEST_CODE){
            String resultText = null;
            if (data != null) {
                resultText = data.getStringExtra("tag");
            }
            else{
                resultText = data.getStringExtra("tag");
            }
            EditText tag_info = (EditText) findViewById(R.id.tag_info);
            tag_info.setText(resultText);
            tag = resultText;
        }

    }

}