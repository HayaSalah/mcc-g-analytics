package com.example.events
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import java.util.*

class detailsSheet : AppCompatActivity() {
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    var timeone : Long = 0
    var timetwo : Long =0
    var timeResult:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product__details)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        timeone = System.currentTimeMillis()
    }
    @Override
    override fun onResume() {
        super.onResume()
        screen("details screen")
    }
    private  fun screen(screenName:String){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME,screenName)
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS,"detailsSheet")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW,bundle)
    }
    override fun onDestroy() {
        timetwo = System.currentTimeMillis()
        timeCount(timeone,timetwo)
        super.onDestroy()
    }
    fun timeCount(t1 : Long,t2:Long){
        timeResult =(t2-t1)
        var bundle = Bundle()
        bundle.putString("id", UUID.randomUUID().toString())
        bundle.putLong("spended",timeResult)

    }

}