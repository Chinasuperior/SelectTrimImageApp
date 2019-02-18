package com.img.selecttrimimageapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

public class MainActivity extends CheckPermissionsActivity {
    ZoomImageView ivImg;

    //相册请求码
    private static final int ALBUM_REQUEST_CODE = 1;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 2;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE = 3;

    //调用照相机返回图片文件
    private File tempFile;
    private Uri tempFileUri;

    Bitmap image;


    //选择保存路径
    ChooseDirDialog mChooseDirDialog = null;
    public final int MESSAGE_RESULT_PATH_IS_OK = 10;


    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (MESSAGE_RESULT_PATH_IS_OK == msg.what) {
                String data = (String) msg.obj;
                String FileName = DateUtil.getNow(DateUtil.FORMAT_LINE_YMDHMS)+".jpg";
                Toast.makeText(MainActivity.this,""+image.getByteCount(),Toast.LENGTH_SHORT).show();
                if (MyFileUtils.saveScalePhoto(image,data+"/"+FileName)){
                    Toast.makeText(MainActivity.this,"保存成功："+data+"/"+FileName,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
                }
                mChooseDirDialog.dismiss();
            }
            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivImg = findViewById(R.id.iv_img);
        ivImg.setImageResource(R.drawable.imgpage);
    }

    /**
     * 去往相册
     * @param v
     */
    public void AlbumClick(View v){
        getPicFromAlbm();
    }

    /**
     * 去往相机
     * @param v
     */
    public void CameraClick(View v){
        getPicFromCamera();
    }


    /**
     * 从相机获取图片
     */
    private void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(MainActivity.this, getPackageName(), tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            Log.e("dasd", contentUri.toString());
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    /**
     * 从相册获取图片
     */
    private void getPicFromAlbm() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }


    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(uri, "image/*");
        } else {
            intent.setDataAndType(uri, "image/*");
        }

        intent.putExtra("crop", "true");
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);

//        intent.putExtra("outputX", 500);
//        intent.putExtra("outputY", 500);
        intent.putExtra("return-data", true);

        tempFileUri = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri);

        startActivityForResult(intent, CROP_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:   //调用相机后返回
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(MainActivity.this, getPackageName(), tempFile);
                        cropPhoto(contentUri);
                    } else {
                        cropPhoto(Uri.fromFile(tempFile));
                    }
                }
                break;
            case ALBUM_REQUEST_CODE:    //调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri);
                }
                break;
            case CROP_REQUEST_CODE:     //调用剪裁后返回

                try {
                    image= BitmapFactory.decodeStream(getContentResolver().openInputStream(tempFileUri));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if (image!=null){
                    //设置到ImageView上
                    ivImg.setImageBitmap(image);
                    saveFile();
                }else{
                    Toast.makeText(MainActivity.this,"裁剪失败",Toast.LENGTH_SHORT).show();
                }

                /*Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    image = bundle.getParcelable("data");
                    //设置到ImageView上
                    ivImg.setImageBitmap(image);
                    saveFile();

                }*/
                break;
        }
    }




    private void saveFile() {
        mChooseDirDialog = new ChooseDirDialog(this,
                ChooseDirDialog.TypeOpen, new String[]{"jpg"});
        mChooseDirDialog.show();
        new Thread(new TransferResultPathThread()).start();
    }



    public class TransferResultPathThread implements Runnable {

        @Override
        public void run() {
            String resultStr = null;
            try {
                while (!mChooseDirDialog.getBtnOkIsPressed()) {
                    resultStr = mChooseDirDialog.getResultPath();
                    Thread.sleep(100);
                }
                Message message = Message.obtain(mHandler,
                        MESSAGE_RESULT_PATH_IS_OK, resultStr);
                message.sendToTarget();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
