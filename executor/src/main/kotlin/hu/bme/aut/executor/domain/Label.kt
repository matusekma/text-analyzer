package hu.bme.aut.executor.domain

import javax.persistence.*

@Entity
@Table(name = "labels")
class Label(
        id: Long? = null,

        var name: String,

        @ManyToMany(
                mappedBy = "labels",
                fetch = FetchType.LAZY,
                cascade = [CascadeType.MERGE, CascadeType.PERSIST]
        )
        var uploads: MutableList<Upload> = mutableListOf(),

        var userId: Long,
) : BaseEntity(id) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as Label
        if (id != that.id) return false
        return true
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0
}