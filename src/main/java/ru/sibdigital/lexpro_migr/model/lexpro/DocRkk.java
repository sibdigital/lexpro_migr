package ru.sibdigital.lexpro_migr.model.lexpro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "doc_rkk", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class DocRkk implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "DOC_RKK_SEQ_GEN", sequenceName = "doc_rkk_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOC_RKK_SEQ_GEN")
    private Long id;
    private String rkkNumber;
    private String npaName;
    private Date registrationDate;
    private Date introductionDate;

    @ManyToOne
    @JoinColumn(name = "id_npa_type", referencedColumnName = "id")
    private ClsNpaType npaType;

    private String legislativeBasis;

    @ManyToOne
    @JoinColumn(name = "id_law_subject", referencedColumnName = "id")
    private ClsOrganization lawSubject;

    @ManyToOne
    @JoinColumn(name = "id_speaker", referencedColumnName = "id")
    private ClsEmployee speaker;

    private Boolean readyForSession;

    @ManyToOne
    @JoinColumn(name = "id_responsible_organization", referencedColumnName = "id")
    private ClsOrganization responsibleOrganization;

    @ManyToOne
    @JoinColumn(name = "id_responsible_employee", referencedColumnName = "id")
    private ClsEmployee responsibleEmployee;

    private Date deadline;

    @ManyToOne
    @JoinColumn(name = "id_session", referencedColumnName = "id")
    private ClsSession session;

    private Date includedInAgenda;
    private String agendaNumber;

    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id")
    private ClsRkkStatus status;

    @ManyToOne
    @JoinColumn(name = "id_stage", referencedColumnName = "id")
    private ClsRkkStage stage;

    private Date headSignature;
    private Date publicationDate;
    private Timestamp timeCreate;
    private Timestamp timeUpdate;
    private Boolean isArchived;
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rkk_number")
    public String getRkkNumber() {
        return rkkNumber;
    }
    public void setRkkNumber(String rkkNumber) {
        this.rkkNumber = rkkNumber;
    }

    @Basic
    @Column(name = "npa_name")
    public String getNpaName() {
        return npaName;
    }
    public void setNpaName(String npaName) {
        this.npaName = npaName;
    }

    @Basic
    @Column(name = "registration_date")
    public Date getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Basic
    @Column(name = "introduction_date")
    public Date getIntroductionDate() {
        return introductionDate;
    }
    public void setIntroductionDate(Date introductionDate) {
        this.introductionDate = introductionDate;
    }

    public ClsNpaType getNpaType() {
        return npaType;
    }
    public void setNpaType(ClsNpaType npaType) {
        this.npaType = npaType;
    }

    @Basic
    @Column(name = "legislative_basis")
    public String getLegislativeBasis() {
        return legislativeBasis;
    }
    public void setLegislativeBasis(String legislativeBasis) {
        this.legislativeBasis = legislativeBasis;
    }

    public ClsOrganization getLawSubject() {
        return lawSubject;
    }
    public void setLawSubject(ClsOrganization lawSubject) {
        this.lawSubject = lawSubject;
    }

    public ClsEmployee getSpeaker() {
        return speaker;
    }
    public void setSpeaker(ClsEmployee speaker) {
        this.speaker = speaker;
    }

    @Basic
    @Column(name = "ready_for_session")
    public Boolean getReadyForSession() {
        return readyForSession;
    }
    public void setReadyForSession(Boolean readyForSession) {
        this.readyForSession = readyForSession;
    }

    public ClsOrganization getResponsibleOrganization() {
        return responsibleOrganization;
    }
    public void setResponsibleOrganization(ClsOrganization responsibleOrganization) {
        this.responsibleOrganization = responsibleOrganization;
    }

    public ClsEmployee getResponsibleEmployee() {
        return responsibleEmployee;
    }
    public void setResponsibleEmployee(ClsEmployee responsibleEmployee) {
        this.responsibleEmployee = responsibleEmployee;
    }

    @Basic
    @Column(name = "deadline")
    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public ClsSession getSession() {
        return session;
    }
    public void setSession(ClsSession session) {
        this.session = session;
    }

    @Basic
    @Column(name = "included_in_agenda")
    public Date getIncludedInAgenda() {
        return includedInAgenda;
    }
    public void setIncludedInAgenda(Date includedInAgenda) {
        this.includedInAgenda = includedInAgenda;
    }

    @Basic
    @Column(name = "agenda_number")
    public String getAgendaNumber() {
        return agendaNumber;
    }
    public void setAgendaNumber(String agendaNumber) {
        this.agendaNumber = agendaNumber;
    }

    public ClsRkkStatus getStatus() {
        return status;
    }
    public void setStatus(ClsRkkStatus status) {
        this.status = status;
    }

    public ClsRkkStage getStage() {
        return stage;
    }
    public void setStage(ClsRkkStage stage) {
        this.stage = stage;
    }

    @Basic
    @Column(name = "head_signature")
    public Date getHeadSignature() {
        return headSignature;
    }
    public void setHeadSignature(Date headSignature) {
        this.headSignature = headSignature;
    }

    @Basic
    @Column(name = "publication_date")
    public Date getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Basic
    @Column(name = "time_create")
    public Timestamp getTimeCreate() {
        return timeCreate;
    }
    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    @Basic
    @Column(name = "time_update")
    public Timestamp getTimeUpdate() {
        return timeUpdate;
    }
    public void setTimeUpdate(Timestamp timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    @Basic
    @Column(name = "is_archived", nullable = false)
    public Boolean getIsArchived() {
        return isArchived;
    }
    public void setIsArchived(Boolean isArchived) {
        this.isArchived = isArchived;
    }

    @Basic
    @Column(name = "is_deleted", nullable = false)
    public Boolean getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

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
