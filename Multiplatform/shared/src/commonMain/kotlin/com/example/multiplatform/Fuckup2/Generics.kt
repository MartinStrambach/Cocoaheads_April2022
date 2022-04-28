package com.example.multiplatform.Fuckup2

fun functionWithGenericReturnValue(): Description {
    return Description("My age is ", mapOf(Pair(28, "!")))
}

typealias Description = Pair<String, Map<Int, String>>

//-----------------------------------------------------------------

data class DescriptionData(
    val value1: String,
    val value2: Map<Int, String>
)

fun functionWithDataClassReturnValue(): DescriptionData {
    return DescriptionData("My age is", mapOf(Pair(28, "!")))
}