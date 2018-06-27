package nejen.controllers

import nejen.AppProperties
import nejen.models.*
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController {

    @Autowired
    lateinit var properties: AppProperties

    @RequestMapping("/tenantry")
    fun getTenantry(): List<TenantsDTO> {
        return transaction {
            logger.addLogger(StdOutSqlLogger)
            TenantsItem.all().toList()
        }.getDto()
    }

    @RequestMapping("/cash")
    fun getCash(): List<CashDTO> {
        return transaction {
            logger.addLogger(StdOutSqlLogger)
            CashItem.all().toList()
        }.getDto()
    }
}
