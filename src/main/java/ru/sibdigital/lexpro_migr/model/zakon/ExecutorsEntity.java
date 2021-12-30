package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "executors")
public class ExecutorsEntity {
    @Id
    @Column(name = "exec_guid")
    private Long execGuid;

    @Basic
    @Column(name = "task_id")
    private Long taskId;

    @Basic
    @Column(name = "person_id")
    private Long personId;

    @Basic
    @Column(name = "task_date")
    private Date taskDate;

    public Long getExecGuid() {
        return execGuid;
    }

    public void setExecGuid(Long execGuid) {
        this.execGuid = execGuid;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExecutorsEntity that = (ExecutorsEntity) o;
        return Objects.equals(execGuid, that.execGuid) && Objects.equals(taskId, that.taskId) && Objects.equals(personId, that.personId) && Objects.equals(taskDate, that.taskDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(execGuid, taskId, personId, taskDate);
    }
}
