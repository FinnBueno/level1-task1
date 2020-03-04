package task1a1.peakfinn.com.task11

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_higher_lower.*

class HigherLowerActivity : AppCompatActivity() {

    var diceSide = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_higher_lower)

        btnLower.setOnClickListener { rollDice(-1) }
        btnEquals.setOnClickListener { rollDice(0) }
        btnHigher.setOnClickListener { rollDice(1) }
    }

    private fun rollDice(comparison: Int) {
        lastThrow.text = getString(R.string.last_throw, diceSide + 1)

        val previousThrow = diceSide
        diceSide = (Math.random() * 6).toInt()

        // show toast
        showResult(diceSide - previousThrow, comparison)

        // set new dice image
        val newImageId = resources.getIdentifier("dice${diceSide + 1}", "drawable", packageName)
        diceView.setImageResource(newImageId)
    }

    private fun showResult(difference: Int, comparison: Int) {
        val relativeDifference = difference / Math.max(Math.abs(difference), 1)
        val messageId = if (relativeDifference == comparison) {
            R.string.success
        } else {
            R.string.failure
        }
        Toast.makeText(applicationContext, messageId, Toast.LENGTH_SHORT).show()
    }
}
