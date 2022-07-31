package app

import infra.CSVReader

class MainApp(private val inputRetriever: InputRetriever, private val outputRetriever: Writter) {

    var amountNeeded = inputRetriever.getLoanAmount()
    var loanService = LoanService(CSVReader())
    var loan = loanService.start(amountNeeded)

    var writter = outputRetriever.setResponse(loan)




}