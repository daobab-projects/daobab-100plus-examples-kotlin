package io.daobab.demo

import com.mysql.cj.jdbc.Driver
import io.daobab.generator.DaobabGenerator
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CheckConnection


var url =
    "jdbc:mysql://127.0.0.1:3306/sakila?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false"
var user = "root"
var pass = "admin"
var driverClassName: Class<*> = Driver::class.java

var generator = DaobabGenerator()

fun main(args: Array<String>) {

    //STEP 1: check the database connection
    generator.checkConnection(url, user, pass, Driver::class.java)
}
