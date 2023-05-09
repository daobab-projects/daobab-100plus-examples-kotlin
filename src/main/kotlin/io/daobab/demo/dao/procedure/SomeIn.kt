package io.daobab.demo.dao.procedure

import io.daobab.model.ProcedureParameters

class SomeIn(test: String?, age: Int?) : ProcedureParameters(2) {
    var test: String?
        get() = getValue("test")
        set(test) {
            setValue(1, test)
        }
    val age: String
        get() = getValue("age")

    fun setAge(age: Int?) {
        setValue(2, age)
    }

    init {
        specifyValue(1, "test", String::class.java)
        specifyValue(2, "age", Int::class.java)
        this.test = test
        setAge(age)
    }
}
