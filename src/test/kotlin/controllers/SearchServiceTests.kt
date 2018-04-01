package controllers


import nejen.Application
import nejen.models.TenantsDTO
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [Application::class], webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SearchServiceTests(@Autowired private val restTemplate: TestRestTemplate) {

    @Test
    fun findAll() {
        /*assertTrue(restTemplate.getForObject<List<TenantsDTO>>("/tenantry")!!.isNotEmpty())*/
    }

    @Test
    fun findByQuery(){
        /*assertTrue(restTemplate.getForObject<String>("/tenantry?query=content")!!.isNotEmpty())*/
    }


}
