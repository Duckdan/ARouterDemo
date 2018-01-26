package com.study.arouterdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.study.arouterdemo.bean.JsonServiceImpl;
import com.study.arouterdemo.bean.Student;

@Route(path = "/test/activity")
public class MyActivity extends Activity {

    @Autowired
    public String name;
    @Autowired
    int age;
    @Autowired(name = "girl")
    boolean sex;
    @Autowired
    JsonServiceImpl jsonBean;
    @Autowired
    Student obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ARouter.getInstance().inject(this);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText(name + "=" + age + "=" + sex + "=" + obj.getGrade());

    }
}
