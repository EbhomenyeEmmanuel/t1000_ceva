package com.example.t1000_ceva.domain

data class AirtimeOperator(
    val id: Long,
    val name: String
) {

    override fun toString(): String {
        return "AirtimeOperator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}'
    }
}
