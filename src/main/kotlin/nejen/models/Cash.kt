package nejen.models

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

object CashTable : IntIdTable() {
    val cash_date = date("cash_date")
    val orders = reference("orders", OrdersTable)
    val sell = varchar("sell", 45)
    val take = varchar("take", 45)
    val discount = varchar("discount", 45)
}

class CashItem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CashItem>(CashTable)

    var cash_date by CashTable.cash_date
    var orders by OrdersItem referencedOn CashTable.orders
    var sell by CashTable.sell
    var take by CashTable.take
    var discount by CashTable.discount
}

class CashDTO(var cash_date: DateTime, var orders: OrdersDTO, var sell: String, var take: String, var discount: String)

fun CashItem.getDto(): CashDTO {
    val cashItem = this
    return transaction {
        return@transaction CashDTO(cashItem.cash_date, cashItem.orders.getDto(), cashItem.sell, cashItem.take, cashItem.discount)
    }
}

fun List<CashItem>.getDto(): List<CashDTO> {
    return this.map { it.getDto() }
}