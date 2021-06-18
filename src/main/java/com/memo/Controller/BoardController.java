package com.memo.Controller;
import com.memo.dto.BoardDto;
import com.memo.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller //컨트롤러 명시 어노테이션
//@RestController도 존재하는데, 이는 @Controller, @ResponseBody가 합쳐진 어노테이션입니다.
//view 페이지가 필요없는 API 응답에 어울리는 어노테이션입니다.
@AllArgsConstructor
@CrossOrigin
public class BoardController implements ErrorController{

    private BoardService boardService;

    @GetMapping("/") //게시글목록
    public String list(Model model) {
        List<BoardDto> boardList = boardService.getBoardlist();
        model.addAttribute("boardList", boardList);
        return "board/list.html";
    }

    //public String list() { return "board/list.html"; }

    @GetMapping("/post")
    public String write() {
        return "board/write.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:/";
    }

   @GetMapping("/post/{no}") //게시글상세조회 페이지
   public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail.html";
    }

    @GetMapping("/post/edit/{no}") //게시글 수정 페이지
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/update.html";
    }

    @PutMapping("/post/edit/{no}") //게시글 수정
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/";
    }

    @DeleteMapping("/post/{no}") //게시글 삭제
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/";
    }

    @RequestMapping("/error")
    public String handleError() {
        return "/index.html";
    }


}
