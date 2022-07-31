package app

import infra.CSVReader

class MainApp(private val inputRetriever: InputRetriever, private val outputRetriever: Writter) {

    var amountNeeded = inputRetriever.getLoanAmount()
    var loanService = LoanService(CSVReader())
    var loan = loanService.start(amountNeeded)

    val writer = outputRetriever.setResponse(loan)
}