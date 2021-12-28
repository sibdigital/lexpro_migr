package ru.sibdigital.lexpro_migr.model.zakon;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "EXECUTORS", schema = "", catalog = "")
public class ExecutorsEntity {
    private int execGuid;
    private Date taskDate;

    @Id
    @Column(name = "EXEC_GUID")
    public int getExecGuid() {
        return execGuid;
    }

    public void setExecGuid(int execGuid) {
        this.execGuid = execGuid;
    }

    @Basic
    @Column(name = "TASK_DATE")
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
        return execGuid == that.execGuid && Objects.equals(taskDate, that.taskDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(execGuid, taskDate);
    }
}
