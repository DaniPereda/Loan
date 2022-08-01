package infra

import domain.Borrower
import app.DataReader

class ObjectReader : DataReader {
    override fun retrieveAll(): List<Borrower> {
        return mutableListOf(
            Borrower("Bob", 0.075F, 640F),
            Borrower("Jane", 0.069F, 480F),
            Borrower("Fred", 0.071F, 520F),
            Borrower("Mary", 0.104F, 170F),
            Borrower("John", 0.081F, 320F),
            Borrower("Dave", 0.074F, 140F),
            Borrower("Angela", 0.071F, 60F)
        )

    }
}