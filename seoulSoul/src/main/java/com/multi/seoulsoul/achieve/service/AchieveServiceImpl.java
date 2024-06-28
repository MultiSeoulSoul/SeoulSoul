package com.multi.seoulsoul.achieve.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.multi.seoulsoul.achieve.model.dao.AchieveDAO;
import com.multi.seoulsoul.achieve.model.dto.AchCateDTO;
import com.multi.seoulsoul.achieve.model.dto.AchCateIconsDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaDTO;
import com.multi.seoulsoul.achieve.model.dto.AchLocaIconsDTO;
import com.multi.seoulsoul.user.model.dto.UserDTO;

@Service
@Transactional(rollbackFor = Exception.class)
public class AchieveServiceImpl implements AchieveService {
	
	private final AchieveDAO achieveDAO;
	@Autowired
	public AchieveServiceImpl(AchieveDAO achieveDAO) {
		this.achieveDAO = achieveDAO;
	}
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
    public int insertAchieveLoca(AchLocaDTO achLocaDTO) throws Exception {
        int result = achieveDAO.insertAchieveLoca(sqlSession, achLocaDTO);
        if (result <= 0) {
            throw new Exception("업적 생성 실패.");
        }
        return result;
    }

    @Override
    public int insertLocaIcons(AchLocaIconsDTO achLocaIconsDTO) throws Exception {
        int result = achieveDAO.insertLocaIcons(sqlSession, achLocaIconsDTO);
        if (result <= 0) {
            throw new Exception("아이콘 삽입 실패.");
        }
        return result;
    }

	@Override
	public int insertAchieveCate(AchCateDTO achCateDTO) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("insertAchieveCate AchieveServiceImpl 도착.");
		
		int result = achieveDAO.insertAchieveCate(sqlSession, achCateDTO);
		
		System.out.println("result >> " + result);
		
		return result;
	}

	@Override
	public int insertCateIcons(AchCateIconsDTO achCateIconsDTO) throws Exception {
		int result = achieveDAO.insertCateIcons(sqlSession, achCateIconsDTO);
        if (result <= 0) {
            throw new Exception("아이콘 삽입 실패.");
        }
        return result;
		
	}

	@Override
	public List<AchLocaDTO> achieveLocaList() {
		// TODO Auto-generated method stub
		System.out.println("achieveLocaList AchieveServiceImpl 도착.");

		List<AchLocaDTO> achieveLocaList = achieveDAO.achieveLocaList(sqlSession);
		
		return achieveLocaList;
	}
	
	@Override
	public List<AchCateDTO> achieveCateList() {
		// TODO Auto-generated method stub
		System.out.println("achieveCateList AchieveServiceImpl 도착.");

		List<AchCateDTO> achieveCateList = achieveDAO.achieveCateList(sqlSession);
		
		return achieveCateList;
	}
	
	@Override
    public AchLocaDTO getAchLocaById(int achNo) {
        return achieveDAO.selectAchLocaById(sqlSession, achNo);
    }
	
	@Override
    public AchLocaIconsDTO getAchLocaIconsByAchNo(int achNo) {
        return achieveDAO.selectAchLocaIconsByAchNo(sqlSession, achNo);
    }
	
	@Override
    public AchCateDTO getAchCateById(int achNo) {
        return achieveDAO.selectAchCateById(sqlSession, achNo);
    }
	
	@Override
    public AchCateIconsDTO getAchCateIconsByAchNo(int achNo) {
        return achieveDAO.selectAchCateIconsByAchNo(sqlSession, achNo);
    }

	@Override
	public int deleteAchieveLoca(int achNo) {
		// TODO Auto-generated method stub
		System.out.println("deleteAchieveLoca AchieveServiceImpl 도착.");
		
		int result = achieveDAO.deleteAchieveLoca(sqlSession, achNo);
		
		System.out.println("result >> " + result);
		
		return result;
	}

	@Override
	public int deleteAchieveCate(int achNo) {
		// TODO Auto-generated method stub
		System.out.println("deleteAchieveCate AchieveServiceImpl 도착.");
		
		int result = achieveDAO.deleteAchieveCate(sqlSession, achNo);
		
		System.out.println("result >> " + result);
		
		return result;
	}

	@Override
	public List<UserDTO> userList() {
		// TODO Auto-generated method stub
		System.out.println("userList AchieveServiceImpl 도착.");

		List<UserDTO> userList = achieveDAO.userList(sqlSession);
		
		return userList;
	}

	@Override
	public void updateAchLoca(AchLocaDTO achLocaDTO, AchLocaIconsDTO achLocaIconsDTO, MultipartFile singleFile,
			HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		int result = achieveDAO.updateAchLoca(sqlSession, achLocaDTO);

        if (result > 0 && !singleFile.isEmpty()) {
            String root = request.getSession().getServletContext().getRealPath("resources");
            String filePath = root + "/uploadFiles";
            File mkdir = new File(filePath);
            if (!mkdir.exists()) {
                mkdir.mkdirs();
            }
            String originFileName = singleFile.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

            singleFile.transferTo(new File(filePath + "/" + savedName));
            
            achLocaIconsDTO.setAchLoca(achLocaDTO);
            achLocaIconsDTO.setOriginalName(originFileName);
            achLocaIconsDTO.setSavedName(savedName);
            
            achieveDAO.updateLocaIcons(sqlSession, achLocaIconsDTO);
        } else {
            throw new Exception("업적 수정 실패");
        }
	}
	
	@Override
	public void updateAchCate(AchCateDTO achCateDTO, AchCateIconsDTO achCateIconsDTO, MultipartFile singleFile,
			HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		int result = achieveDAO.updateAchCate(sqlSession, achCateDTO);

        if (result > 0 && !singleFile.isEmpty()) {
            String root = request.getSession().getServletContext().getRealPath("resources");
            String filePath = root + "/uploadFiles";
            File mkdir = new File(filePath);
            if (!mkdir.exists()) {
                mkdir.mkdirs();
            }
            String originFileName = singleFile.getOriginalFilename();
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

            singleFile.transferTo(new File(filePath + "/" + savedName));
            
            achCateIconsDTO.setAchCate(achCateDTO);
            achCateIconsDTO.setOriginalName(originFileName);
            achCateIconsDTO.setSavedName(savedName);
            
            achieveDAO.updateCateIcons(sqlSession, achCateIconsDTO);
        } else {
            throw new Exception("업적 수정 실패");
        }
	}

}
