package com.example.events


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.TypedArrayUtils.getText
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    var timeone : Long = 0
    var timetwo : Long =0;
    var timeResult:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        timeone = System.currentTimeMillis()
        btnFood.setOnClickListener {
            val i = Intent(applicationContext, food::class.java)
            startActivity(i)
            selectContent(UUID.randomUUID().toString(),btnFood.getText().toString())
        }

        btnElec.setOnClickListener {
            val i = Intent(applicationContext, electronic::class.java)
            startActivity(i)
            selectContent(UUID.randomUUID().toString(),btnElec.getText().toString())

        }

        btnClo.setOnClickListener {
            val i = Intent(applicationContext, clothes::class.java)
            startActivity(i)
            selectContent(UUID.randomUUID().toString(),btnClo.getText().toString())

        }
    }
        fun selectContent(id:String , name:String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID,id)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,name)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE,"categories item")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle)
    }

    @Override
   override fun onResume() {
        super.onResume()
        screen("main")
    }
    private  fun screen(screenName:String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME,screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS,"main")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW,bundle)
    }

    @Override
    override fun onDestroy() {
        timetwo = System.currentTimeMillis()
        timeCount(timeone,timetwo)
        super.onDestroy()
    }
    fun timeCount(t1 : Long,t2:Long){
        timeResult =(t2-t1)
        var bundle = Bundle()
        bundle.putString("id",UUID.randomUUID().toString())
        bundle.putLong("spended",timeResult)

    }
}






