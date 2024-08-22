package com.example.wecare

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class ArticleActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val aid=intent.getIntExtra("articleid",1)
        var layout: Int
        if(aid==1)
            layout=R.layout.article_1
        else if(aid==2)
            layout=R.layout.article_2
        else if(aid==3)
            layout=R.layout.article_3
        else if(aid ==5)
            layout=R.layout.article_5
        else if(aid == 6)
            layout=R.layout.article_6
        else if(aid == 7)
            layout=R.layout.article_7
        else if(aid == 8)
            layout=R.layout.article_8
        else
            layout=R.layout.article_9
        setContentView(layout)
    }
}