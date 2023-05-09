package io.daobab.demo.dao

import io.daobab.model.Entity
import io.daobab.model.ResponseWrapper
import io.daobab.query.base.Query
import io.daobab.target.buffer.remote.RemoteClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class SakilaRemote : RemoteClient() {
    @Value("\${server.port}")
    private val port: String? = null
    override fun callEndpoint(query: Query<out Entity, *, *>, singleResult: Boolean): ResponseWrapper {
        val url = "http://www.daobab.io/remote/export/db"

        //you may use the local url, but your application must be working
//        final String url = "http://localhost:" + port + "/export/db";
        val restTemplate = RestTemplate()
        return restTemplate.postForObject(
            url,
            query.toRemote(singleResult),
            ResponseWrapper::class.java
        )!!
    }
}
