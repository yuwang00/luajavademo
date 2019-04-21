package com.example.chenmin.luajavademo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.luajava.luajava.LuaState;
import com.example.luajava.luajava.LuaStateFactory;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LuaState lua = LuaStateFactory.newLuaState(); //创建栈
        lua.openLibs(); //加载标准库
        lua.LdoString(readAssetsTxt(this,"test.lua"));
        lua.getGlobal("setText"); //获取函数
        lua.pushJavaObject(findViewById(R.id.tv_helloWorld)); //把TextView传入
        lua.pushString("Demo"); //传入一个字符串
        lua.pcall(2,0,0); //执行函数，有2个参数
        lua.close();
    }

    public static String readAssetsTxt(Context context, String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            /* Read the entire asset into a local byte buffer. */
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            /* Convert the buffer into a string. */
            String text = new String(buffer, "utf-8");
            /* Finally stick the string into the text view. */
            return (text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ("err");
    }
}
