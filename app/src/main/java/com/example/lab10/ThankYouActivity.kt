package com.example.lab10

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab10.databinding.ActivityServiceBinding
import com.example.lab10.databinding.ActivityThankYouBinding

class ThankYouActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThankYouBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThankYouBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.portalBtn.setOnClickListener() {
            startActivity(Intent(this, LoginActivity::class.java))
        }

//        enableEdgeToEdge()
//        setContentView(R.layout.activity_thank_you)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}