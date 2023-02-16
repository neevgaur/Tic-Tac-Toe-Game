package com.gaurneev.tictactoe

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.gaurneev.tictactoe.databinding.ActivityGameBinding
import com.gaurneev.tictactoe.databinding.ActivityMainBinding

class game : AppCompatActivity(), View.OnClickListener {
    private var gameBinding: ActivityGameBinding? = null


    var player1 = 0
    var player2 = 1


    var activePlayer = player1
    lateinit var filledPos: IntArray

    var gameActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameBinding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(gameBinding?.root)

        filledPos = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)

        gameBinding?.t1?.setOnClickListener(this)
        gameBinding?.t2?.setOnClickListener(this)
        gameBinding?.t3?.setOnClickListener(this)
        gameBinding?.t4?.setOnClickListener(this)
        gameBinding?.t5?.setOnClickListener(this)
        gameBinding?.t6?.setOnClickListener(this)
        gameBinding?.t7?.setOnClickListener(this)
        gameBinding?.t8?.setOnClickListener(this)
        gameBinding?.t9?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        if (!gameActive)
            return

        var btnClicked = findViewById<TextView>(v!!.id)
        var clickedTag = Integer.parseInt(btnClicked.tag.toString())

        if (filledPos[clickedTag] != -1)
            return

        filledPos[clickedTag] = activePlayer

        if (activePlayer == player1) {
            btnClicked.setText("O")
            activePlayer = player2
            gameBinding?.id?.setText("Player 1 Turn")
        } else {
            btnClicked.setText("X")
            activePlayer = player1
            gameBinding?.id?.setText("Player 2 Turn")
        }

        checkWin()
    }

    private fun checkWin() {
        var winPos = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(6, 4, 2)
        )

        for (i in 0 until winPos.size) {
            var val0 = winPos[i][0]
            var val1 = winPos[i][1]
            var val2 = winPos[i][2]

            if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]) {
                if (filledPos[val0] != -1) {
                    gameActive = false
                    if (filledPos[val0] == player1) {
                        gameBinding?.id?.setText("Player 1 is Winner")
                        winWindow("Player 1 is Winner")
                    } else {
                        gameBinding?.id?.setText("Player 2 is Winner")
                        winWindow("Player 2 is Winner")
                    }
                    return
                }
            }
        }

        //Draw Check
        var count = 0

        for (i in 0 until filledPos.size) {
            if (filledPos[i] == -1) {
                count++
            }
        }
        if (count == 0) {
            winWindow("It's Draw")
        }
    }

    private fun winWindow(s: String) {
        AlertDialog.Builder(this)
            .setMessage(s)
            .setTitle("Tic Tac Toe")
            .setNegativeButton(
                "Restart",
                DialogInterface.OnClickListener { dialogInterface, which ->
                    restartGame()
                })
            .setPositiveButton("Exit",
                DialogInterface.OnClickListener { dialogInterface, which ->
                    exitGame()
                })
            .show()
    }

    private fun restartGame() {
        filledPos = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)
        activePlayer = player1
        gameActive = true
        gameBinding?.id?.setText("Player 2 Turn")
        gameBinding?.t1?.setText("")
        gameBinding?.t2?.setText("")
        gameBinding?.t3?.setText("")
        gameBinding?.t4?.setText("")
        gameBinding?.t5?.setText("")
        gameBinding?.t6?.setText("")
        gameBinding?.t7?.setText("")
        gameBinding?.t8?.setText("")
        gameBinding?.t9?.setText("")
    }

    private fun exitGame() {
        finish()
    }
}