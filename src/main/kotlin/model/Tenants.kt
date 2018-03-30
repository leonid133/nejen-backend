package model

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object TenantsTable : IntIdTable() {
    val name = varchar("name", 45)
    val telefone = varchar("telefone", 45)
    val email = varchar("email", 45)
}

class TenantsItem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TenantsItem>(TenantsTable)

    var name by TenantsTable.name
    var telefone by TenantsTable.telefone
    var email by TenantsTable.email
}
