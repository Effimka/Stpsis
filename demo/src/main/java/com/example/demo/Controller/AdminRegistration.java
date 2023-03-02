package com.example.demo.Controller;

import com.example.demo.Lib.EmploeeService;
import com.example.demo.Lib.ReportService;
import com.example.demo.Models.Emploee;
import com.example.demo.Models.Report;
import com.example.demo.Types.createEmploeeTemplate;
import com.example.demo.Types.reportViewTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminRegistration {

    public final EmploeeService emploeeService;
    public final ReportService reportService;

    @Autowired
    public AdminRegistration(EmploeeService emploeeService, ReportService reportService) {
        this.emploeeService = emploeeService;
        this.reportService = reportService;
    }

    @GetMapping
    public String init() {
        return "admin -> /user/{id} - return user, /users - all user, /makeEmpl - create emploee";
    }

    @GetMapping("/users")
    public List<Emploee> users() {
        return emploeeService.AllEmploee();
    }

    @GetMapping("/user/{id}")
    public Emploee getUser(@PathVariable Long id) {
        return emploeeService.findEmploee(id);
    }

    @PostMapping("/makeEmpl")
    public Emploee makeEmploee(@RequestBody createEmploeeTemplate data) {
        return emploeeService.addEmploeeToDB(data);
    }

    @GetMapping("/reports")
    public ArrayList<reportViewTemplate> reports() {
        return reportService.AllReports();
    }

    @GetMapping("/report/{id}")
    public Report getReport(@PathVariable Long id) { return reportService.findReport(id); }

    @GetMapping("/makeReport")
    public String makeReport() {
        if(reportService.makeReport())
            return "Report created";
        return "Report does't create (1. workers did not pass the report) (2. report exist) ";
    }

    @DeleteMapping("user/remove/{id}")
    public void deleteEmploee (@PathVariable Long id){
        emploeeService.removeEmploee(id);
    }
}
