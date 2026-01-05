package com.example.listadapter.model

data class Cat(
    val catId: Int,
    val name: String,
    val age: Int
) {
    fun getId(): String {
        return catId.toString()
    }

    override fun toString(): String {
        return "id = $catId, name = $name, age = $age"
    }
}
