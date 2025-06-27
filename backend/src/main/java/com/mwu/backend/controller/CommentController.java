package com.mwu.backend.controller;

import com.mwu.backend.requests.CommentAddRequest;
import com.mwu.backend.response.comment.CommentGetResponse;
import com.mwu.backend.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/getall")
    public ResponseEntity<List<CommentGetResponse>> getAllComments() {
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getallbypost/{postId}")
    public ResponseEntity<List<CommentGetResponse>> getAllCommentsByPostId(@PathVariable("postId") int postId) {
        return new ResponseEntity<>(commentService.getAllByPost(postId),HttpStatus.OK);

    }

    @GetMapping("/getallbyuser/{userId}")
    public ResponseEntity<List<CommentGetResponse>> getAllByUser(@PathVariable int userId){
        return new ResponseEntity<>(commentService.getAllByUser(userId),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody CommentAddRequest commentAddRequest){
        commentService.add(commentAddRequest);
        return new ResponseEntity<>("Added",HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam int id){
        commentService.delete(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }


}
