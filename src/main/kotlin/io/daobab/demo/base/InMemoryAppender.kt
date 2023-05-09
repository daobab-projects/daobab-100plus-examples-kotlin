package io.daobab.demo.base

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.AppenderBase
import io.daobab.demo.dao.SakilaDataBase
import io.daobab.parser.ParserGeneral
import org.springframework.context.annotation.Configuration
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Configuration
class InMemoryAppender : AppenderBase<ILoggingEvent>(), ParserGeneral {
    override fun append(event: ILoggingEvent) {
        Companion.eventMap[System.nanoTime()] = event
    }

    val eventMap: Map<Long, ILoggingEvent>
        get() = Companion.eventMap

    fun clear() {
        Companion.eventMap.clear()
    }

    val history: String
        get() {
            val timeborder = System.currentTimeMillis() - 5 * 60 * 1000 + 1000 //5 min
            val map = Companion.eventMap
            val sb = StringBuilder()
            val keys = TreeSet<Long>()
            keys.addAll(map.keys)
            val it = keys.descendingIterator()
            while (it.hasNext()) {
                val key = it.next()
                if (key < timeborder) {
                    map.remove(key)
                    continue
                }
                val record = map[key]
                val query = record!!.loggerName.endsWith(SakilaDataBase::class.java.simpleName)
                val msg = record.message
                if ("<hr>" == msg) {
                    sb.append(msg)
                    continue
                }
                sb.append(toSting(Date(record.timeStamp), "HH:mm:SSS - "))
                    .append(if (query) "<b><big><code>" + record.message + "</b></big></code>" else record.message)
                    .append("<br>")
            }
            return sb.toString()
        }

    companion object {
        //:(
        private val eventMap: ConcurrentMap<Long, ILoggingEvent> = ConcurrentHashMap()
    }
}
