package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.ReportPostDTO;
import com.mwamwayababmeokuh.mwamwa.domain.ReportUserDTO;
import com.mwamwayababmeokuh.mwamwa.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReportRestController {

    @Autowired
    ReportService reportService;

    @PostMapping("/users/report")
    @ResponseBody
    public ReportUserDTO reportUser(@RequestBody ReportUserDTO reportUserDTO) {
        return reportService.saveUserReport(reportUserDTO);
    }

    @PostMapping("/boards/posts/report")
    @ResponseBody
    public ReportPostDTO reportPost(@RequestBody ReportPostDTO reportPostDTO) {
        return reportService.savePostReport(reportPostDTO);
    }

}
