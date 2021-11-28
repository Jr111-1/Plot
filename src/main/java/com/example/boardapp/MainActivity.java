package com.example.boardapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageView ivPaint;
    private ImageView ivEraser;
    private ImageView ivClean;
    private ImageView ivLast;
    private ImageView ivNext;
    private DrawingBoardView mDrawingBoardView;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();//初始化画布
        initEvent();//
    }
    //加载布局
    private void initView() {
        mDrawingBoardView = (DrawingBoardView) findViewById(R.id.dbv);
        ivPaint = (ImageView) findViewById(R.id.ivPaint);
        ivEraser = (ImageView) findViewById(R.id.ivEraser);
        ivClean = (ImageView) findViewById(R.id.ivClean);
        ivLast = (ImageView) findViewById(R.id.ivLast);
        ivNext = (ImageView) findViewById(R.id.ivNext);

    }
    //加载功能按钮
    private void initEvent() {
        ivPaint.getDrawable().setLevel(1);
        ivPaint.getBackground().setLevel(1);
        ivPaint.setOnClickListener(this);
        ivEraser.setOnClickListener(this);
        ivClean.setOnClickListener(this);
        ivLast.setOnClickListener(this);
        ivNext.setOnClickListener(this);


        //检测是否有写的权限
        int permission = ActivityCompat.checkSelfPermission(this,
                "android.permission.WRITE_EXTERNAL_STORAGE");
        if (permission != PackageManager.PERMISSION_GRANTED) {
            try {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivPaint:
                if (mDrawingBoardView.getMode() != DrawMode.PaintMode) {
                    mDrawingBoardView.setMode(DrawMode.PaintMode);
                    ivPaint.getDrawable().setLevel(1);
                    ivPaint.getBackground().setLevel(1);
                    ivEraser.getDrawable().setLevel(0);
                    ivEraser.getBackground().setLevel(0);
                }
                break;
            case R.id.ivEraser:
                if (mDrawingBoardView.getMode() != DrawMode.EraserMode) {
                    mDrawingBoardView.setMode(DrawMode.EraserMode);
                    ivPaint.getDrawable().setLevel(0);
                    ivPaint.getBackground().setLevel(0);
                    ivEraser.getDrawable().setLevel(1);
                    ivEraser.getBackground().setLevel(1);
                }
                break;
            case R.id.ivClean:
                alertDialogClean();
                break;
            case R.id.ivLast:
                mDrawingBoardView.lastStep();
                break;
            case R.id.ivNext:
                mDrawingBoardView.nextStep();
                break;

        }
    }

    public void alertDialogClean() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确定要清空画板吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mDrawingBoardView.clean();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        final AlertDialog dialog = builder.show();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.help2:
                Intent intent2 = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}