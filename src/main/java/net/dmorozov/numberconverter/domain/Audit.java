package net.dmorozov.numberconverter.domain;

import net.dmorozov.numberconverter.converters.ConverterType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "audit")
public class Audit {

    public Audit() {
        logTime = LocalDateTime.now(ZoneOffset.UTC);
    }

    @Id
    @GeneratedValue
    @Column(name="ID", unique = true, nullable = false)
    private Long id;

    @Column(name="LOG_TIME")
    private LocalDateTime logTime;

    @Column(name = "CONVERTER_TYPE")
    @Enumerated(EnumType.STRING)
    private ConverterType converterType;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "RESULT")
    private String result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public ConverterType getConverterType() {
        return converterType;
    }

    public void setConverterType(ConverterType converterType) {
        this.converterType = converterType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
