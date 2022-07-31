package infra

import app.InputRetriever
import app.Writter
import domain.Loan
import domain.State

class ConsoleInConsoleOut : InputRetriever, Writter {
    override fun getLoanAmount(): Float {
        println("Welcome. Please, write the amount you need")
        return readln().toFloat()
    }

    override fun setResponse(loan: Loan) {
        when(loan.state)
        {
            State.OK ->{
                println("Requested amount: £" + loan.amount)
                println("Rate: ${loan.rate}%")
                println("Monthly repayment: £${round2(loan.monthly)}")
                println("Total repayment: £${round1(loan.finalReturnedAmount)}")
            }
            State.INCORRECT_AMOUNT -> println("Error, the amount must be a multiple of 100 and between 1000 and 15000")
            State.NOT_ENOUGH_FOUNDS -> println("Sorry, we have no enough founds")
        }

        if(loan.denied) {
            println("Loan denied")
            println(loan.deniedReason)
        }
        else {

        }
    }

    private fun round2(number:Float):String{
        return (((number*100).toInt()).toFloat()/100).toString()
    }

    private fun round1(number:Float):String{
        return (((number*10).toInt()).toFloat()/10).toString()
    }

}