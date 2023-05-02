package com.mad.neighbourlytest.activites.isuru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mad.neighbourlytest.activites.ishara.ThankYouActivity
import com.mad.neighbourlytest.databinding.ActivityDonate2AddInfoBinding

class Donate2AddInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDonate2AddInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonate2AddInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.DinfoBtn.setOnClickListener {
            startActivity(Intent(this, ThankYouActivity::class.java))
        }
    }
}