package model

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object ShelfTable : IntIdTable() {
    val name = varchar("name", 45)
    val price = integer("price")
}

class ShelfItem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ShelfItem>(ShelfTable)

    var name by ShelfTable.name
    var price by ShelfTable.price
}