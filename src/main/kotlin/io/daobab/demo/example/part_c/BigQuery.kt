package io.daobab.demo.example.part_c

import io.daobab.demo.DemoApplication
import io.daobab.demo.base.ServiceBase
import io.daobab.parser.ParserGeneral
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.util.*

/**
 * ---------------------------------------------------------
 * - big query in use
 * ---------------------------------------------------------
 */
@Component
class BigQuery : ServiceBase<List<String>>(), ParserGeneral {
    override fun call(): List<String> {
        val c = db.tabCustomer
        val city = db.tabCity
        val country = db.tabCountry
        val r = db.tabRental
        val p = db.tabPayment
        val a = db.tabAddress
        val s = db.tabStaff
        val f = db.tabFilm
        val i = db.tabInventory
        return db.select(
            c.colFirstName(), c.colLastName(), f.colTitle(), city.colCity(), p.colPaymentDate(), p.colAmount(),
            country.colCountry(), s.colFirstName(), s.colLastName()
        )
            .join(r, c.colCustomerId())
            .join(i, r.colInventoryId())
            .join(p, c.colCustomerId(), r.colStaffId())
            .join(s, r.colStaffId())
            .join(a, c.colAddressId())
            .join(city, a.colCityId())
            .join(country, city.colCountryId())
            .join(f, i.colFilmId())
            .orderDescBy(p.colPaymentDate())
            .limitBy(10)
            .whereEqual(c.colID(), 1)
            .map {
                val sb: StringBuffer = StringBuffer()
                    .append("Customer:")
                    .append(it.getValue(c.colFirstName()))
                    .append(",")
                    .append(it.getValue(c.colLastName()))
                    .append(" living in ")
                    .append(it.getValue(city.colCity()))
                    .append(",")
                    .append(it.getValue(country.colCountry()))
                    .append(" rent on ")
                    .append(toSting(it.getValue(p.colPaymentDate()), "EEEE, dd MMMM yyyy (HH:mm)", Locale.ENGLISH))
                    .append(" a movie: ")
                    .append(it.getValue(f.colTitle()))
                    .append(" has paid for it: ")
                    .append(it.getValue(p.colAmount()))
                    .append(" and was served by ")
                    .append(it.getValue(s.colFirstName()))
                    .append(" ")
                    .append(
                        it.getValue(s.colLastName())
                    )
                sb.toString()
            }
            .findMany()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, BigQuery::class.java.name)
        }
    }
}
