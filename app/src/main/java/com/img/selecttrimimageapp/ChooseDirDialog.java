package com.img.selecttrimimageapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：SpeedMonitorApp
 * 类描述：
 * 创建人：SpeedMonitorApp
 * 创建时间：2018/11/16 0016 12:18
 */

public class ChooseDirDialog {


    private Dialog mDialog; // 应采用成员变量形式

    private Context mContext;


    private ListView mListView;

    ArrayAdapter<String> mAdapter;

    ArrayList<String> mArrayList = new ArrayList<String>(); //不要用ArrayList，而用ArrayList<T>更安全


    private String mPath;  //选择的目录

    private boolean btnOkIsPressed = false;  //初始化时，btnOk没有按下，所以为false

    public boolean

    getBtnOkIsPressed() {

        return btnOkIsPressed;

    }


    private TextView titleTextView;

    private EditText titleEditText;

    private Button btnHome, btnBack, btnOk;



    private int mType = 1;

    private String[] mFileType = null;


    public final static int TypeOpen = 1;

    public final static int TypeSave = 2;


    public ChooseDirDialog(Context context, int type, String[] fileType) {

        this.mContext = context;

        this.mType = type;

        this.mFileType = fileType;


        mDialog = new Dialog(mContext,R.style.time_dialog);  //在这里new并获得Dialog的实例


//填充界面并初始化各种控件

        View view = LayoutInflater.from(mContext).inflate(
                R.layout.dialog_choose_dir, null);
        mDialog.setContentView(view);
        mDialog.setTitle("选择保存位置");
        getRootDir();
        mArrayList = (ArrayList<String>) getDirs(mPath);
        mAdapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_list_item_1, mArrayList);

        mListView = (ListView) view.findViewById(R.id.list_dir);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(lvLis);
        btnHome = (Button) view.findViewById(R.id.btn_home);// Java多级目录的生成
        btnHome.setOnClickListener(new HomeClickListener());
        btnBack = (Button) view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new BackClickListener());
        btnOk = (Button) view.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new OkClickListener());


        if (mType == TypeOpen) {

            titleTextView = new TextView(mContext);


            titleTextView.setText(mPath);

        } else if (mType == TypeSave) {
            titleEditText = new EditText(mContext);
            titleEditText.setWidth(240);
            titleEditText.setHeight(70);
            titleEditText.setGravity(Gravity.CENTER);
            titleEditText.setPadding(0, 2, 0, 0);
            titleEditText.setText("FileName");
        }
    }


// 动态更新ListView

    Runnable AddThread = new

            Runnable() {


                @Override

                public void run() {

// TODO Auto-generated method stub

                    mArrayList.clear();

// System.out.println("Runnable mPath:"+mPath);


// 必须得用这种方法为arr赋值才能更新

                    List<String> temp = getDirs(mPath);

                    for (int i = 0;
                         i < temp.size(); i++)

                        mArrayList.add(temp.get(i));

                    mAdapter.notifyDataSetChanged();

                }

            };


    private OnItemClickListener lvLis = new

            OnItemClickListener() {

                @Override

                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                    String temp = (String) arg0.getItemAtPosition(arg2);
// System.out.println("OnItemClick path1:"+mPath);
                    if (temp.equals(".."))
                        mPath = getFatherDir(mPath);
                    else if (mPath.equals("/"))
                        mPath = mPath + temp;
                    else
                        mPath = mPath + "/" + temp;
// System.out.println("OnItemClick path2"+mPath);
                    if (mType == TypeOpen) // 还应解决，如选择为文件，而非目录的情况：不应该做任何动作
                        titleTextView.setText(mPath);
// 还应解决，如为TypeSave，选择文件情况：应更新文件
                    Handler handler = new Handler();
                    handler.post(AddThread);

                }

            };


    private List<String> getDirs(String ipath) {

        List<String> file = new ArrayList<String>();

        File[] myFile = new File(ipath).listFiles();

        if (myFile == null) {
            file.add("..");
        } else
            for (File f : myFile) {

// 过滤目录

                if (f.isDirectory()) {
                    String tempf = f.toString();
                    int pos = tempf.lastIndexOf("/");
                    String subTemp = tempf.substring(pos + 1, tempf.length());
// String subTemp =
// tempf.substring(mPath.length(),tempf.length());
                    file.add(subTemp);
// System.out.println("files in dir:"+subTemp);
                }

// 过滤知道类型的文件

                if (f.isFile() && mFileType != null) {
                    for (int i = 0;
                         i < mFileType.length;
                         i++) {
                        int typeStrLen = mFileType[i].length();


                        String fileName = f.getPath().substring(

                                f.getPath().length() - typeStrLen);

                        if (fileName.toLowerCase().equals(mFileType[i])) {
                            file.add(f.toString().substring(mPath.length() + 1,
                                    f.toString().length()));
                        }

                    }

                }

            }


        if (file.size() == 0)
            file.add("..");
// System.out.println("file[0]:"+file.get(0)+" File size:"+file.size());
        return file;

    }


    private String getSDPath() {

        File sdDir = null;

        boolean sdCardExist = Environment.getExternalStorageState().equals(

                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在

        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取根目录
        }

        if (sdDir == null) {

            return null;

        }

        return sdDir.toString();


    }


    private String getRootDir() {
        String root = "/";
        mPath = getSDPath();
        if (mPath == null)
            mPath = "/";
        return mPath;
    }


    private String getFatherDir(String path) {
        String subpath = null;
        int pos = path.lastIndexOf("/");
        if (pos == path.length()) {
            path = path.substring(0, path.length() - 1);
            pos = path.lastIndexOf("/");
        }
        subpath = path.substring(0, pos);

        if (pos == 0)
            subpath = path;
        return subpath;

    }


    private final class HomeClickListener implements View.OnClickListener

    {
        @Override
        public void onClick(View v) {
            mPath = getRootDir();
            if (mType == TypeOpen)
                titleTextView.setText(mPath);
            Handler handler = new Handler();
            handler.post(AddThread);
        }
    }


    private final class BackClickListener implements View.OnClickListener

    {


        @Override

        public void onClick(View v) {

            mPath = getFatherDir(mPath);

            if (mType == TypeOpen)
                titleTextView.setText(mPath);
            Handler handler = new Handler();
            handler.post(AddThread);
        }


    }


    private final class OkClickListener implements View.OnClickListener

    {


        @Override

        public void onClick(View v) {

//仅仅修改btnOkIsPressed的状态，不尝试从这里传递任何参数给调用者，那是绝对不可能的（因为调用者对这个对象来讲，是不可见的）。

            btnOkIsPressed = true;

        }


    }


    public String getResultPath() {

        String resultPath;

        if (mType == TypeSave) {

            resultPath = mPath + "/"

                    + titleEditText.getEditableText().toString();

        } else {

            resultPath = mPath;

        }

        return resultPath;

    }


    public void show() {

        mDialog.show();

    }


    public void

    dismiss() {

        mDialog.dismiss();

    }

}
