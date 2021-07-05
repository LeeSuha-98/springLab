package org.zerock.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController { //   "/board/*"가 나오면 컨트롤러가 돌아감

  private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

  @Inject
  private BoardService service; //서비스를 스ㅐㄴ해서 주입한다.

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public void registerGET(BoardVO board, Model model) throws Exception {

    logger.info("register get ..........."); 
  }
  //리턴문을 안써도 board/register의 화면을 보여준다.

  // @RequestMapping(value = "/register", method = RequestMethod.POST)
  // public String registPOST(BoardVO board, Model model) throws Exception {
  //
  // logger.info("regist post ...........");
  // logger.info(board.toString());
  //
  // service.regist(board);
  //
  // model.addAttribute("result", "success");
  //
  // //return "/board/success";
  // return "redirect:/board/listAll";
  // }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
	  //RedirectAttributes = 화면에 attributes를 추가한다는 뜻

    logger.info("regist post ...........");
    logger.info(board.toString());

    service.regist(board); // 레지스트 메소드로 이동. boardService

    rttr.addFlashAttribute("msg", "SUCCESS");
    return "redirect:/board/listAll";
  }

  @RequestMapping(value = "/listAll", method = RequestMethod.GET)  //BoardService의 listAll을 쓴다는거 
  public void listAll(Model model) throws Exception {

    logger.info("show all list......................");
    model.addAttribute("list", service.listAll());
  }

  @RequestMapping(value = "/read", method = RequestMethod.GET) //get방식으로 bno를 얻어온다 ex) read bno=?
  public void read(@RequestParam("bno") int bno, Model model) throws Exception {

    model.addAttribute(service.read(bno));
  }

  @RequestMapping(value = "/remove", method = RequestMethod.POST)
  public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {

    service.remove(bno);

    rttr.addFlashAttribute("msg", "SUCCESS");

    return "redirect:/board/listAll";
  }

  @RequestMapping(value = "/modify", method = RequestMethod.GET)
  public void modifyGET(int bno, Model model) throws Exception {

    model.addAttribute(service.read(bno));
  }

  @RequestMapping(value = "/modify", method = RequestMethod.POST)
  public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {

    logger.info("mod post............");

    service.modify(board);
    rttr.addFlashAttribute("msg", "SUCCESS");

    return "redirect:/board/listAll";
  }

  @RequestMapping(value = "/listCri", method = RequestMethod.GET)
  public void listAll(Criteria cri, Model model) throws Exception {

    logger.info("show list Page with Criteria......................");

    model.addAttribute("list", service.listCriteria(cri));
  }

  @RequestMapping(value = "/listPage", method = RequestMethod.GET)
  public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {

    logger.info(cri.toString());

    model.addAttribute("list", service.listCriteria(cri));
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    // pageMaker.setTotalCount(131);

    pageMaker.setTotalCount(service.listCountCriteria(cri));

    model.addAttribute("pageMaker", pageMaker);
  }

  @RequestMapping(value = "/readPage", method = RequestMethod.GET)
  public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {

    model.addAttribute(service.read(bno));
  }

  @RequestMapping(value = "/removePage", method = RequestMethod.POST)
  public String remove(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception {

    service.remove(bno);

    rttr.addAttribute("page", cri.getPage());
    rttr.addAttribute("perPageNum", cri.getPerPageNum());
    rttr.addFlashAttribute("msg", "SUCCESS");

    return "redirect:/board/listPage";
  }

  @RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
  public void modifyPagingGET(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model)
      throws Exception {

    model.addAttribute(service.read(bno));
  }

}
