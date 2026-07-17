package com.anmol.accountingsoftware.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "journal_entries")
public class JournalEntryEntity extends AuditableEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private LocalDate transactionDate;

    @OneToMany(
    mappedBy = "journalEntry",

    cascade = CascadeType.ALL,

    orphanRemoval = true
    )

    private List<JournalLineEntity> journalLines=new java.util.ArrayList<>();

    public void addJournalLine(JournalLineEntity line) {
    journalLines.add(line);
    line.setJournalEntry(this);
    }
    
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String referenceNo;


    //GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public List<JournalLineEntity> getJournalLines() {
        return journalLines;
    }

    public void setJournalLines(List<JournalLineEntity> journalLines) {
        this.journalLines = journalLines;
    }


}
