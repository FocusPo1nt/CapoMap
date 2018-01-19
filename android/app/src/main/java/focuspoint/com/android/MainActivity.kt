package focuspoint.com.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.focuspoint.Core

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.text_view)
        textView.text = Core().print()
    }
}
