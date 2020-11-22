package com.reo.running.throwingspeed

import android.content.Context
import android.hardware.*
import android.media.session.MediaController
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var manager: SensorManager
    var judge: TextView = findViewById(R.id.judge)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

    }

    override fun onPause() {
        super.onPause()
        manager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        val sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST)
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val speed = event?.values?.get(0)

        if (speed != null && speed <= 10f) {
            judge.text = "遅い"
        } else if (speed != null && speed <= 20f) {
            judge.text = "微妙"
        } else if (speed != null && speed <= 30f) {
            judge.text = "まぁまぁかな"
        }

    }

}