package com.mad.neighbourlytest.activites.ishara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.activites.isuru.HomeActivity
import com.mad.neighbourlytest.databinding.ActivityThankYouBinding

class ThankYouActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThankYouBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThankYouBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backToHomeBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}