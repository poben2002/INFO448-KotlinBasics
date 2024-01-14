package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {
    return when(arg) {
        is String -> when {
            arg == "Hello" -> "world"
            else -> "Say what?"
        }
        is Int -> when {
            arg == 0 -> "zero"
            arg == 1 -> "one"
            arg in 2..10 -> "low number"
            else -> "a number"
        }
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int = lhs + rhs

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int = lhs - rhs

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int) = op(lhs, rhs)

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}
// write a class "Money"
class Money(val amount: Int, val currency: String) {
    init {
        val acceptedCurrencies = setOf("USD", "EUR", "CAN", "GBP")
        if (currency !in acceptedCurrencies) {
            throw IllegalArgumentException("Not an accepted currency")
        }
        if (amount < 0) {
            throw IllegalArgumentException("Amount must be at least 0")
        }
    }

    fun convert(newCurrency: String): Money {
        var default = 10
        var newAmount = default
        when (this.currency) {
            "USD" -> when (newCurrency) {
                "GBP" -> newAmount = 5
                "USD" -> newAmount = 10
                "EUR" -> newAmount = 15
                "CAN" -> newAmount = 15
            }

            "GBP" -> when (newCurrency) {
                "GBP" -> newAmount = 5
                "USD" -> newAmount = 10
                "EUR" -> newAmount = 15
            }

            "EUR" -> when (newCurrency) {
                "GBP" -> newAmount = 5
                "USD" -> newAmount = 10
                "EUR" -> newAmount = 15
            }

            "CAN" -> when(newCurrency) {
                "USD" -> newAmount = 12
            }
        }
        return Money(newAmount, newCurrency)
    }

    operator fun plus(other: Money): Money {
        var changeMoney = other.convert(this.currency)
        return(Money(changeMoney.amount + this.amount, this.currency))
    }
}

