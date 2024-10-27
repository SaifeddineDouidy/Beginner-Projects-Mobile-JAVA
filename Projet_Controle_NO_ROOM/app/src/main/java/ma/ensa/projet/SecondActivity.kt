package ma.ensa.projet

import android.os.Build
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.BounceInterpolator

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Masquer la barre de statut
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        val title = findViewById<TextView>(R.id.welcomeTitle)
        val message = findViewById<TextView>(R.id.welcomeMessage)

        // Configuration initiale
        title.apply {
            scaleX = 0f
            scaleY = 0f
            alpha = 0f
            translationY = -200f
            rotation = -15f
        }

        message.apply {
            scaleX = 0f
            scaleY = 0f
            alpha = 0f
            translationY = 200f
            rotation = 15f
        }

        // Animation du titre
        title.animate()
            .alpha(1f)
            .translationY(0f)
            .rotation(0f)
            .scaleX(1.2f)
            .scaleY(1.2f)
            .setDuration(1200)
            .setInterpolator(AnticipateOvershootInterpolator(1.5f))
            .withEndAction {
                title.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(400)
                    .setInterpolator(BounceInterpolator())
                    .start()
            }
            .start()

        message.animate()
            .alpha(1f)
            .translationY(0f)
            .rotation(0f)
            .scaleX(1.2f)
            .scaleY(1.2f)
            .setStartDelay(600)
            .setDuration(1200)
            .setInterpolator(AnticipateOvershootInterpolator(1.5f))
            .withEndAction {
                message.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(400)
                    .setInterpolator(BounceInterpolator())
                    .start()
            }
            .start()
    }
}