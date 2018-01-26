package com.study.arouterdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.study.arouterdemo.bean.JsonServiceImpl;
import com.study.arouterdemo.bean.Student;

public class MainActivity extends AppCompatActivity {
    @Autowired
    JsonServiceImpl js;
    private static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);
        activity = this;
    }

    public void jump(View view) {

        Student student = new Student();
        student.setName("阿里");
        student.setGrade("地点");

        String json = ((JsonServiceImpl) ARouter.getInstance().build("/service/json").navigation()).object2Json(student);
//            JsonServiceImpl jb = new JsonServiceImpl();
//        String json = jb.object2Json(student);
        // 2. 跳转并携带参数
        ARouter.getInstance().build("/test/activity")
                .withString("name", "body")
                .withInt("age", 888)
                .withString("obj", json)
                .navigation(this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {

                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        Log.e("ARouter", "被拦截了");
                    }
                });
    }

    public static Activity getThis() {
        return activity;
    }
}
