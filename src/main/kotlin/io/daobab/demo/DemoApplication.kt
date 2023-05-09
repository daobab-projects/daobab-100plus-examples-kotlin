package io.daobab.demo

import io.daobab.demo.base.ServiceBase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.BeansException
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.system.exitProcess

@SpringBootApplication
class DemoApplication : ApplicationRunner, ApplicationContextAware {

    private var applicationContext: ApplicationContext? = null

    companion object {

        private val log: Logger = LoggerFactory.getLogger(DemoApplication::class.java)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, *args)
        }
    }

    @Throws(Exception::class)
    override fun run(args: ApplicationArguments) {
        val singleExecution = AtomicBoolean(false)
        args.sourceArgs
            .filter { it.startsWith("io.daobab") }
            .map {
                try {
                    return@map Class.forName(it)
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                    return@map null
                }
            }
            .filter { Objects.nonNull(it) }
            .filter { ServiceBase::class.java.isAssignableFrom(it!!) }
            .onEach { log.info("*********************************************************************************************") }
            .onEach { log.info("executing an example: {}", it?.name) }
            .onEach { log.info("*********************************************************************************************") }
            .map { applicationContext!!.getBean(it as Class<*>) }
            .map { ServiceBase::class.java.cast(it) }
            .onEach { singleExecution.set(true) }
            .forEach { this.execute(it as ServiceBase<*>) }
        if (singleExecution.get()) {
            log.info("*************************************** -= the end =- ***************************************")
            exitProcess(0)
        }
    }

    private fun <T> execute(sb: ServiceBase<T>) {
        sb.beforeCall()
        sb.afterCall(sb.call())
    }

    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

}
