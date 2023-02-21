package com.example.numberguessinggame

fun main() {
    val number = generateNumber()
    var numberGuessed = userInput(number)

    while (!numberGuessed) {
        numberGuessed = userInput(number)
    }
    println("Congratulations! You have found the number!")
}

fun generateNumber(): String {
    val digits = mutableListOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
    var number = ""
    digits.shuffle()
    for (i in 0 until 4) {
        number += digits[i]
    }
    return number
}

fun userInput(number: String): Boolean {
    print("Enter a 4-digit number: ")
    val input = readLine().toString()
    val inputValid = validateInput(input)
    if(inputValid){
        val numberFound = checkResult(number, input)
        displayOutput(number, input)
        return numberFound
    }
    return false
}

fun getNrCorrectDigits(number: String,  input: String): Int {
    var counter = 0

    for(i in 0..3) {
        for(j in 0..3){
            if(input[i] == number[j]) {
                counter++
            }
        }
    }
    return counter
}

fun getNrCorrectDigitsAtPosition(number: String,  input: String): Int {
    var counter = 0

    for(i in 0..3) {
        if(input[i] == number[i]) {
            counter++
        }
    }
    return counter
}

fun validateInput(input: String): Boolean {
    try{
        //if(Integer.parseInt(input) in 1000 .. 9999) { // does not work since e.g. "0192" is not in the range
        if(input.length == 4 && Integer.parseInt(input) < 9999) {
            return true
        }
        else {
            throw StringIndexOutOfBoundsException("Not a 4 digit number!")
        }
    }
    catch(e: NumberFormatException) {
        println("Invalid input! Not a number!")
    }
    catch(e: StringIndexOutOfBoundsException){
        println("Invalid input! Please enter a 4 digit number.")
    }
    return false
}

fun displayOutput(number: String, input: String) {
    println("${getNrCorrectDigits(number, input)}:${getNrCorrectDigitsAtPosition(number, input)}")
}

fun checkResult(number: String, input: String): Boolean {
    if (Integer.parseInt(input) == Integer.parseInt(number)) {
        return true
    }
    return false
}