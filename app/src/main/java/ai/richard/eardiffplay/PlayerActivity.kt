package ai.richard.eardiffplay

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class PlayerActivity : AppCompatActivity() {

    val MSG_PLAY_COMMON: Int = 0x01
    val MSG_PLAY_COMMON_END: Int = 0x02
    val MSG_PLAY_VOICE: Int = 0x03
    val MSG_PLAY_VOICE_REST = 0x04

    var type: Int = 0

    lateinit var tv_status:TextView
    lateinit var tv_left_words: TextView
    lateinit var tv_right_words: TextView
    lateinit var tv_left_count: TextView
    lateinit var tv_right_count: TextView

    lateinit var mPlayerCommon: MediaPlayer

    var count12 = 0

    var countLeft = 0
    var countRight = 0

    val handler: Handler = object:Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                MSG_PLAY_COMMON -> tv_status.text = "标准音正在播放"
                MSG_PLAY_COMMON_END -> tv_status.text = "标准音播放结束"
                MSG_PLAY_VOICE_REST -> tv_status.text = "END"
                MSG_PLAY_VOICE-> {
                    if (count12++ < 12) {
                        tv_status.text = "START"
                        playVoice()
                    } else {
                        tv_status.text = "测试结束"
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        type = intent.getIntExtra("type", 0)

        mPlayerCommon = MediaPlayer.create(this, R.raw.common)
        mPlayerCommon.setOnCompletionListener {
            handler.sendEmptyMessage(MSG_PLAY_COMMON_END)
        }


        initView()

        setClick()
    }

    private fun initView() {
        tv_status = findViewById(R.id.tv_status)
        tv_left_words = findViewById(R.id.tv_left_words)
        tv_right_words = findViewById(R.id.tv_right_words)
        tv_left_count = findViewById(R.id.tv_left_count)
        tv_right_count = findViewById(R.id.tv_right_count)
    }

    private fun setClick() {
        findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            finish()
        }
        findViewById<Button>(R.id.btn_common).setOnClickListener {
            it.isClickable = false
            handler.sendEmptyMessage(MSG_PLAY_COMMON)
            mPlayerCommon.start()
        }
        findViewById<Button>(R.id.btn_cv).setOnClickListener {
            it.isClickable = false
            findViewById<Button>(R.id.btn_common).isClickable = false
            if (mPlayerCommon.isPlaying) {
                mPlayerCommon.reset()
            }
            handler.sendEmptyMessage(MSG_PLAY_VOICE)
        }

        findViewById<Button>(R.id.btn_select_left).setOnClickListener {
            countLeft++
            setAllCountData()
        }
        findViewById<Button>(R.id.btn_select_right).setOnClickListener {
            countRight++
            setAllCountData()
        }
    }


    private fun setAllCountData() {
        val leftPercent = countLeft * 100 / (countLeft + countRight)
        val rightPercent = countRight * 100 / (countLeft + countRight)

        tv_left_count.text = "左耳次数：$countLeft\n百分比：$leftPercent%"
        tv_right_count.text = "右耳次数：$countRight\n百分比：$rightPercent%"
    }


    private fun playVoice() {

        val voiceLeftId = Voice.getVoiceResourceL(type)
        val voiceRightId = Voice.getVoiceResourceR(type)

        val leftWords = Voice.getWordsByTypeAndRawId(type, voiceLeftId)
        val rightWords = Voice.getWordsByTypeAndRawId(type, voiceRightId)
        tv_left_words.text = "左耳：$leftWords"
        tv_right_words.text = "右耳：$rightWords"

        var mPlayerLeft = MediaPlayer.create(this, voiceLeftId)
        var mPlayerRight = MediaPlayer.create(this, voiceRightId)

        mPlayerLeft.setVolume(1f,0f)
        mPlayerRight.setVolume(0f, 1f)

        mPlayerLeft.setOnCompletionListener {
            handler.sendEmptyMessage(MSG_PLAY_VOICE_REST)
            handler.sendEmptyMessageDelayed(MSG_PLAY_VOICE, 3 * 1000L)
        }
        mPlayerRight.setOnCompletionListener {

        }
        mPlayerLeft.start()
        mPlayerRight.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeMessages(MSG_PLAY_VOICE)
        handler.removeMessages(MSG_PLAY_COMMON)
        handler.removeMessages(MSG_PLAY_COMMON_END)
        if (mPlayerCommon.isPlaying){
            mPlayerCommon.reset()
        }
    }
}