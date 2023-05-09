package io.daobab.demo.dao.procedure

import io.daobab.model.ProcedureParameters
import java.math.BigDecimal

class SomeOut : ProcedureParameters(1) {
    val result: BigDecimal
        get() = getValue("test")

    init {
        specifyValue(1, "test", BigDecimal::class.java)
    }
}
