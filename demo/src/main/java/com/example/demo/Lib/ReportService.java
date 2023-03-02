package com.example.demo.Lib;

import com.example.demo.Models.Emploee;
import com.example.demo.Models.Report;
import com.example.demo.Types.reportViewTemplate;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repo.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ReportService {
    public ReportRepo reportRepo;

    public final EmploeeService emploeeService;

    @Autowired
    public ReportService(ReportRepo reportRepo, EmploeeService emploeeService) {
        this.reportRepo = reportRepo;
        this.emploeeService = emploeeService;
    }

    public Report findReport(Long id){
        return reportRepo.findAll().stream().filter(report -> report.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public ArrayList<reportViewTemplate> AllReports() {
        ArrayList<reportViewTemplate> result = new ArrayList<>();
        int generalRisk = 0;
        for(Report report : reportRepo.findAll())
        {
            generalRisk = (report.getBio() + report.getIntellectual() +
                    report.getMoral() + report.getPsycho() + report.getUnreability()) / 5;
            result.add(new reportViewTemplate(report.getId(), report.getDate(), generalRisk));
        }
        return result;
    }

    public boolean makeReport()
    {
        Report report = new Report();
        report.setBio(0);
        report.setIntellectual(0);
        report.setMoral(0);
        report.setUnreability(0);
        report.setPsycho(0);
        int size = emploeeService.AllEmploee().size();
        if(size == 0)
            size = 1;
        for(Emploee emploee : emploeeService.AllEmploee()) {
            if(emploee.getBio() == -1 || emploee.getMoral() == -1)
                return false;
            report.setBio(report.getBio() + emploee.getBio());
            report.setIntellectual(report.getIntellectual() + emploee.getIntellectual());
            report.setMoral(report.getMoral() + emploee.getMoral());
            report.setUnreability(report.getUnreability() + emploee.getUnreability());
            report.setPsycho(report.getPsycho() + emploee.getUnreability());
        }
        report.setBio(report.getBio() / size);
        report.setIntellectual(report.getIntellectual() / size);
        report.setMoral(report.getMoral() / size);
        report.setUnreability(report.getUnreability() / size);
        report.setPsycho(report.getPsycho() / size);

        for (Report r : reportRepo.findAll())
        {
            if(report.getBio() == r.getBio() && report.getPsycho() == r.getPsycho() &&
               report.getMoral() == r.getMoral() && report.getIntellectual() == r.getIntellectual() &&
               report.getUnreability() == r.getUnreability())
                return false;
        }
        report.setDate(LocalDateTime.now());
        reportRepo.save(report);
        return true;
    }
}
