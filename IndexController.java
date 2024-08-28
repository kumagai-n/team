package jp.co.internous.team2406.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.team2406.model.domain.MstCategory;
import jp.co.internous.team2406.model.domain.MstProduct;
import jp.co.internous.team2406.model.form.SearchForm;
import jp.co.internous.team2406.model.mapper.MstCategoryMapper;
import jp.co.internous.team2406.model.mapper.MstProductMapper;
import jp.co.internous.team2406.model.session.LoginSession;

/**
 * 商品検索に関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/team2406")
public class IndexController {
	
	
	@Autowired
	private MstProductMapper productMapper;
	
	@Autowired
	private MstCategoryMapper categoryMapper;

	@Autowired
	private LoginSession loginSession;
	
	
	/**
	 * トップページを初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/")
	public String index(Model m) {
		if (!loginSession.isLogined() && loginSession.getTmpUserId() == 0) {
			int tmpUserId = new Random().nextInt(1_000_000_000);
			tmpUserId *= -1;
			loginSession.setTmpUserId(tmpUserId);
		}

		List<MstCategory> categories = categoryMapper.find();
		List<MstProduct> products = productMapper.find();

		m.addAttribute("categories", categories);
		m.addAttribute("selected", 0);
		m.addAttribute("products", products);
		m.addAttribute("loginSession", loginSession);
	
		return "index";
	}
	
	/**
	 * 検索処理を行う
	 * @param f 検索用フォーム
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/searchItem")
	public String searchItem(SearchForm f, Model m) {
		List<MstProduct> products = null;

		String keywords = f.getKeywords();
		keywords = keywords.replaceAll("　"," ");
		keywords = keywords.replaceAll("\\s{2,}" ," "); 
		keywords = keywords.trim(); 
		
		String[] keywordArr = keywords.split(" ");
		
		if (f.getCategory() == 0) {
			products = productMapper.findByProductName(keywordArr);
		} else {
			products = productMapper.findByCategoryAndProductName(f.getCategory(), keywordArr);
		}

		List<MstCategory> categories = categoryMapper.find();

		m.addAttribute("keywords", keywords);
		m.addAttribute("selected", f.getCategory()); 
		m.addAttribute("categories", categories); 
		m.addAttribute("products", products);
		m.addAttribute("loginSession", loginSession);

		return "index";
	}
}

