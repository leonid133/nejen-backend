package search

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@RestController
class SearchController() {

    @RequestMapping("/tenantry")
    fun greeting(@RequestParam(value = "query", defaultValue = "all") query: String): String {
        when (query) {
            "all" -> return "all tenantry"
            else -> return query
        }

    }
}
