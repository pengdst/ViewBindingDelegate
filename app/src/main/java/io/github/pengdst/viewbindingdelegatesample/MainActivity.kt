package io.github.pengdst.viewbindingdelegatesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.pengdst.libs.ui.extensions.viewBindings
import io.github.pengdst.viewbindingdelegatesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBindings()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}