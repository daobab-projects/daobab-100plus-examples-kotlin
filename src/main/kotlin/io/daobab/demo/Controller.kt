package io.daobab.demo

import io.daobab.demo.base.InMemoryAppender
import io.daobab.demo.base.ServiceBase
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.nio.charset.StandardCharsets
import java.util.*

@RestController
@RequestMapping("/")
class Controller {
    protected var log = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var inMemoryConsole: InMemoryAppender

    @Autowired
    private lateinit var examples: List<ServiceBase<*>>

    private fun executeExampleNo(no: String?) {
        if (no == null) return
        for (service in examples) {
            if (service.javaClass.simpleName.equals(no, ignoreCase = true)) {
                service.callService()
                return
            }
        }
        log.info("I can't find such example")
    }

    @GetMapping("/")
    fun page(): String {
        return load()
            .replace("_S_SERVICES_E_", getServices(null))
            .replace("_S_LOG_E_", inMemoryConsole.history)
    }

    private fun getServices(selected: String?): String {
        val sb = StringBuilder()
        sb.append("<option").append(if (selected == null) " selected " else "")
            .append(">Choose Daobab example to execute</option>")
        for (service in examples) {
            val chosen = service.javaClass.simpleName == selected
            sb.append("<option ").append(if (chosen) " selected " else "").append("value=\"")
                .append(service.javaClass.simpleName).append("\">").append(service.javaClass.simpleName)
                .append("</option>")
        }
        return sb.toString()
    }

    @GetMapping("/test")
    fun test(@RequestParam(value = "name", required = false) no: String?): String {
        inMemoryConsole.clear()
        no?.let { executeExampleNo(it) }
        return load()
            .replace("_S_SERVICES_E_", getServices(no))
            .replace("_S_LOG_E_", inMemoryConsole.history)
    }

    @GetMapping("/log")
    fun log(): String {
        return inMemoryConsole.history
    }

    fun load(): String {
        return try {
            Scanner(ClassPathResource("page.html").inputStream, StandardCharsets.UTF_8).useDelimiter("\\A").next()
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}
