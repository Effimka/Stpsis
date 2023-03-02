package com.example.demo.Types;

import java.time.LocalDateTime;

public class reportViewTemplate {
    public Long id;
    public LocalDateTime date;
    public int generalRisk;

    public reportViewTemplate() {}

    public reportViewTemplate(Long id, LocalDateTime date, int generalRisk) {
        this.id = id;
        this.date = date;
        this.generalRisk = generalRisk;
    }
}
