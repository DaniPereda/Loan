package app

import domain.Borrower
import domain.Loan

class LoanService(private val dataReader: DataReader) {
    fun start(amount:Float):Loan
    {

        var myLoan = Loan(amount)

        myLoan.checkValidAmount()

        if(!myLoan.denied) {
            var borrowersAvailable = retrieveAll(dataReader)

            myLoan.configureBorrowers(borrowersAvailable)
        }

        if(!myLoan.denied) {
            myLoan.obtainConditions()
        }

        return myLoan


    }

    private fun retrieveAll(dataReader:DataReader): List<Borrower>{
        return dataReader.retrieveAll()
    }


}