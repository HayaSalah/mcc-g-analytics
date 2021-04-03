package com.example.events


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_electronics.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class electronic : AppCompatActivity() {
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    var timeone : Long = 0
    var timetwo : Long =0;
    var timeResult:Long=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electronics)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        timeone = System.currentTimeMillis()

        laptop.setOnClickListener {
            val i = Intent(this,detailsSheet::class.java)
            startActivity(i)
            selectContent(UUID.randomUUID().toString(),laptop.getText().toString())

        }

        iphone.setOnClickListener {
            val i = Intent(this,detailsSheet::class.java)
            startActivity(i)
            selectContent(UUID.randomUUID().toString(),iphone.getText().toString())

        }

        smartTv.setOnClickListener {
            val i = Intent(this,detailsSheet::class.java)
            startActivity(i)
            selectContent(UUID.randomUUID().toString(),smartTv.getText().toString())

        }
    }
    fun selectContent(id:String , name:String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID,id)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME,name)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE,"Product item")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle)
    }
    @Override
    override fun onResume() {
        super.onResume()
        screen("electronic screen")
    }
    private  fun screen(screenName:String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME,screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS,"electronic")
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