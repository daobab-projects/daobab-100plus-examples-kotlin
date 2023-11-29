package io.daobab.demo.base

import io.daobab.demo.dao.SakilaDataBase
import io.daobab.demo.dao.SakilaRemote
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import java.util.function.Supplier

abstract class ServiceBase<V> : ToTableConverter {
    var log = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var db: SakilaDataBase

    @Autowired
    lateinit var remote: SakilaRemote

    fun callService(): V? {
        return call { this.call() }
    }

    abstract fun call(): V

    fun beforeCall() {}
    fun call(scenario: Supplier<V>): V? {
        log.info("<hr>")
        log.info("Executing...." + this.javaClass.simpleName)
        log.info("<div><br><i>" + info().replace("\n".toRegex(), "<br>") + "</i><div>")
        var rv: V? = null
        try {
            rv = scenario.get()
            log.info("result:\n" + resultToTable(rv))
        } catch (e: Exception) {
            log.info("error:", e)
        }
        return rv
    }

    fun afterCall(data: V?) {
        if (data == null) {
            return
        }
        log.info("************************************ -= result =- ************************************")
        if (data is Collection<*>) {
            (data as Collection<*>).forEach { log.info(it.toString()) }
        } else if (data is Array<*>) {
            Arrays.stream(data as Array<*>?).forEach {log.info(it.toString()) }
        } else {
            log.info(data.toString())
        }
    }

    protected fun info(): String {
        return ""
    }
}
