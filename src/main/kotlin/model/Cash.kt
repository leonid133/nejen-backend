package model

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

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