package nejen.models

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction

object ShelfTable : IntIdTable() {
    val name = varchar("name", 45)
    val price = integer("price")
}

class ShelfItem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ShelfItem>(ShelfTable)

    var name by ShelfTable.name
    var price by ShelfTable.price
}

class ShelfDTO(var name: String, var price: Int)

fun ShelfItem.getDto(): ShelfDTO {
    val shelfItem = this
    return transaction {
        return@transaction ShelfDTO(shelfItem.name, shelfItem.price)
    }
}

fun List<ShelfItem>.getDto(): List<ShelfDTO> {
    return this.map { it.getDto() }
}