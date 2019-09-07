package cn.bromine0x23.jasypt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JasyptWebApplication

fun main(args: Array<String>) {
	runApplication<JasyptWebApplication>(*args)
}
