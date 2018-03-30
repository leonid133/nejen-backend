package model

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object RentsTable : IntIdTable() {
    val number = varchar("number", 45)
    val start_date = date("start_date")
    val stop_date = date("stop_date")
    val tenants = reference("tenants", TenantsTable)
}

class RentsItem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RentsItem>(RentsTable)

    var number by RentsTable.number
    var start_date by RentsTable.start_date
    var stop_date by RentsTable.stop_date
    var tenants by TenantsItem referencedOn RentsTable.tenants
}