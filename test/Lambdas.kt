package com.pryjda.app.test


fun main(args: Array<String>) {
//    val square = { number: Int -> number * number }
    val square: (Int) -> Int = { number -> number * number }
    val nine = square(3)
    println(nine)

    val squareNo2 = { number2: Int -> number2 * number2 }

    val magnitude100String = { input: Int ->
        val magnitude = input * 100
        magnitude.toString()
    }

    val result: String = magnitude100String(100)
    println(result)

    val that: (Int) -> Int = { three -> three * three }
    println(that(4))

    val more: (String, Int) -> String = { str, int -> str + int }
    val resultString: String = more("text ", 33)
    println(resultString)

    val noReturn: (Int) -> Unit = { num -> println(num) }
    noReturn(123)

    //........................................................................
    val display: (Any) -> Unit = { any -> println(any) }
    display("do wyswietlenia text")

    //Using lambdas as class extension:.........................................
//    val another: String.(Int) -> String = { this + it }

    fun extendString(arg: String, num: Int): String {
        val another: String.(Int) -> String = { this + it }
        return arg.another(num)
    }
    println(extendString("text ", 146))


    //Returning from a Lambda.....................................................
    val calculateGrade = { grade: Int ->
        when (grade) {
            in 0..40 -> "Fails"
            in 41..70 -> "Pass"
            in 71..100 -> "Distinction"
            else -> false
        }
    }
    println(calculateGrade(100))


    val calculateGradeNo2 = fun(grade: Int): String {
        if (grade < 0 || grade > 100) return "Error"
        else if (grade < 40) return "Fails"
        else if (grade < 70) return "Pass"
        return "Distinction"
    }
    println(calculateGradeNo2(50))


    //it ...............................................................................
    // it is a shorthand of a single argument lambda.

    val array = arrayOf(1, 2, 3, 4, 5, 6)
    //longhand:
    array.forEach { item -> println(item * 4) }
    //shorthand:
    array.forEach { println(it * 5) }

    //Implementing lambdas:...............................................................< ?????????????? > w javie też zoabczyć metody z arg jako lambdy
    fun invokeLambda(lambda: (Double) -> Boolean): Boolean {
        return lambda(4.329)
    }

    println(invokeLambda { 5.5 > 9 })

    //Lambda object variable..................................................................

    val lambdaMy = { arg: Double -> arg == 4.329 }

    println(invokeLambda(lambdaMy))

    //Lambda literal outside the brackets...........................................................
    println(invokeLambda { arg -> arg.isNaN() })

    //Method references:............................................................................
    val methodReference = Double::isFinite
    println(invokeLambda(methodReference))
    println(2.22.isFinite())

    //Anonymous inner classes: ..............................................................

//A) object expression:
    val resultBoolean = Lambdas().performEvent(true, object : ActionCallBack {
        override fun success(): String = "success"
        override fun failure(): String = "failure"
    })

    println(resultBoolean)

//    val resultBooleanNo2 = Lambdas().performEvent(true, {}) - to nie tak...

    //B) Lambda expression:
    var lista: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    var listaStringow = lista.map({ it.toString() + "coś" })

    lista.forEach(::print)
    listaStringow.forEach(::print)
    lista.forEach(::print)

    var listaNo2: List<Int> = lista
            .filter { it % 2 == 0 }
            .map { it * it }
            .drop(1)
            .take(1)



    println()
    lista.forEach(::println)
    println()
    listaNo2.forEach(::println)

    lista
            .filter { it % 2 == 0 }
            .map { it * it }
            .drop(1)
            .take(1)
            .forEach(::println)

//    val target = lista.filter { it % 2 == 0 }
//    target.forEach(::println)

    val target = ArrayList<Int>()

    lista.filterTo(target) { it % 2 == 0 }

    target.forEach(::println)
    println()
    println()
    println()
    val targetNo2 = lista
            .map { it * (it + 1) / 2 }
            .mapIndexed { i, n -> "$i: $n" }

    targetNo2.forEach(::println)

//    val letters: List<Char> = listOf("This", "Is", "An", "Example")
//            .flatMap { it.toCharArray() }
//            .filter{Character.isUpperCase(it)}

    //Additional Operations in Kotlin:..........................................
    val numbers = listOf(1, 2, 3)
    val words = listOf("one", "two", "three")
    numbers.zip(words).forEach(::println) // this produces a List<Pair<Int,String>>

    val squares = listOf(1, 2, 3, 4, 5)
            .associate { it to it * it } //this produces a Map<Int,Int>
            .forEach(::println)

    val squaresNo2 = listOf(1, 2, 3, 4, 5)
            .associate { it.toString() + "lala" to "text" }
            .forEach(::println)


    val theList = listOf("one", "two", "three") //read only
    val theMutableList = mutableListOf("one", "two", "three") //read write

    theMutableList.add("four")
    theMutableList.forEach(::println)

    val theSet = setOf("one", "two", "three") //read only
    val theMutableSet = mutableSetOf("one", "two", "three") //read write

    val theMap = mapOf(1 to "one", 2 to "two", 3 to "three") //read only
    val theMutableMap = mutableMapOf(1 to "one", 2 to "two", 3 to "three") //read write


    theSet.forEach(::println)
    theMutableSet.forEach(::println)

    println()
    println()
    println("two" in theMutableSet)
    var listOfNulls = listOf(null, null, null)
    val resultList: List<String?> = theList + theMutableList + listOfNulls + theSet + "coś nowego"

    println(resultList)

    println(resultList - "one")
    println(resultList)
    println(resultList.slice(0..0))
    println(resultList.filterNotNull())

    println(resultList.drop(2)) //usuwa n pierwszych elementów z listy

    println(resultList.filterNotNull().dropWhile { it.length < 4 } + "tutaj")

    println(resultList.filterNotNull().flatMap{ it.toUpperCase().toList()})


}


class Lambdas {

    fun performEvent(decision: Boolean, callBack: ActionCallBack): String {
        return if (decision) callBack.success() else callBack.failure()
    }

}

interface ActionCallBack {
    fun success(): String
    fun failure(): String
}
