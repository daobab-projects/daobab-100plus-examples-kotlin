package io.daobab.demo

import io.daobab.generator.template.TemplateLanguage
import org.springframework.boot.SpringApplication
import org.springframework.stereotype.Component
import java.sql.Driver

@Component
class ReverseEngineering : H2Database() {
    override fun call() {

        //STEP 2: set the language and package
        generator.setLanguage(TemplateLanguage.KOTLIN)
        generator.setPackage("io.daobab.demo.dao")
        //select the destination path for generated classes
        generator.path = "E:\\Daobabx\\"

        //STEP 3: execute reverse engineering
        generator.reverseEngineering(url, user, pass, driverClass as Class<out Driver>)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, ReverseEngineering::class.java.name)
        }
    }
}
