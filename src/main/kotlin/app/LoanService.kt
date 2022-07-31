package app

import domain.Borrower
import domain.DataReader
import domain.Loan

class LoanService(private val dataReader: DataReader) {
    fun start(amount:Float):Loan
    {

        var myLoan = Loan(amount)

        myLoan.checkValidAmount()

        if(!myLoan.denied) {
            var borrowersAvailable = Borrower().retrieveAllBorrowersAvailable(dataReader)
            myLoan.configureBorrowers(borrowersAvailable)
        }

        if(!myLoan.denied) {
            myLoan.obtainConditions()
        }

        return myLoan


    }



}