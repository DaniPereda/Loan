package app

import domain.Borrower
import domain.Loan
import domain.State

class LoanService(private val dataReader: DataReader) {
    fun start(amount:Float):Loan
    {
        var myLoan = Loan(amount)
        myLoan.checkValidAmount()
        if(myLoan.state == State.OK) {
            var borrowersAvailable = retrieveAll(dataReader)

            myLoan.configureBorrowers(borrowersAvailable)
        }
        if(myLoan.state == State.OK) {
            myLoan.obtainConditions()
        }
        return myLoan


    }

    private fun retrieveAll(dataReader:DataReader): List<Borrower>{
        return dataReader.retrieveAll()
    }


}