package com.example.wecare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.content.Intent;

class ChatForumsActivity: AppCompatActivity() {
    lateinit var cardView: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatforums)
        findViewById<CardView>(R.id.article1_card).setOnClickListener{
                val intent= Intent(this,ArticleActivity::class.java).putExtra("articleid",1)
                startActivity(intent)
            }
        findViewById<CardView>(R.id.article2_card).setOnClickListener{
                val intent= Intent(this,ArticleActivity::class.java).putExtra("articleid",2)
                startActivity(intent)
            }
        findViewById<CardView>(R.id.article3_card).setOnClickListener{
                val intent= Intent(this,ArticleActivity::class.java).putExtra("articleid",3)
                startActivity(intent)
            }
        findViewById<CardView>(R.id.article4_card).setOnClickListener{
                val intent= Intent(this,ArticleActivity::class.java).putExtra("articleid",4)
                startActivity(intent)
            }
        findViewById<CardView>(R.id.article5_card).setOnClickListener{
            val intent= Intent(this,ArticleActivity::class.java).putExtra("articleid",5)
            startActivity(intent)
        }
        findViewById<CardView>(R.id.article6_card).setOnClickListener{
            val intent= Intent(this,ArticleActivity::class.java).putExtra("articleid",6)
            startActivity(intent)
        }
        findViewById<CardView>(R.id.article7_card).setOnClickListener{
            val intent= Intent(this,ArticleActivity::class.java).putExtra("articleid",7)
            startActivity(intent)
        }
        findViewById<CardView>(R.id.article8_card).setOnClickListener{
            val intent= Intent(this,ArticleActivity::class.java).putExtra("articleid",8)
            startActivity(intent)
        }
        findViewById<CardView>(R.id.article9_card).setOnClickListener{
            val intent= Intent(this,ArticleActivity::class.java).putExtra("articleid",9)
            startActivity(intent)
        }
    }
}
