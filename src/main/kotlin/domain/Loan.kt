package domain

import kotlin.math.pow

data class Loan(var amount: Float) {
    var borrowers = mutableListOf<Borrower>()
    var rate: Float = 0F
    var finalReturnedAmount: Float = 0F
    var monthly: Float = 0F
    var denied: Boolean = false
    var deniedReason:String = ""
    var state:State = State.OK


    fun configureBorrowers(allBorrowersAvailable: List<Borrower>) {
        var restOfAmount = amount
        var mutableAllBorrowersAvailable = allBorrowersAvailable.toMutableList()
        while (restOfAmount > 0F) {
            if(mutableAllBorrowersAvailable.isEmpty())
            {
                state = State.NOT_ENOUGH_FOUNDS
                break
            }
            restOfAmount = addMinorRateBorrower(restOfAmount, mutableAllBorrowersAvailable)
        }
    }

    private fun addMinorRateBorrower(restOfAmount: Float, borrowersAvailable: MutableList<Borrower>): Float {

        var selectedBorrower = findNextMinorRateBorrower(borrowersAvailable)

        var loanAmountRemaining = restOfAmount - selectedBorrower.moneyAvailable

        if (NotEnoughtBorrowers(loanAmountRemaining))
        {
            borrowers.add(selectedBorrower)
        }else{
            assignPartialAmount(selectedBorrower, loanAmountRemaining)
            loanAmountRemaining = 0F
        }
        borrowersAvailable.remove(selectedBorrower)
        return loanAmountRemaining


    }

    private fun NotEnoughtBorrowers(loanAmountRemaining: Float) = loanAmountRemaining > 0

    private fun assignPartialAmount(selectedBorrower: Borrower, loanAmountRemaining: Float) {
        borrowers.add(
            Borrower(
                selectedBorrower.name,
                selectedBorrower.rate,
                selectedBorrower.moneyAvailable + loanAmountRemaining
            )
        )
    }

    fun checkValidAmount()
    {
        if (amount%100 != 0F || amount < 1000 || amount > 15000)
        {
            state = State.INCORRECT_AMOUNT
        }
    }

    private fun findNextMinorRateBorrower(borrowersAvailable:List<Borrower>):Borrower
    {
        var selectedBorrower = Borrower()
        var minorRate = selectedBorrower.rate
        for (borrower in borrowersAvailable) {
            if (borrower.rate < minorRate) {
                selectedBorrower = borrower
                minorRate = borrower.rate
            }
        }
        return selectedBorrower
    }

    fun obtainConditions(){
        addRate()
        addMonthlyRepayment()
        addTotalRepayment()

    }
    private fun addRate(){
        for(borrower in borrowers){
            rate += (borrower.rate*borrower.moneyAvailable/amount)
        }
    }

    private fun addTotalRepayment()
    {
        finalReturnedAmount = monthly * 36
    }

    private fun addMonthlyRepayment()
    {

        monthly = (rate * amount)/ (1 - (1 + rate).toDouble().pow((-36).toDouble())).toFloat() //(1 - (1 + rate)^(-36F))

    }
}