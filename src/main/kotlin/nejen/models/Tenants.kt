package nejen.models

import jdk.nashorn.internal.ir.annotations.Ignore
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.transactions.transaction

object TenantsTable : IntIdTable() {
    val name = varchar("name", 45)
    val telefone = varchar("telefone", 45)
    val email = varchar("email", 45)
}

class TenantsItem(id: EntityID<Int>) : IntEntity(id) {
    @Ignore
    companion object : IntEntityClass<TenantsItem>(TenantsTable)

    var name: String by TenantsTable.name
    var telefone: String by TenantsTable.telefone
    var email: String by TenantsTable.email
}

class TenantsDTO(var name: String, var telefone: String, var email: String)

fun TenantsItem.getDto(): TenantsDTO {
    val tenantsItem = this
    return transaction {
        return@transaction TenantsDTO(tenantsItem.name, tenantsItem.telefone, tenantsItem.email)
    }
}

fun List<TenantsItem>.getDto(): List<TenantsDTO> {
    return this.map { it.getDto() }
}
