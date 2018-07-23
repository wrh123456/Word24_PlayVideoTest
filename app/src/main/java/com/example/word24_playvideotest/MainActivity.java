package com.example.word24_playvideotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private VideoView videoView;
    private Button play,pause,resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView=(VideoView)findViewById(R.id.video);
        play=(Button)findViewById(R.id.play);
        pause=(Button)findViewById(R.id.pause);
        resume=(Button)findViewById(R.id.resume);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        resume.setOnClickListener(this);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        else{
            initplayVideo();
        }
    }
    private void initplayVideo(){
        File file=new File(Environment.getExternalStorageDirectory(),"whitebear.mp4");
        videoView.setVideoPath(file.getPath());//获得视频的路径

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initplayVideo();
                }
                else{
                    Toast.makeText(MainActivity.this,"拒绝权限将无法使用功能",Toast.LENGTH_SHORT).show();
                }
                break;
                default:break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                if(!videoView.isPlaying()){
                    videoView.start();
                    Log.d(TAG, "onClick: 播放");
                }
                break;
            case R.id.pause:
                 if(videoView.isPlaying()){
                     videoView.pause();
                     Log.d(TAG, "onClick: 暂停");
                 }
                 else{
                     videoView.start();
                     Log.d(TAG, "onClick: 继续");
                 }
                break;
            case R.id.resume:
                if(videoView.isPlaying()){
                    videoView.resume();//重新播放
                }
                break;
                default:break;
        }
    }
}
