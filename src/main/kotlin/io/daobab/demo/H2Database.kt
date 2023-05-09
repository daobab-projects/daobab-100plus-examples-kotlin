package io.daobab.demo

import io.daobab.demo.base.ServiceBase
import io.daobab.generator.DaobabGenerator
import org.springframework.beans.factory.annotation.Value

abstract class H2Database : ServiceBase<Unit>() {
    @Value("\${spring.datasource.url}")
    protected var url: String? = null

    @Value("\${spring.datasource.username}")
    protected var user: String? = null

    @Value("\${spring.datasource.password}")
    protected var pass: String? = null

    @Value("\${spring.datasource.driverClassName}")
    protected var driverClassName: String? = null
    protected val driverClass: Class<*>
        get() = try {
            Class.forName(driverClassName)
        } catch (e: ClassNotFoundException) {
            throw RuntimeException(e)
        }

    companion object {
        @JvmStatic
        protected var generator: DaobabGenerator = DaobabGenerator()
    }
}
