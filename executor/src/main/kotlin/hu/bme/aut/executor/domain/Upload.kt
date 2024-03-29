package hu.bme.aut.executor.domain

import javax.persistence.*

@Entity
@Table(name = "uploads")
class Upload(
    id: Long? = null,

    var name: String,

    var description: String,

    @Lob
    @Basic(fetch = FetchType.LAZY)
    var text: String,

    var userId: Long,

    @OneToMany(
        mappedBy = "upload",
        cascade = [CascadeType.MERGE, CascadeType.PERSIST],
        fetch = FetchType.LAZY
    )
    var pipelines: MutableList<Pipeline> = mutableListOf(),

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE, CascadeType.PERSIST])
    @JoinTable(
        name = "uploads_labels",
        joinColumns = [JoinColumn(name = "upload_id")],
        inverseJoinColumns = [JoinColumn(name = "label_id")]
    )
    var labels: MutableList<Label> = mutableListOf()

) : BaseEntity(id) {

    fun addLabels(labels: List<Label>) {
        labels.forEach { it.uploads.add(this) }
        this.labels.addAll(labels)
    }

    fun addLabel(label: Label) {
        labels.add(label)
        label.uploads.add(this)
    }

    fun removeLabel(label: Label) {
        labels.remove(label)
        label.uploads.remove(this)
    }

    fun removeAllLabels() {
        for (label: Label in labels) {
            label.uploads.remove(this)
        }
        labels.clear()
    }

    fun removeAllPipelines() {
        for (pipeline: Pipeline in pipelines) {
            pipeline.upload = null
        }
        pipelines.clear()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as Upload
        if (id != that.id) return false
        return true
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0
}