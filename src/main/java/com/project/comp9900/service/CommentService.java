package com.project.comp9900.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.project.comp9900.dao.CommentMapper;
import com.project.comp9900.domain.entity.Comment;
import com.project.comp9900.utils.JsonData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class CommentService {

    @Resource
    CommentMapper commentMapper;

    public Object newComment(Comment comment) {
        commentMapper.insert(comment);
        return JsonData.buildSuccess("新评论创建成功");
    }


    public Object allComment() {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0);
        List<Comment> list = commentMapper.selectList(queryWrapper);
        return JsonData.buildSuccess(list);
    }
}
