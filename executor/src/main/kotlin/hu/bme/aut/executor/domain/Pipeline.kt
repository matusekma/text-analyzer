package hu.bme.aut.executor.domain

import javax.persistence.*

@Entity
@Table(name = "pipelines")
class Pipeline(
    id: Long? = null,
    var name: String,
    @Enumerated(EnumType.STRING)
    var language: Language,
    var userId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    var upload: Upload?,

    @ElementCollection(targetClass = JobType::class)
    @Column(name = "job")
    var jobs: Set<JobType>
) : BaseEntity(id)