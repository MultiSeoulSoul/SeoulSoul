package com.multi.seoulsoul.chatbot.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.multi.seoulsoul.chatbot.model.dto.Message;
import com.multi.seoulsoul.chatbot.model.dto.OutputMessage;

@Controller
public class WebSocketController {

    @MessageMapping("/chat") //채팅 내용 받을 때 사용하는 주소 // 클라이언트에서 /app/chat로 메시지를 보내면 이곳에서 처리
    @SendTo("/topic/messages") // 결과를 /topic/messages로 전송 (클라이언트에서 구독하여 받음)
    public OutputMessage send(Message message) {
		OutputMessage out = new OutputMessage();
		String menu = "";
		switch (message.getText()) {
		
        case "0":
            menu = "자주 묻는 질문\n\n"
            
	            	 + "[1] 서울소울 소개: 서울소울 프로젝트가 뭐야?\n"
	                 + "[2] 서비스 안내: 핵심 서비스들을 설명해줘\n"
	                 + "[3] 트러블슈팅: 서비스 이용 중에 문제가 생겼어";
            break;
            
		case "1":
            menu = "서울소울 소개\n\n서울소울 프로젝트는 멀티잇 백엔드 개발 25회차 세미팀프로젝트 과제로, 팀 소울메이트가 제작하였습니다!\n"
               		+ "서울소울은 서울인들의 문화생활을 공유하는 소셜 미디어 플랫폼으로 기획했습니다.\n\n"
            		+ "서울에서 지낸 일상, 서울에서 한 문화생활을 서울로그에 포스팅해서 나의 소울맵를 채워가며 아카이빙하면, 당신의 서울소울이 진해집니다!\n"
            		+ "서울-소울풀한 나의 경험들을 다른 서울소울들과 공유하세요!\n\n"
            		
            		+ "자주 묻는 질문\n\n"
            		
	            	+ "[1] 서울소울 소개: 서울소울 프로젝트가 뭐야?\n"
	                + "[2] 서비스 안내: 핵심 서비스들을 설명해줘\n"
	                + "[3] 트러블슈팅: 서비스 이용 중에 문제가 생겼어";
            break;
            
        case "2":
            menu = "서비스 안내\n\n"
            
	                 + "[21] 서울로그 서비스를 소개해줘\n"
	                 + "[22] 이벤트 페이지를 안내해줘\n"
	                 + "[23] 추천 페이지를 안내해줘\n"
	                 + "[24] 고객센터 페이지를 안내해줘\n"
	                 + "[0] 자주 묻는 질문";
            break;
        case "21":
            menu = "서울로그 안내\n\n서울로그는 서울에서 지낸 일상, 서울에서 한 문화생활을 지도를 채워가며 기록하고 공유할 수 있는 아카이빙 블로그 서비스입니다.\n"
            		+ "나의 소울맵를 채워가며 서울로그에 포스팅하면, 소울레벨 시스템으로 소울레벨이 진해집니다.\n"
            		+ "서울-소울풀한 나의 경험들을 다른 서울소울들과 공유해보는 건 어때요?\n"
            		+ "내 소울로그뿐만 아니라 다른 서울인들의 소울로그나 이벤트, 추천페이지에 대한 상호작용도 소울레벨, 업적 달성에 적용됩니다.\n\n"
            		
            		+ "서비스 안내\n\n"
            		+ "[21] 서울로그 서비스를 소개해줘\n"
                    + "[22] 이벤트 페이지를 안내해줘\n"
                    + "[23] 추천 페이지를 안내해줘\n"
                    + "[24] 고객센터 페이지를 안내해줘\n"
                    + "[0] 자주 묻는 질문";
            break;
        case "22":
            menu = "이벤트 안내\n\n이벤트 페이지에서는 서울에서 열리는 다양한 행사와 이벤트 정보를 제공합니다.\n"
            		+ "카카오 지도 API와 연동되어 행사 위치정보를 쉽게 확인하실 수 있습니다.\n\n"
            		
            		+ "[21] 서울로그 서비스를 소개해줘\n"
                    + "[22] 이벤트 페이지를 안내해줘\n"
                    + "[23] 추천 페이지를 안내해줘\n"
                    + "[24] 고객센터 페이지를 안내해줘\n"
                    + "[0] 자주 묻는 질문";
            break;
        case "23":
            menu = "추천 서비스 안내\n\n추천 페이지는 서울소울 팀이 직접 엄선한 서울에서 할 수 있는 각종 특별한 추천 문화생활을 제공합니다.\n"
            		+ "추천 페이지 관련 문의사항은 고객센터 문의글을 통해 문의주시길 바랍니다.\n\n"
            		
            		+ "[21] 서울로그 서비스를 소개해줘\n"
                    + "[22] 이벤트 페이지를 안내해줘\n"
                    + "[23] 추천 페이지를 안내해줘\n"
                    + "[24] 고객센터 페이지를 안내해줘\n"
                    + "[0] 자주 묻는 질문";
            break;
        case "24":
            menu = "고객센터 안내\n\n고객센터 페이지에서는 문의사항을 해결할 수 있습니다.\n"
            		+ "문의글은 평균 일주일 이내로 답변해드리고 있으며, 영업시간은 평일 오전 9시~오후 6시입니다.\n"
            		+ "문의글 작성 전에는 카테고리를 미리 확인하고 알맞게 선정하여 작성해주시길 바랍니다.\n\n"
            		
            		+ "[21] 서울로그 서비스를 소개해줘\n"
                    + "[22] 이벤트 페이지를 안내해줘\n"
                    + "[23] 추천 페이지를 안내해줘\n"
                    + "[24] 고객센터 페이지를 안내해줘\n"
                    + "[0] 자주 묻는 질문";
            break;
            
        case "3":
            menu = "트러블슈팅\n\n"
            
                 + "[31] 회원정보 관련 문제\n"
                 + "[32] 카카오 계정 연동 문제\n"
                 + "[33] 웹페이지 오류가 떠요\n"
                 + "[34] 페이지 로딩이 안 돼요\n"
                 + "[35] 게시글 작성이 안 돼요\n"
                 + "[36] 신고/강등 관련 문제\n"
                 + "[37] 소울레벨 관련 문제\n"
                 + "[0] 자주 묻는 질문";
            break;
        case "31":
            menu = "회원정보 관련 문제\n\n회원정보 변경이나 계정 설정에 문제가 있으신가요? "
            	 + "고객센터를 통해 문의해주시면 빠르게 도와드리겠습니다.\n\n"
                 + "[31] 회원정보 관련 문제\n"
                 + "[32] 카카오 계정 연동 문제\n"
                 + "[33] 웹페이지 오류가 떠요\n"
                 + "[34] 페이지 로딩이 안 돼요\n"
                 + "[35] 게시글 작성이 안 돼요\n"
                 + "[36] 신고/강등 관련 문제\n"
                 + "[37] 소울레벨 관련 문제\n"
                 + "[38] 신고/강등 관련 문제\n"
                 + "[0] 자주 묻는 질문";
            break;
        case "32":
            menu = "카카오 계정 연동 문제\n\n카카오 계정 연동에 문제가 있으신가요? 먼저 서울소울의 마이페이지에서 계정 연동 설정을 확인해주시고, "
            	 + "카카오의 계정 페이지에서 서울소울로 계정 연동 설정을 확인해주세요. 그래도 문제가 지속되면 고객센터에 문의해 주세요.\n\n"

                 + "[31] 회원정보 관련 문제\n"
                 + "[32] 카카오 계정 연동 문제\n"
                 + "[33] 웹페이지 오류가 떠요\n"
                 + "[34] 페이지 로딩이 안 돼요\n"
                 + "[35] 게시글 작성이 안 돼요\n"
                 + "[36] 신고/강등 관련 문제\n"
                 + "[37] 소울레벨 관련 문제\n"
                 + "[38] 신고/강등 관련 문제\n"
                 + "[0] 자주 묻는 질문";
            break;
        case "33":
            menu = "웹페이지 오류가 떠요\n\n웹페이지 오류가 발생하나요? 먼저 브라우저 캐시를 지우고 다시 시도해 보세요. "
            	 + "그래도 문제가 지속되면 문제 상황에 대한 캡쳐화면과 함께 고객센터에 문의해 주세요.\n\n"
            
                 + "[31] 회원정보 관련 문제\n"
                 + "[32] 카카오 계정 연동 문제\n"
                 + "[33] 웹페이지 오류가 떠요\n"
                 + "[34] 페이지 로딩이 안 돼요\n"
                 + "[35] 게시글 작성이 안 돼요\n"
                 + "[36] 신고/강등 관련 문제\n"
                 + "[37] 소울레벨 관련 문제\n"
                 + "[38] 신고/강등 관련 문제\n"
                 + "[0] 자주 묻는 질문";
            break;
        case "34":
            menu = "페이지 로딩이 안 돼요\n\n페이지 로딩 문제가 발생하나요? 먼저 인터넷 연결 상태를 확인하고 다시 시도해 주세요. "
            	 + "문제가 지속되면 문제 상황에 대한 캡쳐화면과 함께 고객센터에 문의해 주세요.\n\n"

                 + "[31] 회원정보 관련 문제\n"
                 + "[32] 카카오 계정 연동 문제\n"
                 + "[33] 웹페이지 오류가 떠요\n"
                 + "[34] 페이지 로딩이 안 돼요\n"
                 + "[35] 게시글 작성이 안 돼요\n"
                 + "[36] 신고/강등 관련 문제\n"
                 + "[37] 소울레벨 관련 문제\n"
                 + "[38] 신고/강등 관련 문제\n"
                 + "[0] 자주 묻는 질문";
            break; 
        case "35":
            menu = "게시글 작성이 안 돼요\n\n게시글 작성에 문제가 있나요? 먼저 브라우저 설정을 확인하고, "
            	 + "회원 정보, 계정 설정에 문제가 있는지 확인해주세요. "
            	 + "문제가 지속되면 문제 상황에 대한 캡쳐화면과 함께 고객센터에 문의해 주세요.\n\n"

                 + "[31] 회원정보 관련 문제\n"
                 + "[32] 카카오 계정 연동 문제\n"
                 + "[33] 웹페이지 오류가 떠요\n"
                 + "[34] 페이지 로딩이 안 돼요\n"
                 + "[35] 게시글 작성이 안 돼요\n"
                 + "[36] 신고/강등 관련 문제\n"
                 + "[37] 소울레벨 관련 문제\n"
                 + "[38] 신고/강등 관련 문제\n"
                 + "[0] 자주 묻는 질문";
            break; 
        case "36":
            menu = "신고/강등 관련 문제\n\n신고나 강등에 대한 문제가 발생했나요? "
            	 + "정확한 상황을 고객센터에 문의해 주시면 신속히 처리하겠습니다."
            	 + "문의글 작성시 문제 상황에 대한 캡쳐화면 등 파일을 첨부하실 수 있으니 참고 바랍니다.\n\n"

                 + "[31] 회원정보 관련 문제\n"
                 + "[32] 카카오 계정 연동 문제\n"
                 + "[33] 웹페이지 오류가 떠요\n"
                 + "[34] 페이지 로딩이 안 돼요\n"
                 + "[35] 게시글 작성이 안 돼요\n"
                 + "[36] 신고/강등 관련 문제\n"
                 + "[37] 소울레벨 관련 문제\n"
                 + "[38] 신고/강등 관련 문제\n"
                 + "[0] 자주 묻는 질문";
            break; 
        
        case "37":
            menu = "소울레벨 관련 문제\n\n소울레벨에 대한 문제가 있으신가요? 먼저 나의 소울로그와 마이페이지에서 "
            	 + "활동 기록을 확인해주세요. 활동 기록에 문제가 없고 문제가 지속되면 고객센터에 문의해 주세요.\n\n"

                 + "[31] 회원정보 관련 문제\n"
                 + "[32] 카카오 계정 연동 문제\n"
                 + "[33] 웹페이지 오류가 떠요\n"
                 + "[34] 페이지 로딩이 안 돼요\n"
                 + "[35] 게시글 작성이 안 돼요\n"
                 + "[36] 신고/강등 관련 문제\n"
                 + "[37] 소울레벨 관련 문제\n"
                 + "[38] 신고/강등 관련 문제\n"
                 + "[0] 자주 묻는 질문";
            break; 
        
        default:
            menu = "선택한 번호는 없는 메뉴입니다. 다시 입력해주세요.";
            break;
		}
		out.setMenu(menu);
		return out;
	}
    
}
