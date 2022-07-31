package domain

data class Borrower(
    val name: String = "",
    val rate: Float = 500F,
    val moneyAvailable: Float = 0F
) {
    fun retrieveAllBorrowersAvailable(dataReader:DataReader):List<Borrower>
    {
        return dataReader.retrieveAll()
    }
}