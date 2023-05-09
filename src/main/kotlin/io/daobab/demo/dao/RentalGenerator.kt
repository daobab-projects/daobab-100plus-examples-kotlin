package io.daobab.demo.dao


import io.daobab.demo.dao.table.Rental
import io.daobab.statement.function.FunctionWhispererH2
import io.daobab.target.database.DataBaseIdGeneratorSupplier
import io.daobab.target.database.QueryTarget
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class RentalGenerator : DataBaseIdGeneratorSupplier<BigDecimal>, FunctionWhispererH2 {

    val tabRental=Rental()

    override fun generateId(currentTarget: QueryTarget): BigDecimal {
        return currentTarget.select(max(tabRental.colID())).findFirst().orElse(BigDecimal.ZERO).add(BigDecimal.ONE)
    }
}
