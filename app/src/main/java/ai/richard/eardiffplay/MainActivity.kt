package ai.richard.eardiffplay

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var mCalendar:Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setClick()

        Thread(Runnable {
            getNetTime()
        }).start()
    }

    private fun setClick() {
        findViewById<Button>(R.id.btn_guan).setOnClickListener {
            playVoice(Voice.GUAN)
        }
        findViewById<Button>(R.id.btn_wu).setOnClickListener {
            playVoice(Voice.WU)
        }
        findViewById<Button>(R.id.btn_xiang).setOnClickListener {
            playVoice(Voice.XIANG)
        }
        findViewById<Button>(R.id.btn_gan).setOnClickListener {
            playVoice(Voice.GAN)
        }
        findViewById<Button>(R.id.btn_yue).setOnClickListener {
            playVoice(Voice.YUE)
        }
        findViewById<Button>(R.id.btn_min).setOnClickListener {
            playVoice(Voice.MIN)
        }
        findViewById<Button>(R.id.btn_pu).setOnClickListener {
            playVoice(Voice.PU)
        }
    }

    private fun playVoice(type: Int) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra("type", type)
        startActivity(intent)
//        mCalendar?.let {
//            if (it.get(Calendar.MONTH) <= 2
//                && it.get(Calendar.DATE) <= 13
//                && it.get(Calendar.HOUR) < 12) {
//                val intent = Intent(this, PlayerActivity::class.java)
//                intent.putExtra("type", type)
//                startActivity(intent)
//            } else {
//                Toast.makeText(this, "试用期已结束,请联系开发者", Toast.LENGTH_SHORT).show()
//            }
//        }?: let{
//            Toast.makeText(this, "请检查网络是否连接，退出重试", Toast.LENGTH_SHORT).show()
//        }
    }


    private fun getNetTime() {
        try {
            val url = URL("https://www.baidu.com");
            val uc = url.openConnection();//生成连接对象
            uc.connect(); //发出连接
            val ld = uc.date; //取得网站日期时间
            val calendar = Calendar.getInstance();
            calendar.timeInMillis = ld;
            mCalendar = calendar
        } catch (e: Exception) {
            e.printStackTrace();
        }
    }
}