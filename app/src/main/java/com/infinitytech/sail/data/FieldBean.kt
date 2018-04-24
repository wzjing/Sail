package com.infinitytech.sail.data

data class FieldBean(val fields: List<Field>, val popular: List<Field>)

data class Field(val id: Int, val name: String)