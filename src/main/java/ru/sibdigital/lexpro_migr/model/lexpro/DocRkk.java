package ru.sibdigital.lexpro_migr.model.lexpro;

import com.vladmihalcea.hibernate.type.range.PostgreSQLRangeType;
import com.vladmihalcea.hibernate.type.range.Range;
import lombok.*;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "doc_rkk", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@TypeDef(
        typeClass = PostgreSQLRangeType.class,
        defaultForType = Range.class
)
public class DocRkk implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "DOC_RKK_SEQ_GEN", sequenceName = "doc_rkk_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOC_RKK_SEQ_GEN")
    private Long id;

    @Column(name = "rkk_number")
    private String rkkNumber;
    @Column(name = "npa_name")
    private String npaName;
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "introduction_date")
    private Date introductionDate;

    @ManyToOne
    @JoinColumn(name = "id_npa_type", referencedColumnName = "id")
    private ClsNpaType npaType;

    @Column(name = "legislative_basis")
    private String legislativeBasis;

    @ManyToOne
    @JoinColumn(name = "id_law_subject", referencedColumnName = "id")
    private ClsOrganization lawSubject;

    @ManyToOne
    @JoinColumn(name = "id_speaker", referencedColumnName = "id")
    private ClsEmployee speaker;

    @Column(name = "ready_for_session")
    private Boolean readyForSession;

    @ManyToOne
    @JoinColumn(name = "id_responsible_organization", referencedColumnName = "id")
    private ClsOrganization responsibleOrganization;

    @ManyToOne
    @JoinColumn(name = "id_responsible_employee", referencedColumnName = "id")
    private ClsEmployee responsibleEmployee;

    @Column(name = "deadline")
    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "id_session", referencedColumnName = "id")
    private ClsSession session;

    @Column(name = "included_in_agenda")
    private Date includedInAgenda;
    @Column(name = "agenda_number")
    private String agendaNumber;

    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private ClsRkkStatus status;

    @ManyToOne
    @JoinColumn(name = "id_stage", referencedColumnName = "id")
    private ClsRkkStage stage;

    @Column(name = "head_signature")
    private Date headSignature;
    @Column(name = "publication_date")
    private Date publicationDate;
    @Column(name = "time_create")
    private Timestamp timeCreate;
    @Column(name = "time_update")
    private Timestamp timeUpdate;
    @Column(name = "is_archived", nullable = false)
    private Boolean isArchived;
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "sys_period")
    private Range<ZonedDateTime> sysPeriod;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocRkk docRkk = (DocRkk) o;
        return Objects.equals(id, docRkk.id)
                && Objects.equals(rkkNumber, docRkk.rkkNumber)
                && Objects.equals(npaName, docRkk.npaName)
                && Objects.equals(registrationDate, docRkk.registrationDate)
                && Objects.equals(introductionDate, docRkk.introductionDate)
                && Objects.equals(npaType, docRkk.npaType)
                && Objects.equals(legislativeBasis, docRkk.legislativeBasis)
                && Objects.equals(lawSubject, docRkk.lawSubject)
                && Objects.equals(speaker, docRkk.speaker)
                && Objects.equals(readyForSession, docRkk.readyForSession)
                && Objects.equals(responsibleOrganization, docRkk.responsibleOrganization)
                && Objects.equals(responsibleEmployee, docRkk.responsibleEmployee)
                && Objects.equals(deadline, docRkk.deadline)
                && Objects.equals(session, docRkk.session)
                && Objects.equals(includedInAgenda, docRkk.includedInAgenda)
                && Objects.equals(agendaNumber, docRkk.agendaNumber)
                && Objects.equals(status, docRkk.status)
                && Objects.equals(stage, docRkk.stage)
                && Objects.equals(headSignature, docRkk.headSignature)
                && Objects.equals(publicationDate, docRkk.publicationDate)
                && Objects.equals(timeCreate, docRkk.timeCreate)
                && Objects.equals(timeUpdate, docRkk.timeUpdate)
                && Objects.equals(isArchived, docRkk.isArchived)
                && Objects.equals(isDeleted, docRkk.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rkkNumber, npaName, registrationDate, introductionDate, npaType, legislativeBasis,
                lawSubject, speaker, readyForSession, responsibleOrganization, responsibleEmployee, deadline, session,
                includedInAgenda, agendaNumber, status, stage, headSignature, publicationDate, timeCreate, timeUpdate,
                isArchived, isDeleted);
    }
}
