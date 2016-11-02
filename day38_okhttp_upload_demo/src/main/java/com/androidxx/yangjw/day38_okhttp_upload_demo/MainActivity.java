package com.androidxx.yangjw.day38_okhttp_upload_demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * OkHttp网络请求之上传
 * 流程：
 * 1、打开图片选择器，选择需要上传的图片
 * 2、将选择的图片显示到界面上
 * 3、点击上传按钮完成上传功能
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "androidxx";
    @BindView(R.id.upload_show_ll)
    LinearLayout showPicLL;
    @BindView(R.id.upload_show_iv)
    ImageView mShowImage;
    private byte[] bytes;
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initOkHttp();
    }

    private void initOkHttp() {
        okHttpClient = new OkHttpClient();
    }

    @OnClick(value = {R.id.upload_choose_pic_btn,R.id.upload_submit_btn})
    public void choosePic(View view) {
        switch (view.getId()) {
            case R.id.upload_choose_pic_btn:
                choosePic();
                break;
            case R.id.upload_submit_btn:
                upload();
                break;
        }
    }

    /**
     * 打开图片选择器
     */
    private void choosePic() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    /**
     * 接收startActivityForResult返回的数据
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //接收到选择的图片地址，地址的类型是一个ContentProvider类型的地址。
        Uri uri = data.getData();//一个ContentProvider的地址
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
//            BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer,0,len);
            }
            outputStream.flush();
            bytes = outputStream.toByteArray();
            inputStream.close();
            outputStream.close();

            //将图片的字节数组转换成Bitmap对象
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            mShowImage.setImageBitmap(bitmap);
            showPicLL.setVisibility(View.VISIBLE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //.......
        }
    }

    /**
     * 上传图片
     * 一个特殊的Post请求
     */
    private void upload() {
        //创建一个RequestBody参数，此参数中包含需要上传的图片
        //参数1：MediaType类型的值，主要是说明上传文件的格式（类型）
        //参数2：需要上传的文件的字节数组
        MediaType mediaType = MediaType.parse("image/*");//指定媒体类型
        RequestBody requestBody = RequestBody.create(mediaType, bytes);
        MultipartBody multipartBody = new MultipartBody.Builder()
                //参数1：key的名称
                //参数2：上传到服务器上的图片的名称
                //参数3：RequestBody对象
                .addFormDataPart("upload", "ccc.png", requestBody)
                .addFormDataPart("upload", "abc.png", requestBody)
                .build();
        Request request = new Request.Builder()
                .post(multipartBody)
                .url("http://192.168.52.153:8080/androidxx/upload.do")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i(TAG, "onResponse: " + result);
            }
        });
    }
}
