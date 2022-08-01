package infra

import domain.Borrower
import app.DataReader
import java.io.File


class CSVReader : DataReader {
    override fun retrieveAll(): List<Borrower> {
        var borrowersList = mutableListOf<Borrower>()
        File("Loan.csv").forEachLine {
            if(noFirstLine(it)) {
                borrowersList.add(createBorrowerFromString(it))
            }
        }
        return borrowersList.toList()
    }

    private fun noFirstLine(it: String) = it != "Lender,Rate,Available"

    private fun createBorrowerFromString(borrowerFormatString:String):Borrower
    {
        var splittedBorrower = borrowerFormatString.split(',')
        return Borrower(splittedBorrower[2],splittedBorrower[1].toFloat(),splittedBorrower[2].toFloat())
    }
}