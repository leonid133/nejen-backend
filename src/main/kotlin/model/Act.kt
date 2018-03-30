package model

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

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