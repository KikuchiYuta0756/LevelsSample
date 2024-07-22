package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domainUser.model.CorrectRequestEntity;
import com.example.domainUser.model.PaidAppEntity;
import com.example.domainUser.model.RequestStatesEntity;
import com.example.domainUser.service.CorrectRequestService;
import com.example.domainUser.service.PaidAppService;
import com.example.form.CorrectRequestForm;
import com.example.form.PaidRequestForm;
import com.example.form.requestRecordListForm;

@Controller
@RequestMapping("/admin")
public class RequestRecordADMController {

	@Autowired
	private CorrectRequestService correctrequestservice;

	@Autowired
	private PaidAppService paidappservice;

	@Autowired
	private ModelMapper modelMapper;

	/** 申請履歴画面を表示 */
	@GetMapping("/requestRecordADM")
	public String getRequestRecordADM(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// ログイン認証に使用したログインIDを利用する。
		String paidLoginId = auth.getName();
		
		// 申請ステータスリストを取得
		List<RequestStatesEntity> requestStatesList = correctrequestservice.getAllRequestStates();
		model.addAttribute("requestStatesList", requestStatesList);		

		// 有給申請一覧取得
		List<PaidAppEntity> paidList = paidappservice.getUserPaidRequests(paidLoginId);

		// Modelに登録
		model.addAttribute("paidList", paidList);

		// ログイン認証に使用したログインIDを利用する。
		String correctLoginId = auth.getName();

		// 修正申請一覧取得
		List<CorrectRequestEntity> correctList = correctrequestservice.getUserCorrectRequests(correctLoginId);
		// correctList.sort(Comparator.comparing(CorrectRequestEntity::getCorrectRequestDate));

		// modelに登録
		model.addAttribute("correctList", correctList);

		// ユーザー詳細画面を表示
		return "admin/requestRecordADM";

	}
	
	/**申請の検索処理*/
	@PostMapping("/requestRecordList")
	public String postRequestRecordADM(@ModelAttribute requestRecordListForm form, Model model) {

		// ログイン認証に使用したログインIDを利用する。
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loginId = auth.getName();
		
		// 申請ステータスリストを取得
		List<RequestStatesEntity> requestStatesList = correctrequestservice.getAllRequestStates();
		model.addAttribute("requestStatesList", requestStatesList);		

		//formをRequestStatesEntityクラスに変換
		RequestStatesEntity requeststates = modelMapper.map(form, RequestStatesEntity.class);
		
		// 有給申請一覧取得
		List<PaidAppEntity> paidList = paidappservice.getUserPaidRequests(loginId, requeststates);
		model.addAttribute("paidList", paidList);

		// 修正申請一覧取得
		List<CorrectRequestEntity> correctList = correctrequestservice.getUserCorrectRequests(loginId, requeststates);
		model.addAttribute("correctList", correctList);

		// ユーザー詳細画面を表示
		return "admin/requestRecordADM";

		
	}

	/** 個別申請履歴の有給申請詳細画面を表示 */
	@GetMapping("/requestRecordPaidDetailADM/{paidAppId}")
	public String getRequestRecordPaidDetailADM(PaidRequestForm form, Model model, @PathVariable("paidAppId") int paidAppId) {

		// 有給申請を1件取得
		PaidAppEntity paidappADM = paidappservice.getPaidAppOne(paidAppId);

		// UserMapperEntityをformに変換
		form = modelMapper.map(paidappADM, PaidRequestForm.class);

		// Modelに登録
		model.addAttribute("PaidRequestForm", form);

		// ユーザー詳細画面を表示
		return "admin/requestRecordPaidDetailADM";
	}

	/** 修正申請詳細画面を表示 */
	@GetMapping("/requestRecordCorrectDetailADM/{correctRequestId}")
	public String getRequestRecordCorrectDetailADM(CorrectRequestForm form, Model model,
			@PathVariable("correctRequestId") int correctRequestId) {

		// 修正申請を1件取得
		CorrectRequestEntity correctDetailADM = correctrequestservice.getCorrectRequestOne(correctRequestId);

		System.out.println("修正申請詳細のユーザ情報" + correctDetailADM);

		// UserMapperEntityをformに変換
		form = modelMapper.map(correctDetailADM, CorrectRequestForm.class);

		// Modelに登録
		model.addAttribute("CorrectRequestForm", form);

		// ユーザー詳細画面を表示
		return "admin/requestRecordCorrectDetailADM";
	}

}
