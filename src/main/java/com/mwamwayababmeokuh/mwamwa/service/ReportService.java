package com.mwamwayababmeokuh.mwamwa.service;

import com.mwamwayababmeokuh.mwamwa.domain.ReportPost;
import com.mwamwayababmeokuh.mwamwa.domain.ReportPostDTO;
import com.mwamwayababmeokuh.mwamwa.domain.ReportUser;
import com.mwamwayababmeokuh.mwamwa.domain.ReportUserDTO;
import com.mwamwayababmeokuh.mwamwa.repository.ReportPostRepository;
import com.mwamwayababmeokuh.mwamwa.repository.ReportUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class ReportService {

    @Autowired
    ReportUserRepository reportUserRepository;

    @Autowired
    ReportPostRepository reportPostRepository;

    ModelMapper modelMapper = new ModelMapper();

    public ReportUserDTO saveUserReport(ReportUserDTO reportUserDTO) {
        log.info("saveUserReport()" + reportUserDTO.toString());
        ReportUser reportUser = modelMapper.map(reportUserDTO, ReportUser.class);
        ReportUser result = reportUserRepository.save(reportUser);
        return modelMapper.map(result, ReportUserDTO.class);
    }

    public ReportPostDTO savePostReport(ReportPostDTO reportPostDTO) {
        log.info("savePostReport()" + reportPostDTO.toString());
        ReportPost reportPost = modelMapper.map(reportPostDTO, ReportPost.class);
        ReportPost result = reportPostRepository.save(reportPost);
        return modelMapper.map(result, ReportPostDTO.class);
    }
}
