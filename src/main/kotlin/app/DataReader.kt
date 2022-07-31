package app

import domain.Borrower


interface DataReader {
    fun retrieveAll(): List<Borrower>

}