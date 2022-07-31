package app

import domain.Loan

interface Writter {
    fun setResponse(loan:Loan)
}