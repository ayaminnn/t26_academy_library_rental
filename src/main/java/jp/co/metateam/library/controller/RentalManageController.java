package jp.co.metateam.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import jp.co.metateam.library.model.RentalManage;
import jp.co.metateam.library.model.RentalManageDto;
import jp.co.metateam.library.service.AccountService;
import jp.co.metateam.library.service.RentalManageService;
import jp.co.metateam.library.service.StockService;
import jp.co.metateam.library.values.RentalStatus;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * 貸出管理関連クラスß
 */
@Log4j2
@Controller
public class RentalManageController {

    private RentalManageService rentalManageService;
    private AccountService accountService;
    private StockService stockService;

    /**
     * 貸出一覧画面初期表示
     * 
     * @param model
     * @return
     */
    @GetMapping("/rental/index")
    public String index(Model model) {
        // 貸出管理テーブルから全件取得
        List<RentalManage> rentalList = rentalManageService.findAll();
        // 貸出一覧画面に渡すデータをmodelに追加
        model.addAttribute("rentalManageList", rentalList);
        // 貸出一覧画面に遷移
        return "rental/index";
    }

    /**
     * 貸出登録画面初期表示
     * 
     * @param model
     * @return
     */
    @GetMapping("/rental/add")
    public String add(Model model) {

        // 貸出登録画面に渡すデータをmodelに追加
        model.addAttribute(
                "accounts",
                accountService.findAll());

        model.addAttribute(
                "stockList",
                stockService.findAll());

        model.addAttribute(
                "rentalStatus",
                RentalStatus.values());

        model.addAttribute(
                "rentalManageDto",
                new RentalManageDto());

        // 貸出登録画面に遷移
        return "rental/add";
    }

    @Autowired
    public RentalManageController(
            RentalManageService rentalManageService,
            AccountService accountService,
            StockService stockService) {

        this.rentalManageService = rentalManageService;
        this.accountService = accountService;
        this.stockService = stockService;
    }

    /**
     * 貸出登録
     * 
     * @param rentalManageDto
     * @return
     */
    @PostMapping("/rental/add")
    public String register(
            @Valid @ModelAttribute RentalManageDto rentalManageDto,
            BindingResult bindingResult, Model model) {
        // DTOチェック
        if (bindingResult.hasErrors()) {
            setModel(model);
            return "rental/add";
        }
        // 業務チェック（ステータス・日付ルール）
        rentalManageService.validateBusinessRule(rentalManageDto, bindingResult);

        if (bindingResult.hasErrors()) {
            setModel(model);
            return "rental/add";
        }

        // 重複チェック（service呼ぶ）
        if (rentalManageService.existsOverlap(
                rentalManageDto.getExpectedRentalOn(),
                rentalManageDto.getExpectedReturnOn())) {

            bindingResult.rejectValue("expectedRentalOn", null,
                    "期間が重複しています");
        }
        // 業務チェック後にエラーあれば戻る
        if (bindingResult.hasErrors()) {

            setModel(model);

            return "rental/add";
        }

        // 貸出登録
        this.rentalManageService.save(
                rentalManageDto);

        // 貸出一覧画面にリダイレクト
        return "redirect:/rental/index";
    }

    // 共通Model設定
    private void setModel(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        model.addAttribute("stockList", stockService.findAll());
        model.addAttribute("rentalStatus", RentalStatus.values());
    }
}