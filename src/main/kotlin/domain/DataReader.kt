package domain

import domain.Borrower


interface DataReader {
    fun retrieveAll(): List<Borrower>

}