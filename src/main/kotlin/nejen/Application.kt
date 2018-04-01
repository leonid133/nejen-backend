package nejen

import nejen.models.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import java.sql.Connection


@SpringBootApplication
@EnableAutoConfiguration(exclude = [(DataSourceAutoConfiguration::class)])
class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val context = SpringApplication.run(Application::class.java, *args)
            val bean = context.getBean(AppProperties::class.java)
            Database.connect(bean.database.url, driver = bean.database.driver, user = bean.database.username, password = bean.database.password)
            TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
            transaction {
                logger.addLogger(StdOutSqlLogger)
                create(TenantsTable, RentsTable, ActTable, ShelfTable, OrdersTable, CashTable)
            }
        }
    }
}