package nejen

import nejen.models.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
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
                SchemaUtils.drop(TenantsTable, RentsTable, ActTable, ShelfTable, OrdersTable, CashTable)
                SchemaUtils.create(TenantsTable, RentsTable, ActTable, ShelfTable, OrdersTable, CashTable)
                val tenant = TenantsItem.new {
                    name = "One"
                    telefone = "111111111"
                    email = "One@One.ru"
                }
                val rent = RentsItem.new {
                    number = "1"
                    start_date = DateTime()
                    stop_date = DateTime()
                    tenants = tenant
                }
                val shelfi = ShelfItem.new {
                    name = "Arkadiy"
                    price = 110
                }
                val acti = ActItem.new {
                    rents = rent
                    shelf = shelfi
                    start_date = DateTime()
                    stop_date = DateTime()
                    term = "OK"
                    payment = "TRUE"
                }
                val order = OrdersItem.new {
                    act = acti
                    name_item = "PAKET"
                    desc_item = "BOLSHOY PAKET"
                    qty = "2"
                    price = "220"
                }
                val cash = CashItem.new {
                    cash_date = DateTime()
                    orders = order
                    sell = "3"
                    take = "2"
                    discount = "1"
                }
                commit()
            }
        }
    }
}