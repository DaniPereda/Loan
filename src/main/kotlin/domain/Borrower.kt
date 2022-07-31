package domain

data class Borrower(
    val name: String = "",
    val rate: Float = 500F,
    val moneyAvailable: Float = 0F
) {
}