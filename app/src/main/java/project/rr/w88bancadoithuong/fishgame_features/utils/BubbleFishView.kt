package project.rr.w88bancadoithuong.fishgame_features.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import project.rr.w88bancadoithuong.R

class BubbleFishView(context: Context) : View(context){

    private val fish = arrayOfNulls<Bitmap>(2)
    private val fishX = 10
    private var fishY = 0
    private var fishSpeed = 0

    private var canvasWidth = 0
    private var canvasHeight = 0

    private var yellowX = 0
    private var yellowY = 0
    private var yellowSpeed = 13
    private val yellowPaint = Paint()

    private var greenX = 0
    private var greenY = 0
    private var greenSpeed = 15
    private val greenPaint = Paint()

    private var redX = 0
    private var redY = 0
    private var redSpeed = 16
    private val redPaint = Paint()

    private var score = 0
    private var lifecountFish = 0

    private var touch = false

    private var backgroundImage: Bitmap? = null
    private val scorePaint = Paint()
    private val life = arrayOfNulls<Bitmap>(2)

    var scores = Bundle()

    init {
        bubbleFishView()
    }

    private fun bubbleFishView() {
        fish[0] = BitmapFactory.decodeResource(resources, R.drawable.fish1)
        fish[1] = BitmapFactory.decodeResource(resources, R.drawable.fish2)
        backgroundImage = BitmapFactory.decodeResource(resources, R.drawable.background)

        yellowPaint.color = Color.YELLOW
        yellowPaint.isAntiAlias = false
        greenPaint.color = Color.GREEN
        greenPaint.isAntiAlias = false
        redPaint.color = Color.RED
        redPaint.isAntiAlias = false
        scorePaint.color = Color.WHITE
        scorePaint.textSize = 40f //  70 nichilm age
        scorePaint.typeface = Typeface.DEFAULT_BOLD
        scorePaint.isAntiAlias = true
        life[0] = BitmapFactory.decodeResource(resources, R.drawable.hearts)
        life[1] = BitmapFactory.decodeResource(resources, R.drawable.heart_grey)
        fishY = 550
        score = 0
        lifecountFish = 3
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvasWidth = width
        canvasHeight = height
        canvas.drawBitmap(backgroundImage!!, 0f, 0f, null)
        val minFishY = fish[0]!!.height
        val maxFishY: Int = canvasHeight - fish[0]!!.height * 4
        fishY += fishSpeed
        if (fishY < minFishY) {
            fishY = minFishY
        }
        if (fishY > maxFishY) {
            fishY = maxFishY
        }
        fishSpeed += 2
        if (touch) {
            canvas.drawBitmap(fish[1]!!, fishX.toFloat(), fishY.toFloat(), null)
            touch = false
        } else {
            canvas.drawBitmap(fish[0]!!, fishX.toFloat(), fishY.toFloat(), null)
        }
        yellowX -= yellowSpeed

        //update score
        if (hitBallChecker(yellowX, yellowY)) {
            score = score + 10
            yellowX = -100
        }
        if (yellowX < 0) {
            yellowX = canvasWidth + 21
            yellowY = Math.floor(Math.random() * (maxFishY - minFishY)).toInt() + minFishY
        }
        //radious can be 25
        canvas.drawCircle(yellowX.toFloat(), yellowY.toFloat(), 20f, yellowPaint)
        greenX -= greenSpeed

        //update score
        if (hitBallChecker(greenX, greenY)) {
            score += 20
            greenX = -100
        }
        if (greenX < 0) {
            greenX = canvasWidth + 21
            greenY = Math.floor(Math.random() * (maxFishY - minFishY)).toInt() + minFishY
        }
        //radious can be 25
        canvas.drawCircle(greenX.toFloat(), greenY.toFloat(), 18f, greenPaint)
        redX -= redSpeed

        //update score
        if (hitBallChecker(redX, redY)) {
            redX = -100
            lifecountFish--
            if (lifecountFish == 0) {
                Toast.makeText(context, "Game Over", Toast.LENGTH_SHORT).show()
                scores.putString("scores",score.toString())
                findNavController().navigate(R.id.action_fishFragment_to_gameOverFragment,scores)
            }
        }
        if (redX < 0) {
            redX = canvasWidth + 21
            redY = Math.floor(Math.random() * (maxFishY - minFishY)).toInt() + minFishY
        }
        //radious can be 25
        canvas.drawCircle(redX.toFloat(), redY.toFloat(), 22f, redPaint)
        canvas.drawText("Score: $score", 20f, 60f, scorePaint)
        for (i in 0..2) {
//280 was 580
            val x = (280 + life[0]!!.width * 1.5 * i).toInt()
            val y = 30
            if (i < lifecountFish) {
                canvas.drawBitmap(life[0]!!, x.toFloat(), y.toFloat(), null)
            } else {
                canvas.drawBitmap(life[1]!!, x.toFloat(), y.toFloat(), null)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            touch = true
            fishSpeed = -25
        }
        return true
    }

    fun hitBallChecker(x: Int, y: Int): Boolean {
        return fishX < x && x < fishX + fish[0]!!.width && fishY < y && y < fishY + fish[0]!!
            .height
    }

}