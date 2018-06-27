package nejen.models

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

object ActTable : IntIdTable() {
    val rents = reference("rents", RentsTable)
    val shelf = reference("shelf", ShelfTable)
    val start_date = date("start_date")
    val stop_date = date("stop_date")
    val term = varchar("term", 45)
    val payment = varchar("payment", 45)
}

class ActItem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ActItem>(ActTable)

    var rents by RentsItem referencedOn ActTable.rents
    var shelf by ShelfItem referencedOn ActTable.shelf
    var start_date by ActTable.start_date
    var stop_date by ActTable.stop_date
    var term by ActTable.term
    var payment by ActTable.payment
}

class ActDTO(var rents: RentsDTO, var shelf: ShelfDTO, var start_date: DateTime, var stop_date: DateTime, var term: String, var payment: String)

fun ActItem.getDto(): ActDTO {
    val actItem = this
    return transaction {
        return@transaction ActDTO(actItem.rents.getDto(), actItem.shelf.getDto(), actItem.start_date, actItem.stop_date, actItem.term, actItem.payment)
    }
}

fun List<ActItem>.getDto(): List<ActDTO> {
    return this.map { it.getDto() }
}