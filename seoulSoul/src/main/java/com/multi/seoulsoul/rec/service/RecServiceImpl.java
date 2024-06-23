package com.multi.seoulsoul.rec.service;

import com.multi.seoulsoul.rec.model.dao.RecDAO;
import com.multi.seoulsoul.rec.model.dto.RecDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecServiceImpl implements RecService {

    @Autowired
    private RecDAO recDAO;

    @Override
    public void recInsertForm(RecDTO recDTO) throws Exception {
        recDAO.insertRecommendation(recDTO);
    }
}