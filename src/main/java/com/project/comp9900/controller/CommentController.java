package com.project.comp9900.controller;

import com.project.comp9900.domain.entity.Comment;
import com.project.comp9900.domain.entity.Parking;
import com.project.comp9900.service.CommentService;
import com.project.comp9900.service.ParkService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@AllArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    CommentService commentService;

    /**
     * 新建评论
     */
    @PostMapping("/newcomment")
    public Object newComment(@RequestBody Comment comment) {
        return commentService.newComment(comment);
    }

    /**
     * 查看所有评论
     */
    @PostMapping("/allcomment")
    public Object allComment() {
        return commentService.allComment();
    }
}
