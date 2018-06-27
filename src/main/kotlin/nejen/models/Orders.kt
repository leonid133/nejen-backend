package nejen.models

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction

object OrdersTable : IntIdTable() {
    val act = reference("act", ActTable)
    val name_item = varchar("name_item", 45)
    val desc_item = varchar("desc_item", 45)
    val qty = varchar("qty", 45)
    val price = varchar("price", 45)
}

class OrdersItem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<OrdersItem>(OrdersTable)

    var act by ActItem referencedOn OrdersTable.act
    var name_item by OrdersTable.name_item
    var desc_item by OrdersTable.desc_item
    var qty by OrdersTable.qty
    var price by OrdersTable.price
}


class OrdersDTO(var act: ActDTO, var name_item: String, var desc_item: String, var qty: String, var price: String)

fun OrdersItem.getDto(): OrdersDTO {
    val ordersItem = this
    return transaction {
        return@transaction OrdersDTO(ordersItem.act.getDto(), ordersItem.name_item, ordersItem.desc_item, ordersItem.qty, ordersItem.price)
    }
}

fun List<OrdersItem>.getDto(): List<OrdersDTO> {
    return this.map { it.getDto() }
}