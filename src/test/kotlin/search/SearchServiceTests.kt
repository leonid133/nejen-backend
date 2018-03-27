package search


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.*
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
class SearchServiceTests(@Autowired private val restTemplate: TestRestTemplate) {

    @Test
    fun findAll() {
        val content = """all tenantry"""
        assertEquals(content, restTemplate.getForObject<String>("/tenantry"))
    }

    @Test
    fun findByQuery(){
        val content = """query"""
        assertEquals(content, restTemplate.getForObject<String>("/tenantry?query=${content}"))
    }


}
