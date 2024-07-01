package com.multi.seoulsoul.soulLog.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multi.seoulsoul.soulLog.model.dto.CategoryDTO;
import com.multi.seoulsoul.soulLog.model.dto.DetailRequestDTO;
import com.multi.seoulsoul.soulLog.model.dto.FilesDTO;
import com.multi.seoulsoul.soulLog.model.dto.FilterDTO;
import com.multi.seoulsoul.soulLog.model.dto.LikesDTO;
import com.multi.seoulsoul.soulLog.model.dto.LocationDTO;
import com.multi.seoulsoul.soulLog.model.dto.RepliesDTO;
import com.multi.seoulsoul.soulLog.model.dto.ReplyWriterDTO;
import com.multi.seoulsoul.soulLog.model.dto.SoulLogDTO;
import com.multi.seoulsoul.soulLog.model.dto.WriterDTO;
import com.multi.seoulsoul.soulLog.service.SoulLogService;


@Controller
@RequestMapping("/soulLog")
public class SoulLogController {
	
	
	private final SoulLogService soulLogService;
	
	public SoulLogController(SoulLogService soulLogService) {
		this.soulLogService = soulLogService;
	}
	
    
	// 소울로그 메인 리스트 페이지로 이동
	@RequestMapping("/soulLogMain")
	public String soulLogMain(FilterDTO filterDTO, Model model) {
		
		filterDTO.setStartAndStartIndex(filterDTO.getPage());
		
		System.out.println("검색할 locationCode는 >>>>> " + filterDTO.getLocationCode());
		System.out.println("검색할 categoryCode는 >>>>> " + filterDTO.getCategoryCode());
		System.out.println("검색어는 >>>>> " + filterDTO.getSearchWord());
		System.out.println("시작 페이지는 >>>>> " + filterDTO.getStart());
		
		try {
			
			List<SoulLogDTO> soulLogList = soulLogService.selectSoulLogList(filterDTO);
			
			System.out.println("가져온 리스트 >>>> " + soulLogList);
			
			model.addAttribute("soulLogList", soulLogList);
			
			int SoulLogCount = soulLogService.selectSoulLogCount(filterDTO);
			
			// 글 수를 토대로 전체 페이지 수를 구한다.
			// 글 수가 10의 배수면 +1을 하지 않는다.
			int pages = SoulLogCount / 10;

			// 글 수가 10의 배수가 아니면 +1을 한다.
			if (SoulLogCount % 10 != 0) {
				pages += 1;
			}

			model.addAttribute("filter", filterDTO);
			
			model.addAttribute("pages", pages);
			
			return "soulLog/soulLogMain";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			model.addAttribute("msg", "소울로그 리스트 조회 과정에서 문제가 발생했습니다.");
			
			return "common/errorPage";
			
		}
			
	}
	
	
	// 소울로그 작성 폼으로 이동
	@RequestMapping("/soulLogInsertForm")
	public void soulLogInsertForm() {
	
	}
	
	
	// 자치구 리스트를 조회 (json, ajax)
	@RequestMapping("/locationList")
	@ResponseBody // json으로 요청한 곳으로 전달
	public List<LocationDTO> locationList() throws Exception {
			
		List<LocationDTO> locationList = soulLogService.selectLocationList();
		
		System.out.println("가져온 자치구 리스트는 >>>> " + locationList);
			
		return locationList;
		
	}
	
	
	// 카테고리 리스트를 조회 (json, ajax)
	@RequestMapping("/categoryList")
	@ResponseBody // json으로 요청한 곳으로 전달
	public List<CategoryDTO> categoryList() throws Exception {
				
		List<CategoryDTO> categoryList = soulLogService.selectCategoryList();
			
		System.out.println("가져온 카테고리 리스트는 >>>> " + categoryList);
				
		return categoryList;
			
	}
	
	
	// 소울로그 작성
	@PostMapping("/insertSoulLog")
	public String insertSoulLog(/*@AuthenticationPrincipal Principal principal,*/ LocationDTO locationDTO, CategoryDTO categoryDTO, SoulLogDTO soulLogDTO, HttpServletRequest request, MultipartFile[] imgList, Model model) {
		
		/* 
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
    	CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
    	
    	int userNo = userDetails.getUserNo();
		*/
		
		WriterDTO writerDTO = new WriterDTO();
		writerDTO.setUserNo(1);
		// writerDTO.setUserNo(userNo);
		
		System.out.println("locationDTO는 >>>>> " + locationDTO);
		System.out.println("categoryDTO는 >>>>> " + categoryDTO);
		
		soulLogDTO.setLocation(locationDTO);
		soulLogDTO.setCategory(categoryDTO);
		soulLogDTO.setWriter(writerDTO);
		
		System.out.println("soulLogDTO는 >>>>> " + soulLogDTO);
		
		HttpSession session = request.getSession();
		
		String root = session.getServletContext().getRealPath("resources");
		
		System.out.println("root : " + root);
		
		String filePath = root + "/uploadFiles";
		
		System.out.println("img 개수 >>>>> " + imgList.length);
		
		// img가 있을 때
		if(imgList.length > 0) {
					
			// java.io의 File을 import
			// filePath에 폴더가 없으면 폴더 생성
			File mkdir = new File(filePath);
			if(!mkdir.exists()) {
				mkdir.mkdirs();
			}
			
			List<FilesDTO> fileList = new ArrayList<>();
			
			for(MultipartFile img : imgList) {
				
				if (!img.isEmpty()) {
					
					// 파일명 변경 처리
					// 첨부 파일의 이름을 가져온다.
					String originFileName = img.getOriginalFilename();
					// 거기서 확장자만 잘라서 변수에 저장한다.
					String ext = originFileName.substring(originFileName.lastIndexOf("."));
					// 랜덤한 파일이름을 생성한 후 뒤에 확장자를 붙인다.
					String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
					
					FilesDTO file = new FilesDTO();
					file.setOriginalName(originFileName);
					file.setSavedName(savedName);
					
					fileList.add(file);
					
					try {
						
						img.transferTo(new File(filePath + "/" + savedName));
								
					} catch (Exception e) {
								
						e.printStackTrace();
	
						// 오류 발생 시 생성한 파일을 삭제한다.
						new File(filePath + "/" + savedName).delete();
								
						model.addAttribute("msg", "이미지 업로드 과정에서 문제가 발생했습니다.");
	
						return "common/errorPage";
							
					}
				
				}
				
			}
			
			soulLogDTO.setFiles(fileList);
			
			System.out.println("soulLogDTO는 >>>>> " + soulLogDTO);
					
		} // img가 있을 때
		
		try {
			
			soulLogService.insertSoulLog(soulLogDTO);
			
			// mapper에서 useGeneratedKeys="true" keyProperty="soulLogNo" 해줬기 때문에 soulLogDTO에 insert한 게시글의 No(PK)가 담겨 있다.
			int soulLogNo = soulLogDTO.getSoulLogNo();
			
			return "redirect:/soulLog/soulLogDetail?soulLogNo="+soulLogNo; // 로그인.. 유저 정보 no도..
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			model.addAttribute("msg", "소울로그 작성 과정에서 문제가 발생했습니다.");
			
			return "common/errorPage";
			
		}
		
		
	}
	
	
	// 소울로그 상세 조회
	@RequestMapping("/soulLogDetail")
	public String soulLogDetail(DetailRequestDTO detailRequestDTO, Model model) {
		
		/* 만약 맞다면... login된 유저의 userNo를 가져오는 코드
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        int userNo = userDetails.getUserNo();
			
			
			그런 다음 detailRequestDTO.setLoginUserNo(userNo); 하면 깔-끔
			
			+ detailRequestDTO.setLoginUserNo(userNo)
			
		
		}
		
		 */
		
		detailRequestDTO.setLoginUserNo(2); // 로그인한 유저의 no로 바꿔야 함!!!!!
		
		
		System.out.println("조회할 소울로그 No는 >>>> " + detailRequestDTO.getSoulLogNo());
		System.out.println("로그인한 유저 No는 >>>> " + detailRequestDTO.getLoginUserNo());
		
		try {
			
			soulLogService.addViews(detailRequestDTO.getSoulLogNo());
			
			SoulLogDTO soulLogDetail = soulLogService.soulLogDetail(detailRequestDTO);
			
			System.out.println("가져온 로그 데이터 >>>> " + soulLogDetail);
			
			model.addAttribute("soulLogDetail", soulLogDetail);
			
			return "soulLog/soulLogDetail";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			model.addAttribute("msg", "소울로그 상세 조회 과정에서 문제가 발생했습니다.");
			
			return "common/errorPage";
			
		}
		
	}
	
	
	@PostMapping("/insertSoulLogReply")
	public String insertSoulLogReply(RepliesDTO repliesDTO, ReplyWriterDTO replyWriterDTO, Model model) {
		
		System.out.println("repliesDTO는 >>>>> " + repliesDTO);
		System.out.println("replyWriterDTO는 >>>>> " + replyWriterDTO);
		
		int soulLogNo = repliesDTO.getSoulLogNo();
		
		repliesDTO.setWriter(replyWriterDTO);
		
		try {
			
			soulLogService.insertSoulLogReply(repliesDTO);
			
			return "redirect:/soulLog/soulLogDetail?soulLogNo="+soulLogNo; // 지금은 loginUserNo를 안 보내지만, 만약 디테일 메서드에서 로그인 유저의 no를 가져올 수 있다면 여기선 로그 no만 보내도 됨.
			
		} catch (Exception e) {
		
			e.printStackTrace();
			
			model.addAttribute("msg", "댓글 작성 과정에서 문제가 발생했습니다.");
			
			return "common/errorPage";
			
		}
		
		
	}
	
	
	@RequestMapping("/deleteSoulLog")
	public String deleteSoulLog(int soulLogNo, Model model) {
		
		System.out.println("삭제할 로그 no는 >>>>> " + soulLogNo);
		
		try {
			
			soulLogService.deleteSoulLog(soulLogNo);
			
			return "redirect:/soulLog/soulLogMain?page=1";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			model.addAttribute("msg", "소울로그 삭제 과정에서 문제가 발생했습니다.");
			
			return "common/errorPage";
			
		}
		
	}
	
	
	@RequestMapping("/deleteSoulLogReply")
	public String deleteSoulLogReply(RepliesDTO repliesDTO, Model model) {
		
		int replyNo = repliesDTO.getReplyNo();
		int soulLogNo = repliesDTO.getSoulLogNo();
		
		System.out.println("삭제할 댓글 no는 >>>>> " + replyNo);
		System.out.println("댓글 달린 로그 no는 >>>>> " + soulLogNo);
		
		
		try {
			
			soulLogService.deleteSoulLogReply(replyNo);
			
			return "redirect:/soulLog/soulLogDetail?soulLogNo="+soulLogNo;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			model.addAttribute("msg", "댓글 삭제 과정에서 문제가 발생했습니다.");
			
			return "common/errorPage";
			
		}
		
	}
	
	
	// 소울로그 수정 폼으로 이동
	@RequestMapping("/soulLogUpdateForm")
	public String soulLogUpdateForm(int soulLogNo, Model model) {
	
		try {
			
			SoulLogDTO updateDetail = soulLogService.updateDetail(soulLogNo);
			
			System.out.println("가져온 로그 데이터 >>>> " + updateDetail);
			
			model.addAttribute("updateDetail", updateDetail);
			
			return "soulLog/soulLogUpdateForm";
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			model.addAttribute("msg", "수정할 소울로그 데이터 조회 과정에서 문제가 발생했습니다.");
			
			return "common/errorPage";
			
		}
		
	}
	
	
	@PostMapping("/updateSoulLog")
	public String updateSoulLog(LocationDTO locationDTO, CategoryDTO categoryDTO, SoulLogDTO soulLogDTO, HttpServletRequest request, MultipartFile[] imgList, @RequestParam("status[]") int[] status, @RequestParam("fileNo[]") int[] fileNo, Model model) {
		
		int soulLogNo = soulLogDTO.getSoulLogNo();
		
		System.out.println("locationDTO는 >>>>> " + locationDTO);
		System.out.println("categoryDTO는 >>>>> " + categoryDTO);
		
		soulLogDTO.setLocation(locationDTO);
		soulLogDTO.setCategory(categoryDTO);
		
		// location, category, 제목, 내용 수정에 대한 데이터들
		System.out.println("soulLogDTO는 >>>>> " + soulLogDTO);
		
		try {
			
			soulLogService.updateSoulLog(soulLogDTO);
			
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
			
			model.addAttribute("msg", "소울로그 수정 과정에서 문제가 발생했습니다.");

			return "common/errorPage";
			
		}
		
		
		// 이미지 수정에 대한 내용
		System.out.println("status는 >>>>> " + Arrays.toString(status));
		System.out.println("fileNo는 >>>>> " + Arrays.toString(fileNo));
		
		HttpSession session = request.getSession();
		String root = session.getServletContext().getRealPath("resources");
		String filePath = root + "/uploadFiles";
		
		
		for(int i = 0; i < 5; i++) {
			
			// updateImage
			if(fileNo[i] != 0 && status[i] == -2) {
				
				String originFileName = imgList[i].getOriginalFilename();
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				
				FilesDTO file = new FilesDTO();
				file.setOriginalName(originFileName);
				file.setSavedName(savedName);
				file.setFileNo(fileNo[i]);
				
				try {
					
					imgList[i].transferTo(new File(filePath + "/" + savedName));
							
				} catch (Exception e) {
							
					e.printStackTrace();

					// 오류 발생 시 생성한 파일을 삭제한다.
					new File(filePath + "/" + savedName).delete();
							
					model.addAttribute("msg", "소울로그 수정 과정에서 문제가 발생했습니다.");

					return "common/errorPage";
						
				}
				
				try {
					
					soulLogService.updateImage(file);
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
					model.addAttribute("msg", "소울로그 수정 과정에서 문제가 발생했습니다.");

					return "common/errorPage";
					
				}
				
			}
			// deleteImage
			else if(fileNo[i] != 0 && status[i] == -3) {
				
				try {
					
					soulLogService.deleteImage(fileNo[i]);
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
					model.addAttribute("msg", "소울로그 수정 과정에서 문제가 발생했습니다.");

					return "common/errorPage";
					
				}
				
			}
			// insertImage
			else if(fileNo[i] == 0 && status[i] == -2) {
				
				String originFileName = imgList[i].getOriginalFilename();
				String ext = originFileName.substring(originFileName.lastIndexOf("."));
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
				
				FilesDTO file = new FilesDTO();
				file.setOriginalName(originFileName);
				file.setSavedName(savedName);
				file.setSoulLogNo(soulLogNo);
				
				try {
					
					imgList[i].transferTo(new File(filePath + "/" + savedName));
							
				} catch (Exception e) {
							
					e.printStackTrace();

					// 오류 발생 시 생성한 파일을 삭제한다.
					new File(filePath + "/" + savedName).delete();
							
					model.addAttribute("msg", "소울로그 수정 과정에서 문제가 발생했습니다.");

					return "common/errorPage";
						
				}
				
				try {
					
					soulLogService.insertImage(file);
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
					model.addAttribute("msg", "소울로그 수정 과정에서 문제가 발생했습니다.");

					return "common/errorPage";
					
				}
				
			} // else if end
			
		} // for end
		
		return "redirect:/soulLog/soulLogDetail?soulLogNo="+soulLogNo;
		
	}
	
	
	@PostMapping("/updateSoulLogReply")
	public String updateSoulLogReply(RepliesDTO repliesDTO, Model model) {
		
		int soulLogNo = repliesDTO.getSoulLogNo();
		
		try {
			
			soulLogService.updateSoulLogReply(repliesDTO);
			
			return "redirect:/soulLog/soulLogDetail?soulLogNo="+soulLogNo;
			
		} catch (Exception e) {
		
			e.printStackTrace();
			
			model.addAttribute("msg", "댓글 수정 과정에서 문제가 발생했습니다.");

			return "common/errorPage";
			
		}
		
	}
	
	
	@PostMapping("/insertLike")
	public ResponseEntity<Void> insertLike(/*@AuthenticationPrincipal Principal principal,*/ LikesDTO likesDTO) {
		
		/* 
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
    	CustomUserDetails userDetails = (CustomUserDetails) authenticationToken.getPrincipal();
    	
    	int userNo = userDetails.getUserNo();
		*/
		
		likesDTO.setUserNo(1);
		// likesDTO.setUserNo(userNo);
		
		System.out.println("좋아요 추가할 로그 No는 >>>>> " + likesDTO.getSoulLogNo());
		
		try {
			
			soulLogService.insertLike(likesDTO);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	@PostMapping("/deleteLike")
	public ResponseEntity<Void> deleteLike(LikesDTO likesDTO) {
		
		System.out.println("좋아요 취소할 로그 No는 >>>>> " + likesDTO.getSoulLogNo());
		System.out.println("좋아요 취소하는 유저 No는 >>>>> " + likesDTO.getUserNo());
		
		try {
			
			soulLogService.deleteLike(likesDTO);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
}
