package com.cos.blog.controller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.write(board, principal.getUser());

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.deleteBoard(id);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> updateById(@PathVariable int id, @RequestBody Board board) {
        boardService.updateBoard(id, board);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    // 데이터 받을 때 컨트롤러에서 dto 만드는게 좋다.
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> saveReply(@PathVariable ReplySaveRequestDto replySaveRequestDto) {

        boardService.writeReply(replySaveRequestDto);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

}
